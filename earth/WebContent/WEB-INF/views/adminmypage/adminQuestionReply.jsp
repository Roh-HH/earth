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
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/animate-css/animate.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/popup/magnific-popup.css">
    <!-- main css -->
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style1.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/responsive.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
	</script>
	
	
</head>
<body>

<%@ include file = "../include/header.jsp" %>
<section class="category-page area-padding">
        <div class="container">
        <div class="row" style="width:1000px;">
		      <div class="col-lg-30" style="text-align:left; width:200px;">
                  <div class="blog_right_sidebar" style="text-align:left">
                      <aside class="single_sidebar_widget post_category_widget" style="text-align:left; float:left;">
                        <h4 class="widget_title">관리자페이지</h4>
                        <ul class="list cat-list">
							<li>
								<a href="/earth/adminmypage/adminUser.et">회원관리</a>
							</li>
							<li>
								<a href="/earth/adminmypage/adminBoard.et">게시글관리</a>
							</li>
							<li>
								<a href="/earth/adminmypage/adminComment.et">댓글관리</a>
							</li>
							<li>
								<a href="/earth/adminmypage/adminQuestion.et">1:1문의</a>
							</li>
							<li>
								<a href="/earth/adminmypage/adminReport.et">신고</a>
							</li>					
						</ul>
                      </aside>
					<!-- .widget-area -->
				</div>
				<!-- #secondary -->
			</div>
      <section class="right_side" style="width:600px;">        
		<div class="col-lg-8 posts-list">
			<div class="single-post">
				<div align="center">
					<div class="blog_details" style="width:880px; height:900px;">
							<form action="/earth/adminmypage/adminQuestionReplyPro.et?questionnum=${question.questionnum}" method="post" >
								<table>
									<tr>
										<td><strong>작성자 아이디</strong></td>
										<td>${question.id}</td>
									</tr>
									<tr>
										<td><strong>1:1 문의 제목</strong></td>
										<td>${question.subject}</td>
									</tr>
									<tr>
										<td><strong>1:1 문의 내용</strong></td>
										<td>
											<input type="text" value="${question.ctt}" style="width:700px; height:150px; border:none;" readonly="readonly"/>
										</td>
									</tr>
									<tr>
										<td><strong>답변</strong></td>
										<td>
											<textarea name="replyctt" style="width:700px; height:400px; border-width:3;" autofocus="autofocus"></textarea>
										</td>
									</tr>
									<tr>
										<td colspan="2"> 
											<input type="submit" value="답변하기" style="background-color:#343a40; border:none; height:40px; border-radius:3px;color:#ffffff; float:right; "/><br /><br />
											<input type="button" value="취소" onclick="window.location='/earth/adminmypage/adminQuestion.et'" style="background-color:#f3f3f3; border:none; color:#343a40;"/>
										</td>
									</tr>
								</table>
							</form>	
                    	</div><!-- end : class="blog_details"  -->
                	</div>
				</div>
			</div>	
		</section>
		<!-- #content -->
		</div>
	</div>
</section>
<%@ include file = "../include/footer.jsp" %>
</body>
</html>