create index IX_F7433D8B on MFATOTP (mfaCheckerName[$COLUMN_LENGTH:75$], userId);
create index IX_2A9FEB3F on MFATOTP (userId);

create index IX_43F5B43F on MFATOTPEntry (mfaCheckerName[$COLUMN_LENGTH:75$], userId);
create index IX_2BF90C3F on MFATOTPEntry (userId, mfaCheckerName[$COLUMN_LENGTH:75$]);

create index IX_8CB62719 on TimebasedOTPEntry (mfaCheckerName[$COLUMN_LENGTH:75$], userId);
create index IX_7003B0CD on TimebasedOTPEntry (userId);