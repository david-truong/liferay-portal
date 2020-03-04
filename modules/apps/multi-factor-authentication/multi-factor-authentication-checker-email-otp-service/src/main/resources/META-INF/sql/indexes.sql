create index IX_AD3D6F63 on MFAEmailOTP (mfaCheckerName[$COLUMN_LENGTH:75$], userId);
create index IX_9528CD17 on MFAEmailOTP (userId);

create index IX_E2720E2D on MFAEmailOTPEntry (mfaCheckerName[$COLUMN_LENGTH:75$]);
create index IX_304C4167 on MFAEmailOTPEntry (userId, mfaCheckerName[$COLUMN_LENGTH:75$]);