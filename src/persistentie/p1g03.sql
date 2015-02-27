-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema p1g03
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema p1g03
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `p1g03` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `p1g03` ;

-- -----------------------------------------------------
-- Table `p1g03`.`speler`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `p1g03`.`Speler` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `gebruikersnaam` VARCHAR(30) NOT NULL,
  `wachtwoord` VARCHAR(60) NOT NULL,
  `naam` VARCHAR(30) NULL COMMENT '	',
  `voornaam` VARCHAR(30) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
