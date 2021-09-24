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
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/owl-carousel/owl.carousel.min.css">
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
.singo{
 float:right; 
 background-color:#f3f3f3; 
 border:none; 
 border-radius:3px;
 margin-bottom:15px;
 }
</style>
<body>
<%@ include file="../include/header.jsp" %>
<section class="category-page area-padding">
        <div class="container">   	
    		<h1 align="center"> </h1>	
				<button class="singo"> 👎🏻 신고
				</button>				
				<table>
					<tr>
						<td width="200px"> 제목 </td>
						<td colspan="3"> <b>${article.subject}</b> </td>
						<td width="100px"> 조회수 </td>
						<td>${article.readcount} </td>
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
								<input type="submit" value="수정" style="background-color:#1E88E5; color:#ffffff; float:right; border:none; " onclick="window.location='/earth/board/tipModifyForm.et?boardnum=${article.boardnum}&pageNum=${pageNum}'" /> 
								<input type="submit" value="삭제" style="background-color:#ffffff; color:#1E88E5; float:right; border:none; " onclick="" />		
							</c:if>
							<input type="button" value="목록으로" onclick="window.location='/earth/board/tip.et?pageNum=${pageNum}'" style="background-color:#ffffff; border:none; color:#1E88E5; " />
						</td>
					</tr>
				</table>			
			</div>
	</section>
<%@ include file="../include/footer.jsp" %>
</body>
</html>