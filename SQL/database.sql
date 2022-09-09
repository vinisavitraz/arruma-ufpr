CREATE DATABASE incidents_mgm;

CREATE TABLE IF NOT EXISTS "user" (
    id serial PRIMARY KEY,
    name VARCHAR (100) NOT NULL,
    email VARCHAR (100) NOT NULL,
    type CHAR (1) NOT NULL
);

CREATE TABLE IF NOT EXISTS building (
    id serial PRIMARY KEY,
    name VARCHAR (100) NOT NULL,
    address VARCHAR (250) NOT NULL
);

CREATE TABLE IF NOT EXISTS object (
    id serial PRIMARY KEY,
    name VARCHAR (100) NOT NULL,
    status VARCHAR (50) NOT NULL
);

CREATE TABLE IF NOT EXISTS incident (
    id serial PRIMARY KEY,
    title VARCHAR (100) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR (50) NOT NULL,
    open_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS user_incident (
     user_id INTEGER NOT NULL,
     incident_id INTEGER NOT NULL,
     role CHAR (1) NOT NULL
);

##### SEED #####

INSERT INTO incident (id, title, description) VALUES (1, 'Problema na descarga', 'Descarga nao funciona!');
INSERT INTO building (id, name, address) VALUES (1, 'Sept', 'R. Dr. Alcides Vieira Arcoverde, 1225 - Jardim das Américas, Curitiba - PR');