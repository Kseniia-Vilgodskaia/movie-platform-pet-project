# WhereWatcher ![movie](https://cdn-icons-png.flaticon.com/512/2991/2991552.png)

WhereWatcher is a RESTful service prototype built for aggregating info about movies and streaming platforms where you
can watch them.

# Installation

Use **sql_scripts** folder to create a test database schema.

### Credentials

`ADMIN: login/password - TheBestBoss/TheBestBoss`

`CLIENT: login/password - AssistantRegionalManager/AssistantRegionalManager`

### Test Data

Test data used in the project is random and not accurate. It has been submitted only to verify the business logic.

# Usage

REST

```
GET: /movies - getting a filtered page with available movies
GET: /movies/{id} - getting a movie by its ID
POST: /movies - adding a new movie to the database
UPDATE: /movies/{id} - updating an existing movie
DELETE: /movies/{id} - deleting a movie
_________________
GET: /streamings - getting a page with streaming services
GET: /streamings/{id} - getting a streaming service by its ID
POST: /streamings - adding a new streaming service to the database
UPDATE: /streamings/{id} - updating an existing streaming service
DELETE: /streamings/{id} - deleting a streaming service
_________________
GET: /movie-on-streamings - getting a filtered page of relations between movies and streaming services
GET: /movie-on-streamings/{id} - getting a specific relation by its ID
POST: /movie-on-streamings - adding a new relation
UPDATE: /movie-on-streamings/{id} - updating an existing relation
DELETE: /movie-on-streamings/{id} - deleting a relation
```

Swagger is set and configured.

# Contribution

Contributions are very welcome here! To make a contribution, you can follow these steps:

1. Clone the repo and create a new feature branch.
2. Make your changes and add tests if necessary.
3. Make a pull request with some description of your changes.
4. Get my many thanks for being involved in the project :)

