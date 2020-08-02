CREATE TABLE users (
    id uuid DEFAULT uuid_generate_v4(),
    email VARCHAR(150) not null unique,
    name varchar(150) not null,
    password varchar not null,
    phone varchar not null,
    primary key (id)
);