ALTER TABLE destination
    ADD COLUMN photo_path2 varchar(255) not null,
    ADD COLUMN meta varchar(160) not null,
    ADD COLUMN description text;
