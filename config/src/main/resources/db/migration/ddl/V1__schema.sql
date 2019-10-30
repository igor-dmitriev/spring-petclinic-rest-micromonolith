CREATE SEQUENCE IF NOT EXISTS vet_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS vet
(
    id         INT DEFAULT nextval('vet_seq'),
    first_name VARCHAR(30),
    last_name  VARCHAR(30),
    CONSTRAINT pk_vet PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_vet_last_name ON vet (last_name);

CREATE SEQUENCE IF NOT EXISTS specialty_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS specialty
(
    id   INT DEFAULT nextval('specialty_seq'),
    name VARCHAR(80),
    CONSTRAINT pk_specialty PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_specialty_name ON specialty (name);


CREATE TABLE IF NOT EXISTS vet_specialties
(
    vet_id       INT NOT NULL,
    specialty_id INT NOT NULL,
    FOREIGN KEY (vet_id) REFERENCES vet (id),
    FOREIGN KEY (specialty_id) REFERENCES specialty (id),
    CONSTRAINT unique_ids UNIQUE (vet_id, specialty_id)
);

CREATE SEQUENCE IF NOT EXISTS pet_type_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS pet_type
(
    id     INT DEFAULT nextval('pet_type_seq'),
    animal VARCHAR(80),
    CONSTRAINT pk_pet_type PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_type_name ON pet_type (animal);

CREATE SEQUENCE IF NOT EXISTS owner_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS owner
(
    id         INT DEFAULT nextval('owner_seq'),
    first_name VARCHAR(30),
    last_name  VARCHAR(30),
    address    VARCHAR(255),
    city       VARCHAR(80),
    telephone  VARCHAR(20),
    CONSTRAINT pk_owner PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_owner_last_name ON owner (last_name);


CREATE SEQUENCE IF NOT EXISTS pet_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS pet
(
    id         INT DEFAULT nextval('pet_seq'),
    name       VARCHAR(30),
    birth_date DATE,
    type_id    INT NOT NULL,
    owner_id   INT NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES owner (id),
    FOREIGN KEY (type_id) REFERENCES pet_type (id),
    CONSTRAINT pk_pet PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_pet_name ON pet (name);

CREATE SEQUENCE IF NOT EXISTS visit_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS visit
(
    id          INT DEFAULT nextval('visit_seq'),
    pet_id      INT NOT NULL,
    visit_date  DATE,
    description VARCHAR(255),
    FOREIGN KEY (pet_id) REFERENCES pet (id),
    CONSTRAINT pk_visit PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS audit_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS audit
(
    id   INT DEFAULT nextval('audit_seq'),
    description VARCHAR(255)
);