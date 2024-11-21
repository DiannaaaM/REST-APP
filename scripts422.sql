CREATE TABLE People (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        age INTEGER,
                        has_license BOOLEAN
);

CREATE TABLE Cars (
                      id SERIAL PRIMARY KEY,
                      make VARCHAR(255) NOT NULL,
                      model VARCHAR(255) NOT NULL,
                      cost NUMERIC
);

CREATE TABLE PeopleCars (
                            person_id INTEGER REFERENCES People(id) ON DELETE CASCADE,
                            car_id INTEGER REFERENCES Cars(id) ON DELETE CASCADE,
                            PRIMARY KEY (person_id, car_id)
);


INSERT INTO People (name, age, has_license) VALUES
                                                ('Alice', 30, TRUE),
                                                ('Bob', 25, TRUE),
                                                ('Charlie', 22, FALSE),
                                                ('David', 35, TRUE);

INSERT INTO Cars (make, model, cost) VALUES
                                         ('Toyota', 'Camry', 25000),
                                         ('Honda', 'Civic', 22000),
                                         ('Ford', 'Mustang', 35000);

INSERT INTO PeopleCars (person_id, car_id) VALUES
                                               (1, 1),
                                               (2, 2),
                                               (1, 2),
                                               (3, 1),
                                               (4, 3);