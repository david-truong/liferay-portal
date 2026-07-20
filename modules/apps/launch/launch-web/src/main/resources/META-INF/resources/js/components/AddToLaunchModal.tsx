/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayAlert from '@clayui/alert';
import ClayButton from '@clayui/button';
import ClayForm, {ClayInput, ClaySelectWithOption} from '@clayui/form';
import ClayModal, {useModal} from '@clayui/modal';
import {openToast} from 'frontend-js-components-web';
import {sub} from 'frontend-js-web';
import React, {useEffect, useState} from 'react';

import {
	Launch,
	createLaunch,
	createLaunchEntry,
	getLaunch,
	listLaunchEntriesFor,
	listLaunches,
} from '../api/launches';

export const ADD_TO_LAUNCH_EVENT = 'addToLaunch';

export interface AddToLaunchEventDetail {
	className: string;
	classPK: number;
	classVersion: string;
}

const CREATE_NEW_VALUE = 'create-new-launch';

type Status = 'already-added' | 'loading' | 'picking';

// Mounted once per page via <react:component module="{AddToLaunchModal} from launch-web">; opens on ADD_TO_LAUNCH_EVENT so any surface can trigger it without importing this bundle.

export default function AddToLaunchModal() {
	const {observer, onOpenChange, open} = useModal({
		onClose: () => onOpenChange(false),
	});

	const [detail, setDetail] = useState<AddToLaunchEventDetail | null>(null);

	useEffect(() => {
		const listener = (event: Event) => {
			const customEvent = event as CustomEvent<AddToLaunchEventDetail>;

			setDetail(customEvent.detail);
			onOpenChange(true);
		};

		document.addEventListener(ADD_TO_LAUNCH_EVENT, listener);

		return () =>
			document.removeEventListener(ADD_TO_LAUNCH_EVENT, listener);
	}, [onOpenChange]);

	if (!open || !detail) {
		return null;
	}

	return (
		<AddToLaunchModalContent
			detail={detail}
			observer={observer}
			onClose={() => onOpenChange(false)}
		/>
	);
}

interface ContentProps {
	detail: AddToLaunchEventDetail;
	observer: ReturnType<typeof useModal>['observer'];
	onClose: () => void;
}

