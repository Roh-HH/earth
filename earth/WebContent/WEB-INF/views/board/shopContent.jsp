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
<body>
<%@ include file="../include/header.jsp" %>
<section class="category-page area-padding">
   <div class="container">   	
		<div id="content" class="site-content">
			<div id="primary" class="content-area column full">
				<main id="main" class="site-main" role="main">
				<div id="container">
					<c:set var="path" value="${pageContext.request.contextPath}" scope="application" />
					<div id="content" role="main">
							<table>
								<tr>
									<td width="200px"> 작성자 </td>
									<td> ${article.id} </td>
								</tr>
								<tr>
									<td width="200px"> 상점이름 </td>
									<td> ${article.subject} </td>
								</tr>
							
								<tr>
									<td width="200px">상점링크</td>
									<td>${article.hyperlink }</td>
								</tr>
								<tr>
									<td width="200px">  게시글 등록일자 </td>
									<td>
										<fmt:formatDate value="${article.reg}" pattern="YYYY-MM-dd HH : mm"/>
									</td>
								</tr>
								
								<tr>
									<td width="200px"></td>
									<td align="left">				
										<img src="${pageContext.request.contextPath}/save/${article.img}" width="300"/>
										<br><br>
										${article.img}
									</td>
								</tr>
								
								<tr>
									<td colspan="2">
										<c:if test="${sid eq 'admin'}">
											<input type="submit" value="수정" style="background-color:#1E88E5; color:#ffffff; float:right; border:none" onclick="window.location='/earth/board/shopModifyForm.et?boardnum=${article.boardnum}&pageNum=${pageNum}'" />
											<input type="button" value="삭제" style="background-color:#ffffff; color:#1E88E5; float:right; border:none"  onclick="window.location='/earth/board/delete.et?boardnum=${article.boardnum}&code=6&uri=/earth/board/shop.et&pageNum=${pageNum}'"/>
										</c:if>
										<input type="button" value="목록으로" onclick="window.location='/earth/board/shop.et?pageNum=${pageNum}'" style="background-color:#ffffff; border:none; color:#1E88E5; " />									
									</td>
								</tr>
							</table>
						</div><!-- main -->
					</div>
				</main>	
			</div>
		</div>
	</div>
</section>
<%@ include file="../include/footer.jsp" %>
</body>
</html>