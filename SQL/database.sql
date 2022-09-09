CREATE DATABASE incidents_mgm;

CREATE TABLE IF NOT EXISTS "role" (
    id serial PRIMARY KEY,
    name VARCHAR (100) NOT NULL
);

CREATE TABLE IF NOT EXISTS "permission" (
    id serial PRIMARY KEY,
    name VARCHAR (100) NOT NULL
);

CREATE TABLE IF NOT EXISTS role_permissions (
    role_id INTEGER NOT NULL,
    permission_id  INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS "user" (
    id serial PRIMARY KEY,
    name VARCHAR (100) NOT NULL,
    email VARCHAR (100) NOT NULL,
    password VARCHAR (100) NOT NULL,
    role_id INTEGER NOT NULL
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
INSERT INTO role (id, name) VALUES (1, 'Admin');
INSERT INTO role (id, name) VALUES (2, 'Aluno');
INSERT INTO "permission" (id, name) VALUES (1, 'Criar incidente');
INSERT INTO "permission" (id, name) VALUES (2, 'Visualizar todos os incidentes');
INSERT INTO "permission" (id, name) VALUES (3, 'Visualizar incidentes do usuário');
INSERT INTO "role_permissions" (role_id, permission_id) VALUES (1, 1);
INSERT INTO "role_permissions" (role_id, permission_id) VALUES (2, 1);
INSERT INTO "role_permissions" (role_id, permission_id) VALUES (1, 2);
INSERT INTO "role_permissions" (role_id, permission_id) VALUES (2, 3);
INSERT INTO "user" (id, name, email, password, role_id) VALUES (1, 'Admin', 'admin@ufpr.br', '123', 1);
INSERT INTO "user" (id, name, email, password, role_id) VALUES (2, 'Aluno', 'aluno@ufpr.br', '123', 2);