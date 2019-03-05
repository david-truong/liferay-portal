create table MFATOTP (
	mfaTOTPId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	backupCodes VARCHAR(75) null,
	failedLoginAttempts INTEGER,
	lastLoginDate DATE null,
	lastLoginIP VARCHAR(75) null,
	lastFailedLoginDate DATE null,
	mfaCheckerName VARCHAR(75) null,
	sharedSecret VARCHAR(75) null
);