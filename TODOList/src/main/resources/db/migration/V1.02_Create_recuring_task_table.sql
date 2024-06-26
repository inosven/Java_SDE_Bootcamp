CREATE TABLE tasks
(
    task_id           BIGSERIAL  NOT NULL,
repeat_interval INT[],
    repeat_type VARCHAR(150),
    day_of_week VARCHAR(30),
    day_of_month INT[] NOT NULL DEFAULT 0 CHECK (day_of_month >= 0 AND day_of_month <31),
    month_of_year INT[] NOT NULL DEFAULT 0 CHECK (day_of_month >= 0 AND day_of_month <12),
    repeat_start_date VARCHAR(6) NOT NULL DEFAULT "000000"
);
ALTER TABLE tasks
    ADD CONSTRAINT tasks PRIMARY KEY (task_id);