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
    
</head>
<body>

<%@ include file = "../include/header.jsp" %> 
 
<section class="category-page area-padding">
        <div class="container">   	
    	<!-- - 여기까지 손대지 마세요!!
    	 -->
<!--------------------------------------------------------------------------------------- #masthead
		여러분 여기까지는 건들거 없습니다!!!! -->
		<div id="content" class="site-content">
			<div id="primary" class="content-area column full">
				<main id="main" class="site-main" role="main">
				<div id="container">
					<div id="content" role="main">				
							<h1 align="center"> Monthly 챌린지 등록하기  </h1>
						    <form action="/earth/board/challengeWritePro.et"  method="post" enctype="multipart/form-data" >	 
							<table>
						       	<tr>
									<td>작성자</td>
									<td align="left">
										 ${sessionScope.sid}
										<!-- 추후 세션아이디에서 뽑기  <td align="left">${sessionScope.sid}</td>  -->
									</td>
								<tr>
									<td>비밀번호</td>
										<td align="left"><input type="password" name="pw" /></td>
								</tr>
								<tr>
									<td>제  목</td>
									<td align="left">
										<input type="text" name="subject" />
									</td>
								</tr>
								<tr>
									<td>내  용</td>
									<td><textarea rows="20" cols="60" name="ctt"></textarea></td>
								</tr>
								<tr>
									<td> 목표인원 </td>
									<td><input type="text" name="maxcount" /></td>
								</tr>
								<tr>
									<td> 마감일자 </td>
									 <td> <input type="text" name="enddate" placeholder="yy/mm/dd"> </td>
									 
								</tr>
								
								<tr>
									<td>이미지</td>
									 <td><input type="file" name="uploadFile" /></td>
								</tr>  
								<tr>
									<td colspan="2">
										<input type="submit" value="등 록"
											style="background-color:#1E88E5; color:#ffffff; float:right"/>
										<input type="button" value="목록으로" onclick="window.location='/earth/board/challengeList.et'" 
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
</section>
<!-- .container -->
 
	<!-- .container -->
 <%@ include file = "../include/footer.jsp" %>
<!-- #page -->

</body>
</html>