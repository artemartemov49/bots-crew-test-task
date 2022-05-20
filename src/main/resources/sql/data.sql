INSERT INTO lector(firstname, lastname, salary, degree)
VALUES ('Dima', 'Dimov', 54, 'ASSISTANT'),
       ('Artem', 'Artemov', 100, 'ASSISTANT'),
       ('Olya', 'Kolevna', 25, 'ASSOCIATE_PROFESSOR'),
       ('Sveta', 'Kyznichova', 74, 'PROFESSOR');

INSERT INTO department(title, lector_id)
VALUES ('Math', 1),
       ('Ukrainian', 2),
       ('Emglish', 3);

INSERT INTO department_lector(department_id, lector_id)
VALUES (1,2),
       (1,3),
       (1,4);