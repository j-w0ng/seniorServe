CREATE TABLE PostalCode (
	PostalCode 		CHAR(6),
	City			VARCHAR(20),
	Province		CHAR(2),
	PRIMARY KEY (PostalCode));

CREATE TABLE Location (
	PostalCode		CHAR(6),
	Address		VARCHAR(50),
	PRIMARY KEY (PostalCode, Address),
	FOREIGN KEY (PostalCode) REFERENCES PostalCode
		On update cascade
		On delete no action);

CREATE TABLE Users (
    Username VARCHAR(50) NOT NULL PRIMARY KEY,
    First_Name VARCHAR(100) NOT NULL,
    Last_Name VARCHAR(100),
    PostalCode CHAR(6),
    Address VARCHAR(50),
    FOREIGN KEY (PostalCode, Address) REFERENCES Location
        on delete set NULL
);






