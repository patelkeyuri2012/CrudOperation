CREATE DATABASE `jdbc`;

USE `jdbc`;

CREATE TABLE `student_info` (
  `std_id` int NOT NULL  AUTO_INCREMENT,
  `std_name` varchar(45) NOT NULL,
  `std_cno` varchar(10) NOT NULL,
  `std_email` varchar(20) NOT NULL,
  `std_add` varchar(50) NOT NULL,
  PRIMARY KEY (`std_id`)
);

INSERT INTO `student_info` (`std_name`, `std_cno`, `std_email`, `std_add`) VALUES ('keyuri', '8735979376', 'keyuri@gmail.com', 'devparijat');
INSERT INTO `student_info` (`std_name`, `std_cno`, `std_email`, `std_add`) VALUES ('jainil', '9998495677', 'jainil@gmail.com', 'gayatrihomes');

UPDATE `jdbc`.`student_info` SET `std_add` = 'vishvascity' WHERE (`std_id` = '2');
DELETE FROM `jdbc`.`student_info` WHERE (`std_id` = '2');

SELECT * FROM `student_info`;

SELECT * FROM `student_info` WHERE `std_id` = 0 OR `std_name` = '' OR `std_cno` = '' OR `std_email` = '' OR `std_add` = '';


SELECT * FROM `student_info` WHERE `std_id` = 1;
SELECT * FROM `student_info` WHERE `std_name` LIKE '%%' OR `std_cno` LIKE '%%' OR `std_email` LIKE '%%' OR `std_add` LIKE '%%';
