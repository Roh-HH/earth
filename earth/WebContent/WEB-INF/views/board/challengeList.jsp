<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head> 
	<meta charset="UTF-8">
	<title>게시판</title>
	<link href="/earth/resources/css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<!-- 
	<br />
	<div align="right"> 
		<c:if test="${sessionScope.sid == null}">
			<button onclick="window.location='/spring/member/loginForm.mer'">로그인</button>
		</c:if>
		<c:if test="${sessionScope.sid != null}">
			<button onclick="window.location='/spring/member/logout.mer'">로그아웃</button>
		</c:if>
	</div>
 -->	
	<h1 align="center"> 이달의 챌린지 리스트 </h1>
	
	<c:if test="${count == 0}">
	<table>
		<tr>
			<td align="center">게시글이 없습니다.</td>
		</tr>
	</table>
	</c:if>
	<c:if test="${count != 0}">
		<table>
			<tr>
				<td>제  목</td>
				<td>작성자</td>
				<td>시  간</td>
			</tr>
		<c:forEach var="article" items="${articleList}">
			<tr>
				<td> <a href="/earth/board/challengeContent.et?num=${article.challengenum}&pageNum=${pageNum}"> ${article.subject} </a> </td>			
				<td> ${article.id} </td>
				<td> ${article.reg} </td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
	
	<br /> <br /> 
	<%-- 페이지 번호 --%>
	<div align="center">
	<c:if test="${count > 0}">
		<c:set var="pageBlock" value="3" />
		<fmt:parseNumber var="res" value="${count / pageSize}" integerOnly="true" />
		<c:set var="pageCount" value="${res + (count % pageSize == 0 ? 0 : 1)}" />
		<fmt:parseNumber var="result" value="${(currentPage-1)/pageBlock}" integerOnly="true" />
		<fmt:parseNumber var="startPage" value="${result * pageBlock + 1}"/>
		<fmt:parseNumber var="endPage" value="${startPage + pageBlock -1}" />
		<c:if test="${endPage > pageCount}">
			<c:set var="endPage" value="${pageCount}" /> 
		</c:if>
	
	</c:if> <%-- end:count > 0 --%>
	
	<br /> <br />
	
	<h3 style="color:grey"> 현재페이지 : ${pageNum} </h3>

	<button onclick="window.location='/earth/board/challengeContent.et'"> 돌아가기  </button>
	
	</div>
	
</body>
</html>
