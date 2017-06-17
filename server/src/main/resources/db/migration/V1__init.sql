-- Create syntax for TABLE 'user'
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_admin` bit(1) DEFAULT NULL,
  `user_login` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_LOGIN` (`user_login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `person` (
  `person_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` varchar(2) NOT NULL,
  `fk_user` bigint(20) NOT NULL,
  PRIMARY KEY (`person_id`),
  CONSTRAINT `FK_PERSON_TO_USER` FOREIGN KEY (`fk_user`)
  REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` (`user_id`, `user_admin`, `user_login`, `user_name`, `user_password`)
VALUES
(1, true, 'admin', 'Administrador', 'admin');

INSERT INTO `person` (`person_id`, `name`, `type`, `fk_user`)
VALUES
(1, 'Veterinario 1', 'D', 1);
