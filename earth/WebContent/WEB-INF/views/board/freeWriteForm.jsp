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
    <script type="text/javascript" src="/earth/resources/ckeditor/ckeditor.js"></script>
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/responsive.css">
</head>
<script type="text/javascript">
	<%-- 유효성검사 --%>
	function sendIt(){
		if (n.subject.value == "") {
			alert("제목을 입력하지 않았습니다.")
			n.subject.focus();
			return false;
		}
		if (n.pw.value == "") {
			alert("비밀번호를 입력하지 않았습니다.")
			n.pw.focus();
			return false;
		}
		 
	}
</script>
<body>
	<!--================ Start header Top Area =================-->
    <%@ include file = "../include/header.jsp" %>
	<section class="category-page area-padding">
        <div class="container">   	
			<div id="content" class="site-content">
				<div id="primary" class="content-area column full">
					<main id="main" class="site-main" role="main">
						<div id="container">
							<div id="content" role="main">
								<form name="n" action="/earth/board/freeWritePro.et" method="post" enctype="multipart/form-data" onsubmit="return sendIt();">
									<table>
										<tr>
											<td>작성자</td>
											<td align="left">${id}</td> <%-- ${sessionScope.sid} 회원가입 연동후 --%>
										</tr>
										<tr>
											<td>제  목</td>
											<td align="left">
												<select name="categ" style="border:3px solid #1E88E5; width:85px;height:38px; text-align:center;">
														<option> 카테고리 선택 </option>
													<c:forEach var="Brackets" items="${Brackets}">
													    <option value="${Brackets.num}">${Brackets.name}</option>
												    </c:forEach>
												</select>
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
											<td>내  용</td>
											<td>
												<textarea class="form-control" id="p_content" name="ctt" style='IME-MODE: active'></textarea>
												<script type="text/javascript">
													CKEDITOR.replace('p_content', {filebrowserUploadUrl:'${pageContext.request.contextPath}/fileupload.et'});
												</script>
											</td>
										</tr>
										<tr>
											<td colspan="2">
											<input type="submit" value="저장" style="background-color:#1E88E5; color:#ffffff; float:right"/>
											<input type="button" value="목록으로" onclick="window.location='/earth/board/freeList.et'" style="background-color:#ffffff; color:#1E88E5; " />
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
	</section>
	<%@ include file = "../include/footer.jsp" %>
<!-- #page -->

</body>
</html>
