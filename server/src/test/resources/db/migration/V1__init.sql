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
  comments varchar(255) DEFAULT NULL
);

CREATE TABLE vaccine (
  vaccine_id INTEGER NOT NULL IDENTITY,
  name varchar(100) NOT NULL,
  description varchar(255) NOT NULL
);

CREATE TABLE treatment (
  treatment_id INTEGER NOT NULL IDENTITY,
  fk_doctor INTEGER NOT NULL,
  fk_client INTEGER NOT NULL,
  fk_animal INTEGER NOT NULL,
  comments varchar(255) DEFAULT NULL,
  date DATETIME NOT NULL
);

CREATE TABLE vaccine_treatment (
  vaccine_treatment_id INTEGER NOT NULL IDENTITY,
  fk_treatment INTEGER NOT NULL,
  fk_vaccine INTEGER NOT NULL,
  quantity INTEGER DEFAULT NULL
);

CREATE TABLE schedule_vaccine (
  schedule_vaccine_id INTEGER NOT NULL IDENTITY,
  fk_vaccine INTEGER NOT NULL,
  fk_client INTEGER NOT NULL,
  fk_animal INTEGER NOT NULL,
  date DATETIME NOT NULL,
  notified bit(1) NOT NULL
);
