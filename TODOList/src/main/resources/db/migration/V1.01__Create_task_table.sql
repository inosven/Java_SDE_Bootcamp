CREATE TABLE tasks
(
    task_id           BIGSERIAL  NOT NULL,
    title       VARCHAR(150) NOT NULL,
    notes       VARCHAR(1000),
    start_date   VARCHAR(6) NOT NULL DEFAULT CURRENT_DATE,
    end_date     VARCHAR(6) NOT NULL DEFAULT CURRENT_DATE,
    start_time   VARCHAR(6) NOT NULL CURRENT_TIME,
    end_time     VARCHAR(6) NOT NULL CURRENT_TIME,
    time_slot    VARCHAR(1) NOT NULL DEFAULT 0 CHECK (time_slot >= 0 AND time_slot <4),
    is_done BOOLEAN    NOT NULL DEFAULT 0,
    is_doing BOOLEAN    NOT NULL DEFAULT 0,
    user_id      BIGSERIAL  NOT NULL,
    priority  INTEGER(1) NOT NULL DEFAULT 4,
    is_repeat BOOLEAN    NOT NULL DEFAULT 0,
    tags_ids INTEGER[],
    last_modified_time VARCHAR(6) NOT NULL CURRENT_TIME,
    last_modified_date VARCHAR(6) NOT NULL DEFAULT CURRENT_DATE


);
ALTER TABLE tasks
    ADD CONSTRAINT tasks PRIMARY KEY (task_id);
