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
                        <h2>탈퇴 안내</h2>
                        <p style="color:red;">사용하고 계신 아이디(${user.id})는 탈퇴할 경우 재사용 및 복구가 불가능합니다</p>
                        <h5>회원탈퇴를 신청하시기 전에 안내 사항을 꼭 확인해 주세요.</h5>
                        <p>
                        	탈퇴 후에도 게시판형 서비스에 등록한 게시물은 그대로 남아 있습니다.<br/>
                  			게시글 및 댓글은 탈퇴 시 자동 삭제되지 않고 그대로 남아 있습니다. <br/>
                  			삭제를 원하는 게시글이 있다면 반드시 탈퇴 전 삭제하시기 바랍니다.
                  		</p>
                  		<br/>
                                                
						<form action="/earth/user/userDeletePro.et" method="post">
							<table>
								<tr>	
									<td> 정말 탈퇴를 원하실 경우, 비밀번호를 작성해주세요. <br />
										<input type="password" name="pw" style="width:350px; height:30px;" />
									</td>
								</tr>
								<tr>	
									<td><font color=red>
										탈퇴 후에는 아이디 ${user.id} 는 사용이 불가합니다. <br/>
										또한 회원정보가 삭제되어 본인 여부를 확인 할 수 있는 방법이 없어, 게시글을 임의로 삭제 해 드릴 수 없습니다.
									</font></td>
								</tr>
								<tr>
									<td> 
										<input type="checkbox" /> 안내 사항을 모두 확인하였으며, 이에 동의합니다.<br/><br/> 
										<input type="submit" value="탈퇴하기"  style="background-color:#ffffff; width:200px; height:40px; border-radius:3px;color:#1E88E5;" />
										<input type="button" value="취소" onclick="window.location='/earth/user/userModifyForm.et'" style="background-color:#1E88E5; width:200px; border:none; height:40px; border-radius:3px;color:#ffffff;" />
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