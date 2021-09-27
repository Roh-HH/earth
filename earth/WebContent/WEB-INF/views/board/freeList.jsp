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
<style>.badgeImg{
	width:18px; 
	height:18px; 
	margin-top:4px; 
	margin-right:4px;
	}
</style>

<body>
	<!--================ Start header Top Area =================-->
    <%@ include file = "../include/header.jsp" %>
	<section class="category-page area-padding">
		<div class="container">
		<h3 class="page-title">커뮤니티</h3>
		<h1 class="entry-title">자유게시판 </h1>
			<div class="row">
			<c:if test="${count == 0}">
				<table>
					<tr>
						<td align="center">등록된 게시글이 없습니다.</td>
					</tr>
					<tr>
						<td><button onclick="window.location='/spring/board/freeWriteForm.et'"> 글쓰기 </button></td>
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
								<img class="badgeImg" src="/earth/resources/bootstrap/imgs/logo.png" alt="">${article.nickname}
							</td>
							<td>
								<a href="/earth/board/freeContent.et?pageNum=${pageNum}&boardnum=${article.boardnum}&commentPageNum=1">(${article.categStr}) ${article.subject} <c:if test="${article.refCount != 0}">(${article.refCount})</c:if></a>
							</td>
							<td>
								<fmt:formatDate value="${article.reg}" pattern="YYYY-MM-dd HH : mm"/>
							</td>
							<td> ${article.readcount}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="col-12 text-center">
					<button type="button"class="main_btn" style="width:80px; height:50px; text-align:center;"
					onclick="window.location='/earth/board/freeWriteForm.et'">글쓰기
					</button>	
				</div>
				<br />
				
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
							
							<%-- 검색했을때 페이지번호들 --%>
							<c:if test="${sel != null && search != null}">
								<c:if test="${startPage > pageBlock}">
									<a href="/earth/board/freeList.et?pageNum=${startPage-pageBlock}&sel=${sel}&search=${search}" class="pageNums">Back</a>
								</c:if>
								<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
									<a href="/earth/board/freeList.et?pageNum=${i}&sel=${sel}&search=${search}" class="pageNums">${i}</a>
								</c:forEach>
								<c:if test="${endPage < pageCount}">
									&nbsp; <a href="/earth/board/freeList.et?pageNum=${startPage+pageBlock}&sel=${sel}&search=${search}" class="pageNums">Next</a>
								</c:if>
							</c:if>
							
							<%-- 검색 안했을때 페이지번호들   --%> 
							<c:if test="${sel == null || search == null}">
								<c:if test="${startPage > pageBlock}">
									<a href="/earth/board/freeList.et?pageNum=${startPage-pageBlock}" class="pageNums">Back</a>
								</c:if>
								<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
									<a href="/earth/board/freeList.et?pageNum=${i}" class="pageNums">${i}</a>
								</c:forEach>
								<c:if test="${endPage < pageCount}">
									<a href="/earth/board/freeList.et?pageNum=${startPage+pageBlock}" class="pageNums">Next</a>
								</c:if>
							</c:if>
						</c:if> <%-- end:count > 0 --%>
					</nav> 
				</div>
				<br /> <br /> <br /> <br /> <br /> <br />
			</c:if>
			</div><!-- row -->
		</div><!-- container -->
	</section>
	<%@ include file = "../include/footer.jsp" %>
<!-- #page -->

</body>
</html>