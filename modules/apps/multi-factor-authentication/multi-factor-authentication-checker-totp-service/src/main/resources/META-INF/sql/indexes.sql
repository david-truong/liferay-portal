create index IX_F7433D8B on MFATOTP (mfaCheckerName[$COLUMN_LENGTH:75$], userId);
create unique index IX_2A9FEB3F on MFATOTP (userId);