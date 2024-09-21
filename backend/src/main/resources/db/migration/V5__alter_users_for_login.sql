ALTER TABLE users
    DROP COLUMN is_superuser,
    DROP COLUMN is_staff,
    ADD COLUMN role VARCHAR(50) NOT NULL;
