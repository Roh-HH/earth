<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en-Us">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="img/favicon.png" type="image/png">
    <title>어뜨 - 지구를 살리는 작은 실천 </title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/themify-icons.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/flaticon.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/animate-css/animate.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/popup/magnific-popup.css">
    <!-- main css -->
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style1.css">
    
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/responsive.css">
</head>
<style>
.badgeImg{
width:20px; 
height:20px; 
margin-bottom:2px;
margin-right:4px;
}
</style>
<body>
     <!--================ Start header Top Area =================-->
<%@ include file = "../include/header.jsp" %>
<section class="category-page area-padding">
        <div class="container">   	
			<h1 align="center"> </h1>
				<table>
					<tr>
						<td width="200px"> 제목 </td>
						<td colspan="3"> <b>${article.subject}</b> </td>
						<td width="100px"> 조회수 </td>
						<td>${article.readcount} </td>
					</tr>
					<tr>
						<td width="200px"> 행사일정 </td>
						<td><b> ${article.sch} </b> </td>
					</tr>
					<tr>
						<td width="200px"> 작성자 </td>
						<td>
							<img class="badgeImg" src="${pageContext.request.contextPath}/save/admin.png"/>		                         
							${article.nickname} 
						</td>
					</tr>
					<tr>
						<td width="200px">  게시글 등록일자 </td>
						<td>
							<fmt:formatDate value="${article.reg}" pattern="YYYY-MM-dd HH : mm"/>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<img src="${pageContext.request.contextPath}/save/${article.img}" style="width:400; align:center;" />
						</td>
					</tr>
					<tr>
						<td colspan="4" height="100"> ${article.ctt} </td>
					</tr>
					<tr>
						<td colspan="6" align="right"> 
							<c:if test="${sid eq 'admin'}">
								<button onclick="window.location='/earth/board/eventModifyForm.et?boardnum=${article.boardnum}&pageNum=${pageNum}'"
								style="background-color:#1E88E5; color:#ffffff; border:none;float:right;">수 정</button>
								<button onclick="""
								style="background-color:#ffffff; color:#1E88E5; border:none;float:right;">삭제</button>
							</c:if>
							
							<button onclick="window.location='/earth/board/event.et?pageNum=${pageNum}'"
								style="background-color:#ffffff; color:#1E88E5; border:none;">목록으로</button>
						</td>
					</tr>
				</table>
			</div>
	</section>
	<%@ include file = "../include/footer.jsp" %>
</body>
</html>