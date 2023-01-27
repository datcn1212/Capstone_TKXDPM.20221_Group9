SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ecoBikeN2
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ecoBikeN2`;

-- -----------------------------------------------------
-- Schema ecoBikeN2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ecoBikeN2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ecoBikeN2` ;


-- -----------------------------------------------------
-- Table `ecoBikeN2`.`Dock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecoBikeN2`.`Dock` ;
CREATE TABLE IF NOT EXISTS `ecoBikeN2`.`Dock` (
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
-- Table `ecoBikeN2`.`Bike`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecoBikeN2`.`Bike` ;
CREATE TABLE IF NOT EXISTS `ecoBikeN2`.`Bike` (
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
    REFERENCES `ecoBikeN2`.`Dock` (`DockID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecoBikeN2`.`RentBikeInvoice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecoBikeN2`.`RentBikeInvoice` ;

CREATE TABLE IF NOT EXISTS `ecoBikeN2`.`RentBikeInvoice` (
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
    REFERENCES `ecoBikeN2`.`Bike` (`bikeID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `ecoBikeN2`.`PaymentTransaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecoBikeN2`.`PaymentTransaction` ;

CREATE TABLE IF NOT EXISTS `ecoBikeN2`.`PaymentTransaction` (
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
    REFERENCES `ecoBikeN2`.`RentBikeInvoice` (`rentalCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `dock` VALUES
('BK','Hai Bà Trưng','Hà Nội','1 Đại Cồ Việt, Quận Hai Bà Trưng',27,30),
('HM','Hoàng Mai','Hai Bà Trưng','21 Lê Trọng Tấn, Phường Định Công, Quận Hoàng Mai',27,30),
('HBT','Hai Bà Trưng','Hà Nội','295 Đường Bạch Mai, Phường Bạch Mai, Quận Hai Bà Trưng',27,30);

INSERT INTO `bike` VALUES
(20210000,'single-normal',1,2,1,null,null,0,1000000,'HK-001','HK'),
(20210001,'double-normal',2,4,1,null,null,0,1375000,'HK-002','HK'),
(20210002,'single-electric',1,0,1,92.1,60,0,1750000,'HK-003','HK'),
(20210003,'single-normal',1,2,1,null,null,0,1000000,'HM-001','HM'),
(20210004,'double-normal',2,4,1,null,null,0,1375000,'HM-002','HM'),
(20210005,'single-electric',1,0,1,92.1,60,0,1750000,'HM-003','HM'),
(20210006,'single-normal',1,2,1,null,null,0,1000000,'HBT-001','HBT'),
(20210007,'double-normal',2,4,1,null,null,0,1375000,'HBT-002','HBT'),
(20210008,'single-electric',1,0,1,92.1,60,0,1750000,'HBT-003','HBT');



