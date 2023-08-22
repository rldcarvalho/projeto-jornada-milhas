CREATE TABLE testimonial (
    id bigint not null auto_increment,
    person_name varchar(100) not null,
    testimonial_text varchar(1000) not null,
    image_path varchar(100) not null,
    primary key(id)
);