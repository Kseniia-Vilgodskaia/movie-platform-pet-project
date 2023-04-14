CREATE TABLE movie
(
    id       uuid         NOT NULL PRIMARY KEY,
    title    varchar(256) NOT NULL,
    year     integer      NOT NULL,
    genre    varchar(256) NOT NULL,
    duration integer      NOT NULL,
    director varchar(256) NOT NULL,
    UNIQUE (title, year, director)
)