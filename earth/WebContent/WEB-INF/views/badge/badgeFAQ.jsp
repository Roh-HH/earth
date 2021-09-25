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
	<link rel='stylesheet' href='/earth/resources/bootstrap/css/easy-responsive-shortcodes.css' type='text/css' media='all'/>
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
								<c:if test="${sessionScope.sid != null and sessionScope.sid != 'admin' }">
									<a href="/earth/user/mypage.et">마이페이지</a>&ensp;
								</c:if>
								<c:if test="${sessionScope.sid eq  'admin'}">
									<a href="/earth/adminmypage/adminUserForm.et">관리자페이지</a>&ensp;
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
                                    <li class="nav-item"><a class="nav-link" href="/earth/board/noticeList.et">공지사항</a></li>
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
                                    <li class="nav-item"><a class="nav-link" href="/earth/board/challengeContent.et">이달의 챌린지</a></li>
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
		<div id="content" class="site-content">
			<div id="primary" class="content-area column full">
				<main id="main" class="site-main">
				
				<article class="hentry">
				<header class="entry-header">
				<h3 class="page-title">
				벳지샵
				</h3>
				<h1 class="entry-title">벳지 FAQ </h1>	
				</header>
				<!-- .entry-header -->
					<div class="wpcmsdev-toggle">
						<h3 class="toggle-title"><a href="#toggle-what-is-lore-ipsum"><i class="fa fa-plus icon-for-inactive"></i><i class="fa fa-minus icon-for-active"></i> Q. 포인트를 모으는 방법은? </a></h3>
						<div id="toggle-what-is-lore-ipsum" class="toggle-content">
							<p>	
								포인트는 댓글, 게시물, 출석체크를 통해 모을 수 있습니다. <br>
								댓글의경우 +5 게시물의경우 +5 출석체크의 경우 +5 로 매일 최대 15점까지 받을 수 있습니다. <br>
								주의하실점은 포인트 획득은 하루에 게시글1번, 댓글1번, 출석1번으로 정해져있으며 2번이상 댓글을 다시거나 게시물을 쓰셔도<br> 포인트는 부여되지 않습니다.
								최대포인트를 적용해둔 것은 무분별한 게시물 도배를 막기 위함입니다.<br>
							</p>
						</div>
					</div>
					<div class="wpcmsdev-toggle">
							<h3 class="toggle-title"><a href="#toggle-where-does-it-come-from"><i class="fa fa-plus icon-for-inactive"></i><i class="fa fa-minus icon-for-active"></i>Q. 벳지를 구입하는 방법은?</a></h3>
							<div id="toggle-where-does-it-come-from" class="toggle-content">
								<p>
								벳지는 포인트로만 구입이 가능합니다. <br>
								마음에 드는 배지를 구입하기 위해서는 가능한 포인트가 필요하며 한번 구입된 벳지는 반환이 불가능합니다. <br>
								신중하게 구매해주세요.<br>
								</p>
							</div>
					</div>
					<div class="wpcmsdev-toggle">
							<h3 class="toggle-title"><a href="#toggle-where-does-it-come-from"><i class="fa fa-plus icon-for-inactive"></i><i class="fa fa-minus icon-for-active"></i>Q. 벳지 착용방법 </a></h3>
							<div id="toggle-where-does-it-come-from" class="toggle-content">
								<p>
								구매한 벳지는 마이페이지-나의 에코백에 저장됩니다. <br>
								나의 에코백에서 착용하기 버튼을 눌러 착용해주세요. <br>
								과도한 뱃지 쇼핑은 환영입니다.<br>
								</p>
							</div>
					</div>
				</article>
				</main>
				<!-- #main -->
			</div>
			<!-- #primary -->
		</div>
		<!-- #content -->
	</div>
</section>
	<!-- .container -->
	<footer id="colophon" class="site-footer">
	<div class="container">
		<div class="site-info">
			<h1 style="color: #ccc;text-align:left;margin-bottom:0;margin-top:0;line-height:1.4;font-size: 25px;">어뜨</h1><br>
			어뜨 운영팀: help@earthKorea.com <br> 지구를 살리는 작은 실천, 어뜨는 건강한 커뮤니티를 위해 엄격하게 관리되고 있습니다. <br><i class="fa fa-love"> Copyright ⓒ 어뜨 All Right Reserved.
			</i>
		</div>
	</div>	
	</footer>
	<a href="#top" class="smoothup" title="Back to top"><span class="genericon genericon-collapse"></span></a>
<!-- #page -->
<script src='/earth/resources/bootstrap/js/jquery.js'></script>
<script src='/earth/resources/bootstrap/js/plugins.js'></script>
<script src='/earth/resources/bootstrap/js/scripts.js'></script>
<script src='/earth/resources/bootstrap/js/masonry.pkgd.min.js'></script>
</body>
</html>