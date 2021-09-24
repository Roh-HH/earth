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
<<<<<<< Updated upstream
    
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/responsive.css">
</head>
<body>

     <!--================ Start header Top Area =================-->
    <section class="header-top">
        <div class="container">
            <div class="row align-items-center justify-content-between">
                <div class="col-6 col-lg-4">
                    <div class="float-right">
                        <ul class="header_social">
                            
                        </ul>   
                    </div>
                </div>
                <div class="col-6 col-lg-4 col-md-6 col-sm-6 logo-wrapper">
                    <a href="/earth/main/main.et" class="logo">
                        <img src="/earth/resources/bootstrap/imgs/logo.png" style="width:300px;" alt="">
                    </a>
                    <p> 지구를 살리는 작은 실천</p>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6 search-trigger">
                    <div class="right-button">
                        <ul>
                              <li>	
                            	<c:if test="${sessionScope.sid == null}">
									<a href="/earth/user/loginForm.et">로그인</a>&ensp;
								</c:if>
								<c:if test="${sessionScope.sid != null}">
									<a href="/earth/user/logout.et">로그아웃</a>&ensp;			
								</c:if>
							</li>
							<li>	
                            	<c:if test="${sessionScope.sid == null}">
									<a href="/earth/user/signupForm.et">회원가입</a>&ensp;		
								</c:if>
								<c:if test="${sessionScope.sid != null}">
									<a href="/earth/user/mypage.et">마이페이지</a>&ensp;
								</c:if>
							</li>
				
                            <li><img src="/earth/resources/bootstrap/imgs/message1.png" style="width:20px"></li>
                            
                        </ul>
                    </div>
                </div>
            </div>
        </div>
       
    </section>
    <!--================ End header top Area =================-->

    <!-- Start header Menu Area -->
    <header id="header" class="header_area">
        <div class="main_menu">
            <nav class="navbar navbar-expand-lg navbar-light">
                <div class="container">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        
                    </button>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                        <ul class="nav navbar-nav menu_nav ml-auto mr-auto">
                        
                            <li class="nav-item submenu dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">어뜨</a>
                                <ul class="dropdown-menu">
                                    <li class="nav-item"><a class="nav-link" href="/earth/board/about.et">어뜨</a></li>
                                    <li class="nav-item"><a class="nav-link" href="/earth/board/event.et">행사일정</a></li>
                                    <li class="nav-item"><a class="nav-link" href="/earth/board/tip.et">꿀팁</a></li>
                                </ul>
                            </li>
                            <li class="nav-item submenu dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">커뮤니티</a>
                                <ul class="dropdown-menu">
                                    <li class="nav-item"><a class="nav-link" href="/earth/board/diaryList.et">환경일기</a></li>
                                    <li class="nav-item"><a class="nav-link" href="/earth/board/freeList.et">자유게시판</a></li>
                                    <li class="nav-item"><a class="nav-link" href="/earth/board/dailyChallenge.et">오늘의실천</a></li>
                                    <li class="nav-item"><a class="nav-link" href="/earth/board/challengeList.et">이달의 챌린지</a></li>
                                </ul>
                            </li>
                            <li class="nav-item"><a class="nav-link" href="archive.html">뉴스</a></li>    
                            <li class="nav-item"><a class="nav-link" href="/earth/board/shop.et">상점소개</a></li>
 							 <li class="nav-item submenu dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">벳지샵</a>
                                <ul class="dropdown-menu">
                                    <li class="nav-item"><a class="nav-link" href="/earth/badge/badgeShop.et">벳지샵</a></li>
                                    <li class="nav-item"><a class="nav-link" href="/earth/badge/badgeFAQ.et">벳지 FAQ</a></li>
                                </ul>
                            </li>                        
                          </ul>
                    </div>
                </div>
            </nav>
        </div>
    </header>
    	

=======
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
 
>>>>>>> Stashed changes
	<section class="category-page area-padding">
        <div class="container">
         <h3 class="page-title">
					커뮤니티 
				</h3>
<<<<<<< Updated upstream
				<h1 class="entry-title"> 환경일기  </h1>
				
				<form style="float:right;height:60px;" class="woocommerce-ordering" method="get" action="/earth/board/diaryList.et" >
						<select name="sel" style="height:20px; border:none;">
							<option value="nickname">닉네임</option>
							<option value="ctt">내용</option>	
						</select>
						<input class="form-control" type="text" placeholder="검색" name="search" value="" title="search"
							style="border-top:none; border-left:none; border-right:none;width:150px;height:50px;"/>
							
				</form>
					
			<!-- 	<form class="woocommerce-ordering" method="get">
					<select name="orderby" class="orderby">
						<option value="menu_order" selected="selected">정렬</option>
						<option value="date">최신순</option>
						<option value="price">포인트순</option>
					</select>
				</form> -->
=======
				    	<h1 class="entry-title"> 환경일기  </h1>
					<form action="/earth/board/diaryList.et" >
	         		<div class="input-group" style="width:300px; float:right;">
	         			<select name="sel" style="height:30px;">
							<option value="nickname">닉네임</option>
							<option value="ctt">내용</option>	
						</select>
                       	<input class="form-control" type="text" placeholder="검색" name="search" value="" title="search" style="border-top:none; border-left:none; border-right:none; height:30px;" onfocus="this.placeholder = '내용 검색'" onblur="this.placeholder = '내용 검색'">
                        <input type="image" src="/earth/resources/bootstrap/imgs/icon.png" style="width:18px; height:18px;">
                	</div>
                </form>
