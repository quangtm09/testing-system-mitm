DROP TABLE IF  EXISTS LOGS;
CREATE TABLE LOGS(
	LOG_ID INT NOT NULL AUTO_INCREMENT,
	USER_ID CHAR(100) NOT NULL,
	LOG_DATE DATETIME NOT NULL,
	LOGGER CHAR(100) NOT NULL,
	LEVEL CHAR(10) NOT NULL,
	MESSAGE VARCHAR(1000),
	PRIMARY KEY (LOG_ID),
	CONSTRAINT FK_LOG_USER_ACTION FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID)
)