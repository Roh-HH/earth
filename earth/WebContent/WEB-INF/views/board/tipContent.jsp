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
    <title>어뜨 - 지구를 살리는 작은 실천 </title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/bootstrap.css">
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
<%@ include file="../include/header.jsp" %>
<section class="category-page area-padding">
        <div class="container">   					
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
								<input type="submit" value="삭제" style="background-color:#ffffff; color:#1E88E5; float:right; border:none; " 
								 onclick="window.location='/earth/board/delete.et?boardnum=${article.boardnum}&code=8&uri=/earth/board/tip.et&pageNum=${pageNum}'" />		
							</c:if>
							<input type="button" value="목록으로" onclick="window.location='/earth/board/tip.et?pageNum=${pageNum}'" style="background-color:#ffffff; border:none; color:#1E88E5; " />
						</td>
					</tr>
				</table>
				<div class="navigation-area">
					<div style="display: inline-block">
						<div class="details">
							<c:if test="${next.nextnum == null }">
								<a href="">
									<p>< 이전글</p>
									<h4>가장 최신글입니다.</h4>
								</a>
							</c:if>
							<c:if test="${next.nextnum != null }">
								<a
									href="/earth/board/tipContent.et?pageNum=${pageNum}&boardnum=${next.nextnum}">
									<p>< 이전글</p>
									<h4>${next.nextsub}</h4>
								</a>
							</c:if>
						</div>
					</div>
					<div style="display: inline-block; float: right; text-align: right">
						<div class="detials">
							<c:if test="${prev.prevnum == null }">
								<a href="">
									<p>다음글 ></p>
									<h4>가장 마지막글입니다.</h4>
								</a>
							</c:if>
							<c:if test="${prev.prevnum != null }">
								<a
									href="/earth/board/tipContent.et?pageNum=${pageNum}&boardnum=${prev.prevnum}">
									<p>다음글 ></p>
									<h4>${prev.prevsub}</h4>
								</a>
							</c:if>
						</div>
					</div>
				</div>			
			</div>
	</section>
<%@ include file="../include/footer.jsp" %>
</body>
</html>