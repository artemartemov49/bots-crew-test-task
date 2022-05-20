INSERT INTO lector(name, salary, degree)
VALUES ('Dima', 54, 'ASSISTANT'),
       ('Artem', 100, 'ASSISTANT'),
       ('Olya', 25, 'ASSOCIATE_PROFESSOR'),
       ('Sveta', 74, 'PROFESSOR');

INSERT INTO department(name, lector_id)
VALUES ('Math', 1),
       ('Ukrainian', 2),
       ('Emglish', 3);

INSERT INTO department_lector(department_id, lector_id)
VALUES (1,2),
       (1,3),
       (1,4);