-- -- -- -- -- -- -- -- --
DROP DATABASE IF EXISTS hotel;
DROP USER IF EXISTS read_write;
-- -- -- -- -- -- -- -- --
CREATE USER read_write WITH PASSWORD '123';
CREATE DATABASE hotel OWNER read_write;
-- -- -- -- -- -- -- -- --
\c hotel;
-- -- -- -- -- -- -- -- --
CREATE TABLE users (
  id           SERIAL PRIMARY KEY UNIQUE,
  name         VARCHAR(64) NOT NULL,
  age          INTEGER,
  phone_number VARCHAR(64),
  email        VARCHAR(64) NOT NULL
);

CREATE TYPE ACCOUNT_TYPE AS ENUM ('CLIENT', 'MANAGER');
CREATE TABLE accounts (
  id          SERIAL PRIMARY KEY UNIQUE,
  userid      INTEGER REFERENCES users (id) NOT NULL,
  login       VARCHAR(64)                   NOT NULL UNIQUE,
  password    VARCHAR(64)                   NOT NULL,
  manager     ACCOUNT_TYPE DEFAULT 'CLIENT',
  create_date TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

CREATE TYPE APARTMENTS_STATUS AS ENUM ('FREE', 'BOOKED', 'BUSY', 'NOT AVAILABLE');
CREATE TYPE APARTMENTS_TYPE AS ENUM ('ECONOMY', 'BUSINESS', 'PREMIUM');

CREATE TABLE apartments (
  id           SERIAL PRIMARY KEY UNIQUE,
  number       INTEGER UNIQUE                                        NOT NULL,
  rooms        INTEGER                                               NOT NULL,
  person_count INTEGER CHECK (person_count > 0 AND person_count < 8) NOT NULL,
  price        FLOAT CHECK (price > 0)                             NOT NULL,
  --('FREE', 'BOOKED', 'BUSY', 'NOT AVAILABLE')
  status       APARTMENTS_STATUS DEFAULT 'FREE',
  --('ECONOMY', 'BUSINESS', 'PREMIUM')
  rank         APARTMENTS_TYPE   DEFAULT 'BUSINESS',
  photo_path   VARCHAR(512)
);

CREATE TYPE ORDER_STATUS AS ENUM ('UNCHECKED', 'WAITED', 'PAID', 'IN PROCESS', 'CANCELED', 'DONE');

CREATE TABLE orders (
  id           SERIAL PRIMARY KEY UNIQUE,
  accountid    INTEGER REFERENCES accounts (id) ON DELETE CASCADE                                            NOT NULL,
  apartmentid  INTEGER REFERENCES apartments (id) ON DELETE CASCADE,
  --('UNCHECKED', 'IN PROCESS', 'CANCELED', 'HISTORY')
  status       ORDER_STATUS DEFAULT 'UNCHECKED',
  person_count INTEGER                                                                                       NOT NULL,
  rank         APARTMENTS_TYPE,
  "from"       TIMESTAMP CHECK (orders.from >= CURRENT_TIMESTAMP)                                            NOT NULL,
  "to"         TIMESTAMP CHECK (orders.to > orders.from)                                                     NOT NULL,
  create_date  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);
-- -- -- -- -- -- -- -- --
CREATE OR REPLACE FUNCTION orders_status()
  RETURNS TRIGGER AS $orders_status$
BEGIN
  IF new.status = 'WAITED' OR new.status = 'PAID'
  THEN
    UPDATE apartments
    SET status = 'BOOKED'
    WHERE new.apartmentid = id;
  ELSEIF new.status = 'IN PROCESS'
    THEN
      UPDATE apartments
      SET status = 'BUSY'
      WHERE new.apartmentid = id;
  ELSEIF new.status = 'CANCELED' OR new.status = 'DONE'
    THEN
      UPDATE apartments
      SET status = 'FREE'
      WHERE new.apartmentid = id;
  END IF;
END;
$orders_status$ LANGUAGE plpgsql;

CREATE TRIGGER orders_status
BEFORE INSERT OR UPDATE ON orders
FOR EACH ROW EXECUTE PROCEDURE orders_status();
-- -- -- -- -- -- -- -- --

-- -- -- -- -- -- -- -- --
GRANT ALL
ON ALL TABLES IN SCHEMA public
TO read_write;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO read_write;
-- -- -- -- -- -- -- -- --