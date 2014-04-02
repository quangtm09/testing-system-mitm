DROP TABLE IF  EXISTS LOGS;
CREATE TABLE LOGS(
	LOG_ID INT NOT NULL AUTO_INCREMENT,
	ACC_ID CHAR(100) NOT NULL,
	LOG_DATE DATETIME NOT NULL,
	LOGGER CHAR(100) NOT NULL,
	LEVEL CHAR(10) NOT NULL,
	MESSAGE VARCHAR(1000),
	PRIMARY KEY (LOG_ID),
	CONSTRAINT FK_LOG_ACCOUNT_ACTION FOREIGN KEY (ACC_ID) REFERENCES ACCOUNT(ACC_ID)
)

--DROP TABLE IF  EXISTS LOGS;
--CREATE TABLE LOGS(
--	ACC_ID CHAR(100),
--	LOG_DATE DATETIME NOT NULL,
--	LOGGER CHAR(100) NOT NULL,
--	LEVEL CHAR(10) NOT NULL,
--	MESSAGE VARCHAR(1000)
--)