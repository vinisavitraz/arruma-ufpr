CREATE DATABASE ufprcmms;

CREATE TABLE IF NOT EXISTS incident (
    id serial PRIMARY KEY,
    title VARCHAR (100) NOT NULL,
    description VARCHAR (1000) NOT NULL
);

##### SEED #####

INSERT INTO incident (id, title, description) VALUES (1, 'Problema na descarga', 'Descarga nao funciona!');