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
    <script type="text/javascript" src="/earth/resources/ckeditor/ckeditor.js"></script>
    	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 	<script type="text/javascript" src="/earth/resources/bootstrap/js/valCheck.js"></script>
	
	<script type="text/javascript">
	
		var verifyCheck = false;
		var veriflyCode;
		
		//유효성검사
		function check(form){
			if(!checkExistData(frm.id.value, "아이디를")) {
				return false;
			}  else if(!checkName(frm.name.value)) {
				return false;
			} else if(!checkExistData(frm.nickname.value, "닉네임을")) {
				return false;
			} else if(!checkPw(frm.pw.value)) {
				return false;
			} else if(!checkPhone(frm.phone.value)) {
				return false;
			} else if(!checkEmail(frm.email.value)) {
				return false;
			} else if(verifyCheck == false) {
				return false;
				//return returnfalse("핸드폰을 인증해주세요.")
			}
			return true;
		}
	
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
					
						var idResult = $.trim(data);					// 문자열 비교 하기 위해 공백 제거후 변수에 저장
						
						if(idResult === "이미 사용중인 아이디입니다."){		// 중복 된 아이디의 경우 submit = false
							$("#idCheck").text(data);
							$("#idCheck").css("color", "red");
							$(".signupbtn").prop("disabled", true);
						} else {
							$("#idCheck").text(data);
							$("#idCheck").css("color", "blue");
							$(".signupbtn").prop("disabled", false);
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
						
						var nickResult = $.trim(data);
						if(nickResult === "이미 사용중인 닉네임입니다."){
							$("#nickCheck").text(data);
							$("#nickCheck").css("color", "red");
							$(".signupbtn").prop("disabled", true);					// 중복 된 닉네임의 경우 submit = false
						} else{
							$("#nickCheck").text(data);
							$("#nickCheck").css("color", "blue");
							$(".signupbtn").prop("disabled", false);
						}
												
					},
					error : function(e) {
						console.log("error 발생");
						console.log(e);
					}				
				}); //ajax
			}); //change		
		
		
			// 전화번호 인증(인증번호 전송 후 3분(180000ms) 뒤 인증번호 비활성화)
			$("#sendVerifyBtn").click(function(){
				if(checkPhoneRE($("#phone").val())) {
					$.ajax({
						url : "/earth/user/verifyPhone.et",
						type : "post",
						async : false,
						data : {phone : $("#phone").val(), code : 0},
						success : function(data) {
							verifyCode = data
							$("#phone").attr("readonly", true);
						},
						error : function(e) {
							alert("인증번호 전송 실패")
						}
					})
					var timer = setTimeout(function(){
						$.ajax({
							url : "/earth/user/verifyPhone.et",
							type : "post",
							async : false,
							data : {phone : $("#phone").val(), code : 1},
							success : function(data) {
								verifyCode = data
							}
						})
				    }, 180000)
				} else {
					alert("전화번호를 정확하게 입력해주세요.")
				}
			})
			
			// 인증번호
			$("#verifyBtn").click(function(){
				if(checkVerifyCodeRE($("#verifyCode").val())) {
					if($("#verifyCode").val() == verifyCode) {
						$("#verifyRE").text("인증 성공")
						$("#verifyRE").css("color", "blue")
						$("#verifyCode").attr("readonly", true);
						$("#phone").attr("readonly", true);
						verifyCheck = true;
						console.log(verifyCheck);
					} else {
						alert("인증번호가 틀렸습니다.")
					}
				} else {
					alert("인증번호를 정확하게 입력해주세요.")
				}
			})
			
			// 다시 인증하기
			$("#reVerifyBtn").click(function(){
				$("#verifyCode").removeAttr("readonly");
				$("#phone").removeAttr("readonly");
				verifyCheck = false;
			})
			
			function checkPhoneRE(text) {
				const reg =/^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$/
				if(reg.test(text) == false) {
					return false;
				} else {
					return true;
				}
			}
			
			function checkVerifyCodeRE(text) {
				const reg =/^([0-9]{6})$/
				if(reg.test(text) == false) {
					return false;
				} else {
					return true;
				}
			}
			
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
                            <li><img src="/earth/resources/bootstrap/imgs/silchun.png" style="width:20px"></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
       
    </section>
    <!--================ End header top Area =================-->
<section class="category-page area-padding" style="float:center;">
 <div class="container">   	
	<div id="content" class="site-content"> 
     <div class="maintext" align="center">
		<form action="/earth/user/signupPro.et" method="post" name="frm" onsubmit="return check(this)" style="width:400px; height:700px;">
			<h2>회원가입</h2>
			<table>
				<tr>
					<td>아이디 </td>
					<td>
						<input type="text" name="id" id="id" style="width:250px; height:40px;" required autofocus /> <br />
						<div id="idCheck"></div>
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>
						<input type="text" width="400px" name="name" id="name" style="width:250px; height:40px;"/> 
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
						<input type="password" width="400px;" name="pw" id="pw" placeholder="8자리 ~ 20자리까지 입력해주세요."  style="width:250px; height:40px;"/> 
					</td>
				</tr>
				<tr>
					<td>전화번호 </td>
					<td>
						<input type="number" width="400px;" name="phone" id="phone" placeholder="-를 제외한 숫자만 입력해주세요."  style="width:250px; height:40px;"/><br/>
						<button type="button" class="btn btn-primary verifyBtn" id="sendVerifyBtn">인증번호 전송</button><br/>
					</td>
				</tr>
				<tr>
					<td>본인인증</td>
					<td>
						 <input type="text" class="form-control" name="verifyCode" id="verifyCode" placeholder="인증번호">
						 <button type="button" class="btn btn-primary verifyBtn" id="verifyBtn">인증하기</button>
						 <button type="button" class="btn btn-primary verifyBtn" id="reVerifyBtn">다시 입력</button><br/>
						 <small id="verifyRE" style="color:gray;"></small>
					</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>
						<input type="text" width="400px;" name="email" id="email" placeholder="ex) E-mail@gmail.com" style="width:250px; height:40px;" /> 
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"> 
					<input type="submit" id="submit" class="signupbtn" value="회원가입" style="background-color:#1E88E5; width:400px; border:none; height:40px; border-radius:3px;color:#ffffff;" disabled="disabled" /><br><br>
					<input type="button" value="로그인" onclick="window.location='/earth/user/loginForm.et'" style="background-color:#ffffff; width:400px; height:40px; border-radius:3px;color:#1E88E5; "/><br>
						<input type="button" value="취소" onclick="window.location='/earth/main/main.et'"
									style="background-color:#ffffff; border:none; color:#1E88E5; float:right;"/>
					</td>
				</tr>
			</table>
		</form>
	</div> <!--  maintext  -->
	</div><!-- contetn  -->
</div>
</section>
	<!-- .container -->
<%@ include file = "../include/footer.jsp" %>
<!-- #page -->
</body>
</html>
