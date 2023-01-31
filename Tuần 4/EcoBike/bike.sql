SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ECOBIKE
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ECOBIKE`;

-- -----------------------------------------------------
-- Schema ECOBIKE
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ECOBIKE` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ECOBIKE` ;


-- -----------------------------------------------------
-- Table `ECOBIKE`.`Dock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ECOBIKE`.`Dock` ;
CREATE TABLE IF NOT EXISTS `ECOBIKE`.`Dock` (
  `dockID` CHAR(15) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `area` VARCHAR(50) NOT NULL,
  `address` VARCHAR(60) NOT NULL,
  `remainCapacity` INT(15) NOT NULL,
  `maximumCapacity` INT(15) NOT NULL,
  PRIMARY KEY (`DockID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `ECOBIKE`.`Bike`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ECOBIKE`.`Bike` ;
CREATE TABLE IF NOT EXISTS `ECOBIKE`.`Bike` (
  `bikeID` INT(15) NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `numSaddle` INT(15) NOT NULL,
  `numPedal` INT(15) NOT NULL,
  `numSeat` INT(15) NOT NULL,
  `remainBattery` float(15) NULL,
  `maxTime` FLOAT(15) NULL,
  `inUse` INT(15) NOT NULL,
  `value` INT(15) NOT NULL,
  `licensePlate` VARCHAR(50) NOT NULL,
  `DockID` CHAR(15),
  PRIMARY KEY (`bikeID`),
  CONSTRAINT `DockID`
    FOREIGN KEY (`DockID`)
    REFERENCES `ECOBIKE`.`Dock` (`DockID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ECOBIKE`.`RentBikeInvoice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ECOBIKE`.`RentBikeInvoice` ;

CREATE TABLE IF NOT EXISTS `ECOBIKE`.`RentBikeInvoice` (
  `rentalCode` VARCHAR(50) NOT NULL,
  `bikeID` INT(15) NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `rentBikeCost` INT(15) NOT NULL,
  `owner` VARCHAR(50) NOT NULL,
  `rentTime` VARCHAR(50) NOT NULL,
  `returnTime` VARCHAR(50) NULL DEFAULT NULL,
  `deposit` INT(15) NULL DEFAULT NULL,
  PRIMARY KEY (`rentalCode`),
  CONSTRAINT `bikeID`
    FOREIGN KEY (`bikeID`)
    REFERENCES `ECOBIKE`.`Bike` (`bikeID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `ECOBIKE`.`PaymentTransaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ECOBIKE`.`PaymentTransaction` ;

CREATE TABLE IF NOT EXISTS `ECOBIKE`.`PaymentTransaction` (
  `ID` INT NOT NULL,
  `owner` VARCHAR(50) NOT NULL,
  `transactionContent` VARCHAR(50) NOT NULL,
  `rentalCode` VARCHAR(50) NOT NULL,
  `cardCode` VARCHAR(50) NOT NULL,
  `amount` INT(15) NOT NULL,
  `time` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `rentalCode`
    FOREIGN KEY (`rentalCode`)
    REFERENCES `ECOBIKE`.`RentBikeInvoice` (`rentalCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `dock` VALUES
('DD','Đống Đa','Hà Nội','361 Nguyễn Trãi, Phường Khương Trung, Quận Đống Đa',27,30),
('HM','Hoàng Mai','Hà Nội','13 Lĩnh Nam, Phường Vĩnh Hưng, Quận Hoàng Mai',27,30),
('HBT','Hai Bà Trưng','Hà Nội','1 Đại Cồ Việt, Phường Bách Khoa, Quận Hai Bà Trưng',27,30);

INSERT INTO `bike` VALUES
(20230000,'single-normal',1,2,1,null,null,0,1000000,'DD-001','DD'),
(20230001,'double-normal',2,4,1,null,null,0,1375000,'DD-002','DD'),
(20230002,'single-electric',1,0,1,92.1,60,0,1750000,'DD-003','DD'),
(20230003,'single-normal',1,2,1,null,null,0,1000000,'HM-001','HM'),
(20230004,'double-normal',2,4,1,null,null,0,1375000,'HM-002','HM'),
(20230005,'single-electric',1,0,1,92.1,60,0,1750000,'HM-003','HM'),
(20230006,'single-normal',1,2,1,null,null,0,1000000,'HBT-001','HBT'),
(20230007,'double-normal',2,4,1,null,null,0,1375000,'HBT-002','HBT'),
(20230008,'single-electric',1,0,1,92.1,60,0,1750000,'HBT-003','HBT');



