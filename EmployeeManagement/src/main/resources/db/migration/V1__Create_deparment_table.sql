CREATE TABLE departments (
                             id          BIGSERIAL NOT NULL,
                             name        VARCHAR(30) not null,
                             description VARCHAR(150),
                             location    VARCHAR(100)
);
ALTER TABLE departments ADD CONSTRAINT department_pk PRIMARY KEY (id);
