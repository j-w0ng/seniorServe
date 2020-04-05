CREATE TABLE PostalCode (
	PostalCode 		CHAR(6),
	City			VARCHAR(20),
	Province		CHAR(2),
	PRIMARY KEY (PostalCode));

INSERT INTO PostalCode (PostalCode, City, Province)
VALUES
('M5S1A1', 'Toronto', 'ON'),
('V6T1Z4', 'Vancouver', 'BC'),
('T2N1N4', 'Calgary', 'AB'),
('T6G2R3', 'Edmonton', 'AB'),
('N2L3G1', 'Waterloo', 'ON'),
('G1V0A6', 'Quebec', 'QC'),
('V5A1S6', 'Burnaby', 'BC'),
('N6A3K7', 'London', 'ON');

CREATE TABLE Location (
	PostalCode		CHAR(6),
	Address		VARCHAR(50),
	PRIMARY KEY (PostalCode, Address),
	FOREIGN KEY (PostalCode) REFERENCES PostalCode
		On update cascade
		On delete no action);

INSERT INTO Location (PostalCode, Address)
VALUES
('M5S1A1', '27 King''s College Circle'),
('V6T1Z4', '2329 West Mall'),
('T2N1N4', '2500 University Dr'),
('T6G2R3', '116 St & 85 Ave'),
('N2L3G1', '200 University Ave W'),
('G1V0A6', '2325 Rue de l''Universite'),
('V5A1S6', '8888 University Dr'),
('N6A3K7', '1151 Richmond St');

CREATE TABLE Users (
    Username VARCHAR(50) NOT NULL PRIMARY KEY,
    First_Name VARCHAR(100) NOT NULL,
    Last_Name VARCHAR(100),
    PostalCode CHAR(6),
    Address VARCHAR(50),
    FOREIGN KEY (PostalCode, Address) REFERENCES Location
        on delete set NULL
);

INSERT INTO Users (Username, First_Name, Last_Name, PostalCode, Address)
VALUES
('Sarah123', 'Sarah', 'Stevens', 'G1V0A6', '2325 Rue de l''Universite'),
('Dorothy45', 'Dorothy', 'Davidson', 'N2L3G1',  '200 University Ave W'),
('Ann34', 'Ann', 'Yeung', 'T6G2R3', '116 St & 85 Ave'),
('Jonny55', 'Jonny', 'Wong', 'T2N1N4', '2500 University Dr'),
('Ali67', 'Ali', 'Mo', 'N6A3K7', '1151 Richmond St'),
('Dh78', 'Daniel', 'Humphrey', 'G1V0A6', '2325 Rue de l''Universite'),
('Greg8', 'Greg', 'Donald', 'V5A1S6', '8888 University Dr'),
('OldMac6', 'Ronald', 'MacDonald', 'M5S1A1', '27 King''s College Circle'),
('Bigbird4', 'Tom', 'Tree', 'N2L3G1',  '200 University Ave W'),
('Thomas4', 'Thomas', 'Singh', 'V6T1Z4', '2329 West Mall');

CREATE TABLE Senior (
	Username	VARCHAR(20),
	PRIMARY KEY (Username),
	FOREIGN KEY (Username) REFERENCES Users
		On delete Cascade);

INSERT INTO Senior (Username)
VALUES
('OldMac6'),
('Bigbird4'),
('Thomas4'),
('Sarah123'),
('Dorothy45');

CREATE TABLE Volunteer (
	Username	VARCHAR(20),
	PRIMARY KEY (Username),
	FOREIGN KEY (Username) REFERENCES Users
		On delete Cascade);

INSERT INTO Volunteer (Username)
VALUES
('Ann34'),
('Jonny55'),
('Ali67'),
('Dh78'),
('Greg8');

CREATE TABLE Task (
	Task_ID		    INTEGER,
	Date			Date,
	Description		VARCHAR(50),
    Num_Volunteer	INTEGER,
    Status			VARCHAR(10),
    PostalCode		CHAR(6),
    Address		    VARCHAR(50),
    Username		VARCHAR(20),
    CreateTime		Date,
    PRIMARY KEY (Task_ID),
    FOREIGN KEY (PostalCode, Address) REFERENCES Location
        On delete NO ACTION
        On Update Cascade,
    FOREIGN KEY (Username) REFERENCES Senior
        On delete NO ACTION
        On Update Cascade);

