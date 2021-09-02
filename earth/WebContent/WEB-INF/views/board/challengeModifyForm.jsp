<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="/earth/resources/css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
	<br />
	<h1 align="center"> 게시글 수정 </h1>
	<form action="/earth/board/challengeModifyPro.et?challengenum=${article.challengenum}" method="post" enctype="multipart/form-data">
		<input type="hidden" name="challengenum" value="${article.challengenum}" />
		<table>
			<tr>
				<td>작성자</td>
					<td align="left">${article.id}</td>
					<!-- 추후 세션아이디에서 뽑기  <td align="left">${sessionScope.sid}</td>  -->
			<tr>
				<td>비밀번호</td>
					<td align="left"><input type="password" name="pw" /></td>
			</tr>
			<tr>
				<td>제  목</td>
				<td align="left">
					<input type="text" name="subject" value="${article.subject}"/>
				</td>
			</tr>
			<tr>
				<td>내  용</td>
				<td><textarea rows="20" cols="60" name="ctt" value="${article.ctt}">${article.ctt}</textarea></td>
			</tr>
			<tr>
				<td> 목표인원 </td>
				<td><input type="text" name="maxcount" value="${article.maxcount}" /></td>
			</tr>
			
			<tr>
				<td>이미지</td>
				 <td><input type="file" name="uploadFile" /></td>
			</tr>
			<tr>
				<td colspan="2" >
					<input type="submit" value="수정" />
					<input type="reset" value="재작성" />
					<input type="button" value="목록으로" onclick="window.location='/earth/board/challengeList.et'" />
				</td>
			</tr>
		</table>
	</form>
	
	
</body>
</html>