INSERT INTO USER VALUES('MITIU01','LOAN','HUYNH','loanhuynh89@gmail.com', '0123456789', '1989-12-27', NULL);
INSERT INTO USER VALUES('MITIU00','ABC','HUYNH','loanhuynh89@gmail.com', '0123456789', '1989-01-12',NULL);
INSERT INTO USER VALUES('MITIU02','QUANG','TRAN','tranminhquang@gmail.com','01111111111','1990-01-12',NULL);
INSERT INTO USER VALUES('MITIU03','A','TEST','abc@gmail.com','01111111111','1990-01-12',NULL);
INSERT INTO USER VALUES('MITIU04','TRANG','NGUYEN','trangn@gmail.com','01111111111','1987-01-12',NULL);
INSERT INTO USER VALUES('MITIU05','TEST1','NGUYEN','test1@gmail.com','01111111111','1987-01-12',NULL);
INSERT INTO USER VALUES('MITIU06','TEST2','NGUYEN','test2@gmail.com','01111111111','1987-01-12',NULL);
INSERT INTO USER VALUES('MITIU07','TEST3','NGUYEN','test3@gmail.com','01111111111','1987-01-12',NULL);
INSERT INTO USER VALUES('MITIU08','TEST4','NGUYEN','test3@gmail.com','01111111111','1987-01-12',NULL);

INSERT INTO ACCOUNT VALUES('AD01','MITIU01','123456');
INSERT INTO ACCOUNT VALUES('LEC01','MITIU01','123456');
INSERT INTO ACCOUNT VALUES('STU01','MITIU01','123456');
INSERT INTO ACCOUNT VALUES('AD02','MITIU02','123456');
INSERT INTO ACCOUNT VALUES('LEC02','MITIU02','123456');
INSERT INTO ACCOUNT VALUES('STU02','MITIU02','123456');
INSERT INTO ACCOUNT VALUES('AD00','MITIU00','123456');
INSERT INTO ACCOUNT VALUES('STU03','MITIU03','123456');
INSERT INTO ACCOUNT VALUES('STU04','MITIU04','123456');
INSERT INTO ACCOUNT VALUES('AD03','MITIU05','123456');
INSERT INTO ACCOUNT VALUES('STU05','MITIU06','123456');
INSERT INTO ACCOUNT VALUES('LEC03','MITIU07','123456');
INSERT INTO ACCOUNT VALUES('AD04','MITIU07','123456');

-- ROLE
INSERT INTO ROLE VALUES(NULL, 'ADMIN','ADMINISTRATOR');
INSERT INTO ROLE VALUES(NULL, 'LECTURER','LECTURER');
INSERT INTO ROLE VALUES(NULL, 'STUDENT','STUDENT');

-- ACCOUNT_ROLE_MAP 
INSERT INTO ACCOUNT_ROLE_MAP VALUES(NULL,'AD01',1,'AD00', CURRENT_TIMESTAMP);
INSERT INTO ACCOUNT_ROLE_MAP VALUES(NULL,'AD02',1,'AD00', CURRENT_TIMESTAMP);
INSERT INTO ACCOUNT_ROLE_MAP VALUES(NULL,'LEC02',2,'AD00', CURRENT_TIMESTAMP);
INSERT INTO ACCOUNT_ROLE_MAP VALUES(NULL,'LEC01',2,'AD00', CURRENT_TIMESTAMP);
INSERT INTO ACCOUNT_ROLE_MAP VALUES(NULL,'STU01',3,'AD01', CURRENT_TIMESTAMP);
INSERT INTO ACCOUNT_ROLE_MAP VALUES(NULL,'STU03',3,'AD01', CURRENT_TIMESTAMP);
INSERT INTO ACCOUNT_ROLE_MAP VALUES(NULL,'STU03',3,'AD02', CURRENT_TIMESTAMP);
INSERT INTO ACCOUNT_ROLE_MAP VALUES(NULL,'STU04',3,'AD02', CURRENT_TIMESTAMP);
INSERT INTO ACCOUNT_ROLE_MAP VALUES(NULL,'STU03',3,'LEC01', CURRENT_TIMESTAMP);
INSERT INTO ACCOUNT_ROLE_MAP VALUES(NULL,'STU04',3,'LEC01', CURRENT_TIMESTAMP);
INSERT INTO ACCOUNT_ROLE_MAP VALUES(NULL,'STU02',3,'LEC02', CURRENT_TIMESTAMP);
INSERT INTO ACCOUNT_ROLE_MAP VALUES(NULL,'AD03',1,'AD01', CURRENT_TIMESTAMP);
INSERT INTO ACCOUNT_ROLE_MAP VALUES(NULL,'STU05',3,'LEC02', CURRENT_TIMESTAMP);
INSERT INTO ACCOUNT_ROLE_MAP VALUES(NULL,'LEC02',1,'AD03', CURRENT_TIMESTAMP);
INSERT INTO ACCOUNT_ROLE_MAP VALUES(NULL,'LEC03',1,'AD03', CURRENT_TIMESTAMP);
INSERT INTO ACCOUNT_ROLE_MAP VALUES(NULL,'STU01',1,'AD04', CURRENT_TIMESTAMP);

