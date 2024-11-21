ALTER table Students ADD id SERIAL UNIQUE PRIMARY KEY ;
ALTER table Students ADD name TEXT not null UNIQUE;
ALTER table Students ADD age INTEGER check ( age > 16 ) default 20;
ALTER table Students ADD faculty TEXT;
ALTER table Students ADD faculty_id SERIAL;


ALTER table Faculty add id SERIAL UNIQUE PRIMARY KEY ;
ALTER TABLE  Faculty ADD name TEXT UNIQUE;
ALTER TABLE  Faculty ADD color TEXT UNIQUE;

ALTER table Avatars ADD id SERIAL UNIQUE PRIMARY KEY;
ALTER table Avatars ADD name VARCHAR(255);
ALTER table Avatars ADD path VARCHAR(255);
ALTER table Avatars ADD student VARCHAR(255);
