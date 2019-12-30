CREATE TABLE IF NOT EXISTS "park_hub".blocked_user (

	id   BIGSERIAL   NOT NULL REFERENCES park_hub.user(id),
	blocking_date DATE NOT NULL 

);