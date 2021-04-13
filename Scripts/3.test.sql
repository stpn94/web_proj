select database(),user();
-- 
select * from employee;


-- 
select titleNo, titleName from title;
select titleNo, titleName from title where titleNo = 4;

insert into title values(8,'아일');
update title set titleName = '아이' where titleNo =8;
delete from title where titleNo = 8;


-- 
select now() from dual;
select * from department;

select max(BOARD_NUM) from board;
select * from board;
INSERT INTO web_gradle_erp.board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE)
VALUES(1, '김상건', '1111', '마칠시간', '5시', 'test.txt', 0, 0, 0, 0, '2021-03-03');
