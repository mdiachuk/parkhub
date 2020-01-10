CREATE TABLE IF NOT EXISTS "park_hub".blocked_user (

    blocked_user_id BIGSERIAL PRIMARY KEY NOT NULL,
	user_id   BIGSERIAL NOT NULL REFERENCES park_hub.user(id),
	blocking_date DATE NOT NULL

);
