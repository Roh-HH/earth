<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en-US">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>공지사항 작성 </title>
	<link rel='stylesheet' href='/earth/resources/bootstrap/css/woocommerce-layout.css' type='text/css' media='all'/>
	<link rel='stylesheet' href='/earth/resources/bootstrap/css/woocommerce-smallscreen.css' type='text/css' media='only screen and (max-width: 768px)'/>
	<link rel='stylesheet' href='/earth/resources/bootstrap/css/woocommerce.css' type='text/css' media='all'/>
	<link rel='stylesheet' href='/earth/resources/bootstrap/css/font-awesome.min.css' type='text/css' media='all'/>
	<link rel='stylesheet' href='/earth/resources/bootstrap/css/style.css' type='text/css' media='all'/>
	<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Oswald:400,500,700%7CRoboto:400,500,700%7CHerr+Von+Muellerhoff:400,500,700%7CQuattrocento+Sans:400,500,700' type='text/css' media='all'/>
	<link rel='stylesheet' href='/earth/resources/bootstrap/css/easy-responsive-shortcodes.css' type='text/css' media='all'/>
</head>
<body class="single single-product woocommerce woocommerce-page">
<div id="page">
	<div class="container">
		<header id="masthead" class="site-header">
			<div class="site-branding">
				<h5 class="site-side">
					<c:if test="${sessionScope.sid == null}">
						<a href="/earth/user/loginForm.et">로그인</a>&ensp;		
						<a href="/earth/user/signupForm.et">회원가입</a>&ensp;
					</c:if>
					<c:if test="${sessionScope.sid != null}">
						<a href="/earth/user/logout.et">로그아웃</a>&ensp;		
						<a href="/earth/user/mypage.et">마이페이지</a>&ensp;
					</c:if>
					<img src='/earth/resources/bootstrap/imgs/message1.png' width="20px" style="align:center">			
				</h5>
				<h1 class="site-title">
					<a href="/earth/main/main.et"><img src='/earth/resources/bootstrap/imgs/logo.png' alt="logo" width="300px"></a>
				</h1>
				<h2 class="site-description">지구를 살리는 작은 실천</h2>
			</div>
			<nav id="site-navigation" class="main-navigation">
				<button class="menu-toggle">Menu</button>
				<div class="menu-menu-1-container">
					<ul id="menu-menu-1" class="menu">
						<li><a href="#">어뜨</a>
							<ul class="sub-menu">
								<li><a href="/earth/board/about.et">어뜨</a></li>
								<li><a href="/earth/board/event.et">행사일정</a></li>
								<li><a href="/earth/board/tip.et">꿀팁</a></li>
							</ul>
						</li>
						<li><a href="#">커뮤니티</a>	
							<ul class="sub-menu">
								<li><a href="/earth/board/diaryList.et">환경일기</a></li>
								<li><a href="/earth/board/freeList.et">자유게시판</a></li>
								<li><a href="/earth/board/dailyChallenge.et">오늘의실천</a></li>						
								<li><a href="/earth/board/monthlyChallenge.et">이달의 챌린지</a></li>
							</ul>
						</li>
						<li><a href="news.et">뉴스</a></li>
						<li><a href="/earth/board/shop.et">상점소개</a></li>
						<li><a href="#">벳지샵</a>
							<ul class="sub-menu">
								<li><a href="/earth/badge/badgeShop.et">벳지샵</a></li>
								<li><a href="/earth/badge/badgeFAQ.et">벳지 사용방법</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</nav>
		</header>
		
<!--------------------------------------------------------------------------------------- #masthead 
		여러분 여기까지는 건들거 없습니다!!!! -->

		<div id="content" class="site-content">
			<div id="primary" class="content-area column full">
				<main id="main" class="site-main" role="main">
					<div id="container">
						<div id="content" role="main">
							
							<!-- .summary -->
							<form action="/earth/board/noticeWritePro.et" method="post" enctype="multipart/form-data">
								<table>
									<tr>
										<td>작성자</td>
										<td align="left">${id}</td> <%-- ${sessionScope.sid} 회원가입 연동후 --%>
									</tr>
									<tr>
										<td>제  목</td>
										<td align="left">
											<input type="text" name="subject" style="width:700px;" />
										</td>
									</tr>
									<tr>
										<td>비밀번호</td>
										<td align="left">
											<input type="password" name="pw" style="width:700px;"/>
										</td>
									</tr>
									<tr>
										<td>내  용</td>
										<td>
											<textarea rows="20" cols="50" name="ctt" style='IME-MODE: active'></textarea>
										</td>
									</tr>
									<tr>
										<td>이미지</td>
										<td align="left"><input type="file" name="uploadFile"/></td>
									</tr>
									<tr>
										<td colspan="2" >
											<input type="submit" value="등 록"
											style="background-color:#1E88E5; color:#ffffff; float:right"/>
											<input type="button" value="목록으로" onclick="window.location='/earth/board/noticeList.et'" 
											style="background-color:#ffffff; color:#1E88E5;"/>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</main>
			</div>
			<!-- #main -->
		</div>
		<!-- #primary -->
		</div>
		<!-- #content -->
	</div>
	<!-- .container -->
<div>
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
</div>
<!-- #page -->
<script src='/earth/resources/bootstrap/js/jquery.js'></script>
<script src='/earth/resources/bootstrap/js/plugins.js'></script>
<script src='/earth/resources/bootstrap/js/scripts.js'></script>
<script src='/earth/resources/bootstrap/js/masonry.pkgd.min.js'></script>
</body>
