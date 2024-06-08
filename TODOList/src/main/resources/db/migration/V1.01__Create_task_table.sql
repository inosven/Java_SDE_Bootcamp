CREATE TABLE tasks
(
    id           BIGSERIAL  NOT NULL,
    start_date   VARCHAR(6) NOT NULL DEFAULT CURRENT_DATE,
    end_date     VARCHAR(6) NOT NULL DEFAULT CURRENT_DATE,
    time_task    BOOLEAN    NOT NULL DEFAULT 0,
    is_done BOOLEAN    NOT NULL DEFAULT 0,
    is_doing BOOLEAN    NOT NULL DEFAULT 0,
    user_id      BIGSERIAL  NOT NULL,
    start_time   VARCHAR(6) CURRENT_TIME,
    end_time     VARCHAR(6) NOT NULL,
    is_important BOOLEAN    NOT NULL DEFAULT 0,
    is_urgent    BOOLEAN    NOT NULL DEFAULT 0,
    content VARCHAR(150),



);
ALTER TABLE tasks
    ADD CONSTRAINT tasks PRIMARY KEY (id);
