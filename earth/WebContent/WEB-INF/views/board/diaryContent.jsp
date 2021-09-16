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
    
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
    	
	<h1 align="center">환경일기 </h1>
	
	<table>
		<tr>
			<td width="200px"> 제목 </td>
			<td colspan="3"> <b>${article.subject}</b> </td>
			<td width="100px"> 조회수 </td>
			<td>${article.readcount} </td>
		</tr>
		<tr>
			<td width="200px"> 작성자 </td>
			<td> <img src="/earth/resources/bootstrap/imgs/heart.png" alt="badge" style="width:20px;">
			${nicknamectt}</td>
		</tr>
		<tr>
			<td width="200px">  게시글 등록일자 </td>
			<td>
				<fmt:formatDate value="${article.reg}" pattern="YYYY-MM-dd HH : mm"/>
			</td>
			
		</tr>
		<tr>
			<td colspan="4" height="100"> ${article.ctt} </td>
		</tr>
		<tr>
			<td colspan="6" align="right">
                <c:if test="${sessionScope.sid == article.id}">
					<button onclick="window.location='/earth/board/diaryModifyForm.et?boardnum=${article.boardnum}&pageNum=${pageNum}'"
							style="background-color:#1E88E5; color:#ffffff; border:none;float:right;">수 정</button>
                </c:if> 
                <c:if test="${sessionScope.sid == article.id or sessionScope.sid == 'admin'}">
					<button onclick="popupOpen()"
							style="background-color:#ffffff; color:#1E88E5; border:none;float:right;">삭제</button>
					               	
				</c:if>
					<button onclick="window.location='/earth/board/diaryList.et?pageNum=${pageNum}'"
							style="background-color:#ffffff; color:#1E88E5; border:none;">목록으로</button>
				    <a class="btn-reply text-uppercase">신고</a>
			</td>
		</tr> 
	</table>

<!--삭제팝업  -->
    	<script>
		function popupOpen(){
			var popUrl = "/earth/board/popupForm.et?boardnum=${article.boardnum}&code=4&uri=/earth/board/diaryList.et&pageNum=${pageNum}";
			var popOption = "width=500, height=200, resizable=no, scrollbars=no, status=no;";
			window.open(popUrl,"",popOption);
		}
		
		$(document).ready(function(){
			var hash = $.trim( window.location.hash );
			if (hash) $('.your-css-selector a[href$="'+hash+'"]').trigger('click');
		});

	</script>

<!-- 좋아요   ♡ ♥-->
	<script>
	// 좋아요 insert 
	function likeajax() {
            var boardnum = $("#boardnum").val();
            var pageNum = $("#pageNum").val();
 			 
			// ajax 요청 
			$.ajax({
				url : "/earth/board/likeUp.et",
				type : "post",
				data:{boardnum : boardnum, pageNum:pageNum}, 
				//data:JSON.stringify(obj),
				//contentType:'application/json; charset=UTF-8',
				success : function(data) { //응답받을 데이터를 받아줄 매개변수 
											//컨트롤러에서 넘어온 데이터가 data 매개변수에 들어간다 
				//alert(" 좋아요 등록  ");
				location.reload();
				},
				 
			}); //ajax
	}
	
	// 좋아요 cancel
	function likeajaxCancel() {
            var boardnum = $("#boardnum").val();
            var pageNum = $("#pageNum").val();
 			 
			// ajax 요청 
			$.ajax({
				url : "/earth/board/likeCancel.et",
				type : "post",
				data:{boardnum : boardnum, pageNum:pageNum}, 
				//data:JSON.stringify(obj),
				//contentType:'application/json; charset=UTF-8',
				success : function(data) { //응답받을 데이터를 받아줄 매개변수 
											//컨트롤러에서 넘어온 데이터가 data 매개변수에 들어간다 
				//alert(" 좋아요 취소  ");
				location.reload();
				},
				 
			}); //ajax
	}
	</script>
 
<c:if test="${sessionScope.sid != null}">
	<div align="center">
		<table>
			<tr>
				<td colspan="2" align="center">
					<input type="hidden" name="boardnum" id="boardnum" value="${article.boardnum}"/>
					<input type="hidden" name="pageNum" id="pageNum" value="${pageNum}"/>
			     <c:if test="${idCheck != 0}">		 
		                <button type="button" id="like" onclick="likeajaxCancel()" class="button button-contactForm"
		                        style="width:150px; height:45px; background-color:#1E88E5; border:none;float:right;">
		                        ♥ 좋아요 ${article.recommend}
		                </button>
				 </c:if>
				 <c:if test="${idCheck == 0}">
				  	<button type="button" id="like" onclick="likeajax()" class="button button-contactForm"
		                        style="width:150px; height:45px; background-color:#1E88E5; border:none;float:right;">
		                       ♡ 좋아요 ${article.recommend}
		                </button>
				  </c:if>
				</td>
			</tr>
		</table>
	</div>
</c:if>