-- PERMISSION

INSERT INTO PERMISSION VALUES(NULL,'CREATE TEST',TRUE,'CREATE TESTS');
INSERT INTO PERMISSION VALUES(NULL,'EDIT TEST',TRUE,'CREATE TESTS');
INSERT INTO PERMISSION VALUES(NULL,'TAKE TEST',TRUE,'TAKING TESTS');
INSERT INTO PERMISSION VALUES(NULL,'VIEW TEST',TRUE,'VIEW TESTS');
INSERT INTO PERMISSION VALUES(NULL,'DELETE TEST',TRUE,'DELETE TESTS');
INSERT INTO PERMISSION VALUES(NULL,'UPDATE TEST',TRUE, 'TEST MANAGER UPDATE TEST');
INSERT INTO PERMISSION VALUES(NULL, 'DEPLOY TEST', TRUE,'AFTER DEPLOYMENT, STUDENTS CAN SEE AND TAKE THE TESTS');
INSERT INTO PERMISSION VALUES(NULL, 'CREATE QUESTION', TRUE, 'USER CAN CREATE QUESTION');
INSERT INTO PERMISSION VALUES(NULL, 'VIEW QUESTION', TRUE, 'USER CAN VIEW QUESTION');
INSERT INTO PERMISSION VALUES(NULL, 'UPDATE QUESTION', TRUE, 'USER CAN UPDATE QUESTION');
INSERT INTO PERMISSION VALUES(NULL, 'DROP QUESTION', TRUE, 'USER CAN DROP QUESTION');
INSERT INTO PERMISSION VALUES(NULL,'VIEW RESULTS',TRUE,'VIEW RESULTS');
INSERT INTO PERMISSION VALUES(NULL,'CREATE USER',TRUE,'ADD USERS OR LECTURE CAN ADD STUDENT');
INSERT INTO PERMISSION VALUES(NULL,'UPDATE PROFILES',TRUE,'UPDATE PROFILE OR SETTING ACCOUNT');
INSERT INTO PERMISSION VALUES(NULL,'MANAGE USERS',TRUE,'ADMIN/LECTURERS MANAGES ALL USERS');
INSERT INTO PERMISSION VALUES(NULL,'REMOVE USERS',TRUE,'LECTURE REMOVE STUDENTS');
INSERT INTO PERMISSION VALUES(NULL,'CREATE ACCOUNT',TRUE,'ISSUE ACCOUNTS');
INSERT INTO PERMISSION VALUES(NULL, 'VIEW ACCOUNT', TRUE,'USER CAN VIEW ACCOUNTS');
INSERT INTO PERMISSION VALUES(NULL, 'DROP ACCOUNT', TRUE, 'ADMIN DROP ACCOUNTS');
INSERT INTO PERMISSION VALUES(NULL, 'REMOVE ACCOUNT', TRUE, 'LECTURER CAN REMOVE ACCOUNTS');
INSERT INTO PERMISSION VALUES(NULL, 'CHANGE PASSWORD', TRUE, 'USER CAN CHANGE PASSWORD');

--- ROLE PERMISSION MAP

INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 2, 1,'LEC01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 2, 2,'LEC01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 2, 3,'LEC01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 2, 4,'LEC01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 2, 5,'LEC01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 2, 6,'LEC01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 2, 7,'LEC01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 2, 8,'LEC01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 2, 16,'LEC01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 1, 13,'AD01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 1, 14,'AD01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 1, 15,'AD01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 1, 16,'AD01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 1, 17,'AD01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 1, 18,'AD01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 3, 3,'STU01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 3, 4,'STU01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 3, 21,'STU01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 3, 12,'STU01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 3, 14,'STU01', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 1, 13,'AD02', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 1, 14,'AD02', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 1, 15,'AD02', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 1, 16,'AD02', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 1, 17,'AD02', CURRENT_TIMESTAMP);
INSERT INTO ROLE_PERMISSION_MAP VALUES(NULL, 1, 18,'AD02', CURRENT_TIMESTAMP);


