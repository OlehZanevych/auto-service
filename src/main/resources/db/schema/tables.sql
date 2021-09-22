DROP TABLE IF EXISTS customers;

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