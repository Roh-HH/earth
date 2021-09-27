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
</head>
<script>
	function popupOpen(){
		var popUrl = "/earth/board/popupForm.et?boardnum=${article.boardnum}&code=1&uri=/earth/board/noticeList.et&pageNum=${pageNum}"
		var popOption = "width=500, height=200, resizable=no, scrollbars=no, status=no;";
		window.open(popUrl,"",popOption);
	}
</script>
<body>
	<!--================ Start header Top Area =================-->
    <%@ include file = "../include/header.jsp" %>
	<section class="category-page area-padding">
        <div class="container">   	
			<h1 align="center"> 공지사항 </h1>
			<table>
				<tr>
					<td> 제목 </td>
					<td colspan="3"> <b>${article.subject}</b> </td>
				</tr>
				<tr>
					<td> 작성자 </td>
					<td> 관리자 </td>
					<td>
						<fmt:formatDate value="${article.reg}" pattern="YYYY-MM-dd HH : mm"/>
					</td>
					<td> 조회수 : ${article.readcount} </td>
				</tr>
				<tr>
					<td colspan="4" height="100"> ${article.ctt} </td>
				</tr>
				<tr>
					<td colspan="4" align="right"> 
						<c:if test="${sessionScope.sid == 'admin'}">
							<button onclick="window.location='/earth/board/noticeModifyForm.et?boardnum=${article.boardnum}&pageNum=${pageNum}'"
									style="background-color:#1E88E5; color:#ffffff; border:none;float:right;">수 정</button>
							<button onclick="popupOpen()"
									style="background-color:#ffffff; color:#1E88E5; border:none;float:right;">삭제</button>
						</c:if>
						<button onclick="window.location='/earth/board/noticeList.et?pageNum=${pageNum}'"
								style="background-color:#ffffff; color:#1E88E5; border:none;">목록으로</button>
					</td>
				</tr>
			</table>
				
			</div>
			<!-- #container -->
	</section>
	<%@ include file = "../include/footer.jsp" %>
<!-- #page -->

</body>
</html>