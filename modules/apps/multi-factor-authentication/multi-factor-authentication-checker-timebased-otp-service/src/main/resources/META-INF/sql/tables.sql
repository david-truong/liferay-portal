create table MFATOTP (
	mfaTOTPId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	failedAttempts INTEGER,
	lastFailDate DATE null,
	lastFailIP VARCHAR(75) null,
	lastSuccessDate DATE null,
	lastSuccessIP VARCHAR(75) null,
	mfaCheckerName VARCHAR(75) null,
	sharedSecret VARCHAR(75) null
);

create table MFATOTPEntry (
	entryId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	failedAttempts INTEGER,
	lastFailDate DATE null,
	lastFailIP VARCHAR(75) null,
	lastSuccessDate DATE null,
	lastSuccessIP VARCHAR(75) null,
	mfaCheckerName VARCHAR(75) null,
	sharedSecret VARCHAR(75) null
);

create table TimebasedOTPEntry (
	entryId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	failedAttempts INTEGER,
	lastFailDate DATE null,
	lastFailIP VARCHAR(75) null,
	lastSuccessDate DATE null,
	lastSuccessIP VARCHAR(75) null,
	mfaCheckerName VARCHAR(75) null,
	sharedSecret VARCHAR(75) null
);