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
    <!-- main css -->
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style1.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/responsive.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 	<script type="text/javascript" src="/earth/resources/bootstrap/js/valCheck.js"></script>	
	<script type="text/javascript">
		//유효성검사
		function check(frm){
			if(!checkExistData(frm.pw.value, "비밀번호를")
					|| !checkNewPw(frm.newpw.value)
			) return false;
		   return true;
		 }
	</script>

</head>

<style>
	.alert{
		color:#212529;
	}
	 .center, .silchun, .heading-section{
     	text-align:center;
     	vertical-align:middle;
     }
     .center_ {
     	text-align:center; 
     	width:800px;
     }
     .right_side, .blog_details{
     	width:800px;
     	height:1200px;
     }
</style> 
<body>

     <!--================ Start header Top Area =================-->
  	<%@ include file = "../include/header.jsp" %>
	<section class="blog_area single-post-area area-padding">
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
								<img src='/earth/resources/bootstrap/imgs/${mybadge}' style="width:70px; height:70px; align:center;"/><br>
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
						<button type="button" style="float:right; background-color:#1E88E5; color:#ffffff; border-radius:5px; float:left;"
							onclick="window.location='/earth/user/myCheck.et'">출석체크하기</button>							
						</li>
						</ul>
                      </aside>
					</div>
				</div>
         		<section class="right_side">   
		                    <div class="blog_details">                       
									<div class="row justify-content-center">
										<div class="col-md-6 text-center mb-5">
											<h2 class="heading-section">비밀번호 변경</h2>
                        				</div><br/>
										<form action="/earth/user/userPwModifyPro.et" name="frm" method="post" onsubmit="return check(this)" >
											<table class="table">
												<tbody>
													<tr>
														<td>현재 사용중인 비밀번호</td>
														<td>
															<input type="password" name="pw" placeholder="현재 비밀번호를 입력해주세요." style="width:300px;" autofocus />
														</td>
													</tr>
													<tr>
														<td>새 비밀번호 </td>
														<td>
															<input type="password" name="newpw" placeholder="새 비밀번호를 입력해주세요." style="width:300px;" />					
														</td>
													</tr>
													<tr>
														<td colspan="2">
															<input type="button" value="변경 취소" onclick="window.location='/earth/user/userModifyForm.et'" style="background-color:#ffffff; width:150px; height:40px; color:#343a40; border:none" />
															<input type="submit" value="비밀번호 변경" style="background-color:#343a40; width:150px; border:none; height:40px; color:#ffffff; float:right;" />
														</td>
													</tr>
												</tbody>
											</table>
										</form>
									</div>					
                    			</div> <%--<div class="blog_details">--%>
                   </section>
                </div>	<!-- <div class="row"> -->	
			</div>		<!-- <div class="container"> -->
		</section>
<%@ include file = "../include/footer.jsp" %>
</body>
</html>