<!-- 댓글  -->
	<script>
	function replyajax() {
            var boardnum = $("#boardnum").val();
          
            var pageNum = $("#pageNum").val();
            var pageN = $("#pageN").val(); // 댓글 페이징 처리 
            var ctt = $("#ctt").val();
            var receiver = $("#receiver").val();
            
			// ajax 요청 
			$.ajax({
				url : "/earth/board/diaryReply.et",
				type : "post",
				data:{boardnum : boardnum, pageNum:pageNum, pageN:pageN, ctt:ctt, receiver:receiver},  
				//data:JSON.stringify(obj),
				//contentType:'application/json; charset=UTF-8',
				success : function(data) { //응답받을 데이터를 받아줄 매개변수 
											//컨트롤러에서 넘어온 데이터가 data 매개변수에 들어간다 
				alert("댓글입력 성공! ");
				location.reload();
				
				document.getElementById("ctt").value = "참여하시고 댓글을 올려주세요!"; // input replace
				
				},
				 
			}); 
	}
	</script>
<div>
	<c:if test="${count == 0}">
		<table>
			<tr>
				<td align="center"> 글이 존재하지 않습니다. </td>
			</tr>
		</table>
	</c:if>
	<div class="comments-area">
                    <div class="comment-list">
                    <c:if test="${count != 0}">
						<c:forEach var="replyList" items="${replyList}">
	                       <div class="single-comment justify-content-between d-flex">
	                           <div class="user justify-content-between d-flex">
	                               <div class="thumb">
	                                   <img src="/earth/resources/bootstrap/imgs/logo.png" alt="">
	                               </div>
		                              <div class="desc">
			                                  <p class="comment" style="width:800px;">
												${replyList.commen}                                    
											  </p>
		                                  <div class="d-flex justify-content-between">
		                                      <div class="d-flex align-items-center">
		                                          <h5>
		                                       
		                                             ${replyList.writer}
		                                          </h5>
		                                          <p class="date"><fmt:formatDate value="${replyList.regcomm}" pattern="YY-MM-dd HH:mm"/></p>
		                                      </div>
		                                      <div class="reply-btn" style="width:150px;">
		                                      	<c:if test="${sessionScope.sid == replyList.writer or sessionScope.sid == 'admin'}">
		                                             <input type="hidden" name="commentnum" id="commentnum" value="${replyList.commentnum}">
		                                          	<a onclick="replydelete(); return false;" class="btn-reply text-uppercase"
		                                          		style="cursor:default;">삭제</a>
												</c:if>
												<c:if test="${sessionScope.sid != replyList.writer and sessionScope.sid != 'admin'}">
		                                          	<a class="btn-reply text-uppercase">신고</a>
												</c:if>
		                                      </div>
		                                  </div>
		                              </div>
	                           </div>
	                       </div>
							<hr>               
                        </c:forEach>       
					</c:if>	
                </div>
     </div><!-- comments-area -->
</div>	
<!-- 댓글삭제 -->	
<script>
	function replydelete() {
		var commentnum = $("#commentnum").val();
		var categ = "diary";
       	console.log(commentnum);   
       	  
		// ajax 요청 
		$.ajax({
			url : "/earth/board/replydelete.et",
			type : "post",
			data:{commentnum : commentnum, categ:categ},  
			success : function(data) {  
			
				console.log("data : " + data);
				alert(data); 
				location.reload();		 
			},
			error : function(e){
				console.log("error...");
				console.log(e);
			}	 
		}); //ajax
}
</script>

<!-- 페이징 처리후 돌아오는 부분  -->
<a href="#comment"></a>	


<!--  댓글 입력   -->
<c:if test="${sessionScope.sid != null}">
	<div class="comment-form">
           <h4>댓글달기</h4>
           <form class="form-contact comment_form" action="#" id="commentForm">
               <div class="row">
                   <div class="col-12">
                       <div class="form-group">
                        <input type="hidden" name="boardnum" id="boardnum" value="${article.boardnum}"/>
						<input type="hidden" name="pageNum" id="pageNum" value="${pageNum}"/>
						<input type="hidden" name="pageN" id="pageN" value="${pageN}"/>
						<input type="hidden" name="receiver" id="receiver" value="${article.id}"/>
                           <input class="form-control" name="ctt" id="ctt" type="text" placeholder="예쁘고 바른말을 사용합시다.">
                       </div>
                   </div>
               </div>
               <div class="form-group">
                   <button type="button" onclick="replyajax()" class="button button-contactForm"
                   		style="width:150px; height:45px; background-color:#1E88E5; border:none;float:right;"
                   >댓글달기</button>
               </div>
           </form>
	</div>
</c:if>
<!-- 페이징처리  -->	
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
		
				<c:if test="${startPage > pageBlock}">
					<a class="back page-numbers" href="/earth/board/diaryContent.et?pageN=${startPage-pageBlock}#comment" class="pageNums"> «back</a>
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
					<a class="page-numbers" href="/earth/board/diaryContent.et?pageN=${i}&pageNum=${pageNum}&boardnum=${article.boardnum}#comment" class="pageNums"> &nbsp; ${i} &nbsp; </a>
				</c:forEach>
				
				<c:if test="${endPage < pageCount}">
					<a class="next page-numbers"href="/earth/board/diaryContent.et?pageN=${startPage+pageBlock}&pageNum=${pageNum}&boardnum=${article.boardnum}#comment" class="pageNums"> Next»</a>
				</c:if>
	
		</c:if>  
		</nav> 
		<p style="color:grey"> 현재페이지 : ${pageN} </p> 
	</div>
	

	<button onclick="window.location='/earth/board/diaryList.et'"
				style="background-color:#ffffff;color:#1E88E5; border:#1E88E5;float:center;">목록으로 </button>
	
	</div>
</section>
 <br /> <br /> 
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
<!-- #page -->

</body>
</html>