CREATE TABLE movie_on_streaming_platform
(
    id                        uuid    NOT NULL PRIMARY KEY,
    movie_id                  uuid    NOT NULL,
    streaming_platform_id     uuid    NOT NULL,
    available_for_buying      boolean NOT NULL,
    available_in_subscription boolean NOT NULL,
    price_for_buying          integer CHECK ( price_for_buying IS NULL OR price_for_buying >= 0),
    available_until           date,
    UNIQUE (movie_id, streaming_platform_id),
    CHECK ( available_for_buying OR available_in_subscription ),
    CONSTRAINT FK_movie_on_streaming_platform_movie FOREIGN KEY (movie_id) REFERENCES movie (id),
    CONSTRAINT FK_movie_on_streaming_platform_streaming_platform FOREIGN KEY (streaming_platform_id) REFERENCES streaming_platform (id)
)