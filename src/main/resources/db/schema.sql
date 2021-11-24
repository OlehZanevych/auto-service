DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS auto;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS models;
DROP TABLE IF EXISTS brands;

CREATE TABLE users
(
    id            BIGSERIAL PRIMARY KEY,
    username      VARCHAR(32) UNIQUE NOT NULL,
    password_hash VARCHAR(128)       NOT NULL,
    is_admin      BOOLEAN            NOT NULL,
    first_name    VARCHAR(64)        NOT NULL,
    middle_name   VARCHAR(64)        NOT NULL,
    last_name     VARCHAR(64)        NOT NULL,
    phone         VARCHAR(16)        NOT NULL,
    email         VARCHAR(32)        NOT NULL,
    info          TEXT
);

CREATE TABLE customers
(
    id          BIGSERIAL PRIMARY KEY,
    first_name  VARCHAR(64) NOT NULL,
    middle_name VARCHAR(64) NOT NULL,
    last_name   VARCHAR(64) NOT NULL,
    phone       VARCHAR(16) NOT NULL,
    email       VARCHAR(32) NOT NULL,
    info        TEXT
);

CREATE TABLE brands
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(32) NOT NULL UNIQUE,
    description TEXT        NOT NULL
);

CREATE TABLE models
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(32) NOT NULL UNIQUE,
    brand_id    BIGINT      NOT NULL REFERENCES brands (id),
    year        SMALLINT    NOT NULL,
    description TEXT        NOT NULL
);

CREATE TABLE auto
(
    id          BIGSERIAL PRIMARY KEY,
    customer_id BIGINT   NOT NULL REFERENCES customers (id),
    model_id    BIGINT   NOT NULL REFERENCES models (id),
    mileage     SMALLINT NOT NULL
);