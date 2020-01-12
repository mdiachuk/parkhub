ALTER TABLE park_hub.user
        ADD COLUMN IF NOT EXISTS number_of_failed_pass_entering INTEGER NOT NULL DEFAULT 0;
