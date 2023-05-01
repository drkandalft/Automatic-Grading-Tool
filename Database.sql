

CREATE TABLE IF NOT EXISTS `tblFeedback` (
  `student_id` int(11) NOT NULL,
  `note` text,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `tblScores` (
  `student_id` int(11) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `used_phone` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `tblStudents` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(128) DEFAULT NULL,
  `last_name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
