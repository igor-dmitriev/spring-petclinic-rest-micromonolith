INSERT INTO vet(first_name, last_name) VALUES ('James', 'Carter') ON CONFLICT DO NOTHING;
INSERT INTO vet(first_name, last_name) VALUES ('Helen', 'Leary') ON CONFLICT DO NOTHING;
INSERT INTO vet(first_name, last_name) VALUES ('Linda', 'Douglas') ON CONFLICT DO NOTHING;
INSERT INTO vet(first_name, last_name) VALUES ('Rafael', 'Ortega') ON CONFLICT DO NOTHING;
INSERT INTO vet(first_name, last_name) VALUES ('Henry', 'Stevens') ON CONFLICT DO NOTHING;
INSERT INTO vet(first_name, last_name) VALUES ('Sharon', 'Jenkins') ON CONFLICT DO NOTHING;

INSERT INTO specialty(name) VALUES ('radiology') ON CONFLICT DO NOTHING;
INSERT INTO specialty(name) VALUES ('surgery') ON CONFLICT DO NOTHING;
INSERT INTO specialty(name) VALUES ('dentistry') ON CONFLICT DO NOTHING;

INSERT INTO vet_specialties VALUES (2, 1) ON CONFLICT DO NOTHING;
INSERT INTO vet_specialties VALUES (3, 2) ON CONFLICT DO NOTHING;
INSERT INTO vet_specialties VALUES (3, 3) ON CONFLICT DO NOTHING;
INSERT INTO vet_specialties VALUES (4, 2) ON CONFLICT DO NOTHING;
INSERT INTO vet_specialties VALUES (5, 1) ON CONFLICT DO NOTHING;

INSERT INTO pet_type(animal) VALUES ('CAT') ON CONFLICT DO NOTHING;
INSERT INTO pet_type(animal) VALUES ('DOG') ON CONFLICT DO NOTHING;
INSERT INTO pet_type(animal) VALUES ('LIZARD') ON CONFLICT DO NOTHING;
INSERT INTO pet_type(animal) VALUES ('SNAKE') ON CONFLICT DO NOTHING;
INSERT INTO pet_type(animal) VALUES ('BIRD') ON CONFLICT DO NOTHING;
INSERT INTO pet_type(animal) VALUES ('HAMSTER') ON CONFLICT DO NOTHING;

INSERT INTO owner(first_name, last_name, address, city, telephone) VALUES ('George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023') ON CONFLICT DO NOTHING;
INSERT INTO owner(first_name, last_name, address, city, telephone) VALUES ('Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749') ON CONFLICT DO NOTHING;
INSERT INTO owner(first_name, last_name, address, city, telephone) VALUES ('Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763') ON CONFLICT DO NOTHING;
INSERT INTO owner(first_name, last_name, address, city, telephone) VALUES ('Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198') ON CONFLICT DO NOTHING;
INSERT INTO owner(first_name, last_name, address, city, telephone) VALUES ('Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765') ON CONFLICT DO NOTHING;
INSERT INTO owner(first_name, last_name, address, city, telephone) VALUES ('Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654') ON CONFLICT DO NOTHING;
INSERT INTO owner(first_name, last_name, address, city, telephone) VALUES ('Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387') ON CONFLICT DO NOTHING;
INSERT INTO owner(first_name, last_name, address, city, telephone) VALUES ('Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683') ON CONFLICT DO NOTHING;
INSERT INTO owner(first_name, last_name, address, city, telephone) VALUES ('David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435') ON CONFLICT DO NOTHING;
INSERT INTO owner(first_name, last_name, address, city, telephone) VALUES ('Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487') ON CONFLICT DO NOTHING;

INSERT INTO pet(name, birth_date, type_id, owner_id) VALUES ('Leo', '2000-09-07', 1, 1) ON CONFLICT DO NOTHING;
INSERT INTO pet(name, birth_date, type_id, owner_id) VALUES ('Basil', '2002-08-06', 6, 2) ON CONFLICT DO NOTHING;
INSERT INTO pet(name, birth_date, type_id, owner_id) VALUES ('Rosy', '2001-04-17', 2, 3) ON CONFLICT DO NOTHING;
INSERT INTO pet(name, birth_date, type_id, owner_id) VALUES ('Jewel', '2000-03-07', 2, 3) ON CONFLICT DO NOTHING;
INSERT INTO pet(name, birth_date, type_id, owner_id) VALUES ('Iggy', '2000-11-30', 3, 4) ON CONFLICT DO NOTHING;
INSERT INTO pet(name, birth_date, type_id, owner_id) VALUES ('George', '2000-01-20', 4, 5) ON CONFLICT DO NOTHING;
INSERT INTO pet(name, birth_date, type_id, owner_id) VALUES ('Samantha', '1995-09-04', 1, 6) ON CONFLICT DO NOTHING;
INSERT INTO pet(name, birth_date, type_id, owner_id) VALUES ('Max', '1995-09-04', 1, 6) ON CONFLICT DO NOTHING;
INSERT INTO pet(name, birth_date, type_id, owner_id) VALUES ('Lucky', '1999-08-06', 5, 7) ON CONFLICT DO NOTHING;
INSERT INTO pet(name, birth_date, type_id, owner_id) VALUES ('Mulligan', '1997-02-24', 2, 8) ON CONFLICT DO NOTHING;
INSERT INTO pet(name, birth_date, type_id, owner_id) VALUES ('Freddy', '2000-03-09', 5, 9) ON CONFLICT DO NOTHING;
INSERT INTO pet(name, birth_date, type_id, owner_id) VALUES ('Lucky', '2000-06-24', 2, 10) ON CONFLICT DO NOTHING;
INSERT INTO pet(name, birth_date, type_id, owner_id) VALUES ('Sly', '2002-06-08', 1, 10) ON CONFLICT DO NOTHING;

INSERT INTO visit(pet_id, visit_date, description) VALUES (7, '2010-03-04', 'rabies shot') ON CONFLICT DO NOTHING;
INSERT INTO visit(pet_id, visit_date, description) VALUES (8, '2011-03-04', 'rabies shot') ON CONFLICT DO NOTHING;
INSERT INTO visit(pet_id, visit_date, description) VALUES (8, '2009-06-04', 'neutered') ON CONFLICT DO NOTHING;
INSERT INTO visit(pet_id, visit_date, description) VALUES (7, '2008-09-04', 'spayed') ON CONFLICT DO NOTHING;