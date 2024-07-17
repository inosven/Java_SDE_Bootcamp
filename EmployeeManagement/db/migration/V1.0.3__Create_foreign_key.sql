

ALTER TABLE accounts
    ADD CONSTRAINT account_employee_fk FOREIGN KEY ( employee_id )
        REFERENCES employees ( id );

ALTER TABLE employees
    ADD CONSTRAINT employee_department_fk FOREIGN KEY ( department_id )
        REFERENCES departments ( id );