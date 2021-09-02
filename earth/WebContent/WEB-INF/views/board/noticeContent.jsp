<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>어뜨 - 공지사항</title>
	<link href="/earth/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<c:set var="path" value="${pageContext.request.contextPath}" scope="application" />

<body>
	<h1 align="center"> 공지사항 </h1>
	
	<table>
		<tr>
			<td> 제목 </td>
			<td colspan="3"> <b>${article.subject}</b> </td>
		</tr>
		<tr>
			<td> 작성자 </td>
			<td> ${article.id} </td>
			<td>
				<fmt:formatDate value="${article.reg}" pattern="YYYY-MM-dd HH : mm"/>
			</td>
			<td> 조회수 : ${article.readcount} </td>
		</tr>
		<tr>
			<td colspan="4" height="100"> ${article.ctt} </td>
		</tr>
		<tr>
			<td colspan="4">
				<img src="${pageContext.request.contextPath}/save/${article.img}" width="300"/>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="right"> 
				<button onclick="window.location='/earth/board/noticeModifyForm.et?boardnum=${article.boardnum}&pageNum=${pageNum}'">수 정</button>
				<button onclick="window.location='/earth/board/deleteForm.et?boardnum=${article.boardnum}&pageNum=${pageNum}'">삭 제</button>
				<button onclick="window.location='/earth/board/noticeList.et?pageNum=${pageNum}'">목록으로</button>
			</td>
		</tr>
	</table>
</body>
</html>