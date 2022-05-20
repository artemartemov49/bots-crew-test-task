--liquibase formatted sql

--changeset artem:1
CREATE TABLE IF NOT EXISTS lector
(
    id        SERIAL PRIMARY KEY,
    firstname VARCHAR(128) NOT NULL,
    lastname  VARCHAR(128) NOT NULL,
    salary    INT          NOT NULL,
    degree    VARCHAR(28)  NOT NULL
);

--changeset artem:2
CREATE TABLE IF NOT EXISTS department
(
    id        SERIAL PRIMARY KEY,
    title     VARCHAR(64) UNIQUE NOT NULL,
    lector_id INT UNIQUE         NOT NULL REFERENCES lector
);

--changeset artem:3
CREATE TABLE IF NOT EXISTS department_lector
(
    department_id INT REFERENCES department,
    lector_id     INT REFERENCES lector,
    PRIMARY KEY (department_id, lector_id)
);