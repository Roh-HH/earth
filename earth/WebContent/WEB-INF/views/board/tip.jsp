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
width:18px; 
height:18px; 
margin-top:4px; 
margin-right:4px;
}
</style>
<body>
   <%@ include file = "../include/header.jsp" %>
	<section class="category-page area-padding">
        <div class="container">
      	  <h3 class="page-title">어뜨 </h3>
		  <h1 class="entry-title">꿀팁 </h1>
          		<form action="/earth/board/tip.et" >
	         		<div class="input-group" style="width:300px; float:right;">
	         			<select name="sel" style="height:30px;display:none">
							<option value="subject">행사제목</option>
						</select>
                        <input type="text" class="form-control" name="search" placeholder="꿀팁 검색" style="border-top:none; border-left:none; border-right:none; height:30px;" onfocus="this.placeholder = '꿀팁 검색'" onblur="this.placeholder = '꿀팁 검색'">
                        <input type="image" src="/earth/resources/bootstrap/imgs/icon.png" style="width:18px; height:18px;">
                	</div>
                </form>   		
		<br><br><br>
        <div class="row">
			<c:if test="${count == 0}">	
			  <div class="image-wrap-2">
           		 <div class="image-info">
             		 <h2 class="mb-3">게시글이 없습니다. </h2>			
				</div>
			  </div>
			</c:if>	
			<c:if test="${count != 0}">
			<c:forEach var="article" items="${articleList}">
            <div class="col-md-6 col-lg-4">
                <div class="single-category">
                        <div class="thumb">
							<a href="/earth/board/tipContent.et?pageNum=${pageNum}&boardnum=${article.boardnum}">
								<img src="${pageContext.request.contextPath}/save/${article.img}" style="width:290px; height:290px;"/>
							</a>         
						</div>
                        <div class="short_details">
                         	<div class="meta-top d-flex">
								<img class="badgeImg" src="${pageContext.request.contextPath}/save/admin.png"/>		                        
=                                <a href="#">${article.nickname}</a>
                            </div>
                               <a href="/earth/board/tipContent.et?pageNum=${pageNum}&boardnum=${article.boardnum}">
							<h4>${article.subject}</h4></a><br>
                             <div class="meta-bottom d-flex">
                                <a href="#">조회수 : ${article.readcount}</a>
                            </div>
                         
                        </div>
                     </div> 
                </div>  
               </c:forEach>
             </c:if>
               <!--어드민만 글쓰기 버튼이 활성화됩니다. -->
				<c:if test="${sid eq 'admin'}">
				   <div class="col-12 text-center">
					<button type="button"class="main_btn" style="width:80px; height:50px; text-align:center;"
						onclick="window.location='/earth/board/writeTipForm.et'">글쓰기
					</button>	
                	</div>
				</c:if>
            </div>
        </div>
    </section>
  			<%-- 페이지 번호 --%>
				<div align="center">
				<nav class="pagination">
				
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
							<a class="back page-numbers"  href="/earth/board/tip.et?pageNum=${startPage-pageBlock}&sel=${sel}&search=${search}"  class="pageNums"> «back</a>
						</c:if>
						<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
							<a class="page-numbers" href="/earth/board/tip.et?pageNum=${i}&sel=${sel}&search=${search}"  class="pageNums"> &nbsp; ${i} &nbsp; </a>
						</c:forEach>
						<c:if test="${endPage < pageCount}">
							<a class="next page-numbers" href="/earth/board/tip.et?pageNum=${startPage+pageBlock}&sel=${sel}&search=${search}" class="pageNums"> Next»</a>
						</c:if>
					</c:if>
					<%-- 검색 안했을때 페이지번호들   --%> 
					<c:if test="${sel == null || search == null}">
						<c:if test="${startPage > pageBlock}">
							<a class="back page-numbers" href="/earth/board/tip.et?pageNum=${startPage-pageBlock}" class="pageNums"> «back»</a>
						</c:if>

						<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
							<a class="page-numbers" href="/earth/board/tip.et?pageNum=${i}" class="pageNums"> &nbsp; ${i} &nbsp; </a>
						</c:forEach>
						
						<c:if test="${endPage < pageCount}">
							<a class="next page-numbers" href="/earth/board/tip.et?pageNum=${startPage+pageBlock}" class="pageNums"> Next»&gt; </a>
						</c:if>
					</c:if>
					
				</c:if> <%-- end:count > 0 --%>
				</nav>
				</div>
  <%@ include file = "../include/footer.jsp" %>	
</body>
</html>