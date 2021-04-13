use web_gradle_erp;

drop table if exists web_gradle_erp.board;
create table if not exists web_gradle_erp.board(
   BOARD_NUM INT,
   BOARD_NAME VARCHAR(20) NOT NULL,
   BOARD_PASS VARCHAR(15) NOT NULL,
   BOARD_SUBJECT VARCHAR(50) NOT NULL,
   BOARD_CONTENT VARCHAR(2000) NOT NULL,
   BOARD_FILE VARCHAR(50) NOT NULL,
   BOARD_RE_REF INT NOT NULL,
   BOARD_RE_LEV INT NOT NULL,
   BOARD_RE_SEQ INT NOT NULL,
   BOARD_READCOUNT INT DEFAULT 0,
   BOARD_DATE DATE,
   PRIMARY KEY(BOARD_NUM)
); 


-- 계정 권한 부여
grant all 
   on web_gradle_erp.* 
   to 'user_gradle_erp'@'localhost' identified by 'rootroot';

-- file 권한(backup, load) -- root만 권한 부여 가능
GRANT File 
   ON *.* 
   TO 'user_gradle_erp'@'localhost';