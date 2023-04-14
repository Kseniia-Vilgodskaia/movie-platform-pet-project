CREATE TABLE "user"
(
    id         uuid         NOT NULL PRIMARY KEY,
    login      varchar(256) NOT NULL UNIQUE,
    password   varchar(256) NOT NULL,
    first_name varchar(256) NOT NULL,
    last_name  varchar(256) NOT NULL,
    email      varchar(256) NOT NULL UNIQUE
)