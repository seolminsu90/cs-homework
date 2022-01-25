/* 문의 */
CREATE TABLE Question(
	Seq 		BIGINT NOT NULL AUTO_INCREMENT,
	CustomerId 	VARCHAR(20) NOT NULL,
	Title		VARCHAR(100) NOT NULL,
	Content		VARCHAR(500) NOT NULL,
	RegDate		DATETIME DEFAULT CURRENT_TIMESTAMP,
	
	CONSTRAINT PK_Question_Seq PRIMARY KEY (Seq)
) DEFAULT CHARSET=utf8;


/* 문의 획득 */
CREATE TABLE QuestionReceive(
	QuestionSeq BIGINT NOT NULL,
	CounselorId	VARCHAR(20)	NOT NULL,
	Content		VARCHAR(500) NULL,
	ResponseDate	DATETIME NULL,
	CreateDate 	DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CheckState	TINYINT NOT NULL DEFAULT 0,
	
	CONSTRAINT PK_Question_QuestionSeq PRIMARY KEY (QuestionSeq)
) DEFAULT CHARSET=utf8;


/* 상담사 */
CREATE TABLE Counselor(
	Id 			VARCHAR(20) NOT NULL,
	Name		VARCHAR(5)	NOT NULL,
	Pwd			VARCHAR(100) NOT NULL,
	RegDate		DATETIME DEFAULT CURRENT_TIMESTAMP,
	
	CONSTRAINT PK_Counselor_Id PRIMARY KEY (Id)
) DEFAULT CHARSET=utf8;