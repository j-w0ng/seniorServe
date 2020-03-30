CREATE VIEW UserRatingHours (Username, Rating, totalHours) AS
SELECT m.VUsername as Username, to_char(AVG (rating),'99D99') as Rating, to_char(SUM(Hours),'999999') as totalHours
FROM MakeReview m, VolunteersTimeEntryRecord v
WHERE v.Username = m.VUsername
GROUP BY m.VUsername;

