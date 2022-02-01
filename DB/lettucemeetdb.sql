-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema lettucemeetdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `lettucemeetdb` ;

-- -----------------------------------------------------
-- Schema lettucemeetdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lettucemeetdb` DEFAULT CHARACTER SET utf8 ;
USE `lettucemeetdb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street1` VARCHAR(75) NOT NULL,
  `street2` VARCHAR(75) NULL,
  `city` VARCHAR(100) NOT NULL,
  `state` VARCHAR(10) NOT NULL,
  `zip` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `enabled` TINYINT NULL,
  `role` VARCHAR(75) NULL,
  `first_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(100) NULL,
  `business_name` VARCHAR(150) NULL,
  `image_url` TEXT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `type` ;

CREATE TABLE IF NOT EXISTS `type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product` ;

CREATE TABLE IF NOT EXISTS `product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(500) NULL,
  `organic` TINYINT NULL,
  `unit_price` DOUBLE NULL,
  `image_url` TEXT NULL,
  `quantity` INT NULL,
  `available_date` DATE NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_type_idx` (`type_id` ASC),
  INDEX `fk_product_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_product_type`
    FOREIGN KEY (`type_id`)
    REFERENCES `type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `market`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `market` ;

CREATE TABLE IF NOT EXISTS `market` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(500) NULL,
  `image_url` TEXT NULL,
  `market_date` DATE NOT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` INT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_market_user1_idx` (`user_id` ASC),
  INDEX `fk_market_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_market_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_market_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `seller_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `seller_rating` ;

CREATE TABLE IF NOT EXISTS `seller_rating` (
  `user_id` INT NOT NULL,
  `seller_id` INT NOT NULL,
  `seller_rating` INT NOT NULL,
  `comment` TEXT NULL,
  PRIMARY KEY (`user_id`, `seller_id`),
  INDEX `fk_rating_user1_idx` (`user_id` ASC),
  INDEX `fk_rating_user2_idx` (`seller_id` ASC),
  CONSTRAINT `fk_rating_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rating_user2`
    FOREIGN KEY (`seller_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `product_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product_comment` ;

CREATE TABLE IF NOT EXISTS `product_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NOT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comment_id` INT NULL,
  `product_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_comment1_idx` (`comment_id` ASC),
  INDEX `fk_product_comment_product1_idx` (`product_id` ASC),
  INDEX `fk_product_comment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_comment_comment1`
    FOREIGN KEY (`comment_id`)
    REFERENCES `product_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_comment_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `market_vendor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `market_vendor` ;

