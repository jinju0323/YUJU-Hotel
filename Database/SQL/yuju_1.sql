-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema yuju
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema yuju
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `yuju` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `yuju` ;

-- -----------------------------------------------------
-- Table `yuju`.`members`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `yuju`.`members` (
  `member_id` INT NOT NULL AUTO_INCREMENT COMMENT '회원ID',
  `username` VARCHAR(50) NOT NULL COMMENT '이름',
  `userid` VARCHAR(100) NOT NULL COMMENT '아이디',
  `userpw` VARCHAR(255) NOT NULL COMMENT '비밀번호',
  `admin` ENUM('Y', 'N') NOT NULL DEFAULT 'N' COMMENT '관리자 여부',
  PRIMARY KEY (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '회원';


-- -----------------------------------------------------
-- Table `yuju`.`rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `yuju`.`rooms` (
  `room_id` INT NOT NULL AUTO_INCREMENT COMMENT '객실ID',
  `room_type` ENUM('Standard', 'Deluxe', 'Suite') NOT NULL COMMENT '객실유형',
  `room_category` ENUM('A', 'B', 'C') NOT NULL COMMENT '객실등급',
  `price_per_night` INT NOT NULL COMMENT '객실요금',
  `capacity` INT NOT NULL COMMENT '최대수용인원',
  `description` TEXT NULL DEFAULT NULL COMMENT '설명',
  `is_available` TINYINT(1) NULL DEFAULT '1' COMMENT '이용가능여부',
  `breakfast` ENUM('1', '2') NULL DEFAULT NULL COMMENT '조식',
  `breakfast_price` INT NULL DEFAULT NULL COMMENT '조식가격',
  PRIMARY KEY (`room_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '객실';


-- -----------------------------------------------------
-- Table `yuju`.`reservations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `yuju`.`reservations` (
  `reservation_id` INT NOT NULL AUTO_INCREMENT COMMENT '예약ID',
  `member_id` INT NOT NULL COMMENT '회원ID',
  `room_id` INT NOT NULL COMMENT '객실ID',
  `check_in_date` DATE NOT NULL COMMENT '체크인',
  `check_out_date` DATE NOT NULL COMMENT '체크아웃',
  `total_price` INT NOT NULL COMMENT '총금액',
  `status` ENUM('PENDING', 'CONFIRMED', 'CANCELLED') NULL DEFAULT 'PENDING' COMMENT '예약상태',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '예약시간',
  PRIMARY KEY (`reservation_id`),
  INDEX `reservations_ibfk_1` (`member_id` ASC) VISIBLE,
  INDEX `reservations_ibfk_2` (`room_id` ASC) VISIBLE,
  CONSTRAINT `reservations_ibfk_1`
    FOREIGN KEY (`member_id`)
    REFERENCES `yuju`.`members` (`member_id`)
    ON DELETE CASCADE,
  CONSTRAINT `reservations_ibfk_2`
    FOREIGN KEY (`room_id`)
    REFERENCES `yuju`.`rooms` (`room_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '예약';


-- -----------------------------------------------------
-- Table `yuju`.`payments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `yuju`.`payments` (
  `payment_id` INT NOT NULL AUTO_INCREMENT COMMENT '결제ID',
  `reservation_id` INT NOT NULL COMMENT '예약ID',
  `member_id` INT NOT NULL COMMENT '회원ID',
  `amount` INT NOT NULL COMMENT '결제금액',
  `status` ENUM('PENDING', 'COMPLETED', 'FAILED') NULL DEFAULT 'PENDING' COMMENT '결제상태',
  `payment_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '결제일자',
  PRIMARY KEY (`payment_id`),
  INDEX `payments_ibfk_1` (`reservation_id` ASC) VISIBLE,
  INDEX `payments_ibfk_2` (`member_id` ASC) VISIBLE,
  CONSTRAINT `payments_ibfk_1`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `yuju`.`reservations` (`reservation_id`)
    ON DELETE CASCADE,
  CONSTRAINT `payments_ibfk_2`
    FOREIGN KEY (`member_id`)
    REFERENCES `yuju`.`members` (`member_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '결제';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
