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
    
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/responsive.css">
    <script type="text/javascript" src="/earth/resources/ckeditor/ckeditor.js"></script>
    	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 	<script type="text/javascript" src="/earth/resources/bootstrap/js/valCheck.js"></script>
	
	<script type="text/javascript">
		//유효성검사
		function check(frm){
			if(!checkExistData(frm.id.value, "아이디를") 
					|| !checkName(frm.name.value)
					|| !checkExistData(frm.nickname.value, "닉네임을")
					|| !checkExistData(frm.pw.value, "비밀번호를")
					|| !checkExistData(frm.phone.value, "전화번호를")
					|| !checkEmail(frm.email.value)					 
			) return false;
		   return true;
		 }
		/* 방법1. 회원가입 ID 중복확인
			function confirmId(inputForm){  
			if(inputForm.id.value == "" || !inputForm.id.value) {
				alert("아이디를 입력하세요!");
				return;	// 강제종료
			}
			// 팝업 띄우기
			var url = "/earth/user/confirmId.et?id=" + inputForm.id.value; 
			open(url, "confirmId", "toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no, width=300, height=200"); 
		} */
		
		$(document).ready(function(){
			$("#id").change(function(){ 	// id 입력란에 변화가 있을때 동작
				var idVal = $("#id").val(); // id 입력란에 사용자가 작성한 값을 가져오기 
				console.log("id : " + id); 
				// ajax 요청 
				$.ajax({
					url : "/earth/user/ajaxIdAvail.et", 
					type : "post", 
					data : {id : idVal}, 
					success : function(data){ // data 매개변수 = Controller에서 리턴해준 결과가 대입 
						console.log("success!!!!!");
					
						$("#idCheck").text(data);
						$("#idCheck").css("color", "red");
						
						var idResult = $.trim(data);					// 문자열 비교 하기 위해 공백 제거후 변수에 저장
						if(idResult === "이미 사용중인 아이디입니다."){		// 중복 된 아이디의 경우 submit = false
							$('form').submit(false);
						}
												
					}, 
					error : function(e){ 
						console.log("error 발생");
						console.log(e); 
					}
				});//ajax
			});// change
		});// ready
		
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
						
						$("#nickCheck").text(data);
						$("#nickCheck").css("color", "red");
						
						var nickResult = $.trim(data);
						if(nickResult === "이미 사용중인 닉네임입니다."){
							$('form').submit(false);					// 중복 된 닉네임의 경우 submit = false
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
									<a href="/earth/user/myToday.et">마이페이지</a>&ensp;
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

<section class="category-page area-padding" style="float:center;">
 <div class="container">   	
    	<!-- - 여기까지 손대지 마세요!!
<!--------------------------------------------------------------------------------------- #masthead 
		여러분 여기까지는 건들거 없습니다!!!! -->

	<div id="content" class="site-content"> 
     <div class="maintext" align="center">
		<form action="/earth/user/signupPro.et" method="post" name="inputForm" onsubmit="return check(this)" style="width:400px; height:700px;">
			<h2>회원가입</h2>
			<table>
				<tr>
					<td>아이디 </td>
					<td>
						<input type="text" name="id" id="id" placeholder="ID" style="width:250px; height:40px;" required /> <br />
						<div id="idCheck"></div>
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>
						<input type="text" width="400px" name="name"  style="width:250px; height:40px;"/> 
					</td>
				</tr>
				<tr>
					<td>닉네임</td>
					<td>
						<input type="text" width="400px;" name="nickname" id="nickname" style="width:250px; height:40px;" /> <br />
						<div id="nickCheck"></div>
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="password" width="400px;" name="pw" placeholder="password"  style="width:250px; height:40px;"/> 
					</td>
				</tr>
				<tr>
					<td>전화번호 </td>
					<td>
						<input type="text" width="400px;" name="phone" placeholder="ㅡ 포함하여 작성해주세요."  style="width:250px; height:40px;"/> 
					</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>
						<input type="text" width="400px;" name="email" style="width:250px; height:40px;" /> 
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"> 
					<input type="submit" id="submit" value="회원가입" style="background-color:#1E88E5; width:400px; border:none; height:40px; border-radius:3px;color:#ffffff; "/><br><br>
					<input type="button" value="로그인" style="background-color:#ffffff; width:400px; height:40px; border-radius:3px;color:#1E88E5; "/><br>
						<input type="button" value="취소" onclick="window.location='/earth/main/main.et'"
									style="background-color:#ffffff; border:none; color:#1E88E5; float:right;"/>
					</td>
				</tr>
			</table>
		</form>
	</div> <!--  maintext  -->

	<!-- 여기부터는 건들지마세용  -->
	</div><!-- contetn  -->
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
<!-- #page -->
<script src='/earth/resources/bootstrap/js/jquery.js'></script>
<script src='/earth/resources/bootstrap/js/plugins.js'></script>
<script src='/earth/resources/bootstrap/js/scripts.js'></script>
<script src='/earth/resources/bootstrap/js/masonry.pkgd.min.js'></script>
</body>
<%--</c:if> --%>
</html>


	
		
		<!--------------------- #primary ----------------여러분 여기서부터도 건들지 마세요>
		</div>
		<!-- #content -->
	</div>
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
</div>
<!-- #page -->
<script src='/earth/resources/bootstrap/js/jquery.js'></script>
<script src='/earth/resources/bootstrap/js/plugins.js'></script>
<script src='/earth/resources/bootstrap/js/scripts.js'></script>
<script src='/earth/resources/bootstrap/js/masonry.pkgd.min.js'></script>
</body>
</html>