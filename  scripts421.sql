Create table Students(
    id SERIAL UNIQUE PRIMARY KEY ,
    name TEXT not null UNIQUE,
    age INTEGER check ( age > 16 ) default 20,
    faculty TEXT,
    faculty_id SERIAL
);


Create table Faculty(
    id SERIAL UNIQUE PRIMARY KEY ,
    name TEXT UNIQUE,
    color TEXT UNIQUE
);

CREATE table Avatars(
    id SERIAL UNIQUE PRIMARY KEY,
    name VARCHAR(255),
    path VARCHAR(255),
    student VARCHAR(255)
);
