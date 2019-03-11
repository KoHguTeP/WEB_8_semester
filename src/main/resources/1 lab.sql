-- Database: MusicShop

CREATE TABLE Singer (
ID_singer SERIAL PRIMARY KEY NOT NULL,
name varchar(40) NOT NULL
);

CREATE TABLE Album (
ID_album SERIAL PRIMARY KEY,
name varchar(40) NOT NULL,
genre varchar(40) NOT NULL,
ID_singer INT,
CONSTRAINT singer_id_fkey FOREIGN KEY (ID_singer) REFERENCES Singer(ID_singer) ON DELETE CASCADE ON UPDATE CASCADE
);
	
CREATE TABLE Composition (
ID_composition SERIAL PRIMARY KEY,
name varchar(40) NOT NULL,
duration interval NOT NULL,
ID_album INT,
CONSTRAINT album_ID_fkey FOREIGN KEY (ID_album) REFERENCES Album(ID_album)
);

INSERT INTO Singer VALUES
  (0001, '21 Savage'),
	(0002, 'XXXTENTACION'),
	(0003, 'The Neighbourhood'),
	(0004, 'Harry Styles'),
	(0005, 'The XX');
	
INSERT INTO Album VALUES
	(1001, 'i am > i was', 'rap', 0001),
	(1002, 'SKINS', 'rap', 0002),
	(1003, '?', 'rap', 0002),
	(1004, 'I Love You.', 'indie rock', 0003),
	(1005, 'WIPED OUT!', 'indie rock', 0003),
	(1006, 'Harry Styles', 'soft rock', 0004);
	
INSERT INTO Composition VALUES
	(2001, 'a lot', '00:04:49', 1001),
	(2002, 'all my friends', '00:03:32', 1001),
	(2003, 'BAD!', '00:01:34', 1002),
	(2004, 'Afraid', '00:04:11', 1004),
	(2005, 'Let It Go', '00:03:17', 1004),
	(2006, 'Sign of the Times', '00:05:40', 1006);
	
--1.	
SELECT name || ', ' || duration AS "Song Details" 
FROM composition 

--2.
SELECT name , duration 
FROM composition 
WHERE duration NOT BETWEEN '00:05:00' AND '00:10:00'
GROUP BY name, duration
ORDER BY duration DESC

SELECT name , duration 
FROM composition 
WHERE duration < '00:05:00' OR  duration > '00:10:00'
GROUP BY name, duration
ORDER BY duration DESC

--3.
SELECT name 
FROM singer 
WHERE POSITION ('e' IN name) = 3 AND (ID_singer = 0003 OR ID_singer = 0007)

SELECT name 
FROM singer 
WHERE name LIKE '__e%' AND ID_singer IN (0003,0007)

--4.
SELECT a.name, MIN(duration), MAX(duration), AVG(duration), SUM(duration)
FROM Composition AS c INNER JOIN album a ON c.id_album = a.id_album
GROUP BY a.name

--5.
SELECT a.name 
FROM album AS a INNER JOIN singer s ON a.ID_singer = s.ID_singer 
WHERE s.name = 'XXXTENTACION'

--6.
SELECT c.name, a.name
FROM (SELECT c.id_album, MIN(duration) minimal
	 FROM composition c
	 WHERE c.duration >= '00:05:00'
	 GROUP BY c.id_album) q
INNER JOIN album a ON q.id_album = a.id_album
INNER JOIN composition c ON a.id_album = c.id_album
WHERE c.duration = minimal

--7.
SELECT name, duration 
FROM composition 
WHERE duration > (
	SELECT AVG(duration) 
	FROM composition
)

--8.
SELECT name, duration 
FROM composition 
WHERE duration > ANY (
	SELECT duration 
	FROM composition AS c INNER JOIN album a on c.ID_album = a.ID_album
	WHERE a.name = 'I Love You.'
)

--9.
SELECT name, duration 
FROM composition 
WHERE duration > ALL (
	SELECT duration 
	FROM composition AS c INNER JOIN album a on c.ID_album = a.ID_album
	WHERE a.name = 'I Love You.'
)

--10.
SELECT name 
FROM composition 
WHERE duration IN (
	SELECT duration 
	FROM composition 
	WHERE name = 'Girl'
) 
EXCEPT (
	SELECT name  
	FROM composition 
	WHERE name = 'Girl'
)
