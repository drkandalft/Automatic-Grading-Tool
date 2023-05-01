-- --------------------------------------------------------
-- Host:                         199.80.55.99
-- Server version:               5.7.41-0ubuntu0.18.04.1 - (Ubuntu)
-- Server OS:                    Linux
-- HeidiSQL Version:             12.4.0.6659
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for admin_hekmat
CREATE DATABASE IF NOT EXISTS `admin_hekmat` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `admin_hekmat`;

-- Dumping structure for table admin_hekmat.tblFeedback
CREATE TABLE IF NOT EXISTS `tblFeedback` (
  `student_id` int(11) NOT NULL,
  `note` text,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table admin_hekmat.tblFeedback: ~0 rows (approximately)

-- Dumping structure for table admin_hekmat.tblScores
CREATE TABLE IF NOT EXISTS `tblScores` (
  `student_id` int(11) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `used_phone` int(11) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table admin_hekmat.tblScores: ~0 rows (approximately)

-- Dumping structure for table admin_hekmat.tblStudents
CREATE TABLE IF NOT EXISTS `tblStudents` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(128) DEFAULT NULL,
  `last_name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table admin_hekmat.tblStudents: ~4 rows (approximately)
INSERT INTO `tblStudents` (`student_id`, `first_name`, `last_name`) VALUES
	(1, 'John', 'Doe'),
	(2, 'Mark', 'Twain'),
	(3, 'Jennifer', 'Lopez'),
	(4, 'Nikol', 'Pashinyan');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
