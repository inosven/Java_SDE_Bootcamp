CREATE TABLE users (
                             user_id          BIGSERIAL NOT NULL,
                             username        VARCHAR(30) NOT null,
                             password VARCHAR(100),
                             email    VARCHAR(100),
                             created_time VARCHAR(6) NOT NULL default CURRENT_TIME(),
                             created_date VARCHAR(6) NOT NULL default CURRENT_DATE(),
                            last_modified_time VARCHAR(20) NOT NULL default current_timestamp(),
);
ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY (user_id);