INSERT INTO Task (Task_ID, Date, Description, Num_Volunteer, Status, PostalCode, Address, Username, CreateTime)
VALUES
(1, '2020-01-31', 'Please help me mow my lawn and rake leaves', 2, 'Completed', 'V6T1Z4', '2329 West Mall', 'OldMac6',  '2019-12-31'),
(2, '2019-12-31', 'Drive me to the grocery store', 1, 'Completed', 'M5S1A1', '27 King''s College Circle', 'Bigbird4', '2019-11-30'),
(3, '2018-09-09', 'Take me to my Doctors appointment', 1, 'Completed', 'T6G2R3', '116 St & 85 Ave', 'Sarah123', '2018-08-09'),
(4, '2018-07-17', 'Help me with learn how to use Facebook', 1, 'Completed', 'N6A3K7', '1151 Richmond St', 'Dorothy45', '2018-06-17'),
(5, '2018-05-17', 'Help me mail a package back', 1, 'Completed', 'N6A3K7', '1151 Richmond St', 'Thomas4', '2018-04-17'),
(6, '2020-05-19', 'Help me cut my cat''s nails', 1, 'Upcoming', 'N6A3K7', '1151 Richmond St', 'Thomas4',  '2020-04-19'),
(7, '2020-05-20', 'Help me survive coronavirus', 10, 'Upcoming', 'N6A3K7', '1151 Richmond St', 'Thomas4',  '2020-04-19');

CREATE TABLE Preference (
	Pref_ID	    INTEGER,
	Pref_Name	VARCHAR(20),
	Description	VARCHAR(100),
	PRIMARY KEY (Pref_ID));

INSERT INTO Preference (Pref_ID, Pref_Name, Description)
VALUES
(1, 'Outdoors', 'working outdoors in the sun'),
(2, 'Indoors', 'working inside'),
(3, 'Labour', 'physically demanding tasks either indoors or outdoors'),
(4, 'Social', 'talking with seniors and getting to know them'),
(5, 'Behind the Scenes', 'minimal interactions with seniors, helping with tasks');

CREATE TABLE UsersHasPreference (
	Username	VARCHAR(20),
    Pref_ID	INTEGER,
    PRIMARY KEY (Username, Pref_ID),
    FOREIGN KEY (Pref_ID) REFERENCES Preference,
    FOREIGN KEY (Username) REFERENCES Users);

INSERT INTO UsersHasPreference (Username, Pref_ID)
VALUES
('OldMac6', 4),
('Sarah123', 2),
('Jonny55', 3),
('Ann34', 5),
('Ali67', 5);

CREATE TABLE TasksHasPreference (
	Task_ID	INTEGER,
    Pref_ID	INTEGER,
    PRIMARY KEY (Task_ID, Pref_ID),
    FOREIGN KEY (Pref_ID) REFERENCES Preference,
    FOREIGN KEY (Task_ID) REFERENCES Task
        on delete cascade);

INSERT INTO TasksHasPreference (Task_ID, Pref_ID)
VALUES
(1, 1),
(1, 3),
(2, 1),
(2, 3),
(2, 2),
(3, 1),
(4, 4),
(4, 2),
(5, 5),
(5, 1),
(5, 3);

CREATE TABLE VolunteerVolunteers (
	Username	VARCHAR(20),
	Task_ID	    INTEGER,
	Date		Date,
	PRIMARY KEY (Username, Task_ID),
	FOREIGN KEY (Username) REFERENCES Volunteer,
    FOREIGN KEY (Task_ID) REFERENCES Task
        on delete cascade);

INSERT INTO  VolunteerVolunteers (Username, Task_ID, Date)
VALUES
('Ann34', 1, '2020-01-31'),
('Ann34', 2, '2019-12-31'),
('Ann34', 3, '2018-09-09'),
('Ann34', 4, '2018-07-17'),
('Ann34', 5, '2018-05-17'),
('Ann34', 6, '2020-05-19'),
('Ann34', 7, '2020-05-20'),
('Jonny55', 2, '2019-12-31'),
('Ali67', 3, '2018-09-09'),
('Dh78', 4, '2018-07-17'),
('Greg8', 5, '2018-05-17');

CREATE TABLE VolunteersTimeEntryRecord (
    Record_ID   INTEGER,
	Date		Date,
	TimeOfDay	Time,
    Hours		INTEGER,
	Username	VARCHAR(20),
	Task_ID	    INTEGER,
	PRIMARY KEY (Record_ID),
	FOREIGN KEY (Username) REFERENCES Volunteer
		On delete Cascade,
	FOREIGN KEY (Task_ID) REFERENCES Task
		On delete Cascade);