function AddToLaunchModalContent({detail, observer, onClose}: ContentProps) {
	const [status, setStatus] = useState<Status>('loading');
	const [error, setError] = useState<string | null>(null);
	const [submitting, setSubmitting] = useState(false);

	const [existingLaunchName, setExistingLaunchName] = useState<string | null>(
		null
	);

	const [launches, setLaunches] = useState<Launch[]>([]);
	const [selectedValue, setSelectedValue] = useState<string>('');
	const [newLaunchName, setNewLaunchName] = useState('');
	const [newLaunchDescription, setNewLaunchDescription] = useState('');

	useEffect(() => {
		let canceled = false;

		async function load() {
			try {
				const entries = await listLaunchEntriesFor(detail);

				if (canceled) {
					return;
				}

				if (entries.length) {
					const launch = await getLaunch(
						entries[0].r_launchSetToLaunchEntries_c_launchSetId
					);

					if (canceled) {
						return;
					}

					setExistingLaunchName(launch.name);
					setStatus('already-added');

					return;
				}

				const availableLaunches = await listLaunches();

				if (canceled) {
					return;
				}

				setLaunches(availableLaunches);
				setStatus('picking');
			}
			catch (exception) {
				if (!canceled) {
					setError((exception as Error).message);
				}
			}
		}

		load();

		return () => {
			canceled = true;
		};
	}, [detail]);

	const handleAdd = async () => {
		if (!selectedValue) {
			setError(
				sub(
					Liferay.Language.get('the-x-field-is-required'),
					Liferay.Language.get('launch')
				)
			);

			return;
		}

		const isCreatingNew = selectedValue === CREATE_NEW_VALUE;

		const trimmedName = newLaunchName.trim();

		if (isCreatingNew && !trimmedName) {
			setError(
				sub(
					Liferay.Language.get('the-x-field-is-required'),
					Liferay.Language.get('name')
				)
			);

			return;
		}

		setError(null);
		setSubmitting(true);

		try {
			const launchId = isCreatingNew
				? (
						await createLaunch({
							description: newLaunchDescription.trim(),
							name: trimmedName,
						})
					).id
				: Number(selectedValue);

			const launch = isCreatingNew
				? {id: launchId, name: trimmedName}
				: launches.find((candidate) => candidate.id === launchId);

			await createLaunchEntry({...detail, launchSetId: launchId});

			openToast({
				message: sub(
					Liferay.Language.get('the-item-was-added-to-x'),
					launch?.name || Liferay.Language.get('launch')
				),
				type: 'success',
			});

			onClose();
		}
		catch (exception) {
			setError((exception as Error).message);
		}
		finally {
			setSubmitting(false);
		}
	};

	return (
		<ClayModal observer={observer}>
			<ClayModal.Header
				closeButtonAriaLabel={Liferay.Language.get('close')}
			>
				{Liferay.Language.get('add-to-launch')}
			</ClayModal.Header>

			<ClayModal.Body>
				{status === 'loading' && (
					<div className="text-center">
						<span className="loading-animation" />
					</div>
				)}

				{status === 'already-added' && (
					<ClayAlert displayType="info">
						{sub(
							Liferay.Language.get(
								'this-item-already-belongs-to-x'
							),
							existingLaunchName
						)}
					</ClayAlert>
				)}

				{status === 'picking' && (
					<ClayForm.Group>
						<label htmlFor="addToLaunchSelect">
							{Liferay.Language.get('select-a-launch')}
						</label>

						<ClaySelectWithOption
							id="addToLaunchSelect"
							onChange={(event) =>
								setSelectedValue(event.target.value)
							}
							options={[
								{
									label: Liferay.Language.get('select'),
									value: '',
								},
								...launches.map((launch) => ({
									label: launch.name,
									value: String(launch.id),
								})),
								{
									label: Liferay.Language.get(
										'create-a-new-launch'
									),
									value: CREATE_NEW_VALUE,
								},
							]}
							value={selectedValue}
						/>

						{selectedValue === CREATE_NEW_VALUE && (
							<div className="mt-3">
								<ClayForm.Group>
									<label htmlFor="addToLaunchNewName">
										{Liferay.Language.get('name')}
									</label>

									<ClayInput
										id="addToLaunchNewName"
										onChange={(event) =>
											setNewLaunchName(event.target.value)
										}
										placeholder={Liferay.Language.get(
											'untitled-launch'
										)}
										value={newLaunchName}
									/>
								</ClayForm.Group>

								<ClayForm.Group>
									<label htmlFor="addToLaunchNewDescription">
										{Liferay.Language.get('description')}
									</label>

									<ClayInput
										component="textarea"
										id="addToLaunchNewDescription"
										onChange={(event) =>
											setNewLaunchDescription(
												event.target.value
											)
										}
										value={newLaunchDescription}
									/>
								</ClayForm.Group>
							</div>
						)}
					</ClayForm.Group>
				)}

				{error && (
					<ClayAlert
						displayType="warning"
						onClose={() => setError(null)}
					>
						{error}
					</ClayAlert>
				)}
			</ClayModal.Body>

			<ClayModal.Footer
				last={
					<ClayButton.Group spaced>
						<ClayButton displayType="secondary" onClick={onClose}>
							{Liferay.Language.get('cancel')}
						</ClayButton>

						{status === 'picking' && (
							<ClayButton
								disabled={submitting}
								onClick={handleAdd}
							>
								{Liferay.Language.get('add')}
							</ClayButton>
						)}
					</ClayButton.Group>
				}
			/>
		</ClayModal>
	);
}