CREATE TABLE IF NOT EXISTS `market_vendor` (
  `user_id` INT NOT NULL,
  `market_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `market_id`),
  INDEX `fk_user_has_event_event1_idx` (`market_id` ASC),
  INDEX `fk_user_has_event_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_event_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_event_event1`
    FOREIGN KEY (`market_id`)
    REFERENCES `market` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `market_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `market_product` ;

CREATE TABLE IF NOT EXISTS `market_product` (
  `product_id` INT NOT NULL,
  `market_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `market_id`),
  INDEX `fk_product_has_event_event1_idx` (`market_id` ASC),
  INDEX `fk_product_has_event_product1_idx` (`product_id` ASC),
  CONSTRAINT `fk_product_has_event_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_has_event_event1`
    FOREIGN KEY (`market_id`)
    REFERENCES `market` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `market_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `market_comment` ;

CREATE TABLE IF NOT EXISTS `market_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NOT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comment_id` INT NULL,
  `user_id` INT NOT NULL,
  `market_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_comment1_idx` (`comment_id` ASC),
  INDEX `fk_market_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_market_comment_market1_idx` (`market_id` ASC),
  CONSTRAINT `fk_comment_comment10`
    FOREIGN KEY (`comment_id`)
    REFERENCES `market_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_market_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_market_comment_market1`
    FOREIGN KEY (`market_id`)
    REFERENCES `market` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `product_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product_rating` ;

CREATE TABLE IF NOT EXISTS `product_rating` (
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `product_rating` INT NOT NULL,
  `comment` TEXT NULL,
  PRIMARY KEY (`user_id`, `product_id`),
  INDEX `fk_user_has_product1_product1_idx` (`product_id` ASC),
  INDEX `fk_user_has_product1_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_product1_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_product1_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `market_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `market_rating` ;

CREATE TABLE IF NOT EXISTS `market_rating` (
  `user_id` INT NOT NULL,
  `market_id` INT NOT NULL,
  `market_rating` INT NOT NULL,
  `comment` TEXT NULL,
  PRIMARY KEY (`user_id`, `market_id`),
  INDEX `fk_user_has_event_event2_idx` (`market_id` ASC),
  INDEX `fk_user_has_event_user2_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_event_user2`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_event_event2`
    FOREIGN KEY (`market_id`)
    REFERENCES `market` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

SET SQL_MODE = '';
DROP USER IF EXISTS farmer@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'farmer'@'localhost' IDENTIFIED BY 'farmer';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'farmer'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `lettucemeetdb`;
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (1, '1234 Real Street', NULL, 'Hong Kong', 'FR', '80121');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (2, '6930 Donald Lane', '', 'Lexington', 'KY', '40591');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (3, '48 Grover Street', '', 'Bronx', 'NY', '10474');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (4, '672 Dahle Hill', '', 'Tuscaloosa', 'AL', '35405');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (5, '927 Arizona Point', '', 'Los Angeles', 'CA', '90076');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (6, '09838 Carey Lane', '', 'Syracuse', 'NY', '13205');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (7, '9 Roxbury Lane', '', 'Bellevue', 'WA', '98008');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (8, '64087 Elgar Place', '', 'Hartford', 'CT', '06160');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (9, '8 Elmside Trail', '', 'Salt Lake City', 'UT', '84140');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (10, '66 Kensington Road', '', 'Van Nuys', 'CA', '91411');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (11, '8 Fairview Circle', '', 'Oakland', 'CA', '94611');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (12, '31 Melby Avenue', '', 'Milwaukee', 'WI', '53205');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (13, '6 Vahlen Alley', '', 'Portland', 'OR', '97229');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (14, '99 Anniversary Way', '', 'Durham', 'NC', '27705');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (15, '13 Hovde Street', '', 'Cincinnati', 'OH', '45264');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (16, '8182 David Way', '', 'Johnstown', 'PA', '15906');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (17, '949 Hoepker Alley', '', 'Louisville', 'KY', '40280');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (18, '7 Fordem Trail', '', 'Albany', 'NY', '12247');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (19, '86 Hoepker Way', '', 'Norman', 'OK', '73071');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (20, '14 Lerdahl Pass', '', 'Huntsville', 'TX', '77343');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (21, '95 Southridge Center', '', 'Dayton', 'OH', '45408');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (22, '87 Trailsway Lane', '', 'Cleveland', 'OH', '44125');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (23, '3589 Eastlawn Terrace', '', 'Allentown', 'PA', '18105');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (24, '9272 Superior Hill', '', 'Youngstown', 'OH', '44511');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (25, '78 Del Sol Alley', '', 'Jamaica', 'NY', '11447');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (26, '3 Artisan Park', '', 'Mobile', 'AL', '36641');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (27, '31870 Tennessee Terrace', '', 'Independence', 'MO', '64054');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (28, '5 Pearson Park', '', 'Raleigh', 'NC', '27658');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (29, '0061 Erie Way', '', 'Sacramento', 'CA', '95833');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (30, '72996 Glacier Hill Avenue', '', 'Springfield', 'MA', '01129');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (31, '303 Clove Hill', '', 'Shreveport', 'LA', '71166');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (32, '41059 Weeping Birch Avenue', '', 'Decatur', 'IL', '62525');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (33, '97388 Elgar Road', '', 'Shreveport', 'LA', '71166');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (34, '28886 Monument Plaza', '', 'Sparks', 'NV', '89436');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (35, '10 Ludington Pass', '', 'Arlington', 'VA', '22212');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (36, '768 Hooker Trail', '', 'Terre Haute', 'IN', '47805');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (37, '3 Forest Dale Place', '', 'Pompano Beach', 'FL', '33064');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (38, '99 Swallow Road', '', 'Des Moines', 'IA', '50936');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (39, '2 Fordem Pass', '', 'Pittsburgh', 'PA', '15255');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (40, '8666 Buell Terrace', '', 'Mobile', 'AL', '36622');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (41, '7355 Redwing Plaza', '', 'Washington', 'DC', '20231');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (42, '35 Mallard Plaza', '', 'Albany', 'NY', '12210');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (43, '51257 Prairie Rose Terrace', '', 'San Francisco', 'CA', '94132');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (44, '95358 Hayes Trail', '', 'El Paso', 'TX', '79989');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (45, '45467 Mifflin Lane', '', 'Minneapolis', 'MN', '55448');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (46, '45261 Anthes Place', '', 'Fort Lauderdale', 'FL', '33336');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (47, '68136 Scott Road', '', 'Birmingham', 'AL', '35225');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (48, '1135 Sutteridge Way', '', 'Cleveland', 'OH', '44197');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (49, '98 Blaine Park', '', 'Bakersfield', 'CA', '93386');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (50, '0355 Ramsey Plaza', '', 'Colorado Springs', 'CO', '80920');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (51, '8 Dennis Avenue', '', 'El Paso', 'TX', '79911');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (52, '75 Sunfield Way', '', 'Bethesda', 'MD', '20892');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (53, '78 Hoffman Trail', '', 'Brooklyn', 'NY', '11215');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (54, '05 Northland Way', '', 'Norcross', 'GA', '30092');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (55, '7550 Charing Cross Parkway', '', 'Omaha', 'NE', '68105');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (56, '00 Buell Terrace', '', 'New York City', 'NY', '10203');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (57, '3 Portage Crossing', '', 'Aiken', 'SC', '29805');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (58, '915 Sachs Court', '', 'New York City', 'NY', '10165');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (59, '4378 Boyd Avenue', '', 'San Francisco', 'CA', '94132');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (60, '59575 Granby Parkway', '', 'Portland', 'OR', '97255');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (61, '3 Novick Place', '', 'Atlanta', 'GA', '30316');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (62, '91 Miller Drive', '', 'Fort Worth', 'TX', '76162');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (63, '6 Saint Paul Center', '', 'Vero Beach', 'FL', '32969');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (64, '641 Brown Point', '', 'Boulder', 'CO', '80305');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (65, '64 Eliot Parkway', '', 'Virginia Beach', 'VA', '23459');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (66, '3 Pierstorff Court', '', 'El Paso', 'TX', '79955');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (67, '52 Oxford Lane', '', 'Trenton', 'NJ', '08695');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (68, '77352 Pine View Place', '', 'Grand Rapids', 'MI', '49544');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (69, '5 Butternut Circle', '', 'New York City', 'NY', '10034');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (70, '5 Daystar Road', '', 'Los Angeles', 'CA', '90015');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (71, '5156 Fisk Alley', '', 'New Orleans', 'LA', '70142');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (72, '4 Crownhardt Court', '', 'Canton', 'OH', '44705');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (73, '2447 Bayside Pass', '', 'Naples', 'FL', '34114');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (74, '13 Buhler Pass', '', 'Indianapolis', 'IN', '46216');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (75, '220 Kingsford Trail', '', 'Mobile', 'AL', '36616');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (76, '9 Vera Street', '', 'Newton', 'MA', '02458');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (77, '295 Spohn Avenue', '', 'Savannah', 'GA', '31422');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (78, '1 Westerfield Circle', '', 'Corpus Christi', 'TX', '78470');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (79, '36 Milwaukee Crossing', '', 'Kansas City', 'MO', '64187');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (80, '751 Mariners Cove Junction', '', 'Wichita', 'KS', '67215');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (81, '98 Susan Lane', '', 'Ridgely', 'MD', '21684');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (82, '83661 Bluejay Road', '', 'Amarillo', 'TX', '79105');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (83, '55406 Westerfield Alley', '', 'Albuquerque', 'NM', '87105');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (84, '1543 Golden Leaf Avenue', '', 'Jamaica', 'NY', '11436');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (85, '67 Talisman Place', '', 'Conroe', 'TX', '77305');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (86, '2 Warbler Point', '', 'Los Angeles', 'CA', '90010');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (87, '73402 Hoepker Crossing', '', 'El Paso', 'TX', '79911');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (88, '4 Hayes Avenue', '', 'Charleston', 'SC', '29424');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (89, '5 Killdeer Terrace', '', 'Toledo', 'OH', '43666');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (90, '493 Main Crossing', '', 'Whittier', 'CA', '90610');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (91, '9 Muir Circle', '', 'El Paso', 'TX', '88546');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (92, '08667 Grayhawk Way', '', 'Savannah', 'GA', '31422');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (93, '98 Manitowish Drive', '', 'Wichita', 'KS', '67230');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (94, '461 Bellgrove Pass', '', 'Arlington', 'TX', '76004');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (95, '8598 Prairie Rose Street', '', 'Idaho Falls', 'ID', '83405');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (96, '035 Hanson Terrace', '', 'New Orleans', 'LA', '70142');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (97, '5 Browning Center', '', 'Pittsburgh', 'PA', '15274');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (98, '3 Monica Hill', '', 'Largo', 'FL', '34643');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (99, '153 Sloan Crossing', '', 'Saint Petersburg', 'FL', '33710');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (100, '814 Bunting Street', '', 'Winston Salem', 'NC', '27116');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (101, '82448 Village Green Lane', '', 'Phoenix', 'AZ', '85045');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (102, '25551 Havey Court', '', 'Henderson', 'NV', '89074');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (103, '92 Golf Parkway', '', 'San Jose', 'CA', '95133');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (104, '857 Comanche Junction', '', 'Sacramento', 'CA', '94245');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (105, '836 Green Place', '', 'Minneapolis', 'MN', '55480');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (106, '8818 Del Sol Hill', '', 'Montgomery', 'AL', '36114');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (107, '0946 Hoepker Point', '', 'Des Moines', 'IA', '50335');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (108, '7 Daystar Drive', '', 'Wichita', 'KS', '67215');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (109, '5 North Trail', '', 'Bronx', 'NY', '10459');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (110, '1 Forest Road', '', 'Peoria', 'IL', '61605');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (111, '85664 Columbus Point', '', 'Jefferson City', 'MO', '65105');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (112, '25733 Cottonwood Place', '', 'Kansas City', 'MO', '64193');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (113, '8 Florence Center', '', 'Huntington', 'WV', '25711');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (114, '8770 Cherokee Lane', '', 'Miami', 'FL', '33129');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (115, '215 Sheridan Parkway', '', 'Washington', 'DC', '20420');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (116, '74 Kim Place', '', 'Washington', 'DC', '20337');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (117, '84931 Westridge Hill', '', 'Wichita', 'KS', '67205');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (118, '46 Almo Court', '', 'New York City', 'NY', '10165');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (119, '2 Summerview Lane', '', 'Pasadena', 'CA', '91117');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (120, '349 Vermont Place', '', 'Peoria', 'IL', '61640');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (121, '2 Melrose Terrace', '', 'Denver', 'CO', '80217');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (122, '197 Oxford Avenue', '', 'Des Moines', 'IA', '50393');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (123, '96164 Summit Alley', '', 'Knoxville', 'TN', '37939');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (124, '81 Badeau Crossing', '', 'Pensacola', 'FL', '32520');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (125, '17926 Sauthoff Crossing', '', 'Charlotte', 'NC', '28235');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (126, '47042 Kropf Park', '', 'New York City', 'NY', '10099');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (127, '0441 Southridge Junction', '', 'Maple Plain', 'MN', '55572');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (128, '4325 Garrison Park', '', 'San Francisco', 'CA', '94132');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (129, '385 Upham Park', '', 'Pueblo', 'CO', '81015');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (130, '7 Kipling Alley', '', 'Carson City', 'NV', '89706');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (131, '2 Blaine Plaza', '', 'Sioux Falls', 'SD', '57105');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (132, '8854 Crowley Junction', '', 'Honolulu', 'HI', '96825');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (133, '42 Lake View Trail', '', 'Rockford', 'IL', '61110');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (134, '728 Kingsford Crossing', '', 'San Antonio', 'TX', '78250');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (135, '10 Hayes Junction', '', 'Sioux City', 'IA', '51110');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (136, '7022 Lillian Hill', '', 'Syracuse', 'NY', '13224');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (137, '8672 Onsgard Drive', '', 'Dallas', 'TX', '75241');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (138, '250 Village Green Trail', '', 'Boston', 'MA', '02298');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (139, '2489 Sutherland Way', '', 'Bradenton', 'FL', '34210');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (140, '0098 Laurel Circle', '', 'Pittsburgh', 'PA', '15274');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (141, '5 Susan Road', '', 'Flint', 'MI', '48505');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (142, '6498 Homewood Center', '', 'Montgomery', 'AL', '36195');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (143, '962 Green Ridge Place', '', 'Charlotte', 'NC', '28215');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (144, '1715 Prentice Court', '', 'Oklahoma City', 'OK', '73114');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (145, '95315 Corry Drive', '', 'Albany', 'NY', '12242');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (146, '06 Butternut Center', '', 'New York City', 'NY', '10170');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (147, '02621 Forest Avenue', '', 'White Plains', 'NY', '10606');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (148, '6 Morning Terrace', '', 'Wilmington', 'DE', '19897');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (149, '785 Kings Hill', '', 'Austin', 'TX', '78715');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (150, '178 Hanson Alley', '', 'Denver', 'CO', '80235');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (151, '20778 Sommers Pass', '', 'San Diego', 'CA', '92137');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (152, '05 Emmet Terrace', '', 'Sacramento', 'CA', '95833');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (153, '46 4th Trail', '', 'Billings', 'MT', '59112');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (154, '795 Grasskamp Court', '', 'San Francisco', 'CA', '94132');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (155, '1334 Bellgrove Trail', '', 'Chicago', 'IL', '60669');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (156, '8976 Mayer Court', '', 'High Point', 'NC', '27264');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (157, '2 Tomscot Alley', '', 'Erie', 'PA', '16522');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (158, '5929 Lakewood Gardens Place', '', 'Pasadena', 'CA', '91103');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (159, '65 Goodland Parkway', '', 'Alhambra', 'CA', '91841');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (160, '6 Dakota Drive', '', 'Bethesda', 'MD', '20892');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (161, '9522 Farmco Drive', '', 'Lubbock', 'TX', '79452');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (162, '96796 Moland Trail', '', 'Dallas', 'TX', '75205');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (163, '481 Raven Court', '', 'Arlington', 'VA', '22234');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (164, '66 Farmco Street', '', 'Sacramento', 'CA', '95838');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (165, '1338 Mcguire Road', '', 'Whittier', 'CA', '90610');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (166, '594 Mesta Park', '', 'Albuquerque', 'NM', '87195');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (167, '6055 Katie Crossing', '', 'Fort Wayne', 'IN', '46867');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (168, '96800 Kennedy Alley', '', 'Wichita Falls', 'TX', '76310');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (169, '15 Vidon Avenue', '', 'Trenton', 'NJ', '08638');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (170, '108 Gale Place', '', 'Raleigh', 'NC', '27610');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (171, '02866 4th Point', '', 'Baltimore', 'MD', '21282');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (172, '1776 Little Fleur Alley', '', 'Los Angeles', 'CA', '90030');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (173, '9343 Reindahl Plaza', '', 'Kansas City', 'MO', '64153');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (174, '611 Delladonna Hill', '', 'Fairfield', 'CT', '06825');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (175, '681 Park Meadow Point', '', 'Savannah', 'GA', '31416');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (176, '4848 Chinook Plaza', '', 'Roanoke', 'VA', '24048');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (177, '98 Helena Pass', '', 'Charleston', 'SC', '29416');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (178, '387 Morrow Drive', '', 'Sioux City', 'IA', '51110');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (179, '28155 Novick Center', '', 'Duluth', 'GA', '30096');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (180, '1 Grasskamp Pass', '', 'Charlotte', 'NC', '28220');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (181, '7396 Ridge Oak Lane', '', 'Apache Junction', 'AZ', '85219');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (182, '124 Longview Place', '', 'Pasadena', 'CA', '91131');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (183, '1334 Hudson Alley', '', 'Evansville', 'IN', '47712');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (184, '932 Old Gate Way', '', 'Charlotte', 'NC', '28242');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (185, '21 Briar Crest Alley', '', 'Sacramento', 'CA', '94273');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (186, '152 Eliot Alley', '', 'El Paso', 'TX', '79916');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (187, '294 Upham Parkway', '', 'Fort Wayne', 'IN', '46867');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (188, '53 Morningstar Street', '', 'Charlotte', 'NC', '28235');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (189, '3462 Shasta Trail', '', 'Louisville', 'KY', '40205');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (190, '2 West Place', '', 'Houston', 'TX', '77218');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (191, '40 Dovetail Point', '', 'Naples', 'FL', '33961');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (192, '22466 Portage Way', '', 'Lima', 'OH', '45807');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (193, '92494 Lukken Alley', '', 'Indianapolis', 'IN', '46247');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (194, '9827 Doe Crossing Center', '', 'Fort Worth', 'TX', '76192');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (195, '9921 Norway Maple Court', '', 'San Antonio', 'TX', '78296');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (196, '615 Knutson Place', '', 'Long Beach', 'CA', '90810');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (197, '1 Hallows Trail', '', 'El Paso', 'TX', '79977');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (198, '4 International Circle', '', 'Fairfield', 'CT', '06825');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (199, '037 American Ash Trail', '', 'Washington', 'DC', '20016');
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `zip`) VALUES (200, '303 Kenwood Pass', '', 'Los Angeles', 'CA', '90071');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `lettucemeetdb`;
INSERT INTO `user` (`id`, `username`, `email`, `password`, `enabled`, `role`, `first_name`, `last_name`, `business_name`, `image_url`, `create_time`, `address_id`) VALUES (1, 'admin', 'admin@admin.com', '$2a$10$51f0HNB9wi4vjp2ghLi7B.u6bJGFGHxnAMRtJPiteJjwzGxGLdJIW', true, 'admin', 'Admin', NULL, NULL, NULL, '2016-02-26 18:23:39', 1);
INSERT INTO `user` (`id`, `username`, `email`, `password`, `enabled`, `role`, `first_name`, `last_name`, `business_name`, `image_url`, `create_time`, `address_id`) VALUES (2, 'sshearn0', 'sshearn0@sakura.ne.jp', '$2a$10$wCmDBZLXzLSWP8GXce6kIOaRr37dC4XtjAQXaXJoHJsOyG7j0YfPW', true, 'user', 'Silvana', 'Sheam', 'Silva\'s Farm', NULL, '2016-02-26 18:23:39', 2);
INSERT INTO `user` (`id`, `username`, `email`, `password`, `enabled`, `role`, `first_name`, `last_name`, `business_name`, `image_url`, `create_time`, `address_id`) VALUES (3, 'kscholling1', 'kscholling@email.com', '$2a$10$wCmDBZLXzLSWP8GXce6kIOaRr37dC4XtjAQXaXJoHJsOyG7j0YfPW', true, 'user', 'Katey', 'Scholing', 'Harvest Moon', NULL, '2018-12-26 23:49:49', 3);
INSERT INTO `user` (`id`, `username`, `email`, `password`, `enabled`, `role`, `first_name`, `last_name`, `business_name`, `image_url`, `create_time`, `address_id`) VALUES (4, 'fbygraves2', 'fbygraves@behance.net', '$2a$10$wCmDBZLXzLSWP8GXce6kIOaRr37dC4XtjAQXaXJoHJsOyG7j0YfPW', true, 'user', 'Fransisco', 'Bygraves', 'Edgepulse', NULL, '2012-12-02 21:03:13', 4);
INSERT INTO `user` (`id`, `username`, `email`, `password`, `enabled`, `role`, `first_name`, `last_name`, `business_name`, `image_url`, `create_time`, `address_id`) VALUES (5, 'mswatten3', 'mswatten3@loc.gov', '$2a$10$wCmDBZLXzLSWP8GXce6kIOaRr37dC4XtjAQXaXJoHJsOyG7j0YfPW', true, 'user', 'Marle-Jeanne', 'Swatten', 'Marie-Jeanne\'s Farm', NULL, '2014-11-09 17:50:46', 5);
INSERT INTO `user` (`id`, `username`, `email`, `password`, `enabled`, `role`, `first_name`, `last_name`, `business_name`, `image_url`, `create_time`, `address_id`) VALUES (6, 'adiboldi4', 'adibold4@mozilla.org', '$2a$10$wCmDBZLXzLSWP8GXce6kIOaRr37dC4XtjAQXaXJoHJsOyG7j0YfPW', true, 'user', 'Alva', 'Di Boldi', 'Boldi Bunch Farms', NULL, '2014-05-09 12:52:44', 6);
INSERT INTO `user` (`id`, `username`, `email`, `password`, `enabled`, `role`, `first_name`, `last_name`, `business_name`, `image_url`, `create_time`, `address_id`) VALUES (7, 'user', 'user@email.com', '$2a$10$wCmDBZLXzLSWP8GXce6kIOaRr37dC4XtjAQXaXJoHJsOyG7j0YfPW', true, 'user', 'Jake', 'Finn', 'Velvet Twilight', NULL, '2019-12-26 23:49:49', 7);

COMMIT;


-- -----------------------------------------------------
-- Data for table `type`
-- -----------------------------------------------------
START TRANSACTION;
USE `lettucemeetdb`;
INSERT INTO `type` (`id`, `name`) VALUES (1, 'produce');
INSERT INTO `type` (`id`, `name`) VALUES (2, 'milk, eggs, meat');
INSERT INTO `type` (`id`, `name`) VALUES (3, 'baked goods');
INSERT INTO `type` (`id`, `name`) VALUES (4, 'flowers, plants, seedlings');
INSERT INTO `type` (`id`, `name`) VALUES (5, 'honey');
INSERT INTO `type` (`id`, `name`) VALUES (6, 'soap & skincare');
INSERT INTO `type` (`id`, `name`) VALUES (7, 'beverages');
INSERT INTO `type` (`id`, `name`) VALUES (8, 'prepared foods');
INSERT INTO `type` (`id`, `name`) VALUES (9, 'arts & crafts');
INSERT INTO `type` (`id`, `name`) VALUES (10, 'other');

COMMIT;


-- -----------------------------------------------------
-- Data for table `product`
-- -----------------------------------------------------
START TRANSACTION;
USE `lettucemeetdb`;
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (1, 'Beef - Texas Style Burger', 'Burger from TEXAS.', true, 3.02, 'https://images.food52.com/LWzCZlCqlD7Yl-9VDWfoHv1zEE0=/2016x1344/filters:format(webp)/133eb19c-b40f-46cb-a8c5-0251afd60969--2014-0715_jalapeno-cheddar-burger-004.jpg', 68, '2022-05-28 03:46:46', '2022-01-19 17:21:19', '2021-10-28 10:24:14', 4, 1);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (2, 'Lamb - Sausage Casings', 'This is a lamb sausage casing.', true, 5.08, 'https://www.waltonsinc.com/media/cache/attachment/filter/product_gallery_main/b6d3b12a2194f276376d682d2e7e6bd1/205/5ea323af8696c677778233.png', 99, '2022-04-20 09:47:22', '2021-07-24 22:02:57', '2021-11-30 05:32:04', 3, 1);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (3, 'Sprouts Dikon', 'Dikon sprouts oh my!', false, 10.29, 'https://www.superfoodevolution.com/images/daikon-sprouts-microgreens.jpg', 88, '2022-05-03 17:59:10', '2021-07-07 15:31:52', '2021-11-10 03:52:28', 5, 2);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (4, 'Salmon - Whole, 4 - 6 Pounds', 'What a giant salmon.', true, 12.26, 'https://thecornishfishmonger.co.uk/media/magefan_blog/how-to_-cook-a-whole-salmon.jpg', 68, '2022-06-12 05:32:36', '2021-09-09 08:14:17', '2021-12-14 06:41:14', 4, 1);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (5, 'Cake - Pancake', 'A cake that tastes like a pancake', false, 23.87, 'https://assets.epicurious.com/photos/54ad650a19925f464b3b5f1f/master/w_1280,c_limit/51196630_pancake-cake_1x1.jpg', 80, '2022-03-12 19:13:13', '2021-09-20 06:00:11', '2021-12-01 15:37:57', 3, 2);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (6, 'Coffee - Colombian, Portioned', 'Coffee from Columbia', true, 5.36, 'https://perfectdailygrind.com/wp-content/uploads/2021/04/Coffee-Bean-Hardness-1.jpg', 56, '2022-06-23 22:22:53', '2021-12-04 00:51:45', '2021-10-16 06:45:49', 5, 3);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (7, 'Beans - Fava Fresh', 'These are beans.', true, 8.37, '', 33, '2022-03-19 08:11:22', '2021-11-26 06:20:10', '2021-11-17 01:04:43', 5, 3);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (8, 'Langers - Ruby Red Grapfruit', 'Grapefruit so red!', false, 18.98, '', 11, '2022-03-25 11:17:39', '2021-11-26 02:25:22', '2022-01-17 06:45:54', 2, 4);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (9, 'Wine - White, Lindemans Bin 95', 'It is a good bottle of wine. Buy it.', false, 20.01, '', 28, '2022-01-25 01:33:24', '2021-12-15 05:08:54', '2021-12-25 05:44:57', 2, 5);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (10, 'Bamboo Shoots - Sliced', 'Bamboo. It’s actually grass', false, 4.35, '', 18, '2022-06-19 23:03:14', '2021-04-05 09:02:41', '2022-01-01 19:43:20', 5, 2);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (11, 'Seedlings - Clamshell', 'These are plants that grow clam shells! Magical', false, 9.10, '', 52, '2022-05-24 10:54:02', '2021-12-15 03:21:27', '2021-10-17 12:25:48', 3, 3);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (12, 'Muffin - Zero Transfat', 'Muffin. No transfat. Simple.', true, 8.75, '', 57, '2022-06-02 19:31:58', '2021-10-23 04:47:10', '2022-01-02 02:03:30', 2, 4);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (13, 'Wine - Sherry Dry Sack, William', 'Sherry Wine.', true, 21.23, '', 34, '2022-02-16 03:18:12', '2021-10-04 11:22:47', '2021-11-23 11:01:40', 4, 4);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (14, 'Chicken - Diced, Cooked', 'Chicken! Diced and of course cooked.', false, 25.49, '', 57, '2022-02-09 14:53:33', '2021-12-17 06:22:14', '2022-01-03 06:15:52', 1, 1);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (15, 'Cleaner - Bleach', 'The best bleach ever made.', true, 12.97, '', 89, '2022-05-20 12:52:05', '2021-10-21 19:34:15', '2021-12-21 12:14:53', 1, 6);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (16, 'Lid - High Heat, Super Clear', 'This lid is extremely hot and extremely clear!', false, 14.24, '', 72, '2022-04-19 19:35:50', '2021-07-07 12:50:20', '2021-11-07 09:31:22', 4, 7);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (17, 'Sauce - Hp', 'Sauce made by HP.', true, 16.55, '', 62, '2022-03-05 05:58:58', '2021-06-15 08:37:57', '2021-11-28 14:42:30', 1, 6);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (18, 'Bread - Italian Roll With Herbs', 'Delicious rolls with herbs', false, 5.66, '', 33, '2022-05-13 18:26:02', '2021-10-05 16:33:15', '2021-11-27 11:36:48', 3, 7);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (19, 'Nut - Cashews, Whole, Raw', 'Cashews straight from the tree.', true, 17.58, '', 6, '2022-04-09 08:40:40', '2021-02-16 14:41:20', '2021-10-04 06:58:36', 4, 3);
INSERT INTO `product` (`id`, `name`, `description`, `organic`, `unit_price`, `image_url`, `quantity`, `available_date`, `create_time`, `update_time`, `type_id`, `user_id`) VALUES (20, 'Fish - Soup Base, Bouillon', 'Make your soup taste like fish with these!', true, 19.74, '', 4, '2022-06-07 19:08:11', '2021-11-16 01:17:01', '2021-11-26 13:42:23', 4, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `market`
-- -----------------------------------------------------
START TRANSACTION;
USE `lettucemeetdb`;
INSERT INTO `market` (`id`, `name`, `description`, `image_url`, `market_date`, `create_time`, `update_time`, `user_id`, `address_id`) VALUES (1, 'Harvest Celebration', 'Pumpkins festivals, do not miss it!!', 'https://www.worldatlas.com/r/w960-q80/upload/e3/44/14/shutterstock-510958279.jpg', '2021-11-11 04:43:52', '2021-09-12 13:18:35', '2021-11-06 10:58:55', 1, 8);
INSERT INTO `market` (`id`, `name`, `description`, `image_url`, `market_date`, `create_time`, `update_time`, `user_id`, `address_id`) VALUES (2, 'Grass Feed Meat', '100% grass feed top round, sirloin ,rib eye, pastured-raised egg.', 'https://steakschool.com/wp-content/uploads/2018/01/Stanbroke30thJune2018-56.jpg', '2021-09-27 08:26:41', '2020-08-05 09:45:39', '2021-05-03 00:26:45', 2, 9);
INSERT INTO `market` (`id`, `name`, `description`, `image_url`, `market_date`, `create_time`, `update_time`, `user_id`, `address_id`) VALUES (3, 'Sweetest Fruit', 'Seasonal sweetest fruits, strawberries, crispy apples, blueberry, dragon fruit.', 'https://orangetop.eu/wp-content/uploads/2020/03/orangetop-fruit-vegetables-groente-export.jpg', '2021-11-30 01:50:43', '2020-12-03 17:24:13', '2021-09-08 20:53:27', 3, 10);
INSERT INTO `market` (`id`, `name`, `description`, `image_url`, `market_date`, `create_time`, `update_time`, `user_id`, `address_id`) VALUES (4, 'Worldwide Vegetables', 'Oversea vegetables, tomatos, potatoes, avocado, spinach.', 'https://globalhealth.washington.edu/sites/default/files/styles/blog_post/public/eating-fruit-and-vegetables2.jpg?itok=K307tlAf', '2021-11-01 03:21:00', '2020-11-28 03:10:48', '2021-10-19 08:17:39', 4, 11);
INSERT INTO `market` (`id`, `name`, `description`, `image_url`, `market_date`, `create_time`, `update_time`, `user_id`, `address_id`) VALUES (5, 'Local Farms Only', 'Support our local farms, directly from locals, eggs, fresh vegetables, grass-feed beef.', 'https://shop.shoprite.com/-/media/fbfb139/images/custom-pages/locally-grown/locally-grown-carousel.ashx', '2021-08-06 11:54:58', '2020-01-16 00:05:08', '2021-07-16 09:32:34', 5, 12);
INSERT INTO `market` (`id`, `name`, `description`, `image_url`, `market_date`, `create_time`, `update_time`, `user_id`, `address_id`) VALUES (6, 'U-pick Market', 'Every-Year’s most favorite event, take as much as you can in one bag!!!!', 'https://catalogue.novascotia.com/ManagedMedia/18100.jpg', '2021-02-07 13:51:24', '2020-06-17 11:48:08', '2021-01-18 10:18:58', 6, 13);
INSERT INTO `market` (`id`, `name`, `description`, `image_url`, `market_date`, `create_time`, `update_time`, `user_id`, `address_id`) VALUES (7, 'Bake-Island', 'Most popular bakery in our town', 'https://www.supermarketnews.com/sites/supermarketnews.com/files/styles/article_featured_retina/public/St._Pierre.png?itok=HhhYeGBa', '2021-06-04 19:26:31', '2020-02-15 10:03:35', '2021-05-30 11:11:56', 7, 14);

COMMIT;


-- -----------------------------------------------------
-- Data for table `seller_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `lettucemeetdb`;
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (1, 2, 4, 'Nice seller, good quality');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (1, 3, 4, 'I will definitely come again');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (1, 4, 3, 'Nicer seller ');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (1, 5, 2, 'Seller is friendly and generous');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (1, 6, 2, 'He is good person and just lack of varieties');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (2, 1, 5, 'I love the seller');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (2, 3, 1, 'Not suggested');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (2, 4, 3, 'Not bad');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (2, 5, 2, 'Not good ');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (2, 6, 3, 'Seller is good');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (3, 2, 2, 'Nice people here');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (3, 1, 4, 'Seller is really really good, fresh and good quality');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (3, 4, 2, 'Quality is good');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (3, 6, 4, 'Definitely will come again');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (3, 7, 1, 'This seller is rude ,though the quality is good');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (4, 1, 1, 'Never come again');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (4, 2, 3, 'Suggested by my friend and is good');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (4, 3, 1, 'Not suggested');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (4, 5, 3, 'I will recommend my friend to come');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (4, 6, 3, 'Nice place, nice people ,fair price');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (5, 2, 2, 'Food is too expensive here');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (5, 3, 3, 'Fresh, organic, vegetables');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (5, 4, 3, 'Nice people here and enough varieties');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (5, 6, 3, 'I love the environment here and ');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (5, 7, 1, 'Not suggested,  too expensive');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (6, 1, 4, 'Quality is good and price is fair');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (6, 2, 2, 'Nice people here');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (6, 3, 5, 'I love the food, the seller and the price is totally acceptable');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (6, 4, 2, 'Price is good, but not as fresh as I thought');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (6, 5, 1, 'Not suggested');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (6, 7, 4, 'Definitely will come again, good quality and fresh products');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (7, 1, 3, 'Nice people here and enough varieties');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (7, 2, 2, 'Not bad, ');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (7, 3, 2, 'Not suggested,  too expensive');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (7, 4, 3, 'Nice place and fair price ,good quality and fresh products');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (7, 5, 1, 'Not suggested');
INSERT INTO `seller_rating` (`user_id`, `seller_id`, `seller_rating`, `comment`) VALUES (7, 6, 4, 'Definitely the best seller I have ever met and good quality');

COMMIT;


-- -----------------------------------------------------
-- Data for table `product_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `lettucemeetdb`;
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (1, 'Do you make this with turkey?', '2022-01-01 20:47:14', '2021-04-22 12:48:23', null, 1, 1);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (2, 'What are these used for?', '2021-08-04 10:56:06', '2021-07-16 10:58:54', null, 2, 2);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (3, 'Do you also sell Daikon fully grown?', '2021-10-13 22:04:17', '2021-05-19 21:46:44', null, 3, 3);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (4, 'Where are these salmon from?', '2021-02-11 17:23:51', '2021-05-11 09:13:28', null, 4, 4);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (5, 'Could I get my cake decorated??', '2021-10-19 03:45:09', '2021-08-30 09:51:55', null, 5, 5);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (6, 'I really love coffeeeeee', '2021-06-05 04:15:24', '2021-09-03 09:22:57', null, 6, 6);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (7, 'What is this', '2021-07-14 02:01:25', '2021-06-08 05:23:36', null, 7, 7);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (8, 'These are nice looking grapefruits, but why does anyone eat these?', '2021-12-26 02:24:58', '2021-06-12 14:01:04', null, 8, 1);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (9, 'Whats the %', '2021-06-10 23:56:19', '2021-03-12 19:32:31', null, 9, 2);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (10, 'Can I buy the whole bamboo?', '2021-05-25 09:44:05', '2021-09-14 20:42:46', null, 10, 3);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (11, 'what exactly is this??? A plant that grows clamshells????', '2022-01-21 05:27:57', '2021-10-13 00:35:09', null, 11, 4);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (12, 'How much protein?', '2021-06-24 02:59:06', '2021-07-31 09:18:23', null, 12, 5);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (13, 'Is this for cooking', '2021-06-30 18:19:33', '2021-12-12 02:12:33', null, 13, 6);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (14, 'Do you dice the bones too?', '2021-11-05 16:38:41', '2021-10-10 16:14:12', null, 14, 7);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (15, 'Organic bleach?', '2021-02-24 22:05:23', '2021-07-08 05:29:46', null, 15, 1);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (16, 'What is this for?', '2021-07-31 09:24:20', '2021-10-29 04:15:01', null, 16, 2);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (17, 'How big is this burger?', '2021-09-01 03:45:58', '2021-06-17 22:34:41', null, 1, 3);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (18, 'Lamb casings are awesome!', '2021-08-06 15:18:35', '2021-03-27 01:47:15', null, 2, 4);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (19, 'Do you have seeds?', '2021-12-09 18:53:57', '2021-03-09 22:34:22', null, 3, 5);
INSERT INTO `product_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `product_id`, `user_id`) VALUES (20, 'Farm raised or wild caught?', '2021-05-05 20:28:38', '2021-06-25 21:02:43', null, 4, 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `market_vendor`
-- -----------------------------------------------------
START TRANSACTION;
USE `lettucemeetdb`;
INSERT INTO `market_vendor` (`user_id`, `market_id`) VALUES (1, 7);
INSERT INTO `market_vendor` (`user_id`, `market_id`) VALUES (2, 6);
INSERT INTO `market_vendor` (`user_id`, `market_id`) VALUES (3, 5);
INSERT INTO `market_vendor` (`user_id`, `market_id`) VALUES (4, 4);
INSERT INTO `market_vendor` (`user_id`, `market_id`) VALUES (5, 3);
INSERT INTO `market_vendor` (`user_id`, `market_id`) VALUES (6, 2);
INSERT INTO `market_vendor` (`user_id`, `market_id`) VALUES (7, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `market_product`
-- -----------------------------------------------------
START TRANSACTION;
USE `lettucemeetdb`;
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (1, 1);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (2, 1);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (3, 1);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (4, 2);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (5, 2);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (6, 2);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (7, 3);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (8, 3);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (9, 3);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (10, 4);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (11, 4);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (12, 4);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (13, 5);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (14, 5);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (15, 5);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (16, 6);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (17, 6);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (18, 6);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (19, 7);
INSERT INTO `market_product` (`product_id`, `market_id`) VALUES (20, 7);

COMMIT;


-- -----------------------------------------------------
-- Data for table `market_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `lettucemeetdb`;
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (1, 'The coordinators relished conversation with the farmers, learning about what they grew and where.', '2021-11-10 07:35:06', '2022-01-11 10:18:47', Null, 6, 1);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (2, 'Vendors sold fresh, shining fruits, vegetables and herbs, wine from family vineyards, and handed over warm loaves of bread. Anyone with enough money and enough to do on a Sunday morning would peruse the tents, trying slices of crisp peaches and bites of juicy smoked sausage, and fill their fisherman net bags with weekly wares.', '2021-11-11 07:35:06', '2022-01-12 10:18:47', Null, 2, 2);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (3, 'For being so early in the season, the tables on either side of the street were heavily laden with produce. I could see English peas, asparagus, arugula, several varieties of chard, kale, rhubarb, radishes', '2021-11-12 07:35:06', '2022-01-13 10:18:47', Null, 7, 3);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (4, 'Gus longed to crunch on a few baby carrots, dreamed of giving them a quick blanch and a dab of butter and parsley. Yum!', '2021-11-13 07:35:06', '2022-01-14 10:18:47', Null, 2, 4);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (5, ' Minivans, SUVs, and cars, many bearing out-of-state plates, filled the lot. Inside the store, freezers contained frozen cherries, apple juice from last season, and pies. ', '2021-11-14 07:35:06', '2022-01-15 10:18:47', 1, 1, 5);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (6, 'A café sold freshly roasted Door County-brand coffee and cherry sodas made with Door County cherry juice.', '2021-11-15 07:35:06', '2022-01-16 10:18:47', 2, 2, 1);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (7, 'The tables at the market always feel a little schizophrenic this time of year, as piles of fat summer tomatoes rub shoulders with apples and knobby winter squash. ', '2021-11-16 07:35:06', '2022-01-17 10:18:47', 3, 1, 6);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (8, 'It all looked so good', '2021-11-17 07:35:06', '2022-01-18 10:18:47', 4, 2, 3);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (9, 'All the vendors were super friendly too', '2021-11-18 07:35:06', '2022-01-19 10:18:47', 1, 3, 4);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (10, 'It rained but the kids had fun ', '2021-11-19 07:35:06', '2022-01-20 10:18:47', 1, 1, 6);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (11, 'Family-friendly and lots of parking', '2021-11-20 07:35:06', '2022-01-21 10:18:47', 1, 2, 7);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (12, 'I feel like there were smaller vendors than last time', '2021-11-21 07:35:06', '2022-01-22 10:18:47', 2, 3, 2);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (13, 'Gotta go for the bacon', '2021-11-22 07:35:06', '2022-01-23 10:18:47', 3, 4, 3);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (14, 'Best green produce in town', '2021-11-23 07:35:06', '2022-01-24 10:18:47', 4, 5, 4);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (15, 'Also not forgot to mention all the free samples! Bring your kids ', '2021-11-24 07:35:06', '2022-01-25 10:18:47', 1, 7, 5);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (16, 'Forgot to mention ATM is just around the corner at the end of the parking lot ', '2021-11-25 07:35:06', '2022-01-26 10:18:47', 2, 2, 7);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (17, 'Parking was hard to find, would recommend street parking and walking ', '2021-11-26 07:35:06', '2022-01-27 10:18:47', 3, 3, 1);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (18, 'I love this market! ', '2021-11-27 07:35:06', '2022-01-28 10:18:47', 4, 4, 3);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (19, 'Try the private owned farms', '2021-11-28 07:35:06', '2022-01-29 10:18:47', 1, 5, 4);
INSERT INTO `market_comment` (`id`, `comment`, `create_time`, `update_time`, `comment_id`, `user_id`, `market_id`) VALUES (20, 'Best Farmers Market in town', '2021-11-29 07:35:06', '2022-01-30 10:18:47', 2, 5, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `product_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `lettucemeetdb`;
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (1, 1, 5, 'This burger is really delicious!');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (2, 2, 4, 'I make the best sausages with these.');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (3, 3, 5, 'Very Sprouty');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (4, 4, 5, 'This salmon is the best and freshest.');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (5, 5, 3, 'Pancake cake!!!!');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (6, 6, 1, 'Plain coffee beans. Boring');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (7, 7, 2, 'Not sure what fava beans are….');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (1, 8, 2, 'I don’t like grapefruits.');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (2, 9, 4, 'This is some good wine.');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (3, 10, 4, 'I always enjoy eating some bamboo.');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (4, 11, 5, 'These are magical. I have never grown my own clamshells before.');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (5, 12, 5, 'A muffin with no transfat? YES PLEASE');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (6, 13, 5, 'Good for cooking');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (7, 14, 2, 'Why is this chicken so expensive?');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (1, 15, 5, 'Organic bleach. Simply amazing.');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (2, 16, 4, 'This lid never cools down. I don’t really understand the point but it’s still hot to this day.');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (3, 17, 1, 'Tastes like printer.');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (4, 18, 5, 'Some delicious rolls here');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (5, 1, 1, 'Not big enough to be called a Texas burger.');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (6, 2, 3, 'Nothing special about these sausage casings.');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (7, 3, 2, 'Why sell the sprouts??');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (1, 4, 2, 'This salmon tasted like fish!');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (2, 5, 5, 'This is the only way to eat cake and pancakes!');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (3, 6, 1, 'It’s not even ground coffee….. Waste of time.');
INSERT INTO `product_rating` (`user_id`, `product_id`, `product_rating`, `comment`) VALUES (4, 7, 5, 'So much fava. Amazing');

COMMIT;


-- -----------------------------------------------------
-- Data for table `market_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `lettucemeetdb`;
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (1, 1, 3, 'Kids always like the pumpkins');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (1, 2, 4, 'The only place I buy the meat for my family');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (1, 3, 3, 'I love the vegetables here, no reason');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (1, 4, 3, 'Many of my friends suggest me to come, really good');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (1, 5, 3, 'Pumpkins are good');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (2, 1, 3, 'Good quality');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (2, 2, 3, 'Sweet and fresh fruit');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (2, 3, 4, 'Not bad');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (2, 4, 2, 'This place’s parking lot is too small');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (2, 6, 2, 'Best breakfast choice');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (2, 7, 5, 'Nice place for fun');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (3, 1, 5, 'Too far away from me');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (3, 2, 3, 'Locals are the best');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (3, 5, 3, 'Pick whatever you want and as much as you can, rally nice');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (3, 6, 4, 'It’s ok, better than nothing');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (3, 7, 2, 'I don’t like it, ');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (4, 1, 1, 'Not too many choices');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (4, 2, 2, 'Fruits are good');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (4, 3, 3, 'I love vegetables');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (4, 4, 5, 'I picked for the whole months vegetables');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (4, 6, 4, 'My mom baked better than people there');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (4, 7, 1, 'Good place for family');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (5, 1, 2, 'Sold out too quickly');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (5, 2, 2, 'Not really fresh on Tuesday');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (5, 4, 3, 'I love local farms');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (5, 5, 5, 'Great place for fun');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (5, 6, 4, 'I go once a week');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (6, 2, 2, 'Kids love the fruits here');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (6, 3, 4, 'Nice place');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (6, 5, 3, 'Good, I love it');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (6, 6, 4, 'Not bad, at least, I found what I want ');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (6, 7, 3, 'Never come again');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (7, 1, 2, 'People there are so nice and the meat is fresh and good quality');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (7, 2, 3, 'Not as sweet as people said');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (7, 3, 2, 'Lack too many vegetables I want');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (7, 5, 3, 'I will definitely come again');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (7, 6, 4, 'I think it is good place');
INSERT INTO `market_rating` (`user_id`, `market_id`, `market_rating`, `comment`) VALUES (7, 7, 3, 'I will recommend all of my friends to come, absolutely the freshest');

COMMIT;

