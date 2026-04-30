/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.change.tracking.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.change.tracking.constants.CTConstants;
import com.liferay.change.tracking.model.CTCollection;
import com.liferay.change.tracking.model.CTPreferences;
import com.liferay.change.tracking.model.CTProcess;
import com.liferay.change.tracking.service.CTCollectionLocalService;
import com.liferay.change.tracking.service.CTPreferencesLocalService;
import com.liferay.change.tracking.service.CTProcessLocalService;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalFolderLocalService;
import com.liferay.journal.test.util.JournalFolderFixture;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.portal.background.task.model.BackgroundTask;
import com.liferay.portal.background.task.service.BackgroundTaskLocalService;
import com.liferay.portal.kernel.backgroundtask.constants.BackgroundTaskConstants;
import com.liferay.portal.kernel.change.tracking.CTCollectionThreadLocal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.log.LogCapture;
import com.liferay.portal.test.log.LogEntry;
import com.liferay.portal.test.log.LoggerTestUtil;
import com.liferay.portal.test.rule.FeatureFlag;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Gislayne Vitorino
 */
@RunWith(Arquillian.class)
public class CTProcessLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_guestUserId = _userLocalService.getGuestUserId(
			TestPropsValues.getCompanyId());

		_journalFolderClassNameId = _classNameLocalService.getClassNameId(
			JournalFolder.class);
	}

	@After
	public void tearDown() throws Exception {
		CTPreferences guestCTPreferences =
			_ctPreferencesLocalService.fetchCTPreferences(
				TestPropsValues.getCompanyId(), _guestUserId);

		if (guestCTPreferences != null) {
			guestCTPreferences.setCtCollectionId(
				CTConstants.CT_COLLECTION_ID_PRODUCTION);

			_ctPreferencesLocalService.updateCTPreferences(guestCTPreferences);
		}
	}

	@FeatureFlag("LPD-39203")
	@Test
	public void testAddCTProcessWithInstantPublish() throws Exception {
		CTCollection ctCollection = _addCTCollectionWithContent();

		try (LogCapture ctPreferencesLogCapture =
				LoggerTestUtil.configureLog4JLogger(
					"com.liferay.change.tracking.internal.spi.listener." +
						"CTPreferencesEventListener",
					LoggerTestUtil.INFO);
			LogCapture ctProcessLocalServiceImplLogCapture =
				LoggerTestUtil.configureLog4JLogger(
					"com.liferay.change.tracking.service.impl." +
						"CTProcessLocalServiceImpl",
					LoggerTestUtil.INFO)) {

			CTProcess ctProcess = _ctProcessLocalService.addCTProcess(
				TestPropsValues.getUserId(), ctCollection.getCtCollectionId());

			List<LogEntry> ctProcessLogEntries =
				ctProcessLocalServiceImplLogCapture.getLogEntries();

			Assert.assertEquals(
				ctProcessLogEntries.toString(), 1, ctProcessLogEntries.size());

			LogEntry ctProcessLogEntry = ctProcessLogEntries.get(0);

			Assert.assertEquals(
				"Using publication " + ctCollection.getName() +
					" temporarily in place of production",
				ctProcessLogEntry.getMessage());

			CTPreferences userCTPreferences =
				_ctPreferencesLocalService.getCTPreferences(
					TestPropsValues.getCompanyId(),
					TestPropsValues.getUserId());

			Assert.assertEquals(
				CTConstants.CT_COLLECTION_ID_PRODUCTION,
				userCTPreferences.getCtCollectionId());

			BackgroundTask backgroundTask =
				_backgroundTaskLocalService.getBackgroundTask(
					ctProcess.getBackgroundTaskId());

			Assert.assertEquals(
				BackgroundTaskConstants.STATUS_SUCCESSFUL,
				backgroundTask.getStatus());

			List<LogEntry> ctPreferencesLogEntries =
				ctPreferencesLogCapture.getLogEntries();

			Assert.assertEquals(
				ctPreferencesLogEntries.toString(), 1,
				ctPreferencesLogEntries.size());

			LogEntry ctPreferencesLogEntry = ctPreferencesLogEntries.get(0);

			Assert.assertEquals(
				"Publication " + ctCollection.getName() +
					" was published. Production is live.",
				ctPreferencesLogEntry.getMessage());

			CTPreferences guestCTPreferences =
				_ctPreferencesLocalService.getCTPreferences(
					TestPropsValues.getCompanyId(), _guestUserId);

			Assert.assertEquals(
				CTConstants.CT_COLLECTION_ID_PRODUCTION,
				guestCTPreferences.getCtCollectionId());
		}
	}

	@FeatureFlag("LPD-39203")
	@Test
	public void testAddCTProcessWithInstantPublishConflict() throws Exception {
		CTCollection ctCollection = _addCTCollection();

		String conflictingFolderName = RandomTestUtil.randomString();

		try (SafeCloseable safeCloseable =
				CTCollectionThreadLocal.setCTCollectionIdWithSafeCloseable(
					ctCollection.getCtCollectionId())) {

			_journalFolderFixture.addFolder(
				_group.getGroupId(), conflictingFolderName);
		}

		_journalFolderFixture.addFolder(
			_group.getGroupId(), conflictingFolderName);

		try (LogCapture logCapture = LoggerTestUtil.configureLog4JLogger(
				"com.liferay.portal.background.task.internal.messaging." +
					"BackgroundTaskMessageListener",
				LoggerTestUtil.ERROR)) {

			CTProcess ctProcess = _ctProcessLocalService.addCTProcess(
				TestPropsValues.getUserId(), ctCollection.getCtCollectionId());

			BackgroundTask backgroundTask =
				_backgroundTaskLocalService.getBackgroundTask(
					ctProcess.getBackgroundTaskId());

			Assert.assertEquals(
				BackgroundTaskConstants.STATUS_FAILED,
				backgroundTask.getStatus());

			List<LogEntry> logEntries = logCapture.getLogEntries();

			Assert.assertEquals(logEntries.toString(), 1, logEntries.size());

			LogEntry logEntry = logEntries.get(0);

			Assert.assertEquals(
				_UNABLE_TO_EXECUTE_BACKGROUND_TASK, logEntry.getMessage());
		}

		CTPreferences guestCTPreferences =
			_ctPreferencesLocalService.getCTPreferences(
				TestPropsValues.getCompanyId(), _guestUserId);

		Assert.assertEquals(
			CTConstants.CT_COLLECTION_ID_PRODUCTION,
			guestCTPreferences.getCtCollectionId());
	}

	@FeatureFlag("LPD-39203")
	@Test
	public void testAddCTProcessWithInstantPublishUserInOtherPublication()
		throws Exception {

		CTCollection otherCTCollection = _addCTCollection();

		CTPreferences userCTPreferences =
			_ctPreferencesLocalService.getCTPreferences(
				TestPropsValues.getCompanyId(), TestPropsValues.getUserId());

		userCTPreferences.setCtCollectionId(
			otherCTCollection.getCtCollectionId());

		_ctPreferencesLocalService.updateCTPreferences(userCTPreferences);

		CTCollection ctCollection = _addCTCollectionWithContent();

		_ctProcessLocalService.addCTProcess(
			TestPropsValues.getUserId(), ctCollection.getCtCollectionId());

		userCTPreferences = _ctPreferencesLocalService.getCTPreferences(
			TestPropsValues.getCompanyId(), TestPropsValues.getUserId());

		Assert.assertEquals(
			otherCTCollection.getCtCollectionId(),
			userCTPreferences.getCtCollectionId());
	}

	@Test
	public void testCannotAddCTProcessWithEmptyCTCollection()
		throws PortalException {

		CTCollection ctCollection = _ctCollectionLocalService.addCTCollection(
			null, TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
			0, CTCollectionLocalServiceTest.class.getSimpleName(), null);

		try {
			_ctProcessLocalService.addCTProcess(
				ctCollection.getUserId(), ctCollection.getCtCollectionId());

			Assert.fail();
		}
		catch (IllegalStateException illegalStateException) {
			Assert.assertEquals(
				"Change tracking collection is empty " + ctCollection,
				illegalStateException.getMessage());
		}
	}

	@Test
	public void testDeleteCTCollectionWithCTProcess() throws Exception {
		CTCollection ctCollection = _ctCollectionLocalService.addCTCollection(
			null, TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
			0, CTCollectionLocalServiceTest.class.getSimpleName(), null);

		String conflictingFolderName = "conflictingFolderName";

		JournalFolder ctJournalFolder = null;

		try (SafeCloseable safeCloseable =
				CTCollectionThreadLocal.setCTCollectionIdWithSafeCloseable(
					ctCollection.getCtCollectionId())) {

			ctJournalFolder = _journalFolderFixture.addFolder(
				_group.getGroupId(), conflictingFolderName);

			_journalFolderFixture.addFolder(
				_group.getGroupId(), RandomTestUtil.randomString());
		}

		_journalFolderFixture.addFolder(
			_group.getGroupId(), conflictingFolderName);

		try (LogCapture logCapture = LoggerTestUtil.configureLog4JLogger(
				"com.liferay.portal.background.task.internal.messaging." +
					"BackgroundTaskMessageListener",
				LoggerTestUtil.ERROR)) {

			CTProcess ctProcess = _ctProcessLocalService.addCTProcess(
				ctCollection.getUserId(), ctCollection.getCtCollectionId());

			BackgroundTask backgroundTask =
				_backgroundTaskLocalService.getBackgroundTask(
					ctProcess.getBackgroundTaskId());

			Assert.assertEquals(
				BackgroundTaskConstants.STATUS_FAILED,
				backgroundTask.getStatus());

			ctProcess = _ctProcessLocalService.deleteCTProcess(
				ctProcess.getCtProcessId());

			ctCollection = _ctCollectionLocalService.fetchCTCollection(
				ctProcess.getCtCollectionId());

			Assert.assertNotNull(ctCollection);

			_ctCollectionLocalService.discardCTEntry(
				ctCollection.getCtCollectionId(), _journalFolderClassNameId,
				ctJournalFolder.getFolderId(), false);

			ctProcess = _ctProcessLocalService.addCTProcess(
				ctCollection.getUserId(), ctCollection.getCtCollectionId());

			backgroundTask = _backgroundTaskLocalService.getBackgroundTask(
				ctProcess.getBackgroundTaskId());

			Assert.assertEquals(
				BackgroundTaskConstants.STATUS_SUCCESSFUL,
				backgroundTask.getStatus());

			ctProcess = _ctProcessLocalService.deleteCTProcess(
				ctProcess.getCtProcessId());

			ctCollection = _ctCollectionLocalService.fetchCTCollection(
				ctProcess.getCtProcessId());

			Assert.assertNull(ctCollection);

			List<LogEntry> logEntries = logCapture.getLogEntries();

			Assert.assertEquals(logEntries.toString(), 1, logEntries.size());

			LogEntry logEntry = logEntries.get(0);

			Assert.assertEquals(
				_UNABLE_TO_EXECUTE_BACKGROUND_TASK, logEntry.getMessage());
		}
	}

	private CTCollection _addCTCollection() throws Exception {
		return _ctCollectionLocalService.addCTCollection(
			null, TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
			0, RandomTestUtil.randomString(), null);
	}

	private CTCollection _addCTCollectionWithContent() throws Exception {
		CTCollection ctCollection = _addCTCollection();

		try (SafeCloseable safeCloseable =
				CTCollectionThreadLocal.setCTCollectionIdWithSafeCloseable(
					ctCollection.getCtCollectionId())) {

			_journalFolderFixture.addFolder(
				_group.getGroupId(), RandomTestUtil.randomString());
		}

		return ctCollection;
	}

	private static final String _UNABLE_TO_EXECUTE_BACKGROUND_TASK =
		"Unable to execute background task";

	@Inject
	private static JournalFolderLocalService _journalFolderLocalService;

	@Inject
	private BackgroundTaskLocalService _backgroundTaskLocalService;

	@Inject
	private ClassNameLocalService _classNameLocalService;

	@Inject
	private CTCollectionLocalService _ctCollectionLocalService;

	@Inject
	private CTPreferencesLocalService _ctPreferencesLocalService;

	@Inject
	private CTProcessLocalService _ctProcessLocalService;

	@DeleteAfterTestRun
	private Group _group;

	private long _guestUserId;
	private long _journalFolderClassNameId;
	private final JournalFolderFixture _journalFolderFixture =
		new JournalFolderFixture(_journalFolderLocalService);

	@Inject
	private UserLocalService _userLocalService;

}