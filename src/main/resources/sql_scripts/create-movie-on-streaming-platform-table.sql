CREATE TABLE movie_on_streaming_platform
(
    id                        uuid    NOT NULL PRIMARY KEY,
    movie_id                  uuid    NOT NULL,
    streaming_platform_id     uuid    NOT NULL,
    available_for_buying      boolean NOT NULL,
    available_in_subscription boolean NOT NULL,
    price_for_buying          integer,
    available_until           date,
    UNIQUE (movie_id, streaming_platform_id),
    CHECK ( available_for_buying OR available_in_subscription )
)