>>>>>>> Stashed changes
				<br><br><br>
        	<div class="row">
				<c:if test="${count == 0}">	
				  <div class="image-wrap-2">
           			 <div class="image-info">
             			 <p>게시글이 없습니다. </p>			
					</div>
				  </div>
				 </c:if>
			 <!-- 다희언니용 -->	
			<c:if test="${count != 0}">
				<c:forEach var="article" items="${articleList}">
           	 		<div class="col-md-6 col-lg-4">
                		<div class="single-category">
                        	<div class="thumb">
								<a href="/earth/board/diaryContent.et?pageNum=${pageNum}&boardnum=${article.boardnum}">
								<c:if test="${article.img != null}">
									<img src="${pageContext.request.contextPath}/save/${article.img}" style="width:290px; height:290px;"/>
								</c:if>
								<c:if test="${article.img == null}">
									<img src="" style="width:290px; height:290px;"/>
								</c:if>							
								</a>
							</div>
	                    	<div class="short_details">
		                    	<div class="meta-top d-flex">
<<<<<<< Updated upstream
		                            <a href="#">${article.id}</a>
                                	<a href="#" >조회수 :${article.readcount} </a>
		                        </div>
		                        <a href="/earth/board/diaryContent.et?pageNum=${pageNum}&boardnum=${article.boardnum}">
									<h4>${article.subject}</h4>
								</a><br>
=======
		                    		<img class="badgeImg" src="${pageContext.request.contextPath}/save/${article.badgeimg}" />
		                            <a href="#">${article.nickname}</a>
                                	<a href="#" >조회수 :${article.readcount} </a>
		                        </div>
		                        <a href="/earth/board/diaryContent.et?pageNum=${pageNum}&boardnum=${article.boardnum}">
		                        	<h4 style="height:60px;"> ${article.subject}</h4></a><br>
>>>>>>> Stashed changes
	                           	<div class="meta-bottom d-flex">
	                           		<c:if test="${article.recommend == 0}">	
	                           			<a href="#">♡ ${article.recommend}like</a>
	                           		</c:if>
	                           		<c:if test="${article.recommend > 0}">	
                                		<a href="#">♥ ${article.recommend}like</a>
                                	</c:if>                    
	                            </div>
								<p><fmt:formatDate value="${article.reg}" pattern="YYYY-MM-dd HH:mm"/></p>
								
							</div>
                     	</div> 
                	</div>  
                 </c:forEach>
               </c:if>
				<div class="col-12 text-center">
				<c:if test="${sessionScope.sid != null}">
					<button type="button"class="main_btn" style="text-align:center;"
						onclick="window.location='/earth/board/diaryWriteForm.et'">일기쓰기 
					</button>	
				</c:if>
<<<<<<< Updated upstream
=======
				<c:if test="${sel != null}">
					<button type="button"class="main_btn" style="text-align:center;"
						onclick="window.location='/earth/board/diaryList.et?pageNum=${pageNum}'"class="main_btn">목록으로
					</button>	
				</c:if>
>>>>>>> Stashed changes
                </div>			
 			</div>
        </div>
    </section>
                		
		<br /><br><br>
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
								<a class="back page-numbers"  href="/earth/board/diaryList.et?pageNum=${startPage-pageBlock}&sel=${sel}&search=${search}"  class="pageNums"> «back</a>
							</c:if>
							<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
								<a class="page-numbers" href="/earth/board/diaryList.et?pageNum=${i}&sel=${sel}&search=${search}"  class="pageNums"> &nbsp; ${i} &nbsp; </a>
							</c:forEach>
							<c:if test="${endPage < pageCount}">
								<a class="next page-numbers" href="/earth/board/diaryList.et?pageNum=${startPage+pageBlock}&sel=${sel}&search=${search}" class="pageNums"> Next»</a>
							</c:if>
						</c:if>
						
						<%-- 검색 안했을때 페이지번호들   --%> 
						<c:if test="${sel == null || search == null}">
							<c:if test="${startPage > pageBlock}">
								<a class="back page-numbers" href="/earth/board/diaryList.et?pageNum=${startPage-pageBlock}" class="pageNums"> «back</a>
							</c:if>
							<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
								<a class="page-numbers" href="/earth/board/diaryList.et?pageNum=${i}" class="pageNums"> &nbsp; ${i} &nbsp; </a>
							</c:forEach>
							
							<c:if test="${endPage < pageCount}">
								<a class="next page-numbers" href="/earth/board/diaryList.et?pageNum=${startPage+pageBlock}" class="pageNums"> Next»</a>
							</c:if>
						</c:if>
						
					</c:if> <%-- end:count > 0 --%>
					</nav>
						<p align="center" style="color:grey"> 현재페이지 : ${pageNum} </p>
				</div>
					
<<<<<<< Updated upstream
					
					
	
	
	<footer id="colophon" class="site-footer">
	<div class="container">
		<div class="site-info">
			<h1 style="color: #ccc;text-align:left;margin-bottom:0;margin-top:0;line-height:1.4;font-size: 25px;">어뜨</h1><br>
			어뜨 운영팀: help@earthKorea.com <br> 지구를 살리는 작은 실천, 어뜨는 건강한 커뮤니티를 위해 엄격하게 관리되고 있습니다. <br><i class="fa fa-love"> Copyright ⓒ 어뜨 All Right Reserved.
			</i>
		</div>
	</div>	
	</footer>
<!-- #page -->

</body>
</html>
=======
<%@ include file = "../include/footer.jsp" %>
<!-- #page -->

</body>
</html>				
 
>>>>>>> Stashed changes
