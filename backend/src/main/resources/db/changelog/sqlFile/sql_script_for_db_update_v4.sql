
ALTER TABLE park_hub.user
    RENAME COLUMN second_name TO last_name;
ALTER TABLE park_hub.booking
    RENAME COLUMN cheсk_in TO check_in;
    ALTER TABLE park_hub.user
    ADD COLUMN number_of_faild_pass_entering INTEGER NOT NULL DEFAULT 0;
