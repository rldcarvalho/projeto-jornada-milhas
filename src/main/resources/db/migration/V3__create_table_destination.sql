CREATE TABLE destination(
    id bigint not null auto_increment,
    name varchar(100) not null,
    photo_path varchar(255) not null,
    price decimal(10, 2) not null,
    active tinyint not null,
    primary key(id)
);