drop schema if exists testdb;
create schema testdb;

use testdb;

ALTER DATABASE testdb DEFAULT CHARACTER SET UTF8;

create table Students (
StudentId INT,
FirstName VARCHAR(20),
LASTNAME VARCHAR(10),
Birthdate DATE,
Gender CHAR(1),
GPA FLOAT
);



create table Courses (
CourseID INT,
CourseName VARCHAR(20),
Instructor VARCHAR(30),
Department VARCHAR(20)
);

create table Enrollments (
EnrollmentID INT,
StudentID INT,
CourseID INT,
EnrollmentDate DATE
-- FOREIGN KEY (StudentID) REFERENCES Students(StudentId),
-- FOREIGN KEY (CouresID) REFERENCES Courses(CouresID)
);

alter table Students add primary key (StudentId);
alter table Courses add primary key (CourseID);
alter table Enrollments add primary key (EnrollmentID);

create index Enrollments_idx ON Enrollments(StudentID, CourseID);

insert into Students values (1, '가', '가나', '2000-05-15', 'M', 3.75);
insert into Students values (2, '가', '다라', '1999-09-21', 'F', 3.9);
insert into Students values (3, '나', '마바', '2001-02-10', 'M', 3.2);
insert into Students values (4, '나', '사아', '2000-11-30', 'F', 3.6);
insert into Students values (5, '나', '자차', '2002-07-18', 'M', 3.45);


select * from Students;


-- 1. 학점이 높은 순서대로 학생들의 이름을 조회 (order by)

select * from Students order by GPA desc;

-- 2. 학생들의 평균 학점을 조회 (SUM)

select sum(GPA)/count(*) from Students;
-- OR
select avg(GPA) from Students;

-- 3. 학점이 가장 낮은 학생의 이름을 조회 (order by / limit)

select * from Students order by GPA limit 1;


-- 4. 성별 별 학점 평균 조회
select gender, avg(GPA) from Students group by gender;


-- 5. firstName별 가장 늦은 생일 (group by / max)
select firstName, max(birthdate) from Students group by firstName;


-- 6. FirstName, Gender 별 학점 평균 (group by / avg)

select firstName, Gender, avg(GPA) from Students group by firstName, Gender;




insert into Courses (CourseID, CourseName, Instructor, Department) VALUES
	(101, 'JAVA', '김상현', 'Mathematics'),
	(102, 'Python', '깨중', 'Arts'),
	(103, '', 'Prof. Williams', 'Computer Sci');
	

insert into Enrollments (EnrollmentID, StudentID, CourseID, EnrollmentDate) VALUES
	(1, 1, 101, '2023-01-15'),
	(2, 2, 102, '2023-02-10'),
	(3, 3, 101, '2023-03-05'),
	(4, 4, 103, '2023-04-22'),
	(5, 5, 102, '2023-05-10'),
    (6, 5, 101, '2023-05-10');




select CourseName, FirstName, Lastname, EnrollmentDate from
     Students
         join Enrollments on Students.StudentId = Enrollments.StudentID
         join Courses on Enrollments.CourseID = Courses.CourseID;


SELECT
    *
FROM Students
    join Enrollments on Students.StudentId = Enrollments.StudentID
    join Courses on Enrollments.CourseID = Courses.CourseID;

-- 7. 각 강의 이름 (CourseName)별 수강자 학생 수 조회
select CourseName, count(*) from
     Students
         join Enrollments on Students.StudentId = Enrollments.StudentID
         join Courses on Enrollments.CourseID = Courses.CourseID
     group by CourseName;



-- 8.각 강의 이름별 최고 학점 출력
select CourseName, max(GPA)
    from Students
        join Enrollments on Students.StudentID = Enrollments.StudentID
        join Courses on Enrollments.CourseID = Courses.CourseID
    group by CourseName;

