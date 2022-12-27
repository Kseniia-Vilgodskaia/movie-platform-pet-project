CREATE TABLE movie
(
    id       uuid         NOT NULL PRIMARY KEY,
    title    varchar(254) NOT NULL,
    year     integer      NOT NULL,
    genre    varchar(254) NOT NULL,
    duration integer      NOT NULL,
    director varchar(254) NOT NULL,
    UNIQUE (title, year, director)
)