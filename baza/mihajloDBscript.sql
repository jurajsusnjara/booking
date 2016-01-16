-- MySQL Script generated by MySQL Workbench
-- 12/17/15 20:20:36
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mihajloDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mihajloDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mihajloDB` DEFAULT CHARACTER SET utf8 ;
USE `mihajloDB` ;

-- -----------------------------------------------------
-- Table `mihajloDB`.`Objekt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mihajloDB`.`Objekt` ;

CREATE TABLE IF NOT EXISTS `mihajloDB`.`Objekt` (
  `objektID` INT NOT NULL AUTO_INCREMENT,
  `nazivObjekt` VARCHAR(20) NOT NULL,
  `fotografija` VARCHAR(45),
  PRIMARY KEY (`objektID`))
ENGINE = InnoDB
COMMENT = 'Objekt u kojem se nalaze apartmani (smještajne jedinice)';


-- -----------------------------------------------------
-- Table `mihajloDB`.`OpisApartmana`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mihajloDB`.`OpisApartmana` ;

CREATE TABLE IF NOT EXISTS `mihajloDB`.`OpisApartmana` (
  `opisID` INT NOT NULL AUTO_INCREMENT,
  `naslov` VARCHAR(20),
  `kat` TINYINT(10) NOT NULL,
  `pogled` VARCHAR(45) NOT NULL,
  `minBroj` TINYINT(10) NOT NULL,
  `maxBroj` TINYINT(10) NOT NULL,
  `opis` VARCHAR(10000) NULL,
  PRIMARY KEY (`opisID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mihajloDB`.`Apartman`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mihajloDB`.`Apartman` ;

CREATE TABLE IF NOT EXISTS `mihajloDB`.`Apartman` (
  `apartmanID` INT NOT NULL AUTO_INCREMENT,
  `nazivApartman` VARCHAR(45) NOT NULL,
  `objektID` INT NOT NULL,
  `opisID` INT,
  PRIMARY KEY (`apartmanID`),
  INDEX `objektID_idx` (`objektID` ASC),
  INDEX `opisID_idx` (`opisID` ASC),
  CONSTRAINT `objektID`
    FOREIGN KEY (`objektID`)
    REFERENCES `mihajloDB`.`Objekt` (`objektID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `opisID`
    FOREIGN KEY (`opisID`)
    REFERENCES `mihajloDB`.`OpisApartmana` (`opisID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mihajloDB`.`Fotografija`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mihajloDB`.`Fotografija` ;

CREATE TABLE IF NOT EXISTS `mihajloDB`.`Fotografija` (
  `fotoID` INT NOT NULL AUTO_INCREMENT,
  `fotoDatoteka` VARCHAR(50) NOT NULL,
  `opisID` INT NULL,
  PRIMARY KEY (`fotoID`),
  INDEX `opisID_idx` (`opisID` ASC),
  CONSTRAINT `opisfotoID`
    FOREIGN KEY (`opisID`)
    REFERENCES `mihajloDB`.`OpisApartmana` (`opisID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mihajloDB`.`Adresa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mihajloDB`.`Adresa` ;

CREATE TABLE IF NOT EXISTS `mihajloDB`.`Adresa` (
  `adresaID` INT NOT NULL AUTO_INCREMENT,
  `adresa` VARCHAR(100) NOT NULL,
  `grad` VARCHAR(45) NOT NULL,
  `drzava` VARCHAR(45) NOT NULL,
  `postanskiBroj` INT NOT NULL,
  PRIMARY KEY (`adresaID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mihajloDB`.`Korisnik`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mihajloDB`.`Korisnik` ;

CREATE TABLE IF NOT EXISTS `mihajloDB`.`Korisnik` (
  `korisnikID` VARCHAR(20) NOT NULL,
  `ime` VARCHAR(20) NOT NULL,
  `prezime` VARCHAR(20) NOT NULL,
  `email` VARCHAR(30) NOT NULL,
  `telefon` VARCHAR(20) NULL,
  `datumReg` DATETIME NOT NULL,
  `lozinka` VARCHAR(45) NOT NULL,
  `adresaID` INT NULL,
  `uloga` INT NOT NULL,
  PRIMARY KEY (`korisnikID`),
  INDEX `adresaID_idx` (`adresaID` ASC),
  CONSTRAINT `adresaID`
    FOREIGN KEY (`adresaID`)
    REFERENCES `mihajloDB`.`Adresa` (`adresaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mihajloDB`.`Gost`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mihajloDB`.`Gost` ;

CREATE TABLE IF NOT EXISTS `mihajloDB`.`Gost` (
  `gostID` INT NOT NULL AUTO_INCREMENT,
  `godina0_1` TINYINT(10) NOT NULL,
  `godina2_7` TINYINT(10) NOT NULL,
  `godina8_14` TINYINT(10) NOT NULL,
  `odrasli` TINYINT(10) NOT NULL,
  PRIMARY KEY (`gostID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mihajloDB`.`Rezervacija`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mihajloDB`.`Rezervacija` ;

CREATE TABLE IF NOT EXISTS `mihajloDB`.`Rezervacija` (
  `apartmanID` INT NOT NULL,
  `gostID` INT NOT NULL,
  `korisnikID` VARCHAR(20) NOT NULL,
  `datumRezervacije` DATETIME NOT NULL,
  `rezerviranoOd` DATETIME NOT NULL,
  `rezerviranoDo` DATETIME NOT NULL,
  `parking` TINYINT(1) NOT NULL,
  `internet` TINYINT(1) NOT NULL,
  `satelitskaTV` TINYINT(1) NOT NULL,
  `potvrda` TINYINT(1) NOT NULL,
  PRIMARY KEY (`apartmanID`, `gostID`, `korisnikID`),
  INDEX `gostID_idx` (`gostID` ASC),
  INDEX `korisnikID_idx` (`korisnikID` ASC),
  CONSTRAINT `apartmanID`
    FOREIGN KEY (`apartmanID`)
    REFERENCES `mihajloDB`.`Apartman` (`apartmanID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `gostID`
    FOREIGN KEY (`gostID`)
    REFERENCES `mihajloDB`.`Gost` (`gostID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `korisnikID`
    FOREIGN KEY (`korisnikID`)
    REFERENCES `mihajloDB`.`Korisnik` (`korisnikID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
