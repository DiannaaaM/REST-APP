SELECT students.name, students.age, faculty.name
FROM students
JOIN faculty ON students.faculty_id = faculty.id;


SELECT students.name, students.age
FROM students
JOIN avatars ON students.id = avatars.student;
