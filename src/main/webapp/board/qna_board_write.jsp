<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/write.css">

</head>
<body>
	<!--게시판 등록-->
	<section id="registForm">
		<h2>게시판글등록</h2>
		<form action="boardWritePro.do" method="post"
			enctype="multipart/form-data" name="boardform">
			<table>
				<tr>
					<td class="td_left"><label for="BOARD_NAME">글쓴이</label></td>
					<td class="td_right"><input type = "text" name="BOARD_NAME" id="BOARD_NAME" required="required"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_PASS">비밀번호</label></td>
					<td class="td_right"><input type = "password" name="BOARD_PASS" id="BOARD_PASS" required="required"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_SUBJECT">제목</label></td>
					<td class="td_right"><input type = "text" name="BOARD_SUBJECT" id="BOARD_SUBJECT" required="required"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_CONTENT">내용</label></td>
					<td class="td_right"><textarea id="BOARD_CONTENT" name="BOARD_CONTENT" cols="40" rows="15" required="required"></textarea></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_FILE">파일첨부</label></td>
					<td class="td_right"><input type = "file" name="BOARD_FILE" id="BOARD_FILE" required="required"></td>
				</tr>
			</table>
			<section id= "commandCell">
				<input type ="submit" value="등록">&nbsp;&nbsp;<!--띄어쓰기-->
				<input type ="reset" value="다시 쓰기" >
			</section>
		</form>
	</section>
<!--게시판 등록-->
<!-- &nbsp;&nbsp; 띄어쓰기-->
<!-- required 반드시 입력-->
</body>
</html>