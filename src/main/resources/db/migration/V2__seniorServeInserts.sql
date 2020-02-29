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
