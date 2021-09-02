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
<%-- 
<c:set var="path" value="<%=request.getContextPath() %>" scope="application" />
--%>
<body>
	<br />
	<h1 align="center"> 이 달의 챌린지 </h1>
<div>
	<table>
	<c:if test="${article.img != null}">
		<tr>
		<!-- <td colspan="2" height="100"> <img src="${path}/save/${article.img}" style="max-width: 50%" /> </td>
		 -->	
		 <td colspan="2" height="100"> <img src="${pageContext.request.contextPath}/save/${article.img}" style="max-width: 50%" />
		</tr>
	</c:if>	
		<tr>
			<td colspan="2"> <b>${article.subject}</b> </td>
		</tr>
 
		<tr>
			<td colspan="2" height="100"> ${article.ctt} </td>
		</tr>
		
		
		<tr>
			<td>목표인원 ${article.maxcount}</td>
			<td>참여인원 ${article.joincount}</td>
		</tr>
		<tr>
			<td>
			<button>챌린지 참여하기</button>
			</td>
		</tr>
	</table>
</div>

<!-- 코멘트 부분  -->
<div>
	<c:if test="${count == 0}">
		<table>
			<tr>
				<td align="center"> 글이 존재하지 않습니다. </td>
			</tr>
		</table>
	</c:if>
	
	<c:if test="${count != 0}">
		<table>
			 
		</table>
	</c:if>
	
</div>	
<div>
		<form action="/earth/board/challengeReply.et" method="post">
			<table>
				<tr>
					<td colspan="2" align="center">
						<input type="hidden" name="challengenum" value="${article.challengenum}"/>
 						<input type="text" name="ctt" placeholder="참여하시고 댓글을 올려주세요!"/>
						<input type="submit" value="댓글달기"/>
					</td>
				</tr>
			</table>
		</form>
	</div>

<div>
 
		<!-- 관리자 -->
		<button onclick="window.location='/earth/board/challengeWriteForm.et'">챌린지 추가</button>
		<button onclick="window.location='/earth/board/challengeModifyForm.et?challengenum=${article.challengenum}&pageNum=${pageNum}'">수 정</button>
		<button onclick="window.location='/earth/board/deleteForm.et?challengenum=${article.challengenum}&pageNum=${pageNum}'">삭 제</button> 
		<!-- 모두 보기  -->
		<button onclick="window.location='/earth/board/challengeList.et?pageNum=${pageNum}'"> 지난챌린지 보기 </button>

</div>

</body>
</html>