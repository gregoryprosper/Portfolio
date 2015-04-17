use college;

SELECT p.pname FROM prof p JOIN dept d USING (dname)
WHERE d.numphds < 50;


SELECT * FROM student
ORDER BY gpa;


SELECT e.cno, e.sectno, AVG(s.gpa) average FROM student s JOIN enroll e USING (sid)
WHERE dname = "Computer Sciences"
GROUP BY cno,sectno;


SELECT * FROM course c JOIN enroll e USING (cno)
GROUP BY (c.cname)
HAVING count(*) < 6;


SELECT s.sname, s.sid FROM student s JOIN enroll e USING (sid)
GROUP BY (sid)
ORDER BY count(*) DESC;


SELECT m.dname FROM major m JOIN student s USING (sid)
WHERE s.age < 18;


SELECT s.sname, m.dname FROM student s 
JOIN major m USING (sid) 
JOIN enroll e USING (sid) 
JOIN course c USING (cno)
WHERE c.cname LIKE "College Geometry%";


SELECT DISTINCT m.dname,d.numphds FROM major m
JOIN enroll e ON m.sid = e.sid
JOIN course c ON e.cno = c.cno
JOIN dept d ON m.dname = d.dname
WHERE m.dname NOT IN (SELECT m.dname FROM major m
						JOIN enroll e ON m.sid = e.sid
						JOIN course c ON e.cno = c.cno
						WHERE c.cname LIKE "College Geometry%");
                        
         
         
SELECT * FROM student s 
JOIN enroll e USING (sid)
WHERE e.dname = "Computer Sciences" AND e.dname = "Mathematics";


SELECT MAX(s.age) - MIN(s.age) AgeDiff FROM student s
JOIN major m USING (sid)
WHERE m.dname = "Computer Sciences";


SELECT m.dname, AVG(s.gpa) average FROM student s 
JOIN major m USING(sid)
WHERE s.gpa < 1
GROUP BY m.dname;


SELECT s.sid, s.sname, s.gpa FROM student s 
JOIN enroll e USING (sid)
WHERE e.dname = "Civil Engineering"
GROUP BY s.sname
HAVING count(*) = (SELECT count(*) FROM course c 
					WHERE c.dname = "Civil Engineering");

