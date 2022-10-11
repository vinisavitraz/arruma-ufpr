CREATE DATABASE arrumaufpr;

CREATE TABLE IF NOT EXISTS "role" (
    id serial PRIMARY KEY,
    name VARCHAR (100) NOT NULL
);

CREATE TABLE IF NOT EXISTS "permission" (
    id serial PRIMARY KEY,
    name VARCHAR (100) NOT NULL
);

CREATE SEQUENCE permission_id START 1 INCREMENT 1 MINVALUE 1 OWNED BY "permission".id;

CREATE TABLE IF NOT EXISTS role_permissions (
    role_id INTEGER NOT NULL,
    permission_id  INTEGER NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES "role"(id),
    FOREIGN KEY (permission_id) REFERENCES "permission"(id)
);

CREATE TABLE IF NOT EXISTS "user" (
    id serial PRIMARY KEY,
    name VARCHAR (100) NOT NULL,
    email VARCHAR (100) NOT NULL,
    password VARCHAR (100) NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY(role_id) REFERENCES "role"(id)
);

CREATE SEQUENCE user_id START 1 INCREMENT 1 MINVALUE 1 OWNED BY "user".id;
CREATE INDEX idx_user_email ON "user"(email);

CREATE TABLE IF NOT EXISTS location (
    id serial PRIMARY KEY,
    name VARCHAR (100) NOT NULL,
    description VARCHAR (250) NOT NULL
);

CREATE SEQUENCE location_id START 1 INCREMENT 1 MINVALUE 1 OWNED BY "location".id;
CREATE INDEX idx_location_name ON "location"(name);

CREATE TABLE IF NOT EXISTS object (
    id serial PRIMARY KEY,
    name VARCHAR (100) NOT NULL,
    description VARCHAR (250) NOT NULL,
    status VARCHAR (50) NOT NULL
);

CREATE SEQUENCE object_id START 1 INCREMENT 1 MINVALUE 1 OWNED BY "object".id;
CREATE INDEX idx_object_name ON "object"(name);

CREATE TABLE IF NOT EXISTS incident_type (
    id serial PRIMARY KEY,
    name VARCHAR (100) NOT NULL,
    description TEXT NOT NULL
);

CREATE SEQUENCE incident_type_id START 1 INCREMENT 1 MINVALUE 1 OWNED BY "incident_type".id;
CREATE INDEX idx_incident_type_name ON "incident_type"(name);

CREATE TABLE IF NOT EXISTS incident (
    id serial PRIMARY KEY,
    title VARCHAR (100) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR (50) NOT NULL,
    open_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NULL,
    incident_type_id INTEGER NOT NULL,
    object_id INTEGER NOT NULL,
    location_id INTEGER NOT NULL,
    FOREIGN KEY(incident_type_id) REFERENCES "incident_type"(id),
    FOREIGN KEY(object_id) REFERENCES "object"(id),
    FOREIGN KEY(location_id) REFERENCES "location"(id)
);

CREATE SEQUENCE incident_id START 1 INCREMENT 1 MINVALUE 1 OWNED BY "incident".id;
CREATE INDEX idx_incident_status ON "incident"(status);
CREATE INDEX idx_incident_open_date ON "incident"(open_date);

CREATE TABLE IF NOT EXISTS incident_user (
    user_id INTEGER NOT NULL,
    incident_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, incident_id),
    FOREIGN KEY (user_id) REFERENCES "user"(id),
    FOREIGN KEY (incident_id) REFERENCES "incident"(id)
);

CREATE TABLE IF NOT EXISTS incident_interaction (
    id serial PRIMARY KEY,
    title VARCHAR (100) NOT NULL,
    description TEXT NOT NULL,
    interaction_date TIMESTAMP NOT NULL,
    user_id INTEGER NOT NULL,
    incident_id INTEGER NOT NULL,
    FOREIGN KEY(user_id) REFERENCES "user"(id),
    FOREIGN KEY(incident_id) REFERENCES "incident"(id)
);

CREATE SEQUENCE incident_interaction_id START 1 INCREMENT 1 MINVALUE 1 OWNED BY "incident_interaction".id;
CREATE INDEX idx_incident_interaction_date ON "incident_interaction"(interaction_date);

##### SEED #####

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
INSERT INTO location (id, name, description) VALUES (1, 'Banheiro', 'Banheiro sept 2 andar');
INSERT INTO location (id, name, description) VALUES (2, 'Sala A123', '2 andar sept');
INSERT INTO object (id, name, status) VALUES (1, 'Projetor', 'Ativo');
INSERT INTO incident_type (id, name, description) VALUES (1, 'Quebrado', 'Item quebrado/Não funcional');
INSERT INTO incident (id, title, description, status, open_date, end_date, incident_type_id, object_id, location_id)
    VALUES (1, 'Problema no projetor', 'Projetor não funciona na sala A123', 'Aberto', '2022-09-15 10:00:00', null, 1, 1, 1);