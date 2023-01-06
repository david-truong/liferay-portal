/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import ClayAlert from '@clayui/alert';
import ClayButton, {ClayButtonWithIcon} from '@clayui/button';
import ClayForm, {ClaySelect} from '@clayui/form';
import ClayIcon from '@clayui/icon';
import ClayModal, {useModal} from '@clayui/modal';
import ClaySticker from '@clayui/sticker';
import {fetch, navigate, objectToFormData} from 'frontend-js-web';
import React, {useState} from 'react';

export default function MoveChangesModal({
	changes,
	ctCollectionId,
	moveChangesURL,
	namespace,
	publications,
	spritemap,
	trigger,
}) {
	const [showModal, setShowModal] = useState(false);
	const [targetCTCollectionId, setTargetCTCollectionId] = useState(null);
	const {observer, onClose} = useModal({
		onClose: () => setShowModal(false),
	});

	const handleSubmit = () => {
		const modelClassNameIds = [];
		const modelClassPKs = [];

		for (let i = 0; i < changes.length; i++) {
			modelClassNameIds.push(changes[i].modelClassNameId);
			modelClassPKs.push(changes[i].modelClassPK);
		}

		const formData = objectToFormData({
			[`${namespace}modelClassNameIds`]: modelClassNameIds.join(','),
			[`${namespace}modelClassPKs`]: modelClassPKs.join(','),
			[`${namespace}sourceCtCollectionId`]: ctCollectionId,
			[`${namespace}targetCTCollectionId`]: targetCTCollectionId,
		});

		fetch(moveChangesURL, {
			body: formData,
			method: 'POST',
		})
			.then((response) => {
				return response.json();
			})
			.then((json) => navigate(json.redirectURL));
	};

	const resetForm = () => {
		setTargetCTCollectionId(null);
	};

	const renderTrigger = () => {
		if (trigger) {
			return trigger;
		}

		if (ctCollectionId !== 0) {
			return (
				<ClayButtonWithIcon
					aria-label="Show Move Changes Modal"
					data-tooltip-align="top"
					displayType="secondary"
					onClick={() => setShowModal(true)}
					symbol="move-folder"
					title={Liferay.Language.get('move-changes')}
				/>
			);
		}
	};

	if (!showModal) {
		return renderTrigger();
	}
	else {
		return (
			<>
				<ClayModal
					className="publications-move-changes-modal"
					observer={observer}
					size="lg"
					spritemap={spritemap}
				>
					<ClayForm onSubmit={handleSubmit}>
						<ClayModal.Header>
							<div className="autofit-row">
								<div className="autofit-col">
									<ClaySticker
										className="sticker-use-icon user-icon-color-0"
										displayType="secondary"
										shape="circle"
									>
										<ClayIcon symbol="move-folder" />
									</ClaySticker>
								</div>

								<div className="autofit-col">
									<div className="modal-title">
										{Liferay.Language.get('move-changes')}
									</div>
								</div>
							</div>
						</ClayModal.Header>

						<div className="inline-scroller modal-body publications-invite-users-modal-body">
							<ClayAlert
								displayType="info"
								spritemap={spritemap}
								title={Liferay.Language.get(
									'select-the-target-publication-to-move-the-selected-changes-to'
								)}
							/>

							<span>{`${changes.length} changes have been selected to move to another Publication.`}</span>

							<br />

							<ClaySelect
								aria-label="Select the target Publication"
								defaultValue={0}
								id="targetPublicationSelect"
								onChange={(event) => {
									setTargetCTCollectionId(event.target.value);
								}}
							>
								<ClaySelect.Option
									disabled
									hidden
									label={Liferay.Language.get(
										'select-the-target-publication'
									)}
									value={0}
								/>

								{publications &&
									publications.map((publication) => (
										<ClaySelect.Option
											key={publication.ctCollectionId}
											label={publication.name}
											value={publication.ctCollectionId}
										/>
									))}
							</ClaySelect>
						</div>

						<ClayModal.Footer
							last={
								<ClayButton.Group spaced>
									<ClayButton
										displayType="secondary"
										onClick={() => {
											onClose();
											resetForm();
										}}
									>
										{Liferay.Language.get('cancel')}
									</ClayButton>

									<ClayButton
										disabled={!targetCTCollectionId}
										displayType="primary"
										type="submit"
									>
										{Liferay.Language.get('move')}
									</ClayButton>
								</ClayButton.Group>
							}
						/>
					</ClayForm>
				</ClayModal>

				{renderTrigger()}
			</>
		);
	}
}
