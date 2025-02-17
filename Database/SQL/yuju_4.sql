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
  `user_name` VARCHAR(50) NOT NULL COMMENT '이름',
  `user_id` VARCHAR(100) NOT NULL COMMENT '아이디',
  `user_pw` VARCHAR(255) NOT NULL COMMENT '비밀번호',
  `admin` ENUM('Y', 'N') NOT NULL DEFAULT 'N' COMMENT '관리자 여부',
  `reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록 일자',
  `edit_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 일자',
  PRIMARY KEY (`member_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
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
  `capacity` INT NOT NULL COMMENT ' 최대수용인원',
  `description` TEXT NULL DEFAULT NULL COMMENT '설명',
  `is_available` TINYINT(1) NULL DEFAULT '1' COMMENT '이용가능여부',
  `breakfast` ENUM('1', '2') NULL DEFAULT NULL COMMENT '조식',
  `breakfastprice` INT NULL DEFAULT NULL COMMENT '조식가격',
  `reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  `edit_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '변경일자',
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
  `reserved_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '예약일자',
  `reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  `edit_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '변경일자',
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
-- Table `yuju`.`breakfast`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `yuju`.`breakfast` (
  `breakfast_id` INT NOT NULL AUTO_INCREMENT COMMENT '조식ID',
  `reservation_id` INT NOT NULL COMMENT '예약ID',
  `breakfast_price` INT NOT NULL COMMENT '조식가격',
  `reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  `edit_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '변경일자',
  PRIMARY KEY (`breakfast_id`),
  INDEX `fk_breakfast_reservations1_idx` (`reservation_id` ASC) VISIBLE,
  CONSTRAINT `fk_breakfast_reservations1`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `yuju`.`reservations` (`reservation_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '조식';


-- -----------------------------------------------------
-- Table `yuju`.`inquiries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `yuju`.`inquiries` (
  `inquiry_id` INT NOT NULL AUTO_INCREMENT COMMENT '문의ID',
  `member_id` INT NULL DEFAULT NULL COMMENT '회원ID',
  `subject` VARCHAR(255) NOT NULL COMMENT '제목',
  `message` TEXT NOT NULL COMMENT '내용',
  `status` ENUM('PENDING', 'ANSWERED') NULL DEFAULT 'PENDING' COMMENT '상태',
  `reply` TEXT NULL DEFAULT NULL COMMENT '답변',
  `reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  `edit_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '변경일자',
  PRIMARY KEY (`inquiry_id`),
  INDEX `inquiries_ibfk_1` (`member_id` ASC) VISIBLE,
  CONSTRAINT `inquiries_ibfk_1`
    FOREIGN KEY (`member_id`)
    REFERENCES `yuju`.`members` (`member_id`)
    ON DELETE SET NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '문의';


-- -----------------------------------------------------
-- Table `yuju`.`inquiry_files`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `yuju`.`inquiry_files` (
  `file_id` INT NOT NULL AUTO_INCREMENT COMMENT '문의파일ID',
  `inquiry_id` INT NOT NULL COMMENT '문의ID',
  `file_name` VARCHAR(255) NOT NULL COMMENT '파일명',
  `file_path` VARCHAR(512) NOT NULL COMMENT '파일경로',
  `reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  `edit_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '변경일자',
  PRIMARY KEY (`file_id`),
  INDEX `inquiry_id` (`inquiry_id` ASC) VISIBLE,
  CONSTRAINT `inquiry_files_ibfk_1`
    FOREIGN KEY (`inquiry_id`)
    REFERENCES `yuju`.`inquiries` (`inquiry_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '문의파일';


-- -----------------------------------------------------
-- Table `yuju`.`notices`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `yuju`.`notices` (
  `notice_id` INT NOT NULL AUTO_INCREMENT COMMENT '공지사항ID',
  `member_id` INT NOT NULL COMMENT '회원ID',
  `title` VARCHAR(255) NOT NULL COMMENT '제목',
  `content` TEXT NOT NULL COMMENT '내용',
  `reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `edit_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`notice_id`),
  INDEX `member_id` (`member_id` ASC) VISIBLE,
  CONSTRAINT `notices_ibfk_1`
    FOREIGN KEY (`member_id`)
    REFERENCES `yuju`.`members` (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '공지사항';


-- -----------------------------------------------------
-- Table `yuju`.`payments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `yuju`.`payments` (
  `payment_id` INT NOT NULL AUTO_INCREMENT COMMENT '결제ID',
  `reservation_id` INT NOT NULL COMMENT '예약ID',
  `member_id` INT NOT NULL COMMENT '회원ID',
  `amount` INT NOT NULL COMMENT '결제금액',
  `status` ENUM('PENDING', 'COMPLETED', 'FAILED') NULL DEFAULT 'PENDING' COMMENT '결제상태',
  `payment_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `reg_date` DATETIME NOT NULL COMMENT '등록일자',
  `edit_date` DATETIME NOT NULL COMMENT '변경일자',
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


-- -----------------------------------------------------
-- Table `yuju`.`room_imgs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `yuju`.`room_imgs` (
  `room_img_id` INT NOT NULL AUTO_INCREMENT COMMENT '이미지ID',
  `room_id` INT NOT NULL COMMENT '객실ID',
  `img_url` VARCHAR(255) NOT NULL COMMENT '사진경로',
  `reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  `edit_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '변경일자',
  PRIMARY KEY (`room_img_id`),
  INDEX `fk_room_imgs_rooms1_idx` (`room_id` ASC) VISIBLE,
  CONSTRAINT `fk_room_imgs_rooms1`
    FOREIGN KEY (`room_id`)
    REFERENCES `yuju`.`rooms` (`room_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '객실 사진';


-- -----------------------------------------------------
-- Table `yuju`.`spring_session`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `yuju`.`spring_session` (
  `PRIMARY_ID` CHAR(36) NOT NULL COMMENT '세션 고유 식별자',
  `SESSION_ID` CHAR(36) NOT NULL COMMENT '세션 ID',
  `CREATION_TIME` BIGINT NOT NULL COMMENT '세션 생성 시간',
  `LAST_ACCESS_TIME` BIGINT NOT NULL COMMENT '마지막 접근 시간',
  `MAX_INACTIVE_INTERVAL` INT NOT NULL COMMENT '세션 비활성화 허용시간',
  `EXPIRY_TIME` BIGINT NOT NULL COMMENT '세션 만료시간',
  `PRINCIPAL_NAME` VARCHAR(100) NULL DEFAULT NULL COMMENT '세션과 연결된 사용자명',
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE INDEX `SPRING_SESSION_IX1` (`SESSION_ID` ASC) VISIBLE,
  INDEX `SPRING_SESSION_IX2` (`EXPIRY_TIME` ASC) VISIBLE,
  INDEX `SPRING_SESSION_IX3` (`PRINCIPAL_NAME` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '스프링 세션';


-- -----------------------------------------------------
-- Table `yuju`.`spring_session_attributes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `yuju`.`spring_session_attributes` (
  `SESSION_PRIMARY_ID` CHAR(36) NOT NULL COMMENT '세션 고유 식별자',
  `ATTRIBUTE_NAME` VARCHAR(200) NOT NULL COMMENT '세션 속성명(키)',
  `ATTRIBUTE_BYTES` BLOB NOT NULL COMMENT '직렬화된 세션 데이터',
  PRIMARY KEY (`SESSION_PRIMARY_ID`, `ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK`
    FOREIGN KEY (`SESSION_PRIMARY_ID`)
    REFERENCES `yuju`.`spring_session` (`PRIMARY_ID`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '스프링 세션 속성';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