INSERT INTO VolunteersTimeEntryRecord (Record_ID, Date, TimeOfDay, Hours, Username, Task_ID)
VALUES
(123,'2020-01-31', '13:15', 3, 'Ann34', 1),
(124,'2019-12-31', '14:00', 10, 'Ann34', 2),
(125,'2018-09-09', '15:15', 2, 'Ann34', 3),
(126,'2018-07-17', '16:30', 5, 'Ann34', 4),
(127,'2018-05-17', '17:45', 20, 'Ann34', 5),
(234,'2019-12-31', '14:50', 2, 'Jonny55', 2),
(456,'2018-09-09', '9:30', 3, 'Ali67', 3),
(567,'2018-07-17', '12:30', 2, 'Dh78', 4),
(678,'2018-05-17', '14:23', 4, 'Greg8', 5);

CREATE TABLE TaskRequestPlace (
	Request_ID 		INTEGER,
	Date			Date,
	Task_ID		    INTEGER,
	PRIMARY KEY (Request_ID),
	FOREIGN KEY (Task_ID) REFERENCES Task
		On delete cascade);

INSERT INTO TaskRequestPlace (Request_ID, Date, Task_ID)
VALUES
(612, '2018-06-17', 6),
(613, '2018-06-17', 6),
(614, '2018-06-17', 6),
(712, '2020-05-18', 7),
(713, '2020-05-19', 7),
(714, '2020-05-19', 7);

CREATE TABLE VolunteerPlaceRequest (
	Username	VARCHAR(20),
	Request_ID 	INTEGER,
	PRIMARY KEY (Request_ID),
	FOREIGN KEY (Username) REFERENCES Volunteer,
	FOREIGN KEY (Request_ID) REFERENCES TaskRequestPlace
		On delete cascade
		On Update Cascade);

INSERT INTO VolunteerPlaceRequest (Request_ID, Username)
VALUES
(612, 'Greg8'),
(613, 'Greg8'),
(614, 'Greg8'),
(712, 'Ali67'),
(713, 'Dh78'),
(714, 'Jonny55');

CREATE TABLE TaskCompletePlaced (
	Complete_ID		INTEGER,
	Date			Date,
	Task_ID		    INTEGER,
	MonetaryAmount	REAL,
    PRIMARY KEY (Complete_ID),
    FOREIGN KEY (Task_ID) REFERENCES Task
        On delete Cascade);

INSERT INTO TaskCompletePlaced (Complete_ID, Date, Task_ID, MonetaryAmount)
VALUES
(1, '2020-01-31', 1, 0),
(2, '2019-12-31', 2, 0),
(3, '2018-09-09', 3, 0),
(4, '2018-07-17', 4, 0),
(5, '2018-05-17', 5, 10);

CREATE TABLE SeniorPlaceCompleteTask (
	Username		VARCHAR(20),
	Complete_ID		INTEGER,
	PRIMARY KEY (Complete_ID),
	FOREIGN KEY (Complete_ID) REFERENCES TaskCompletePlaced
	    on delete cascade,
	FOREIGN KEY (Username) REFERENCES Senior
		On delete NO ACTION
		On Update Cascade);

INSERT INTO SeniorPlaceCompleteTask (Username, Complete_ID)
VALUES
('OldMac6', 1),
('Bigbird4', 2),
('Sarah123', 3),
('Dorothy45', 4),
('Thomas4', 5);

CREATE TABLE MakeReview (
	Review_ID		INTEGER,
	Description		VARCHAR (250),
	Rating			INTEGER,
    Task_ID		    INTEGER,
	VUsername		VARCHAR(20),
    PRIMARY KEY (Review_ID),
    FOREIGN KEY (VUsername) REFERENCES Volunteer
        On delete set NULL,
    FOREIGN KEY (Task_ID) REFERENCES Task
        On delete set NULL);

INSERT INTO MakeReview (Review_ID, Description, Rating, Task_ID, VUsername)
VALUES
(10, 'Great Help! My lawn looks amazing!', 10, 1,  'Ann34'),
(20, 'Not the best drive, a little too speedy for my liking', 7, 2, 'Jonny55'),
(103, 'Made me feel very supported at the doctors and held the door', 10, 3, 'Ali67'),
(13, 'good support, but other volunteer was better.', 7, 3, 'Ann34'),
(45, 'I know how to use facebook now well but they were a bit impatient', 8, 4, 'Dh78'),
(22, 'Ann did a very bad job. Do not recommend.', 1, 4, 'Ann34'),
(11, 'My package got shipped, no complaints!', 9, 5, 'Greg8'),
(29, 'My package got shipped, no complaints great work!', 9, 5, 'Ann34');





