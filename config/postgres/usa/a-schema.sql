CREATE SCHEMA usa;

CREATE TABLE usa.owner (
    id VARCHAR(50) PRIMARY KEY,
    country VARCHAR(2) NOT NULL,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE usa.thingy (
    id INTEGER PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    owner_id VARCHAR(50) NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES usa.owner(id)
);
