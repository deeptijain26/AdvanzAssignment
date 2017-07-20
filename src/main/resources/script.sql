Create database development;

alter table answers drop Foreign key answers_ibfk_1;
alter table ans_sub_questions drop Foreign key ans_sub_questions_ibfk_1;
alter table ans_sub_questions drop Foreign key ans_sub_questions_ibfk_2;

drop table questions;
drop table answers;
drop table ans_sub_questions;

CREATE TABLE QUESTIONS (
	ID INT PRIMARY KEY AUTO_INCREMENT, 
	Q_TYPE INT(1) NOT NULL DEFAULT 1, 
	TEXT VARCHAR(500) NOT NULL
	);


CREATE TABLE ANSWERS (
	ID INT PRIMARY KEY AUTO_INCREMENT, 
	QUESTION_ID INT NOT NULL, 
	TEXT VARCHAR(500) NOT NULL,
	CONSTRAINT FK_QUES_ANS FOREIGN KEY (QUESTION_ID) REFERENCES QUESTIONS(ID) 
	);
	
CREATE TABLE ANS_SUB_QUESTIONS(
	ID INT PRIMARY KEY AUTO_INCREMENT, 
	ANSWER_ID INT NOT NULL, 
	QUESTION_ID INT NOT NULL,
	CONSTRAINT FK_ANS_SUB_QUES_1 FOREIGN KEY (QUESTION_ID) REFERENCES QUESTIONS(ID),
	CONSTRAINT FK_ANS_SUB_QUES_2 FOREIGN KEY (ANSWER_ID) REFERENCES ANSWERS(ID) 
	);

create sequence QUES_SEQ start with 1 increment by 1;
create sequence ANS_SEQ start with 1 increment by 1;