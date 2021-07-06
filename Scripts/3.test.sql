
-- 
select now() from dual;
select * from department;

select max(BOARD_NUM) from board;
select * from board;
INSERT INTO web_gradle_erp.board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE)
VALUES(3, '김상건', '1111', '마칠시간', '5시', 'test.txt', 0, 0, 0, 0, '2021-03-03');


INSERT INTO web_gradle_erp.board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF)
VALUES(50, '김상건', '1111', '마칠시간', '5시', 'test.txt', 50);

select * from board b ;
-- listcount
select count(*) from board;


-- list 페이징

/* 
 * [1][2][3]
 * 
 * (page-1) * 10  1page--> 0, 2page-->10, 3page-->20
 * */
select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE,
	   BOARD_RE_REF,BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE
from board 
order by BOARD_RE_REF desc, BOARD_RE_SEQ asc
limit 0, 2;


select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE,
	   BOARD_RE_REF,BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE
from board 
order by BOARD_RE_REF desc, BOARD_RE_SEQ asc
limit 10, 10;
/*limit 페이지 번호, 한번에 보이는 게시물 수*/

-- selectArticle
select * from board where BOARD_NUM = 3;

-- read board
update board set BOARD_READCOUNT =BOARD_READCOUNT + 1 where BOARD_NUM = 3;

-- reply
update board set BOARD_RE_SEQ = BOARD_RE_SEQ + 1
where BOARD_RE_REF=68 and BOARD_RE_SEQ > 0;

select * from board b;
where BOARD_RE_REF=0 and BOARD_RE_SEQ > 0;

INSERT INTO web_gradle_erp.board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF)
VALUES(50, '김상건', '1111', '마칠시간', '5시', 'test.txt', 50);

-- delete
select 1 from board where board_num = 62 and BOARD_PASS = 12 ;\
-- return 이 1이면 True 0이면 fulse(게시글 넘버와 패스워드가 일치하면 1 )

delete from board where BOARD_NUM = ?
