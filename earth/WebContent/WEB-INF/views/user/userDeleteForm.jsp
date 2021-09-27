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
</head>

	<script>
	// 안내사항 체크 후에만 탈퇴 가능 : submit 버튼 활성화
	function agreeCheck(frm){
	   if (frm.deletebtn.disabled == true) {
	    	frm.deletebtn.disabled = false;
	   }else {
	    	frm.deletebtn.disabled=true;
	   } 
	}
	</script>

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
<%@ include file = "../include/header.jsp" %>
<section class="blog_area single-post-area area-padding">
  <div class="container">
        <div class="row" style="width:1000px; height:1200px;">        			
		      <div class="col-lg-30" style="text-align:left; width:200px;">
                  <div class="blog_right_sidebar">
                      <aside class="single_sidebar_widget search_widget">
                            <div class="form-group">
                              <div class="input-group mb-3">
								<!-- 사용자 착용벳지 이미지 -->
								<img src='/earth/resources/bootstrap/imgs/${mybadge}' style="width:70px; height:70px; align:center;"/><br>
                              </div>
                            </div>
                        	<h4>${user.nickname}</h4>                      	
                        	<!--경고횟수 분기처리 : 경고횟수로만 분기처리 (신고 카운트에 따른 변화X)-->
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
											<h2 class="heading-section">탈퇴 안내</h2>
										</div><br>
                        				<p style="color:red;">* 회원탈퇴를 신청하시기 전에 안내 사항을 꼭 확인해 주세요.</p>
	                        			<div class="center_">
					                        <p style="font-weight:bold;">
					                        		< 안내사항 ><br>
					                        	* 탈퇴 후에도 게시판형 서비스에 등록한 게시물은 그대로 남아 있습니다.<br/>
					                  			삭제를 원하는 게시글이 있다면 반드시 탈퇴 전 삭제하시기 바랍니다.<br><br>
					                  			
					                  			<font color=red>
												* 탈퇴 후에는 아이디 '${user.id}' 는 재사용이 불가한 점 유의해주시기 바랍니다.<br/>
												* 또한 탈퇴 처리 후에는 회원정보가 삭제되어 본인 여부를 확인 할 수 있는 방법이 없어, <br>작성하신 게시글과 댓글을 임의로 삭제 해 드릴 수 없습니다.
												</font>
					                  		</p>
					                  		<br/>
											<form action="/earth/user/userDeletePro.et" name="frm" method="post">
												<table class="table">
													<tbody>						
														<tr>	
															<td class="center"> 정말 탈퇴를 원하실 경우, 비밀번호를 작성해주세요. <br />
																<input type="password" name="pw" style="width:350px; height:50px;" />
															</td>
														</tr>
														<tr>
															<td class="center"> 
																<input type="checkbox" name="agree" onClick="agreeCheck(this.form)"/> 안내 사항을 모두 확인하였으며, 이에 동의합니다.<br/><br/> 
																<input type="submit" name="deletebtn" value="탈퇴하기"  style="background-color:#ffffff; width:200px; height:40px; border:none; color:#343a40;" disabled="disabled"/>
																<input type="button" value="취소" onclick="window.location='/earth/user/userModifyForm.et'" style="background-color:#343a40; width:200px; border:none; height:40px; border-radius:3px;color:#ffffff;" />
															</td>
														</tr>
													</tbody>
												</table>
											</form>					
	                   				 	</div> 
	                   				 </div>
                			</div>	
                </section>	
		</div>
	</div>
</section>
<%@ include file = "../include/footer.jsp" %>
</body>
</html>