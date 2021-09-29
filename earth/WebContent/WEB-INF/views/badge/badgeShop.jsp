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
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/themify-icons.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/flaticon.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/animate-css/animate.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/popup/magnific-popup.css">
    <!-- main css -->
        
    <!-- main css -->
    <link rel="stylesheet" href="/earth/resources/bootstrap/css2/style.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style1.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/responsive.css">
<script language="javascript">

	function showPopup(num) {
		window.open("/earth/badge/buyBadge.et?num="+num, "a", "width=460, height=370, left=100, top=50");
	}

 </script>    
</head>
<body>
    <%@ include file = "../include/header.jsp" %>
 
        <div class="container">   	
        <!--================Home Gallery Area =================-->
        <section class="home_gallery_area p_120">        
        	
        		<div class="isotope_fillter">
        			<ul class="gallery_filter list">
        				
        			 	<li <c:if test='${filter eq "basic"}'>class="active"</c:if>><a href="/earth/badge/badgeShop.et">최신순</a></li>
						<li <c:if test='${filter eq "popul"}'>class="active"</c:if>><a href="/earth/badge/badgeShop.et?filter=popul">인기순</a></li>
						<li <c:if test='${filter eq "price"}'>class="active"</c:if>><a href="/earth/badge/badgeShop.et?filter=price">포인트순</a></li>
					</ul>
        		</div>     	
						<c:if test="${count == 0}">				
        					<div class="g_item_text">
								<h5>게시글이 없습니다.</h5>
							</div>
						</c:if>
    	 <div class="maintext" align="center">
    	 
        		<div class="gallery_f_inner row imageGallery1">
        		<c:if test="${count != 0}">
				<c:forEach var="article" items="${articleList}">
        			<div class="col-lg-4 col-md-4 col-sm-6 brand manipul design print" style="width:100px;">
        				<div class="h_gallery_item" style="align:center;">
        					<div class="g_img_item" style="float:center;">
								<img src='/earth/save/${article.badgeimg}' style="width:140px; height:140px;align:center;"/>
								<c:if test="${mybadge}"></c:if>
        						<a class="light" onclick="showPopup(${article.badgenum}); return false;" style="color:#ffffff;">
        						<img src="/earth/resources/bootstrap/imgs/badge_buy.png" alt=""></a>
        					</div>
        					<div class="g_item_text">
        						<p style="font-size:17px;font-weight: bold ;">${article.badgename}</p>
        						<p>${article.badgeprice} Point</p>
        					</div>
        				</div>
        			</div>
        			
        		</c:forEach>
				</c:if>
				</div>
        		<c:if test="${sessionScope.sid eq 'admin'}">
	        		<div class="more_btn">
	        			<a class="main_btn" onclick="window.location='/earth/badge/addBadgeForm.et'" style="color:#ffffff;"> 뱃지등록</a>
	        		</div>
				</c:if>
        	</div>
        </section>
        <!--================End Home Gallery Area =================-->
        
 
        </div>
        <!-- .container -->
        
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
							
							<c:if test="${startPage > pageBlock}">
								<a class="back page-numbers" href="/earth/badge/badgeShop.et?pageNum=${startPage-pageBlock}&filter=${filter}" class="pageNums">«back</a>
							</c:if>
		
							<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
								<c:choose>
									<c:when test="${pageNum eq i}">
										<a class="current" href="/earth/badge/badgeShop.et?pageNum=${pageNum}&filter=${filter}">${i}</a>
									</c:when>
									<c:otherwise>
										<a class="page-numbers" href="/earth/badge/badgeShop.et?pageNum=${i}&filter=${filter}" class="pageNums">${i}</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							
							<c:if test="${endPage < pageCount}">
								<a class="next page-numbers" href="/earth/badge/badgeShop.et?pageNum=${startPage+pageBlock}&filter=${filter}" class="pageNums">Next»</a>
							</c:if>
		
							
						</c:if> <%-- end:count > 0 --%>
				</nav>
				</div>
	<%@ include file = "../include/footer.jsp" %>
</body>
</html>
