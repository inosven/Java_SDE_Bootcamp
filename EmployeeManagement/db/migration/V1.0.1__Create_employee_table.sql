CREATE TABLE employees (
                           id              BIGSERIAL NOT NULL,
                           name            VARCHAR(30) not null,
                           first_name      VARCHAR(30),
                           last_name       VARCHAR(30),
                           email           VARCHAR(50),
                           address         VARCHAR(150),
                           hired_date      date default CURRENT_DATE,
                           department_id   BIGSERIAL NOT NULL
);
ALTER TABLE employees ADD CONSTRAINT employee_pk PRIMARY KEY ( id );

