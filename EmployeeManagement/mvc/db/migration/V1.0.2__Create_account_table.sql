
CREATE TABLE accounts (
                          id             BIGSERIAL NOT NULL,
                          account_type   VARCHAR(30),
                          balance        NUMERIC(10, 2),
                          create_date    date default CURRENT_DATE,
                          employee_id    BIGSERIAL NOT NULL
);
ALTER TABLE accounts ADD CONSTRAINT account_pk PRIMARY KEY ( id );

