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
  `role` VARCHAR(50) NULL,
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
  `comment_id` INT NOT NULL,
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
  `comment_id` INT NOT NULL,
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
