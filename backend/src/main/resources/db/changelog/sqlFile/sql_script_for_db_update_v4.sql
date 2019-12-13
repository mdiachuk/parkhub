CREATE TABLE IF NOT EXISTS "park_hub".ticket_solver(
ticket_id    int REFERENCES park_hub.support_ticket (id) ,
solver_id int REFERENCES park_hub.user (id) ,
CONSTRAINT ticket_solver_pkey PRIMARY KEY (ticket_id, solver_id)

);
ALTER TABLE park_hub.user RENAME COLUMN second_name TO last_name;
ALTER TABLE park_hub.booking RENAME COLUMN cheсk_in TO check_in;