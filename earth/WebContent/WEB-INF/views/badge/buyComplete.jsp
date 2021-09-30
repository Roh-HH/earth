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
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/flaticon.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/animate-css/animate.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/popup/magnific-popup.css">
    <!-- main css -->
        
    <!-- main css -->
    <link rel="stylesheet" href="/earth/resources/bootstrap/css2/style.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style1.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/responsive.css">
    
<script language="javascript">

	function showPopup(num) {
		window.open("/earth/badge/buyBadge.et?num="+num, "a", "width=400, height=300, left=100, top=50");
	}

	function NotReload(){ 
		if((event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82)) || (event.keyCode == 116) ) {
			event.keyCode = 0; 
			event.cancelBubble = true; 
			event.returnValue = false; 
		} 
	} 
			
	document.onkeydown = NotReload;
		
</script>    
    
</head>
<body>
	<%@ include file = "../include/header.jsp" %>
   
    <div class="container">   	
     	 <div class="main_menu" style="text-align:center;">
      
		    <br><br>
			<p style="text-align:center; font-size:18px; font-weight:bold; color:#111111;">뱃지 구매가 완료되었습니다.</p>
			<br><br>
			<button onclick="window.location='/earth/badge/badgeShop.et'"
					style="background-color:#1E88E5; color:#ffffff; border:none;"> 
					계속 쇼핑하기 
			</button>
			
			<button onclick="window.location='/earth/user/myBag.et'"
					style="background-color:#ffffff; color:#1E88E5;  border:none;"> 
					나의 에코백 </button>
						<br><br>
	  	</div>
	 </div>

	<%@ include file = "../include/footer.jsp" %>
</body>
</html>
