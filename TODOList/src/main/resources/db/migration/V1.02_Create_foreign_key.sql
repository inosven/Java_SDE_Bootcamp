ALTER TABLE tasks
    ADD CONSTRAINT task_user_fk FOREIGN KEY ( user_id )
        REFERENCES users ( id );
