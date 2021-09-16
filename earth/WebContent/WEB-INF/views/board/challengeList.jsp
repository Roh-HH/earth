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
    	

	<section class="category-page area-padding">
        <div class="container">
         <h3 class="page-title">
					커뮤니티 
				</h3>
				<h1 class="entry-title"> 이달의 챌린지  </h1>

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
								<a href="/earth/board/challengeContent.et?pageNum=${pageNum}&boardnum=${article.boardnum}">
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
		                            <a href="#">${article.id}</a>                         
		                        </div>
		                        <a href="/earth/board/challengeContent.et?pageNum=${pageNum}&boardnum=${article.boardnum}">	 
										<c:if test="${article.dateck == 0}">[챌린지 마감]
											<c:if test="${article.ck == 1}">[챌린지 성공]</c:if>
											<c:if test="${article.ck == 0}">[챌린지 실패]</c:if> 
										</c:if>	
										
										<c:if test="${article.maxcount > article.joincount}">
											<c:if test="${article.dateck == 1}">[챌린지 진행중]	</c:if>	
										</c:if>	
										<c:if test="${article.maxcount == article.joincount}">
											<c:if test="${article.dateck == 1}">[챌린지 마감]	
												<c:if test="${article.ck == 1}">[챌린지 성공]</c:if>
												<c:if test="${article.ck == 0}">[챌린지 실패]</c:if> 
											</c:if>
										</c:if>
										
									<h4>
										<br /> ${article.subject}
									</h4>
								</a><br>
								<p><fmt:formatDate value="${article.reg}" pattern="YYYY-MM-dd HH:mm"/></p>
								
								
								
								
								
							</div>
                     	</div> 
                	</div>  
                 </c:forEach>
               </c:if>
				<div class="col-12 text-center">
				<c:if test="${sessionScope.sid == 'admin'}">
					<button type="button"class="main_btn" style="text-align:center;"
						onclick="window.location='/earth/board/challengeWriteForm.et'"> 챌린지 등록
					</button>	
					<button type="button"class="main_btn" style="text-align:center;"
						oclick="history.back();">뒤로가기 
					</button>
				</c:if>
                </div>			
 			</div>
        </div>
    </section>
                		
		<br /><br><br>
				<%-- 페이지 번호 --%>
	<div align="center">
		<nav class="pagination">
		<c:if test="${count > 0}">
			<c:set var="pageBlock" value="3" />
			<fmt:parseNumber var="res" value="${count / pageSize}" integerOnly="true" />
			<c:set var="pageCount" value="${res + (count % pageSize == 0 ? 0 : 1)}" />
			<fmt:parseNumber var="result" value="${(currentPage-1)/pageBlock}" integerOnly="true" />
			<fmt:parseNumber var="startPage" value="${result * pageBlock + 1}"/>
			<fmt:parseNumber var="endPage" value="${startPage + pageBlock -1}" />
			<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}" /> 
			</c:if>	
		</c:if> <%-- end:count > 0 --%>
			<c:if test="${startPage > pageBlock}">
				<a class="back page-numbers" href="/earth/board/challengeList.et?pageNum=${startPage-pageBlock}" class="pageNums"> «back</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
				<a class="page-numbers" href="/earth/board/challengeList.et?pageNum=${i}" class="pageNums"> &nbsp; ${i} &nbsp; </a>
			</c:forEach>
			<c:if test="${endPage < pageCount}">
				<a class="next page-numbers" href="/earth/board/challengeList.et?pageNum=${startPage+pageBlock}" class="pageNums"> Next»</a>
			</c:if>
		</nav>
			<p align="center" style="color:grey"> 현재페이지 : ${pageNum} </p>
	
	</div>
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