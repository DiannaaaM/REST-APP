CREATE TABLE students (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        age INT NOT NULL,
                        faculty VARCHAR not null
);

CREATE TABLE faculty (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          color VARCHAR(255) NOT NULL
);

CREATE TABLE studentsAndFaculty (
                                 id SERIAL PRIMARY KEY,
                                 name VARCHAR(255) NOT NULL,
                                 faculty VARCHAR(255) NOT NULL,
                                 color VARCHAR(255) NOT NULL
);