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
    <title>어뜨 - 지구를 살리는 작은 실천 </title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/bootstrap.css">
    <!-- main css -->
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style1.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/responsive.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="/earth/resources/bootstrap/css/table.css"> <%--table css 추가요  --%>
</head>
<style>
.news {
	width: 80%;
	margin: auto;
	align: center;
	margin-top: 20px;
	border-radius: 15px;
}
.table{
	text-align:center;
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
		<p style="font-weight:bold; font-size:20px;text-align:center;">쓰레기 문제 뉴스를 보여드립니다. 모든 뉴스는 네이버에서 참고하였습니다. </p>
            <div class="row">
            	<div class="news">
					<table class="table" style="text-align:center;">
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
				</div>
		  </div>
	</div>
</section>
<%@ include file="../include/footer.jsp"%>
</body>
</html>