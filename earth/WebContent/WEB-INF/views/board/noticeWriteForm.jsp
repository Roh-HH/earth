<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공지사항 작성</title>
	<link href="/earth/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1 align="center"> 공지사항 작성 </h1>
	<form action="/earth/board/noticeWritePro.et" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>작성자</td>
				<td align="left">${id}</td> <%-- ${sessionScope.sid} 회원가입 연동후 --%>
			</tr>
			<tr>
				<td>제  목</td>
				<td align="left">
					<input type="text" name="subject" />
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td align="left"><input type="password" name="pw"/></td>
			</tr>
			<tr>
				<td>내  용</td>
				<td><textarea rows="20" cols="60" name="ctt"></textarea></td>
			</tr>
			<tr>
				<td>이미지</td>
				<td align="left"><input type="file" name="uploadFile"/></td>
			</tr>
			<tr>
				<td colspan="2" >
					<input type="submit" value="등 록" />
					<input type="button" value="목록으로" onclick="window.location='/earth/board/noticeList.et'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>