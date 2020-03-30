CREATE VIEW UserHours AS
(SELECT username as username, to_char(SUM(Hours),'999999') as totalHours
FROM VolunteersTimeEntryRecord
GROUP BY username)
UNION
(SELECT Username, to_char(0, '999999') FROM Volunteer
WHERE Username NOT IN (SELECT DISTINCT v.Username FROM VolunteersTimeEntryRecord v));

CREATE VIEW UserRating AS
((SELECT m.VUsername as Username, to_char(AVG (rating),'99D99') as Rating
FROM MakeReview m
GROUP BY m.VUsername)
UNION
(SELECT Username, to_char(-1,'99D99') as Rating
FROM Volunteer v
WHERE username NOT IN (SELECT DISTINCT VUsername FROM MakeReview)));

CREATE VIEW UserRatingHours AS
SELECT UserHours.username, Rating, TotalHours
FROM UserHours JOIN UserRating ON UserHours.username = UserRating.username