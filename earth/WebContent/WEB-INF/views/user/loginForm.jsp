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
   	<script type="text/javascript" src="/earth/resources/bootstrap/js/valCheck.js"></script>
    
   	<script type="text/javascript">
	//유효성검사
	function check(frm){
		if(!checkExistData(frm.id.value, "아이디를") 
				|| !checkExistData(frm.pw.value, "비밀번호를")
		) return false;
	   return true;
	 }
	</script>

</head>
<%-- 로그인 상태에서 로그인폼 접근시  check == 1
<c:if test="${check != 0}">
	<script>
		alert("이미 로그인 상태입니다.");
		window.location = "/earth/user/main.do"; 
	</script>
</c:if>
--%>

<%-- 로그아웃 상태에서 로그인하려고 접근시 check == 0
<c:if test="${check == 0}">
--%>
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
    	<!-- - 여기까지 손대지 마세요!!
    	 -->
    	 <div id="content" class="site-content"> 
    	 <div class="maintext" align="center">
			<form action="/earth/user/loginPro.et" method="post" style="width:300px; height:700px;" onsubmit="return check(this)">
				<table>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="id" width="300px;" autofocus/></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="pw" width="300px;" /></td>
					</tr>			
					<tr>
						<td colspan="2">
							<input type="submit" value="로그인" style="background-color:#1E88E5; border : none;width:330px; height:40px; border-radius:3px;color:#ffffff;"/><br><br>
							<input type="button" value="회원가입" onclick="window.location='/earth/user/signupForm.et'"
									style="background-color:#ffffff; border:none; color:#1E88E5; float:right;"/>
						</td>
					</tr>
				</table>
			</form>
			</div>
		</div>
</div>
</section>
	<!-- .container -->
<%@ include file = "../include/footer.jsp" %>
<!-- #page -->
<script src='/earth/resources/bootstrap/js/jquery.js'></script>
<script src='/earth/resources/bootstrap/js/plugins.js'></script>
<script src='/earth/resources/bootstrap/js/scripts.js'></script>
<script src='/earth/resources/bootstrap/js/masonry.pkgd.min.js'></script>
</body>
<%--</c:if> --%>
</html>


