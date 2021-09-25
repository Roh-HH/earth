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
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/animate-css/animate.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/popup/magnific-popup.css">
    <!-- main css -->
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style1.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/responsive.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

    <%@ include file = "../include/header.jsp" %>
	<section class="category-page area-padding">
    
		<!--------------------------------------------------------------------------------------- #masthead 
		여러분 여기까지는 건들거 없습니다!!!! -->
		
	 <!-- 홍재 여기부터 수정가능.  -->
	 
        <div class="container">
        <div class="row" style="width:1000px;">
        		
        		
        		
        		<!--홍재야 여기 사이드 바라서 경로 잘못됐거나 바꿔야되면 여기서 바꾸면돼!!!! -->
		
		      <div class="col-lg-30" style="text-align:left; width:200px;">
                  <div class="blog_right_sidebar" style="text-align:left">
                      <aside class="single_sidebar_widget post_category_widget" style="text-align:left; float:left;">
                        <h4 class="widget_title">관리자페이지</h4>
                        <ul class="list cat-list">
							<li>
								<a href="/earth/adminmypage/adminUser.et">회원관리</a>
							</li>
							<li>
								<a href="/earth/adminmypage/adminModify.et">운영자권한관리</a>
							</li>
							<li>
								<a href="/earth/adminmypage/adminBoard.et">게시글관리</a>
							</li>
							<li>
								<a href="/earth/adminmypage/adminComment.et">댓글관리</a>
							</li>
							<li>
								<a href="/earth/adminmypage/adminQuestion.et">1:1문의</a>
							</li>
							<li>
								<a href="/earth/adminmypage/adminNotice.et">공지사항</a>
							</li>
							<li>
								<a href="/earth/adminmypage/adminReport.et">신고</a>
							</li>					
						</ul>
                      </aside>
		
				
					<!-- .widget-area -->
				</div>
				<!-- #secondary -->
			</div>
        
        
      <section class="right_side" style="width:600px;">        
        
            <div class="col-lg-8 posts-list">
           

                <div class="single-post">
                   
					<div align="center">
			
                    <div class="blog_details" style="width:880px; height:900px;">
                        <c:if test="${count == 0}">
	           			 <div style="width:400px; height:30px;">
	             			 <p>게시글이 없습니다. </p>			
					  	</div>				        
					  	</c:if>
				        <c:if test="${count != 0}">
				            <table align="center">
				            <tr>
				            	<td align="center"><%--체크박스용--%></td>
				            	<td align="center">권한</td>
				            	<td align="center">ID</td> 
				           	 	<td align="center">닉네임</td>
				           	 	<td align="center">이메일</td>
				       		</tr>
				       		
				      		<%-- 유저정보 view, 옵션추가시 해당 tr onclick으로 유저 세부정보 팝업창 호출 --%>
				      		<c:forEach var="user" items="${UserList}" varStatus="status">
								<tr align="center">
									<td><input type="checkbox" name="info" value="${user.id}"/>
									<td>
									<c:if test="${user.admin == 0}">일반회원</c:if>
									<c:if test="${user.admin == 1}">관리자</c:if>
									<c:if test="${user.admin == 2}">운영자</c:if>
									</td>
									<td>${user.id}</td>
									<td>${user.nickname}</td>
									<td>${user.email}</td>
								</tr>
							</c:forEach>
				            </table>
				            
				            <%-- 페이지번호 조정, 12345/678910 5마다 세트로 묶음 --%>
								<c:set var="pageBlock" value="3" />
								<fmt:parseNumber var="res" value="${count / pageSize}" integerOnly="true" />
								<c:set var="pageCount" value="${res + (count % pageSize == 0 ? 0 : 1)}" />
								<fmt:parseNumber var="result" value="${(currentPage-1)/pageBlock}" integerOnly="true" />
								<fmt:parseNumber var="startPage" value="${result * pageBlock + 1}"/>
								<fmt:parseNumber var="endPage" value="${startPage + pageBlock -1}" />
								<c:if test="${endPage > pageCount}">
									<c:set var="endPage" value="${pageCount}" /> 
								</c:if>
								
								<%-- 검색O 페이지번호 --%>
								<c:if test="${sel != null && search != null}">
									<c:if test="${startPage > pageBlock}">
										<a href="/earth/adminmypage/adminModify.et?pageNum=${startPage-pageBlock}&sel=${sel}&search=${search}" class="pageNums"> &lt; &nbsp;</a>
									</c:if>
									<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
										<a href="/earth/adminmypage/adminModify.et?pageNum=${i}&sel=${sel}&search=${search}" class="pageNums"> &nbsp; ${i} &nbsp; </a>
									</c:forEach>
									<c:if test="${endPage < pageCount}">
										&nbsp; <a href="/earth/adminmypage/adminModify.et?pageNum=${startPage+pageBlock}&sel=${sel}&search=${search}" class="pageNums"> &gt; </a>
									</c:if>
								</c:if>
								
								<%-- 검색X 페이지번호   --%> 
								<c:if test="${sel == null || search == null}">
									<c:if test="${startPage > pageBlock}">
										<a href="/earth/adminmypage/adminModify.et?pageNum=${startPage-pageBlock}" class="pageNums"> &lt; &nbsp;</a>
									</c:if>
									<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
										<c:if test="${pagenum == i}">
										<h3 style="colorgray">${i}</h3>
										</c:if>
										<c:if test="${pagenum != i}">
										<a href="/earth/adminmypage/adminModify.et?pageNum=${i}" class="pageNums"> &nbsp; ${i} &nbsp; </a>
										</c:if>
									</c:forEach>
									<c:if test="${endPage < pageCount}">
										&nbsp; <a href="/earth/adminmypage/adminModify.et?pageNum=${startPage+pageBlock}" class="pageNums"> &gt; </a>
									</c:if>
								</c:if>
							</c:if> 
							
							
							<br /> <br />
						
							
							
							<br /> 
							<c:if test="${sel != null && search != null}">
								<button onclick="window.location='/earth/adminmypage/adminModify.et'">초기화</button> <br />
							</c:if>
							 <%-- 작성자/내용 검색 --%>
		            	<div class="text-align:right;">
							<form style="float:right;height:60px;" class="woocommerce-ordering" method="get" action="/earth/adminmypage/adminModify.et" >
							
								<select name="sel">
									<option value="id">아이디</option>
								</select>
							
								<input class="form-control" type="text" placeholder="아이디" name="search" value="" title="search"
									style="border-top:none; border-left:none; border-right:none;width:150px;height:50px;"/>
							</form>
						</div>
									
							
							
                    </div>
                </div>
			</div>
		</div>	
		</section>
			<!--------------------------------------------------------------------------------------- #masthead 
		여러분 여기서부터는 건들거 없습니다!!!! -->
		
		<!-- -------------------------------------------------------- -->

		<!-- #content -->
	</div>
	</div>

<!-- - 여기서부터는 건들지 마세요.  -->

</section>

<%@ include file = "../include/footer.jsp" %>

</body>
</html>