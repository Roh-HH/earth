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
<script type="text/javascript">
function readURL(input) {
   	if (input.files && input.files[0]) {
       var reader = new FileReader();
       reader.onload = function (e) {
   
       		var thumb=new Image();
       		thumb.src=reader.result;
       		thumb.onload=function(){
       			var canvas=document.createElement('canvas');
       			var ctx=canvas.getContext("2d");
       		
           	var image = new Image();
        	image.src = e.target.result;
      
 
       		canvas.width='300';
       		canvas.height='300';
       		
       		ctx.drawImage(this,0,0,canvas.width,canvas.height);
       		
       		var dataURI=canvas.toDataURL("image/jpeg");
       		
       		document.querySelector("#thumbnail").src=dataURI;
       	}
       };
       reader.readAsDataURL(input.files[0]);

   	}
}
</script>
<body>
<%@ include file="../include/header.jsp" %>
	<section class="category-page area-padding">
        <div class="container">
		<div id="content" class="site-content">
			<div id="primary" class="content-area column full">
				<main id="main" class="site-main" role="main">
				<div id="container">
					<div id="content" role="main">
					<form action="/earth/board/writeTipPro.et" method="post" enctype="multipart/form-data">
							<table>
								<tr>
									<td>작성자</td>
									<td align="left">${id} </td>
								</tr>
								<tr>
									<td>제 목</td>
									<td align="left">
										<input type="text" name="subject" style="width:700px;" />
									</td>
								</tr>
								<tr>
									<td>비밀번호</td>
									<td align="left">
										<input type="password" name="pw" style="width:700px;"/>
									</td>
								</tr>
								<tr>
									<td>내 용</td>
									<td align="left">
										<textarea class="form-control" id="p_content" name="ctt" style='IME-MODE: active'></textarea>
										<script type="text/javascript">
											CKEDITOR.replace('p_content', {filebrowserUploadUrl:'${pageContext.request.contextPath}/fileupload.et'});
										</script>
									</td>								</tr>
								<tr>
									<td>이미지</td>
									<td align="left"><input type="file" name="uploadFile" onchange="readURL(this)" /><br>
										<img src="" id="thumbnail"/> 
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<input type="submit" value="저장" style="background-color:#1E88E5; color:#ffffff; float:right"/>
										<input type="button" value="목록으로" onclick="window.location='/earth/board/tip.et'" style="background-color:#ffffff; color:#1E88E5; " />
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				</main>
			</div>
		</div>
	</div>
	</section>
<%@ include file="../include/footer.jsp" %>
</body>
</html>