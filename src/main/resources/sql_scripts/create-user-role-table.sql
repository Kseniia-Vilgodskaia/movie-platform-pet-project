CREATE TABLE user_role
(
    user_id uuid         NOT NULL,
    role    varchar(256) NOT NULL,
    PRIMARY KEY (user_id, role),
    CONSTRAINT FK_role_user FOREIGN KEY (user_id) REFERENCES "user" (id)
)