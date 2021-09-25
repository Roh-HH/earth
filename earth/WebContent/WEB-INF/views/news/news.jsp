<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.jsoup.Jsoup" %>
<%@ page import="org.jsoup.nodes.Document" %>
<%@ page import="org.jsoup.nodes.Element" %>
<%@ page import="org.jsoup.select.Elements" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
<style>
table th {
	text-align: center;
	color:#1E88E5;
}
.news {
	width: 80%;
	margin: auto;
	align: center;
	margin-top: 20px;
	border-radius: 15px;
}
.newsElement {
	width: 100%;
}
.newsElement tr{
	height: 50px;
}
.newsElement tr, .newsElement td, .newsElement tr, .newsElement td
	{
	align:center;
}
mark{
	background-color:#fff;
	color:#666666;
}

</style>
<body>
 <%@ include file="../include/header.jsp"%>
<section class="category-page area-padding">		
<div class="container">
  <div class="row" style="width:1000px;">     
  <table class="news">
		<thead>
			<colgroup>
				<col width="100%">
			</colgroup>
			<tr>
				<th>쓰레기 문제 뉴스를 보여드립니다. 모든 뉴스는 네이버에서 참고하였습니다. </th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<table class = "newsElement">
						<colgroup>
							<col width="5%">
							<col width="10%">
							<col width="85%">
						</colgroup>
						<c:forEach var="news2" items="${news2}" varStatus="status">
							<c:if test="${news3[status.index]!=''}">
								<tr>
									<td>&emsp;</td>
									<td>${ status.count}</td>
									<td><a href="${news2}">${news3[status.index]}</a></td>
								</tr>
								<c:out value="${status.end}" />
							</c:if>
						</c:forEach>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
  </div>
</div>
</section>
<%@ include file="../include/footer.jsp"%>
</body>
</html>