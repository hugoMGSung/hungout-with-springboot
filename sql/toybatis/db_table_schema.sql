-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        11.1.2-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- toybatis 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `toybatis` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `toybatis`;

-- 프로시저 toybatis.loopInsert 구조 내보내기
DELIMITER //
CREATE PROCEDURE `loopInsert`()
BEGIN
	DECLARE i INT DEFAULT 1;
        
	WHILE i <= 50 DO
   	INSERT INTO tb_user (email , password, reg_date , mod_date)
   	VALUES (concat('user_',i), concat('pass_',i), NOW(), now());
   	SET i = i + 1;
   END WHILE;
END//
DELIMITER ;

-- 테이블 toybatis.tb_user 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_user` (
  `Idx` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(120) NOT NULL DEFAULT '0',
  `password` varchar(120) NOT NULL DEFAULT '0',
  `reg_date` date DEFAULT NULL,
  `mod_date` date DEFAULT NULL,
  PRIMARY KEY (`Idx`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='사용자 유저 테이블(테스트용)';

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
