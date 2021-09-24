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

	<script>
	//유효성검사
	function check(frm){
		if(!checkExistData(frm.subject.value, "문의 제목을") 
				|| !checkExistData(frm.ctt.value, "문의 내용을")
		) return false;
	   return true;
	 }	
	</script>

</head>
<body>

     <!--================ Start header Top Area =================-->
  	<%@ include file = "../include/header.jsp" %>
    <!-- 영인이 여기부터 수정가능.  -->   
	<section class="blog_area single-post-area area-padding">
	<%-- // check == 0 : 비로그인  check == 1 : 로그인
	<c:if test="${check == 0}">
		<script>
			alert("로그인후 이용해주세요"); 
			window.location = "/earth/user/loginForm.et";
		</script>
	</c:if>
	
	<c:if test="${check != 0}">
	 --%>
			
    <div class="container">
        <div class="row" style="width:1000px;">        			
		<!-- -------------------------------------------------------- -->
		<!-- 영인아여기 사이드 바라서 경로 잘못됐거나 바꿔야되면 여기서 바꾸면돼!!!! -->	
		      <div class="col-lg-30" style="text-align:left; width:200px;">
                  <div class="blog_right_sidebar">
                      <aside class="single_sidebar_widget search_widget">
                            <div class="form-group">
                              <div class="input-group mb-3">
                              
								<!--  여기 벳지 받아오는 이미지 입니다.  -->
								<img src='/earth/resources/bootstrap/imgs/logo.png' height='100px' width='100px'/><br>
                              </div>
                            </div>
                        	<h4>${user.nickname}</h4>                      	
                        	<!--경고횟수 분기처리 : 일단 경고횟수로만 분기처리 (신고 카운트에 따른 변화X)-->
                        	<c:if test="${user.warncount != 0}">            	
	                        	<p> 
	                        		<a href="/earth/user/mypage.et" style="color:red;"> 
			                        	<c:if test="${user.warncount == 1}">
			                        		경고 - 1회 
			                        	</c:if>
			                        	<c:if test="${user.warncount == 2}">
			                        		경고 - 2회 
			                        	</c:if> 
			                        	<c:if test="${user.warncount == 3}">
			                        		경고 - 3회 
			                        	</c:if> 			                        				                        	 
	                        		</a> 
	                        	</p>
                        	</c:if>      
                      </aside>

                      <aside class="single_sidebar_widget post_category_widget" style="text-align:left; float:left;">
                        <h4 class="widget_title">마이페이지</h4>
                        <ul class="list cat-list">
						<li>
						<a href="/earth/user/userModifyForm.et">내 프로필 보기</a>
						</li>
						<li>
						<a href="/earth/user/myToday.et">나의 실천</a>
						</li>
						<li>
						<a href="/earth/user/myDiary.et">나의 환경일기</a>
						</li>
						<li>
						<a href="/earth/user/myBoard.et">내 게시글 보기</a>
						</li>
						<li>
						<a href="/earth/user/myComment.et">내가 작성한 댓글</a>
						</li>
						<li>
						<a href="/earth/user/myOnetoOne.et">1:1문의</a>
						</li>
						<li>
						<a href="/earth/user/myReport.et">신고내역</a>
						</li>						        
						<li>
						<a href="/earth/user/myBag.et">나의 에코백</a>
						</li>
						<li>
						<button type="button" style="float:right; background-color:#1E88E5; color:#ffffff; border-radius:5px; float:left;">출석체크하기</button><br>	<br>							
						<button type="button" style="float:right; background-color:#ffffff; color:#1E88E5; border-radius:5px; float:left;">출석체크현황</button>							
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
                        <h2>${user.name}님의 1:1 문의</h2>
                        <table>
                        	<tr>
                        		<td>1:1 문의글은 오직 관리자만 열람 및 답변 가능합니다.<br /> 궁금하신 점을 자유롭게 문의해주세요.</td>
                        	</tr>
                        </table>
						<form action="/earth/user/myQnAWritePro.et" method="post" onsubmit="return check(this)" style="width:600px; height:700px;">
							<table>
								<tr>
									<td>아이디 </td>
									<td>${user.id}</td>				
								</tr>
								<tr>
									<td>1:1 문의 제목</td>
									<td>
										<input type="text" width="400px;" name="subject" style="width:450px; height:40px;"/> 
									</td>
								</tr>
								<tr>
									<td>1:1 문의 내용</td>
									<td>
										<input type="text" width="400px;" name="ctt" style="width:450px; height:450px; border-width:3;" /> 
									</td>
								</tr>
								<tr>
									<td colspan="2"> 
										<input type="submit" value="문의하기" style="background-color:#1E88E5; width:300px; border:none; height:40px; border-radius:3px;color:#ffffff; float:right; "/><br /><br />
										<input type="button" value="취소" onclick="window.location='/earth/user/myOnetoOne.et'" style="background-color:#ffffff; border:none; color:#1E88E5; float:right;"/>
									</td>
								</tr>
							</table>
						</form>					
                    </div> <%--<div class="blog_details">--%>
                </div>		
			</div>
		</div>
		</section>
			<!--------------------------------------------------------------------------------------- #masthead 
		여러분 여기서부터는 건들거 없습니다!!!! -->

		<!-- #content -->
		</div>	<!-- <div class="row"> -->
	</div> 		<!-- <div class="container"> -->
</section>
<!-- - 여기서부터는 건들지 마세요.  -->
<%@ include file = "../include/footer.jsp" %>
</body>
<%-- </c:if> --%>
</html>