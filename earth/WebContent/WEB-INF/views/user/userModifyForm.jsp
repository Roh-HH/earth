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
	<link rel="stylesheet" href="/earth/resources/bootstrap/css/table.css"> <%--table css 추가요  --%>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="/earth/resources/bootstrap/js/valCheck.js"></script>

	<script type="text/javascript">
		//유효성검사
		 function check(frm){
				if(!checkName(frm.name.value)) {
					return false;
				} else if(!checkExistData(frm.nickname.value, "닉네임을")) {
					return false;
				} else if(!checkPhone(frm.phone.value)) {
					return false;
				} else if(!checkEmail(frm.email.value)) {
					return false;
				} else if(!checkExistData(frm.pw.value, "비밀번호를")) {
					return false;
				}
			   return true;
		 }
		
		$(document).ready(function(){
			$(".modifybtn").prop("disabled", false);
		}); //ready	
			
		$(document).ready(function(){
			$("#nickname").change(function(){			// nickname 입력란에 변화가 있을 경우 동작하기
				var nickVal = $("#nickname").val();		// nickname 입력란에 사용자가 작성한 값을 가져오기 (html에 작성 된 값을 스크립트로 꺼내오기)
				console.log("nickname" + nickname);
				// ajax 요청
				$.ajax({
					url : "/earth/user/ajaxNicknameAvail.et",
					type : "post",
					data : {nickname : nickVal},
					success : function(data){	// data 매개변수 : Controller 에서 리턴해준 결과가 대입
						console.log("success");
						console.log("data : " + data);
						
						var nickResult = $.trim(data);
						if(nickResult === "이미 사용중인 닉네임입니다."){
							$("#nickCheck").text(data);
							$("#nickCheck").css("color", "red");
							$(".modifybtn").prop("disabled", true);					// 중복 된 닉네임의 경우 submit = false
						}else if(nickResult === "사용가능한 닉네임입니다."){
							$("#nickCheck").text(data);
							$("#nickCheck").css("color", "blue");
							$(".modifybtn").prop("disabled", false);
						}
												
					},
					error : function(e) {
						console.log("error 발생");
						console.log(e);
					}				
				}); //ajax
			}); //change		
		}); //ready	
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
     input[type="text"]{
     	border:none;
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
								<h2 class="heading-section">${user.name}님의 회원 정보</h2>
							</div>
						<form action="/earth/user/userModifyPro.et" method="post" name="frm" onsubmit="return check(this)" style="width:800px; height:800px;" >
								<p class="heading-section" align="center"> 어뜨 가입날짜 : <fmt:formatDate value="${user.reg}" pattern="yyyy-MM-dd [E]"/> </p>
								<table class="table">
									<tbody>
									<tr class="alert" role="alert">
										<td>아이디</td>
										<td>
											${user.id}
										</td>
									</tr>
									<tr>
										<td>이름 </td>
										<td>
											<input type="text" name="name" value="${user.name}" autofocus />					
										</td>
									</tr>
									<tr>
										<td>닉네임 </td>
										<td>
											<input type="text" name="nickname" id="nickname" value="${user.nickname}" /> <br/>
											<div id="nickCheck"></div> 
										</td>
									</tr>
									<tr>
										<td>전화번호</td>
										<td>
											<input type="text" name="phone" value="${user.phone}" /> 
										</td>
									</tr>
									<tr>
										<td>이메일</td>
										<td>
											<input type="text" name="email" value="${user.email}"/>
										</td>
									</tr>
									<tr>
										<td>비밀번호</td>
										<td colspan="2" align="center">
											<input type="password" name="pw" placeholder="비밀번호를 입력해주세요." /> 
											<input type="button" value="비밀번호 변경하기" onclick="window.location='/earth/user/userPwModifyForm.et'" style="background-color:#fff; border:none; color:#343a40; float:right;" />
										</td>
									</tr>
									<tr>
										<td colspan="2" class="center"> 
											<input type="submit" value="수정하기" class="modifybtn" style="background-color:#343a40; width:200px;height:40px; border:none;color:#ffffff;" disabled="disabled" />
											<input type="button" value="취소" onclick="window.location='/earth/user/userModifyForm.et'" style="background-color:#fff; width:200px; height:40px; border:1px solid #343a40;color:#343a40; "/><br>
											<input type="button" value="탈퇴하기" onclick="window.location='/earth/user/userDeleteForm.et'" style="background-color:#fff; border:none; color:#343a40; float:right;"/>
										</td>
									</tr>
								</table>
							</form>					
                   	 	</div>
                	</div>	 <%--<div class="blog_details">--%>	
               </section>
			</div>	<!-- <div class="row"> -->
		</div> 		<!-- <div class="container"> -->
		</section>
<%@ include file = "../include/footer.jsp" %>
</body>
</html>