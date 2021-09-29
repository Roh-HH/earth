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
<script>
	$(document).ready(function(){
		var hash = $.trim( window.location.hash );
		if (hash) $('.your-css-selector a[href$="'+hash+'"]').trigger('click');
	});
</script>
<style>
	.badgeImg{
		width:18px; 
		height:18px; 
		margin-top:4px; 
		margin-right:4px;
	}
</style>
<body>
	<%-- 앵커태그(위치이동/지우지말것) --%>
	<a href="#list"></a>
	<!--================ Start header Top Area =================-->
	<%@ include file = "../include/header.jsp" %>
	<section id="list" class="category-page area-padding">
		<div class="container">
		<h3 class="page-title">커뮤니티</h3>
		<h1 class="entry-title">공지사항 </h1>
	
		<c:if test="${count == 0}">
			<table>
				<tr>
					<td align="center">등록된 게시글이 없습니다.</td>
				</tr>
				<tr>
					<td><button onclick="window.location='/spring/board/noticeWriteForm.et'"> 글쓰기 </button></td>
				</tr>
			</table>
		</c:if>
		<c:if test="${count != 0}">
			<table>
				<tr>
					<td> 등록번호 </td>
					<td> 작성자 </td>
					<td> 제목 </td>
					<td> 등록일 </td>
					<td> 조회수 </td>
				</tr>
					<c:forEach var="article" items="${articleList}">
						<tr>
							<td> ${article.boardnum} </td>
							<td>
								<img class="badgeImg" src="${pageContext.request.contextPath}/save/admin.png"/>관리자
							</td>
							<td>
								<a href="/earth/board/noticeContent.et?pageNum=${pageNum}&boardnum=${article.boardnum}">${article.subject}</a>
							</td>
							<td>
								<fmt:formatDate value="${article.reg}" pattern="YYYY-MM-dd HH : mm"/>
							</td>
							<td> ${article.readcount}</td>
						</tr>
					</c:forEach>
			</table>
			 	<div class="col-12 text-center">
			 		<c:if test="${sessionScope.sid == 'admin'}">
						<button type="button"class="main_btn" style="width:100px; height:50px; text-align:center;"
							onclick="window.location='/earth/board/noticeWriteForm.et'">글쓰기
						</button>	
			 		</c:if>
                </div>
			<br />
		</c:if>

				<%-- 페이지 번호 --%>
				<div align="center" class="col-12 text-center">
					<nav class="pagination" style="float:center;">
						<c:if test="${count > 0}">
							<c:set var="pageBlock" value="5" />
							<fmt:parseNumber var="res" value="${count / pageSize}" integerOnly="true" />
							<c:set var="pageCount" value="${res + (count % pageSize == 0 ? 0 : 1)}" />
							<fmt:parseNumber var="result" value="${(currentPage-1)/pageBlock}" integerOnly="true" />
							<fmt:parseNumber var="startPage" value="${result * pageBlock + 1}"/>
							<fmt:parseNumber var="endPage" value="${startPage + pageBlock -1}" />
							<c:if test="${endPage > pageCount}">
								<c:set var="endPage" value="${pageCount}" /> 
							</c:if>
							
							<%-- 검색 안했을때 페이지번호들   --%> 
							<c:if test="${sel == null || search == null}">
								<c:if test="${startPage > pageBlock}">
									<a class="back page-numbers" href="/earth/board/noticeList.et?pageNum=${startPage-pageBlock}#list" class="pageNums">«Back</a>
								</c:if>
								<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
									<c:choose>
										<c:when test="${pageNum eq i}">
											<a class="current" href="/earth/board/noticeList.et?pageNum=${pageNum}#list">${i}</a>
										</c:when>
										<c:otherwise>
											<a class="page-numbers" href="/earth/board/noticeList.et?pageNum=${i}#list" class="pageNums">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${endPage < pageCount}">
									<a class="next page-numbers" href="/earth/board/noticeList.et?pageNum=${startPage+pageBlock}#list" class="pageNums">Next»</a>
								</c:if>
							</c:if>
						</c:if> <%-- end:count > 0 --%>
					</nav>
				</div>

       </div><!-- container -->
    </section>		

	<%@ include file = "../include/footer.jsp" %>
<!-- #page -->

</body>
</html>
