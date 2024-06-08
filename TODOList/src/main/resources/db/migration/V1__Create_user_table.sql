CREATE TABLE users (
                             id          BIGSERIAL NOT NULL,
                             username        VARCHAR(30) not null,
                             password VARCHAR(100),
                             email    VARCHAR(100),
                             event_id    BIGSERIAL NOT NULL
);
ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY (id);
