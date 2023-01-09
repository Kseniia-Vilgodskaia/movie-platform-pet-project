CREATE TABLE streaming_platform
(
    id   uuid         NOT NULL PRIMARY KEY,
    name varchar(256) NOT NULL UNIQUE
)