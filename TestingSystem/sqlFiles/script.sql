-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.22 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             8.1.0.4545
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for testingsystem
CREATE DATABASE IF NOT EXISTS `testingsystem` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `testingsystem`;


-- Dumping structure for table testingsystem.account
DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `ACC_ID` char(100) NOT NULL,
  `USER_ID` char(10) DEFAULT NULL,
  `ACC_PWD` char(100) NOT NULL,
  PRIMARY KEY (`ACC_ID`),
  KEY `FK_OWNER_OF_ACCOUNTS` (`USER_ID`),
  CONSTRAINT `FK_OWNER_OF_ACCOUNTS` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.account: ~7 rows (approximately)
DELETE FROM `account`;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`ACC_ID`, `USER_ID`, `ACC_PWD`) VALUES
	('AD00', 'MITIU00', '123456'),
	('AD01', 'MITIU01', '123456'),
	('AD02', 'MITIU02', '123456'),
	('LEC01', 'MITIU01', '123456'),
	('LEC02', 'MITIU02', '123456'),
	('STU01', 'MITIU01', '123456'),
	('STU02', 'MITIU02', '123456');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;


-- Dumping structure for table testingsystem.account_role_map
DROP TABLE IF EXISTS `account_role_map`;
CREATE TABLE IF NOT EXISTS `account_role_map` (
  `ACC_ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACC_ID` char(100) DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  `CREATOR_ACC_ROLE_ID` char(100) DEFAULT NULL,
  `ACC_ROLE_GRANTED_DATE` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ACC_ROLE_ID`),
  KEY `FK_ACCOUNT_OF_ROLES` (`ACC_ID`),
  KEY `FK_CREATOR_OF_ACCOUNT_TO_ROLE_MAPPING` (`CREATOR_ACC_ROLE_ID`),
  KEY `FK_ROLE_OF_ACCOUNTS` (`ROLE_ID`),
  CONSTRAINT `FK_ACCOUNT_OF_ROLES` FOREIGN KEY (`ACC_ID`) REFERENCES `account` (`ACC_ID`),
  CONSTRAINT `FK_CREATOR_OF_ACCOUNT_TO_ROLE_MAPPING` FOREIGN KEY (`CREATOR_ACC_ROLE_ID`) REFERENCES `account` (`ACC_ID`),
  CONSTRAINT `FK_ROLE_OF_ACCOUNTS` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.account_role_map: ~8 rows (approximately)
DELETE FROM `account_role_map`;
/*!40000 ALTER TABLE `account_role_map` DISABLE KEYS */;
INSERT INTO `account_role_map` (`ACC_ROLE_ID`, `ACC_ID`, `ROLE_ID`, `CREATOR_ACC_ROLE_ID`, `ACC_ROLE_GRANTED_DATE`) VALUES
	(1, 'AD01', 1, 'AD00', '2014-03-15 22:22:12'),
	(2, 'AD02', 1, 'AD00', '2014-03-15 22:22:13'),
	(3, 'LEC02', 2, 'AD00', '2014-03-15 22:22:14'),
	(4, 'LEC01', 2, 'AD00', '2014-03-15 22:22:15'),
	(5, 'STU01', 3, 'AD01', '2014-03-15 22:22:16'),
	(6, 'STU01', 3, 'AD01', '2014-03-15 22:22:17'),
	(7, 'STU02', 3, 'AD02', '2014-03-15 22:22:18'),
	(8, 'STU01', 3, 'AD02', '2014-03-15 22:22:19');
/*!40000 ALTER TABLE `account_role_map` ENABLE KEYS */;


-- Dumping structure for table testingsystem.figurelink
DROP TABLE IF EXISTS `figurelink`;
CREATE TABLE IF NOT EXISTS `figurelink` (
  `FIGURE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIGURE_DESC` text,
  `FIGURE_PATH` text NOT NULL,
  PRIMARY KEY (`FIGURE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.figurelink: ~0 rows (approximately)
DELETE FROM `figurelink`;
/*!40000 ALTER TABLE `figurelink` DISABLE KEYS */;
/*!40000 ALTER TABLE `figurelink` ENABLE KEYS */;


-- Dumping structure for table testingsystem.lecturer
DROP TABLE IF EXISTS `lecturer`;
CREATE TABLE IF NOT EXISTS `lecturer` (
  `LECTR_ID` char(10) NOT NULL,
  `LECTR_NAME` char(100) NOT NULL,
  `LECTR_EMAIL` char(100) DEFAULT NULL,
  `LECTR_FONE` char(12) DEFAULT NULL,
  PRIMARY KEY (`LECTR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.lecturer: ~0 rows (approximately)
DELETE FROM `lecturer`;
/*!40000 ALTER TABLE `lecturer` DISABLE KEYS */;
/*!40000 ALTER TABLE `lecturer` ENABLE KEYS */;


-- Dumping structure for table testingsystem.logs
DROP TABLE IF EXISTS `logs`;
CREATE TABLE IF NOT EXISTS `logs` (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` char(100) NOT NULL,
  `LOG_DATE` datetime NOT NULL,
  `LOGGER` char(100) NOT NULL,
  `LEVEL` char(10) NOT NULL,
  `MESSAGE` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`),
  KEY `FK_LOG_USER_ACTION` (`USER_ID`),
  CONSTRAINT `FK_LOG_USER_ACTION` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.logs: ~0 rows (approximately)
DELETE FROM `logs`;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;


-- Dumping structure for table testingsystem.permission
DROP TABLE IF EXISTS `permission`;
CREATE TABLE IF NOT EXISTS `permission` (
  `PERMISSION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PERMISSION_NAME` char(255) NOT NULL,
  `PERMISSION_VALUE` tinyint(1) NOT NULL,
  `PERMISSION_DESC` text,
  PRIMARY KEY (`PERMISSION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.permission: ~12 rows (approximately)
DELETE FROM `permission`;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` (`PERMISSION_ID`, `PERMISSION_NAME`, `PERMISSION_VALUE`, `PERMISSION_DESC`) VALUES
	(1, 'CREATE TEST', 1, 'CREATE TESTS'),
	(2, 'EDIT TEST', 1, 'CREATE TESTS'),
	(3, 'DO TEST', 1, 'DO TESTS'),
	(4, 'VIEW TEST', 1, 'VIEW TESTS'),
	(5, 'DELETE TEST', 1, 'DELETE TESTS'),
	(6, 'VIEW RESULTS', 1, 'VIEW TESTS'),
	(7, 'CREATE ACCOUNT', 1, 'ISSUE ACCOUNTS'),
	(8, 'ADD USER', 1, 'ADD USERS OR LECTURE CAN ADD STUDENT'),
	(9, 'UPDATE PROFILE', 1, 'UPDATE PROFILE OR SETTING ACCOUNT'),
	(10, 'VIEW HISTORY', 1, 'VIEW HISTORY'),
	(11, 'MANAGE', 1, 'ADMIN MANAGES ALL USERS'),
	(12, 'REMOVE USER', 1, 'LECTURE REMOVE STUDENTS');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;


-- Dumping structure for table testingsystem.question
DROP TABLE IF EXISTS `question`;
CREATE TABLE IF NOT EXISTS `question` (
  `QUESTION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUESTION_DESC` text,
  `QUESTION_TYPE` char(15) NOT NULL COMMENT 'SINGLE_RESPONSE\r\n            MULTI_RESPONSE\r\n            FILL_BLANK\r\n            ASSOCIATE\r\n            ORDER_ITEMS',
  `QUESTION_LEVEL` char(1) NOT NULL,
  PRIMARY KEY (`QUESTION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.question: ~10 rows (approximately)
DELETE FROM `question`;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` (`QUESTION_ID`, `QUESTION_DESC`, `QUESTION_TYPE`, `QUESTION_LEVEL`) VALUES
	(1, 'Which of the following tools is used to create a risk management plan?', 'SINGLE_RESPONSE', '1'),
	(2, 'What is capital of England', 'SINGLE_RESPONSE', '1'),
	(3, 'Network templates which contain only portions of a network are often referred to as', 'MULTI_RESPONSE', '2'),
	(4, 'which is quadrilateral?', 'MULTI_RESPONSE', '2'),
	(5, 'A document called ____________ is created by decomposing the project scope into smaller, more manageable elements.', 'FILL_BLANK', '1'),
	(6, 'She _____ to school.', 'FILL_BLANK', '1'),
	(7, 'Match each term with its definition.', 'ASSOCIATE', '2'),
	(8, 'Match each term with its definition', 'ASSOCIATE', '2'),
	(9, 'of what/The scope/consists / baseline', 'ORDER_ITEMS', '1'),
	(10, 'the following/Which of/?/statements/is not true?', 'ORDER_ITEMS', '1');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;


-- Dumping structure for table testingsystem.questionfigure
DROP TABLE IF EXISTS `questionfigure`;
CREATE TABLE IF NOT EXISTS `questionfigure` (
  `QUEST_FIG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIGURE_ID` int(11) DEFAULT NULL,
  `QUESTION_ID` int(11) DEFAULT NULL,
  `QUEST_FIG_DESC` text,
  PRIMARY KEY (`QUEST_FIG_ID`),
  KEY `FK_FIGURE_LINK_IN_QUESTION` (`FIGURE_ID`),
  KEY `FK_FIGURE_OF_QUESTIONS` (`QUESTION_ID`),
  CONSTRAINT `FK_FIGURE_LINK_IN_QUESTION` FOREIGN KEY (`FIGURE_ID`) REFERENCES `figurelink` (`FIGURE_ID`),
  CONSTRAINT `FK_FIGURE_OF_QUESTIONS` FOREIGN KEY (`QUESTION_ID`) REFERENCES `question` (`QUESTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.questionfigure: ~0 rows (approximately)
DELETE FROM `questionfigure`;
/*!40000 ALTER TABLE `questionfigure` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionfigure` ENABLE KEYS */;


-- Dumping structure for table testingsystem.questioninfo
DROP TABLE IF EXISTS `questioninfo`;
CREATE TABLE IF NOT EXISTS `questioninfo` (
  `QUEST_INFO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUESTION_ID` int(11) DEFAULT NULL,
  `QUEST_INFO_PROP` char(50) NOT NULL,
  `QUEST_INFO_VALUE` char(200) NOT NULL,
  PRIMARY KEY (`QUEST_INFO_ID`),
  KEY `FK_ADDTIONAL_INFORMATION_OF_QUESTIONS` (`QUESTION_ID`),
  CONSTRAINT `FK_ADDTIONAL_INFORMATION_OF_QUESTIONS` FOREIGN KEY (`QUESTION_ID`) REFERENCES `question` (`QUESTION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.questioninfo: ~10 rows (approximately)
DELETE FROM `questioninfo`;
/*!40000 ALTER TABLE `questioninfo` DISABLE KEYS */;
INSERT INTO `questioninfo` (`QUEST_INFO_ID`, `QUESTION_ID`, `QUEST_INFO_PROP`, `QUEST_INFO_VALUE`) VALUES
	(1, 1, 'REN_TYPE', 'RADIOBUTTON'),
	(2, 2, 'REN_TYPE', 'RADIOBUTTON'),
	(3, 3, 'REN_TYPE', 'COMBOBOX'),
	(4, 4, 'REN_TYPE', 'COMBOBOX'),
	(5, 5, 'FACE_VALUE_MATCH', 'YES'),
	(6, 6, 'FACE_VALUE_MATCH', 'YES'),
	(7, 7, 'REN_TYPE', 'DRAW LINKS'),
	(8, 8, 'REN_TYPE', 'DRAG & DROP'),
	(9, 9, 'REN_TYPE', 'LIST'),
	(10, 10, 'REN_TYPE', 'LIST');
/*!40000 ALTER TABLE `questioninfo` ENABLE KEYS */;


-- Dumping structure for table testingsystem.questionitem
DROP TABLE IF EXISTS `questionitem`;
CREATE TABLE IF NOT EXISTS `questionitem` (
  `QUEST_ITEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUESTION_ID` int(11) DEFAULT NULL,
  `QUEST_ITEM_FACE` text COMMENT 'This value is showed to users.\r\n            When question is the filling into the blank, this field is compared to \r\n            QUEST_ITEM_VALUE to verify that they are match each other.',
  `QUEST_ITEM_VALUE` text,
  PRIMARY KEY (`QUEST_ITEM_ID`),
  KEY `FK_ANSWER_ITEMS_OF_QUESTIONS` (`QUESTION_ID`),
  CONSTRAINT `FK_ANSWER_ITEMS_OF_QUESTIONS` FOREIGN KEY (`QUESTION_ID`) REFERENCES `question` (`QUESTION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.questionitem: ~43 rows (approximately)
DELETE FROM `questionitem`;
/*!40000 ALTER TABLE `questionitem` DISABLE KEYS */;
INSERT INTO `questionitem` (`QUEST_ITEM_ID`, `QUESTION_ID`, `QUEST_ITEM_FACE`, `QUEST_ITEM_VALUE`) VALUES
	(1, 1, 'Risk planning meetings', 'Risk planning meetings'),
	(2, 1, 'Documentation reviews', 'Documentation reviews'),
	(3, 1, 'Data precision rankings', 'Data precision rankings'),
	(4, 1, 'Diagramming techniques', 'Diagramming techniques'),
	(5, 2, 'London', 'London'),
	(6, 2, 'Paris', 'paris'),
	(7, 2, 'Hanoi', 'Hanoi'),
	(8, 2, 'Roma', 'Roma'),
	(9, 3, 'Sub-networks or fragment networks', 'Sub-networks or fragment networks'),
	(10, 3, 'Subprojects', 'Subprojects'),
	(11, 3, 'Programs', 'Programs'),
	(12, 3, 'WBS items', 'WBS items'),
	(13, 4, 'square', 'square'),
	(14, 4, 'rectangular', 'rectangular'),
	(15, 4, 'circle', 'circle'),
	(16, 4, 'trapezium', 'trapezium'),
	(17, 5, '', 'Work Breakdown Structure'),
	(18, 6, '', 'goes'),
	(19, 7, 'Program', NULL),
	(20, 7, 'Project', NULL),
	(21, 7, 'Project management', NULL),
	(22, 7, 'Project Management Institute', NULL),
	(23, 7, 'A group of projects managed in a coordinated way to obtain benefits and control that are not available from managing projects individually.', NULL),
	(24, 7, 'A temporary endeavor undertaken to create a unique product,service,orresult.', NULL),
	(25, 7, 'The application of knowledge,skills,tools,and techniques to project activities to meet project requirements.', NULL),
	(26, 7, 'An international professional society for project managers.', NULL),
	(27, 8, 'Process', NULL),
	(28, 8, 'Product backlog', NULL),
	(29, 8, 'Methodology', NULL),
	(30, 8, 'Artifact', NULL),
	(31, 8, 'A series of actions directed toward a particular result.', NULL),
	(32, 8, 'A single list of features prioritized by business value.', NULL),
	(33, 8, 'A description of how things should be done.', NULL),
	(34, 8, 'A useful object created by people', NULL),
	(35, 9, 'of what', NULL),
	(36, 9, 'The scope', NULL),
	(37, 9, 'consists', NULL),
	(38, 9, 'baseline', NULL),
	(39, 10, 'the following', NULL),
	(40, 10, 'Which of', NULL),
	(41, 10, '?', NULL),
	(42, 10, 'statements', NULL),
	(43, 10, 'is not true?', NULL);
/*!40000 ALTER TABLE `questionitem` ENABLE KEYS */;


-- Dumping structure for table testingsystem.questionitemfigure
DROP TABLE IF EXISTS `questionitemfigure`;
CREATE TABLE IF NOT EXISTS `questionitemfigure` (
  `ITEM_FIG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUEST_ITEM_ID` int(11) DEFAULT NULL,
  `FIGURE_ID` int(11) DEFAULT NULL,
  `ITEM_FIG_DESC` text,
  PRIMARY KEY (`ITEM_FIG_ID`),
  KEY `FK_FIGURE_LINK_IN_QUESTION_ITEMS` (`FIGURE_ID`),
  KEY `FK_FIGURE_OF_QUESTION_ITEMS` (`QUEST_ITEM_ID`),
  CONSTRAINT `FK_FIGURE_LINK_IN_QUESTION_ITEMS` FOREIGN KEY (`FIGURE_ID`) REFERENCES `figurelink` (`FIGURE_ID`),
  CONSTRAINT `FK_FIGURE_OF_QUESTION_ITEMS` FOREIGN KEY (`QUEST_ITEM_ID`) REFERENCES `questionitem` (`QUEST_ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.questionitemfigure: ~0 rows (approximately)
DELETE FROM `questionitemfigure`;
/*!40000 ALTER TABLE `questionitemfigure` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionitemfigure` ENABLE KEYS */;


-- Dumping structure for table testingsystem.questioniteminfo
DROP TABLE IF EXISTS `questioniteminfo`;
CREATE TABLE IF NOT EXISTS `questioniteminfo` (
  `ITEM_INFO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUEST_ITEM_ID` int(11) DEFAULT NULL,
  `ITEM_INFO_PROP` char(50) NOT NULL,
  `ITEM_INFO_VALUE` char(200) NOT NULL,
  PRIMARY KEY (`ITEM_INFO_ID`),
  KEY `FK_ADDITIONAL_INFOMATION_OF_QUESTION_ITEMS` (`QUEST_ITEM_ID`),
  CONSTRAINT `FK_ADDITIONAL_INFOMATION_OF_QUESTION_ITEMS` FOREIGN KEY (`QUEST_ITEM_ID`) REFERENCES `questionitem` (`QUEST_ITEM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.questioniteminfo: ~2 rows (approximately)
DELETE FROM `questioniteminfo`;
/*!40000 ALTER TABLE `questioniteminfo` DISABLE KEYS */;
INSERT INTO `questioniteminfo` (`ITEM_INFO_ID`, `QUEST_ITEM_ID`, `ITEM_INFO_PROP`, `ITEM_INFO_VALUE`) VALUES
	(1, 17, 'FACE_VALUE_MATCH', 'Work Breakdown Structure'),
	(2, 18, 'FACE_VALUE_MATCH', 'goes');
/*!40000 ALTER TABLE `questioniteminfo` ENABLE KEYS */;


-- Dumping structure for table testingsystem.questionowner
DROP TABLE IF EXISTS `questionowner`;
CREATE TABLE IF NOT EXISTS `questionowner` (
  `QUEST_OWNER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LECTR_ID` char(10) DEFAULT NULL,
  `QUESTION_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`QUEST_OWNER_ID`),
  KEY `FK_ONWER_TO_QUESTIONS` (`LECTR_ID`),
  KEY `FK_QUESTION_TO_OWNER` (`QUESTION_ID`),
  CONSTRAINT `FK_ONWER_TO_QUESTIONS` FOREIGN KEY (`LECTR_ID`) REFERENCES `lecturer` (`LECTR_ID`),
  CONSTRAINT `FK_QUESTION_TO_OWNER` FOREIGN KEY (`QUESTION_ID`) REFERENCES `question` (`QUESTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.questionowner: ~0 rows (approximately)
DELETE FROM `questionowner`;
/*!40000 ALTER TABLE `questionowner` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionowner` ENABLE KEYS */;


-- Dumping structure for table testingsystem.questionsolution
DROP TABLE IF EXISTS `questionsolution`;
CREATE TABLE IF NOT EXISTS `questionsolution` (
  `QUEST_SOL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUEST_ITEM_ID` int(11) DEFAULT NULL,
  `PREVIOUS_QUEST_ITEM_ID` int(11) DEFAULT NULL,
  `NEXT_QUEST_ITEM_ID` int(11) DEFAULT NULL,
  `QUESTION_ID` int(11) DEFAULT NULL,
  `QUEST_SOL_ORDER` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`QUEST_SOL_ID`),
  KEY `FK_ITEM_IN_SOLUTION` (`QUEST_ITEM_ID`),
  KEY `FK_NEXT_ITEM` (`NEXT_QUEST_ITEM_ID`),
  KEY `FK_PREVIOUS_ITEM` (`PREVIOUS_QUEST_ITEM_ID`),
  KEY `FK_QUESTION_IN_THE_SOLUTION` (`QUESTION_ID`),
  CONSTRAINT `FK_ITEM_IN_SOLUTION` FOREIGN KEY (`QUEST_ITEM_ID`) REFERENCES `questionitem` (`QUEST_ITEM_ID`),
  CONSTRAINT `FK_NEXT_ITEM` FOREIGN KEY (`NEXT_QUEST_ITEM_ID`) REFERENCES `questionitem` (`QUEST_ITEM_ID`),
  CONSTRAINT `FK_PREVIOUS_ITEM` FOREIGN KEY (`PREVIOUS_QUEST_ITEM_ID`) REFERENCES `questionitem` (`QUEST_ITEM_ID`),
  CONSTRAINT `FK_QUESTION_IN_THE_SOLUTION` FOREIGN KEY (`QUESTION_ID`) REFERENCES `question` (`QUESTION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.questionsolution: ~17 rows (approximately)
DELETE FROM `questionsolution`;
/*!40000 ALTER TABLE `questionsolution` DISABLE KEYS */;
INSERT INTO `questionsolution` (`QUEST_SOL_ID`, `QUEST_ITEM_ID`, `PREVIOUS_QUEST_ITEM_ID`, `NEXT_QUEST_ITEM_ID`, `QUESTION_ID`, `QUEST_SOL_ORDER`) VALUES
	(1, NULL, 19, 23, 7, NULL),
	(2, NULL, 20, 24, 7, NULL),
	(3, NULL, 21, 25, 7, NULL),
	(4, NULL, 22, 26, 7, NULL),
	(5, NULL, 27, 31, 8, NULL),
	(6, NULL, 28, 32, 8, NULL),
	(7, NULL, 29, 33, 8, NULL),
	(8, NULL, 30, 34, 8, NULL),
	(9, 36, NULL, 38, 9, 1),
	(10, 38, 36, 37, 9, 2),
	(11, 37, 38, 35, 9, 3),
	(12, 35, 37, NULL, 9, 4),
	(13, 40, NULL, 39, 10, 1),
	(14, 39, 40, 42, 10, 2),
	(15, 42, 39, 43, 10, 3),
	(16, 43, 42, 41, 10, 4),
	(17, 41, 43, NULL, 10, 5);
/*!40000 ALTER TABLE `questionsolution` ENABLE KEYS */;


-- Dumping structure for table testingsystem.questiontopic
DROP TABLE IF EXISTS `questiontopic`;
CREATE TABLE IF NOT EXISTS `questiontopic` (
  `QUEST_TOPIC_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TOPIC_ID` char(20) DEFAULT NULL,
  `QUESTION_ID` int(11) DEFAULT NULL,
  `QUEST_TOPIC_LEVEL` char(1) DEFAULT NULL COMMENT 'The question level maybe associated with the topic',
  PRIMARY KEY (`QUEST_TOPIC_ID`),
  KEY `FK_RELATIONSHIP_7` (`TOPIC_ID`),
  KEY `FK_RELATIONSHIP_8` (`QUESTION_ID`),
  CONSTRAINT `FK_RELATIONSHIP_7` FOREIGN KEY (`TOPIC_ID`) REFERENCES `topic` (`TOPIC_ID`),
  CONSTRAINT `FK_RELATIONSHIP_8` FOREIGN KEY (`QUESTION_ID`) REFERENCES `question` (`QUESTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.questiontopic: ~0 rows (approximately)
DELETE FROM `questiontopic`;
/*!40000 ALTER TABLE `questiontopic` DISABLE KEYS */;
/*!40000 ALTER TABLE `questiontopic` ENABLE KEYS */;


-- Dumping structure for table testingsystem.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` char(255) NOT NULL COMMENT 'Roles: Lecturer, Student, AAOAdmin, SysAdmin, etc',
  `ROLE_DESC` text,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.role: ~3 rows (approximately)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`ROLE_ID`, `ROLE_NAME`, `ROLE_DESC`) VALUES
	(1, 'ADMIN', 'ADMINISTRATOR'),
	(2, 'LECTURER', 'LECTURER'),
	(3, 'STUDENT', 'STUDENT');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- Dumping structure for table testingsystem.role_permission_map
DROP TABLE IF EXISTS `role_permission_map`;
CREATE TABLE IF NOT EXISTS `role_permission_map` (
  `ROLE_PERMISSION_MAP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int(11) DEFAULT NULL,
  `PERMISSION_ID` int(11) DEFAULT NULL,
  `ACC_ID` char(100) DEFAULT NULL,
  `ROLE_PERMISSION_GRANTED_DATE` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ROLE_PERMISSION_MAP_ID`),
  KEY `FK_ROLE_OF_PERMISSION` (`ROLE_ID`),
  KEY `FK_PERMISSIONS_OF_ROLE` (`PERMISSION_ID`),
  KEY `FK_CREATOR_OF_PERMISSION_AND_ROLE_MAPPING` (`ACC_ID`),
  CONSTRAINT `FK_CREATOR_OF_PERMISSION_AND_ROLE_MAPPING` FOREIGN KEY (`ACC_ID`) REFERENCES `account` (`ACC_ID`),
  CONSTRAINT `FK_PERMISSIONS_OF_ROLE` FOREIGN KEY (`PERMISSION_ID`) REFERENCES `permission` (`PERMISSION_ID`),
  CONSTRAINT `FK_ROLE_OF_PERMISSION` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.role_permission_map: ~20 rows (approximately)
DELETE FROM `role_permission_map`;
/*!40000 ALTER TABLE `role_permission_map` DISABLE KEYS */;
INSERT INTO `role_permission_map` (`ROLE_PERMISSION_MAP_ID`, `ROLE_ID`, `PERMISSION_ID`, `ACC_ID`, `ROLE_PERMISSION_GRANTED_DATE`) VALUES
	(1, 2, 2, 'LEC01', '2014-03-15 22:22:46'),
	(2, 2, 3, 'LEC01', '2014-03-15 22:22:47'),
	(3, 2, 4, 'LEC01', '2014-03-15 22:22:48'),
	(4, 2, 5, 'LEC01', '2014-03-15 22:22:49'),
	(5, 2, 6, 'LEC01', '2014-03-15 22:22:50'),
	(6, 2, 7, 'LEC01', '2014-03-15 22:22:51'),
	(7, 2, 9, 'LEC01', '2014-03-15 22:22:52'),
	(8, 1, 11, 'AD01', '2014-03-15 22:22:53'),
	(9, 1, 7, 'AD01', '2014-03-15 22:22:54'),
	(10, 1, 8, 'AD01', '2014-03-15 22:22:55'),
	(11, 1, 9, 'AD01', '2014-03-15 22:22:56'),
	(12, 1, 10, 'AD01', '2014-03-15 22:22:57'),
	(13, 1, 12, 'AD01', '2014-03-15 22:22:59'),
	(14, 2, 12, 'LEC01', '2014-03-15 22:23:00'),
	(15, 3, 3, 'STU01', '2014-03-15 22:23:01'),
	(16, 3, 4, 'STU01', '2014-03-15 22:23:02'),
	(17, 3, 6, 'STU01', '2014-03-15 22:23:03'),
	(18, 3, 9, 'STU01', '2014-03-15 22:23:04'),
	(19, 3, 10, 'STU01', '2014-03-15 22:23:05'),
	(20, 2, 1, 'LEC01', '2014-03-15 22:24:20');
/*!40000 ALTER TABLE `role_permission_map` ENABLE KEYS */;


-- Dumping structure for table testingsystem.sharedquestion
DROP TABLE IF EXISTS `sharedquestion`;
CREATE TABLE IF NOT EXISTS `sharedquestion` (
  `SHARED_QUEST_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SHARER_ID` char(10) DEFAULT NULL,
  `SHAREE_ID` char(10) DEFAULT NULL,
  `QUESTION_ID` int(11) DEFAULT NULL,
  `SHARED_QUEST_VIEWABLE` tinyint(1) NOT NULL,
  `SHARED_QUEST_MODIFIABLE` tinyint(1) NOT NULL,
  `SHARED_QUEST_RESHARABLE` tinyint(1) NOT NULL,
  PRIMARY KEY (`SHARED_QUEST_ID`),
  KEY `FK_IS_SHARED_QUESTIONS` (`QUESTION_ID`),
  KEY `FK_SHAREE` (`SHAREE_ID`),
  KEY `FK_SHARER` (`SHARER_ID`),
  CONSTRAINT `FK_IS_SHARED_QUESTIONS` FOREIGN KEY (`QUESTION_ID`) REFERENCES `question` (`QUESTION_ID`),
  CONSTRAINT `FK_SHAREE` FOREIGN KEY (`SHAREE_ID`) REFERENCES `lecturer` (`LECTR_ID`),
  CONSTRAINT `FK_SHARER` FOREIGN KEY (`SHARER_ID`) REFERENCES `lecturer` (`LECTR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.sharedquestion: ~0 rows (approximately)
DELETE FROM `sharedquestion`;
/*!40000 ALTER TABLE `sharedquestion` DISABLE KEYS */;
/*!40000 ALTER TABLE `sharedquestion` ENABLE KEYS */;


-- Dumping structure for table testingsystem.squestionsolution
DROP TABLE IF EXISTS `squestionsolution`;
CREATE TABLE IF NOT EXISTS `squestionsolution` (
  `SQUEST_SOL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STUDENT_SOL_ID` int(11) DEFAULT NULL,
  `TQUEST_ITEM_ID` int(11) DEFAULT NULL,
  `TQUEST_PREVIOUS_ITEM_ID` int(11) DEFAULT NULL,
  `TQUEST_NEXT_ITEM_ID` int(11) DEFAULT NULL,
  `SQUEST_SOL_ORDER` tinyint(4) DEFAULT NULL,
  `SQUEST_SOL_COMMENT` text,
  PRIMARY KEY (`SQUEST_SOL_ID`),
  KEY `FK_ITEMSELECTED` (`TQUEST_ITEM_ID`),
  KEY `FK_NEXTOFSELECTEDITEM` (`TQUEST_NEXT_ITEM_ID`),
  KEY `FK_PREVOF_SELECTEDITEM` (`TQUEST_PREVIOUS_ITEM_ID`),
  KEY `FK_SOLUTION_DETAIL` (`STUDENT_SOL_ID`),
  CONSTRAINT `FK_ITEMSELECTED` FOREIGN KEY (`TQUEST_ITEM_ID`) REFERENCES `tquestionitem` (`TQUEST_ITEM_ID`),
  CONSTRAINT `FK_NEXTOFSELECTEDITEM` FOREIGN KEY (`TQUEST_NEXT_ITEM_ID`) REFERENCES `tquestionitem` (`TQUEST_ITEM_ID`),
  CONSTRAINT `FK_PREVOF_SELECTEDITEM` FOREIGN KEY (`TQUEST_PREVIOUS_ITEM_ID`) REFERENCES `tquestionitem` (`TQUEST_ITEM_ID`),
  CONSTRAINT `FK_SOLUTION_DETAIL` FOREIGN KEY (`STUDENT_SOL_ID`) REFERENCES `studentsolution` (`STUDENT_SOL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.squestionsolution: ~0 rows (approximately)
DELETE FROM `squestionsolution`;
/*!40000 ALTER TABLE `squestionsolution` DISABLE KEYS */;
/*!40000 ALTER TABLE `squestionsolution` ENABLE KEYS */;


-- Dumping structure for table testingsystem.student
DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `STUDENT_ID` char(10) NOT NULL,
  `STUDENT_NAME` char(50) NOT NULL,
  PRIMARY KEY (`STUDENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.student: ~0 rows (approximately)
DELETE FROM `student`;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
/*!40000 ALTER TABLE `student` ENABLE KEYS */;


-- Dumping structure for table testingsystem.studentsolution
DROP TABLE IF EXISTS `studentsolution`;
CREATE TABLE IF NOT EXISTS `studentsolution` (
  `STUDENT_SOL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STUDENT_ID` char(10) DEFAULT NULL,
  `TQUESTION_ID` int(11) DEFAULT NULL,
  `STUDENT_SOL_COMMENT` text,
  PRIMARY KEY (`STUDENT_SOL_ID`),
  KEY `FK_RELATIONSHIP_44` (`STUDENT_ID`),
  KEY `FK_RELATIONSHIP_45` (`TQUESTION_ID`),
  CONSTRAINT `FK_RELATIONSHIP_44` FOREIGN KEY (`STUDENT_ID`) REFERENCES `student` (`STUDENT_ID`),
  CONSTRAINT `FK_RELATIONSHIP_45` FOREIGN KEY (`TQUESTION_ID`) REFERENCES `tquestion` (`TQUESTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.studentsolution: ~0 rows (approximately)
DELETE FROM `studentsolution`;
/*!40000 ALTER TABLE `studentsolution` DISABLE KEYS */;
/*!40000 ALTER TABLE `studentsolution` ENABLE KEYS */;


-- Dumping structure for table testingsystem.studenttestmap
DROP TABLE IF EXISTS `studenttestmap`;
CREATE TABLE IF NOT EXISTS `studenttestmap` (
  `STUDENT_TEST_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STUDENT_ID` char(10) DEFAULT NULL,
  `TEST_ID` int(11) DEFAULT NULL,
  `STUDENT_TEST_STARTDATE` date DEFAULT NULL,
  `STUDENT_TEST_STARTTIME` time DEFAULT NULL,
  `STUDENT_TEST_ENDDATE` date DEFAULT NULL,
  `STUDENT_TEST_ENDTIME` time DEFAULT NULL,
  `STUDENT_TEST_VISIBLE` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`STUDENT_TEST_ID`),
  KEY `FK_RELATIONSHIP_50` (`STUDENT_ID`),
  KEY `FK_RELATIONSHIP_51` (`TEST_ID`),
  CONSTRAINT `FK_RELATIONSHIP_50` FOREIGN KEY (`STUDENT_ID`) REFERENCES `student` (`STUDENT_ID`),
  CONSTRAINT `FK_RELATIONSHIP_51` FOREIGN KEY (`TEST_ID`) REFERENCES `test` (`TEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.studenttestmap: ~0 rows (approximately)
DELETE FROM `studenttestmap`;
/*!40000 ALTER TABLE `studenttestmap` DISABLE KEYS */;
/*!40000 ALTER TABLE `studenttestmap` ENABLE KEYS */;


-- Dumping structure for table testingsystem.subject
DROP TABLE IF EXISTS `subject`;
CREATE TABLE IF NOT EXISTS `subject` (
  `SUBJECT_ID` char(10) NOT NULL,
  `SUBJECT_NAME` char(100) NOT NULL,
  `SUBJECT_CREDIT` tinyint(4) NOT NULL,
  PRIMARY KEY (`SUBJECT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.subject: ~0 rows (approximately)
DELETE FROM `subject`;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;


-- Dumping structure for table testingsystem.subjectandtopic
DROP TABLE IF EXISTS `subjectandtopic`;
CREATE TABLE IF NOT EXISTS `subjectandtopic` (
  `SUBJECT_TOP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SUBJECT_ID` char(10) DEFAULT NULL,
  `TOPIC_ID` char(20) DEFAULT NULL,
  `SUBJECT_TOP_PERCENT` float DEFAULT NULL COMMENT 'The percentage of the topic''s work load inside the subject',
  PRIMARY KEY (`SUBJECT_TOP_ID`),
  KEY `FK_THE_CONTAINER_OF_TOPICS` (`SUBJECT_ID`),
  KEY `FK_TOPIC_INSIDE_THE_CONTAINER` (`TOPIC_ID`),
  CONSTRAINT `FK_THE_CONTAINER_OF_TOPICS` FOREIGN KEY (`SUBJECT_ID`) REFERENCES `subject` (`SUBJECT_ID`),
  CONSTRAINT `FK_TOPIC_INSIDE_THE_CONTAINER` FOREIGN KEY (`TOPIC_ID`) REFERENCES `topic` (`TOPIC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.subjectandtopic: ~0 rows (approximately)
DELETE FROM `subjectandtopic`;
/*!40000 ALTER TABLE `subjectandtopic` DISABLE KEYS */;
/*!40000 ALTER TABLE `subjectandtopic` ENABLE KEYS */;


-- Dumping structure for table testingsystem.subjectassignment
DROP TABLE IF EXISTS `subjectassignment`;
CREATE TABLE IF NOT EXISTS `subjectassignment` (
  `ASSIGN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LECTR_ID` char(10) DEFAULT NULL,
  `SUBJECT_ID` char(10) DEFAULT NULL,
  `ASSIGN_STARTDATE` date DEFAULT NULL,
  `ASSIGN_ENDDATE` date DEFAULT NULL,
  PRIMARY KEY (`ASSIGN_ID`),
  KEY `FK_LECTURER_AND_ASSIGMENT` (`LECTR_ID`),
  KEY `FK_SUBJECT_AND_ASSIGMENT` (`SUBJECT_ID`),
  CONSTRAINT `FK_LECTURER_AND_ASSIGMENT` FOREIGN KEY (`LECTR_ID`) REFERENCES `lecturer` (`LECTR_ID`),
  CONSTRAINT `FK_SUBJECT_AND_ASSIGMENT` FOREIGN KEY (`SUBJECT_ID`) REFERENCES `subject` (`SUBJECT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.subjectassignment: ~0 rows (approximately)
DELETE FROM `subjectassignment`;
/*!40000 ALTER TABLE `subjectassignment` DISABLE KEYS */;
/*!40000 ALTER TABLE `subjectassignment` ENABLE KEYS */;


-- Dumping structure for table testingsystem.test
DROP TABLE IF EXISTS `test`;
CREATE TABLE IF NOT EXISTS `test` (
  `TEST_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LECTR_ID` char(10) DEFAULT NULL,
  `TEST_DESC` text,
  `TEST_CREATION_DATE` date DEFAULT NULL,
  `TEST_START_TIME` datetime DEFAULT NULL,
  `TEST_DURATION` int(11) DEFAULT NULL COMMENT 'In Minutes',
  PRIMARY KEY (`TEST_ID`),
  KEY `FK_CREATOR_OF_TESTS` (`LECTR_ID`),
  CONSTRAINT `FK_CREATOR_OF_TESTS` FOREIGN KEY (`LECTR_ID`) REFERENCES `lecturer` (`LECTR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.test: ~0 rows (approximately)
DELETE FROM `test`;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;


-- Dumping structure for table testingsystem.testreview
DROP TABLE IF EXISTS `testreview`;
CREATE TABLE IF NOT EXISTS `testreview` (
  `TEST_REV_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LECTR_ID` char(10) DEFAULT NULL,
  `TEST_ID` int(11) DEFAULT NULL,
  `TEST_REV_COMMENT` text,
  `TEST_REV_DATE` date DEFAULT NULL,
  `TEST_REV_APPROVED` tinyint(1) DEFAULT NULL COMMENT 'This field = TRUE: the test has been checked by the reviewer\r\n            ',
  PRIMARY KEY (`TEST_REV_ID`),
  KEY `FK_REVIEWER_OF_TEST` (`LECTR_ID`),
  KEY `FK_TEST_TO_REVIEWER` (`TEST_ID`),
  CONSTRAINT `FK_REVIEWER_OF_TEST` FOREIGN KEY (`LECTR_ID`) REFERENCES `lecturer` (`LECTR_ID`),
  CONSTRAINT `FK_TEST_TO_REVIEWER` FOREIGN KEY (`TEST_ID`) REFERENCES `test` (`TEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.testreview: ~0 rows (approximately)
DELETE FROM `testreview`;
/*!40000 ALTER TABLE `testreview` DISABLE KEYS */;
/*!40000 ALTER TABLE `testreview` ENABLE KEYS */;


-- Dumping structure for table testingsystem.testsection
DROP TABLE IF EXISTS `testsection`;
CREATE TABLE IF NOT EXISTS `testsection` (
  `TEST_SEC_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TEST_ID` int(11) DEFAULT NULL,
  `TEST_SEC_DESC` text,
  PRIMARY KEY (`TEST_SEC_ID`),
  KEY `FK_RELATIONSHIP_27` (`TEST_ID`),
  CONSTRAINT `FK_RELATIONSHIP_27` FOREIGN KEY (`TEST_ID`) REFERENCES `test` (`TEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.testsection: ~0 rows (approximately)
DELETE FROM `testsection`;
/*!40000 ALTER TABLE `testsection` DISABLE KEYS */;
/*!40000 ALTER TABLE `testsection` ENABLE KEYS */;


-- Dumping structure for table testingsystem.testsectionitem
DROP TABLE IF EXISTS `testsectionitem`;
CREATE TABLE IF NOT EXISTS `testsectionitem` (
  `TEST_SEC_ITEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TEST_SEC_ID` int(11) DEFAULT NULL,
  `QUESTION_ID` int(11) DEFAULT NULL,
  `TEST_SEC_ITEM_SCORE` float DEFAULT NULL,
  PRIMARY KEY (`TEST_SEC_ITEM_ID`),
  KEY `FK_RELATIONSHIP_28` (`TEST_SEC_ID`),
  KEY `FK_RELATIONSHIP_29` (`QUESTION_ID`),
  CONSTRAINT `FK_RELATIONSHIP_28` FOREIGN KEY (`TEST_SEC_ID`) REFERENCES `testsection` (`TEST_SEC_ID`),
  CONSTRAINT `FK_RELATIONSHIP_29` FOREIGN KEY (`QUESTION_ID`) REFERENCES `question` (`QUESTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.testsectionitem: ~0 rows (approximately)
DELETE FROM `testsectionitem`;
/*!40000 ALTER TABLE `testsectionitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `testsectionitem` ENABLE KEYS */;


-- Dumping structure for table testingsystem.tfigurelink
DROP TABLE IF EXISTS `tfigurelink`;
CREATE TABLE IF NOT EXISTS `tfigurelink` (
  `TFIGURE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TFIGURE_DESC` text,
  `TFIGURE_PATH` text,
  PRIMARY KEY (`TFIGURE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.tfigurelink: ~0 rows (approximately)
DELETE FROM `tfigurelink`;
/*!40000 ALTER TABLE `tfigurelink` DISABLE KEYS */;
/*!40000 ALTER TABLE `tfigurelink` ENABLE KEYS */;


-- Dumping structure for table testingsystem.topic
DROP TABLE IF EXISTS `topic`;
CREATE TABLE IF NOT EXISTS `topic` (
  `TOPIC_ID` char(20) NOT NULL,
  `TOPIC_PARENT_ID` char(20) DEFAULT NULL,
  `TOPIC_NAME` char(200) NOT NULL,
  `TOPIC_DESC` text,
  PRIMARY KEY (`TOPIC_ID`),
  KEY `FK_SUBTOPIC` (`TOPIC_PARENT_ID`),
  CONSTRAINT `FK_SUBTOPIC` FOREIGN KEY (`TOPIC_PARENT_ID`) REFERENCES `topic` (`TOPIC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.topic: ~0 rows (approximately)
DELETE FROM `topic`;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;


-- Dumping structure for table testingsystem.tquestion
DROP TABLE IF EXISTS `tquestion`;
CREATE TABLE IF NOT EXISTS `tquestion` (
  `TQUESTION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TEST_SEC_ID` int(11) DEFAULT NULL,
  `TQUESTION_DESC` text,
  `TQUESTION_TYPE` char(15) DEFAULT NULL,
  `TQUESTION_LEVEL` char(1) DEFAULT NULL,
  `TQUESTION_SCORE` float DEFAULT NULL,
  PRIMARY KEY (`TQUESTION_ID`),
  KEY `FK_RELATIONSHIP_30` (`TEST_SEC_ID`),
  CONSTRAINT `FK_RELATIONSHIP_30` FOREIGN KEY (`TEST_SEC_ID`) REFERENCES `testsection` (`TEST_SEC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.tquestion: ~0 rows (approximately)
DELETE FROM `tquestion`;
/*!40000 ALTER TABLE `tquestion` DISABLE KEYS */;
/*!40000 ALTER TABLE `tquestion` ENABLE KEYS */;


-- Dumping structure for table testingsystem.tquestionfigure
DROP TABLE IF EXISTS `tquestionfigure`;
CREATE TABLE IF NOT EXISTS `tquestionfigure` (
  `TQUEST_FIG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TQUESTION_ID` int(11) DEFAULT NULL,
  `TFIGURE_ID` int(11) DEFAULT NULL,
  `TQUEST_FIG_DESC` text,
  PRIMARY KEY (`TQUEST_FIG_ID`),
  KEY `FK_RELATIONSHIP_38` (`TQUESTION_ID`),
  KEY `FK_RELATIONSHIP_39` (`TFIGURE_ID`),
  CONSTRAINT `FK_RELATIONSHIP_38` FOREIGN KEY (`TQUESTION_ID`) REFERENCES `tquestion` (`TQUESTION_ID`),
  CONSTRAINT `FK_RELATIONSHIP_39` FOREIGN KEY (`TFIGURE_ID`) REFERENCES `tfigurelink` (`TFIGURE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.tquestionfigure: ~0 rows (approximately)
DELETE FROM `tquestionfigure`;
/*!40000 ALTER TABLE `tquestionfigure` DISABLE KEYS */;
/*!40000 ALTER TABLE `tquestionfigure` ENABLE KEYS */;


-- Dumping structure for table testingsystem.tquestioninfo
DROP TABLE IF EXISTS `tquestioninfo`;
CREATE TABLE IF NOT EXISTS `tquestioninfo` (
  `TQUEST_INFO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TQUESTION_ID` int(11) DEFAULT NULL,
  `TQUEST_INFO_PROP` char(50) NOT NULL,
  `TQUEST_INFO_VALUE` char(200) DEFAULT NULL,
  PRIMARY KEY (`TQUEST_INFO_ID`),
  KEY `FK_RELATIONSHIP_31` (`TQUESTION_ID`),
  CONSTRAINT `FK_RELATIONSHIP_31` FOREIGN KEY (`TQUESTION_ID`) REFERENCES `tquestion` (`TQUESTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.tquestioninfo: ~0 rows (approximately)
DELETE FROM `tquestioninfo`;
/*!40000 ALTER TABLE `tquestioninfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tquestioninfo` ENABLE KEYS */;


-- Dumping structure for table testingsystem.tquestionitem
DROP TABLE IF EXISTS `tquestionitem`;
CREATE TABLE IF NOT EXISTS `tquestionitem` (
  `TQUEST_ITEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TQUESTION_ID` int(11) DEFAULT NULL,
  `TQUEST_ITEM_FACE` text,
  `TQUEST_ITEM_VALUE` text,
  PRIMARY KEY (`TQUEST_ITEM_ID`),
  KEY `FK_RELATIONSHIP_32` (`TQUESTION_ID`),
  CONSTRAINT `FK_RELATIONSHIP_32` FOREIGN KEY (`TQUESTION_ID`) REFERENCES `tquestion` (`TQUESTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.tquestionitem: ~0 rows (approximately)
DELETE FROM `tquestionitem`;
/*!40000 ALTER TABLE `tquestionitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `tquestionitem` ENABLE KEYS */;


-- Dumping structure for table testingsystem.tquestionitemfigure
DROP TABLE IF EXISTS `tquestionitemfigure`;
CREATE TABLE IF NOT EXISTS `tquestionitemfigure` (
  `TQUEST_ITEM_FIG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TFIGURE_ID` int(11) DEFAULT NULL,
  `TQUEST_ITEM_ID` int(11) DEFAULT NULL,
  `TQUEST_ITEM_FIG_DESC` text,
  PRIMARY KEY (`TQUEST_ITEM_FIG_ID`),
  KEY `FK_RELATIONSHIP_40` (`TFIGURE_ID`),
  KEY `FK_RELATIONSHIP_41` (`TQUEST_ITEM_ID`),
  CONSTRAINT `FK_RELATIONSHIP_40` FOREIGN KEY (`TFIGURE_ID`) REFERENCES `tfigurelink` (`TFIGURE_ID`),
  CONSTRAINT `FK_RELATIONSHIP_41` FOREIGN KEY (`TQUEST_ITEM_ID`) REFERENCES `tquestionitem` (`TQUEST_ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.tquestionitemfigure: ~0 rows (approximately)
DELETE FROM `tquestionitemfigure`;
/*!40000 ALTER TABLE `tquestionitemfigure` DISABLE KEYS */;
/*!40000 ALTER TABLE `tquestionitemfigure` ENABLE KEYS */;


-- Dumping structure for table testingsystem.tquestioniteminfo
DROP TABLE IF EXISTS `tquestioniteminfo`;
CREATE TABLE IF NOT EXISTS `tquestioniteminfo` (
  `TQUEST_ITEM_INFO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TQUEST_ITEM_ID` int(11) DEFAULT NULL,
  `TQUEST_ITEM_INFO_PROP` char(50) NOT NULL,
  `TQUEST_ITEM_INFO_VALUE` char(200) NOT NULL,
  PRIMARY KEY (`TQUEST_ITEM_INFO_ID`),
  KEY `FK_RELATIONSHIP_33` (`TQUEST_ITEM_ID`),
  CONSTRAINT `FK_RELATIONSHIP_33` FOREIGN KEY (`TQUEST_ITEM_ID`) REFERENCES `tquestionitem` (`TQUEST_ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.tquestioniteminfo: ~0 rows (approximately)
DELETE FROM `tquestioniteminfo`;
/*!40000 ALTER TABLE `tquestioniteminfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tquestioniteminfo` ENABLE KEYS */;


-- Dumping structure for table testingsystem.tquestionowner
DROP TABLE IF EXISTS `tquestionowner`;
CREATE TABLE IF NOT EXISTS `tquestionowner` (
  `TQUEST_OWNER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LECTR_ID` char(10) DEFAULT NULL,
  `TQUESTION_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`TQUEST_OWNER_ID`),
  KEY `FK_RELATIONSHIP_42` (`LECTR_ID`),
  KEY `FK_RELATIONSHIP_43` (`TQUESTION_ID`),
  CONSTRAINT `FK_RELATIONSHIP_42` FOREIGN KEY (`LECTR_ID`) REFERENCES `lecturer` (`LECTR_ID`),
  CONSTRAINT `FK_RELATIONSHIP_43` FOREIGN KEY (`TQUESTION_ID`) REFERENCES `tquestion` (`TQUESTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.tquestionowner: ~0 rows (approximately)
DELETE FROM `tquestionowner`;
/*!40000 ALTER TABLE `tquestionowner` DISABLE KEYS */;
/*!40000 ALTER TABLE `tquestionowner` ENABLE KEYS */;


-- Dumping structure for table testingsystem.tquestionsolution
DROP TABLE IF EXISTS `tquestionsolution`;
CREATE TABLE IF NOT EXISTS `tquestionsolution` (
  `TQUEST_SOL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TQUESTION_ID` int(11) DEFAULT NULL,
  `TQUEST_ITEM_ID` int(11) DEFAULT NULL,
  `TQUEST_NEXT_ITEM_ID` int(11) DEFAULT NULL,
  `TQUEST_PREVIOUS_ITEM_ID` int(11) DEFAULT NULL,
  `TQUEST_SOL_ORDER` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`TQUEST_SOL_ID`),
  KEY `FK_NEXT_ITEM_IN_QUESTION_IN_TEST` (`TQUEST_NEXT_ITEM_ID`),
  KEY `FK_PREVIOUS_ITEM_IN_QUESTION_IN_TEST` (`TQUEST_PREVIOUS_ITEM_ID`),
  KEY `FK_SOLUTION_ITEM` (`TQUEST_ITEM_ID`),
  KEY `FK_SOLUTION_OF_QUESTION_IN_TEST` (`TQUESTION_ID`),
  CONSTRAINT `FK_NEXT_ITEM_IN_QUESTION_IN_TEST` FOREIGN KEY (`TQUEST_NEXT_ITEM_ID`) REFERENCES `tquestionitem` (`TQUEST_ITEM_ID`),
  CONSTRAINT `FK_PREVIOUS_ITEM_IN_QUESTION_IN_TEST` FOREIGN KEY (`TQUEST_PREVIOUS_ITEM_ID`) REFERENCES `tquestionitem` (`TQUEST_ITEM_ID`),
  CONSTRAINT `FK_SOLUTION_ITEM` FOREIGN KEY (`TQUEST_ITEM_ID`) REFERENCES `tquestionitem` (`TQUEST_ITEM_ID`),
  CONSTRAINT `FK_SOLUTION_OF_QUESTION_IN_TEST` FOREIGN KEY (`TQUESTION_ID`) REFERENCES `tquestion` (`TQUESTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.tquestionsolution: ~0 rows (approximately)
DELETE FROM `tquestionsolution`;
/*!40000 ALTER TABLE `tquestionsolution` DISABLE KEYS */;
/*!40000 ALTER TABLE `tquestionsolution` ENABLE KEYS */;


-- Dumping structure for table testingsystem.tquestiontopic
DROP TABLE IF EXISTS `tquestiontopic`;
CREATE TABLE IF NOT EXISTS `tquestiontopic` (
  `TQUEST_TOPIC_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TQUESTION_ID` int(11) DEFAULT NULL,
  `TOPIC_ID` char(20) DEFAULT NULL,
  `TQUEST_TOPIC_LEVEL` char(1) DEFAULT NULL,
  PRIMARY KEY (`TQUEST_TOPIC_ID`),
  KEY `FK_RELATIONSHIP_52` (`TQUESTION_ID`),
  KEY `FK_RELATIONSHIP_53` (`TOPIC_ID`),
  CONSTRAINT `FK_RELATIONSHIP_52` FOREIGN KEY (`TQUESTION_ID`) REFERENCES `tquestion` (`TQUESTION_ID`),
  CONSTRAINT `FK_RELATIONSHIP_53` FOREIGN KEY (`TOPIC_ID`) REFERENCES `topic` (`TOPIC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.tquestiontopic: ~0 rows (approximately)
DELETE FROM `tquestiontopic`;
/*!40000 ALTER TABLE `tquestiontopic` DISABLE KEYS */;
/*!40000 ALTER TABLE `tquestiontopic` ENABLE KEYS */;


-- Dumping structure for table testingsystem.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `USER_ID` char(10) NOT NULL,
  `FNAME` char(100) NOT NULL,
  `LNAME` char(100) DEFAULT NULL,
  `EMAIL` char(100) DEFAULT NULL,
  `MOBILE` char(12) DEFAULT NULL,
  `BDATE` date DEFAULT NULL,
  `ADDRESS` text,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingsystem.user: ~3 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`USER_ID`, `FNAME`, `LNAME`, `EMAIL`, `MOBILE`, `BDATE`, `ADDRESS`) VALUES
	('MITIU00', 'ABC', 'HUYNH', 'loanhuynh89@gmail.com', '0123456789', '1989-01-12', NULL),
	('MITIU01', 'LOAN', 'HUYNH', 'loanhuynh89@gmail.com', '0123456789', '1989-12-27', NULL),
	('MITIU02', 'QUANG', 'TRAN', 'tranminhquang@gmail.com', '01111111111', '1990-01-12', NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
