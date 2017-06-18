-- TABLES
CREATE TABLE user (
  user_id INTEGER NOT NULL IDENTITY,
  user_admin bit(1) DEFAULT NULL,
  user_login varchar(255) NOT NULL,
  user_password varchar(255) NOT NULL
);

CREATE TABLE person (
  person_id INTEGER NOT NULL IDENTITY,
  name varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  type varchar(2) NOT NULL,
  fk_user INTEGER NOT NULL
);

CREATE TABLE animal (
  animal_id INTEGER NOT NULL IDENTITY,
  name varchar(100) NOT NULL,
  type varchar(2) NOT NULL,
  comments varchar(255) NOT NULL
);

CREATE TABLE vaccine (
  vaccine_id INTEGER NOT NULL IDENTITY,
  name varchar(100) NOT NULL,
  description varchar(255) NOT NULL
);

-- INSERTS
-- users
INSERT INTO user (user_id, user_admin, user_login, user_password)
VALUES
(1, true, 'admin', 'admin');

-- persons
INSERT INTO person (person_id, name, email, type, fk_user)
VALUES
(1, 'Veterinario 1', 'llacau@gmail.com', 'D', 1);

-- vaccines
INSERT INTO vaccine (vaccine_id, name, description)
VALUES
(1, 'Vaccine 1', 'Description of vaccine one.');
INSERT INTO vaccine (vaccine_id, name, description)
VALUES
(2, 'Vaccine 2', 'Description of vaccine two.');
INSERT INTO vaccine (vaccine_id, name, description)
VALUES
(3, 'Vaccine 3', 'Description of vaccine three.');

-- animals
INSERT INTO animal (animal_id, name, type, comments)
VALUES
(1, 'Dog', 'D', 'Dog is black.');
INSERT INTO animal (animal_id, name, type, comments)
VALUES
(2, 'Cat', 'C', 'Cat is gray.');
INSERT INTO animal (animal_id, name, type, comments)
VALUES
(3, 'Rat', 'R', 'Rat is yellow :)');