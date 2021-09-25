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
</head>
<script>
function readURL(input) {
   	if (input.files && input.files[0]) {
       var reader = new FileReader();
       reader.readAsDataURL(input.files[0]);

   
       reader.onload = function (e) {
   
       	var tempImage=new Image();
       	tempImage.src=reader.result;
       	console.log(tempImage);
       	tempImage.onload=function(){
       		var canvas=document.createElement('canvas');
       		var canvasContext=canvas.getContext("2d");
       		
       		
           	var img = new Image();
        	img.src = e.target.result;
        	
        	
       		canvas.width=img.width*0.5;
       		canvas.height=img.height*0.5;
       		
       		canvasContext.drawImage(this,0,0,canvas.width,canvas.height);
       		
       		var dataURI=canvas.toDataURL("image/png");
       		
       		document.querySelector("#thumbnail").src=dataURI;
  
       	}
       };
   
   	}
}
</script>	
<body>

	<%@ include file = "../include/header.jsp" %>


	<section class="category-page area-padding">
        <div class="container">
		<!-- #masthead -->

	

	<img src="/earth/resources/bootstrap/imgs/default_original.png" style="border: 2px dotted black; float:center; " id="thumbnail"/> 

	<form action="/earth/badge/addBadgePro.et" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>뱃지 이름</td>
				<td align="left">
					<input type="text" name="badgename" style="width:700px;" />
				</td>
			</tr>
			<tr>
				<td>뱃지 가격</td>
				<td align="left">
					<input type="text" name="badgeprice" style="width:700px;"/>
				</td>
			</tr>
			<tr>
				<td>뱃지 이미지</td>
				<td align="left"><input type="file" name="img" onchange="readURL(this)"></td>
			</tr>
			<tr>
				<td colspan="2" >
					<input type="submit" value="등 록"
					style="background-color:#1E88E5; color:#ffffff; float:right"/>
					<input type="button" value="뱃지샵으로" onclick="window.location='/earth/badge/badgeShop.et'" 
					style="background-color:#ffffff; color:#1E88E5;"/>
				</td>
			</tr>
		</table>
	</form>
	</div><!-- container -->
	</section>
	
	<%@ include file = "../include/footer.jsp" %>
</body>
</html>