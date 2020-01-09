ALTER TABLE park_hub.payment
    ADD IF NOT EXISTS is_cancelled BOOLEAN DEFAULT FALSE;