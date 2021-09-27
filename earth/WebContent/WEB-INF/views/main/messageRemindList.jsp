<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="img/favicon.png" type="image/png">
    <title>어뜨 - 쪽지 보관함 </title>
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
		var popUrl = "/earth/main/messageWriteForm.et?uri=/earth/main/messageRemindList.et"
		var popOption = "width=500, height=340, resizable=no, scrollbars=no, status=no;";
		window.open(popUrl,"",popOption);
	}
	function msgContent(messagenum){
		var popUrl = "/earth/main/messageContent.et?pageNum=${pageNum}&uri=/earth/main/messageRemindList.et&messagenum=" + messagenum;
		var popOption = "width=500, height=340, resizable=no, scrollbars=no, status=no;";
		window.open(popUrl,"",popOption);
	}
	$(document).ready(function(){
		var hash = $.trim( window.location.hash );
		if (hash) $('.your-css-selector a[href$="'+hash+'"]').trigger('click');
	});
</script>
<style>
button{
    border: 1px solid #ff7a7f;
    border-radius: 0px;
    background: #ff7a7f;
    color: #fff;
    cursor: pointer;
    -webkit-appearance: button;
    padding: 3px 15px;
}
</style>

<body>
	<%-- 앵커태그(위치이동/지우지말것) --%>
	<a href="#anc"></a>

	<!--================ Start header Top Area =================-->
	<%@ include file = "../include/header.jsp" %>
	<section class="category-page area-padding">
        <div class="container">
            <div class="row" style="width:1000px;">
				<div class="col-lg-30" style="text-align:left; width:200px;">
					<div class="blog_right_sidebar"  >
						<aside class="single_sidebar_widget post_category_widget" >
						<h4 class="widget_title">쪽지함</h4>
							<ul class="list cat-list">
								<li>
									<a href="/earth/main/messageReceiveList.et">받은쪽지함 (${countReceive})</a>
								</li>
								<li>
									<a href="/earth/main/messageSendList.et">보낸쪽지함 (${countSend})</a>
								</li>
								<li>
									<a href="/earth/main/messageRemindList.et">쪽지보관함 (${countRemind})</a>
								</li>
								<li>
									<button type="button" style="float:right; background-color:#1E88E5; border:none; color:#ffffff; border-radius:5px; float:left; width:100px; height:50px;"
									onclick="popupOpen()">쪽지보내기</button><br>	<br>							
								</li>
							</ul>
						</aside><!-- .widget-area -->
					</div>
				</div>

				<section class="right_side" style="width:600px;">        
					<div class="col-lg-8 posts-list">
						<div class="single-post">
							<div align="center">
			                    <div id="anc" class="blog_details" style="width:880px; height:900px;">
					        	<h3 class="page-title">쪽지</h3>
								<h1 class="entry-title">쪽지 보관함</h1>
									<c:if test="${count == 0}">
										<table>
											<tr>
												<td align="center">보관된 쪽지가 없습니다.</td>
											</tr>
										</table>
									</c:if>
									<c:if test="${count != 0}">
										<table >
											<tr>
												<td> 발신자 </td>
												<td> 내용 </td>
												<td> 받은날짜 </td>
												<td> 부가기능 </td>
											</tr>
											<c:forEach var="message" items="${messageList}">
												<tr>
													<td> ${message.id} </td>
													<td>
														<a onclick="msgContent(${message.messagenum}); return false;" class="btn-reply text-uppercase" style="cursor:default;">${message.ctt}</a>
													</td>
													<td>
														<fmt:formatDate value="${message.reg}" pattern="YYYY-MM-dd HH : mm"/>
													</td>
													<td>
														<button onclick="window.location='/earth/main/messageRemind.et?pageNum=${pageNum}&messagenum=${message.messagenum}&uri=/main/messageRemindList.et'"
																style="background-color:#1E88E5; border:none; color:#ffffff">보관 취소</button>
														<button onclick="" style="background-color:#fff; border:none; color:#1E88E5">신고</button>
														<button type="button" class="delete_btn" onclick="window.location='/earth/main/messageDelete.et?pageNum=${pageNum}&messagenum=${message.messagenum}&uri=/main/messageReceiveList.et#anc'">삭제</button>
													</td>
												</tr>
											</c:forEach>
										</table>
										<br />
										
										<%-- 페이지 번호 --%>
										<div align="center" class="col-12 text-center">
											<nav class="pagination" style="float:center;">					
												<c:if test="${count > 0}">
													<c:set var="pageBlock" value="5" />
													<fmt:parseNumber var="res" value="${count / pageSize}" integerOnly="true" />
													<c:set var="pageCount" value="${res + (count % pageSize == 0 ? 0 : 1)}" />
													<fmt:parseNumber var="result" value="${(currentPage-1)/pageBlock}" integerOnly="true" />
													<fmt:parseNumber var="startPage" value="${result * pageBlock + 1}"/>
													<fmt:parseNumber var="endPage" value="${startPage + pageBlock -1}" />
													<c:if test="${endPage > pageCount}">
														<c:set var="endPage" value="${pageCount}" /> 
													</c:if>
													
													<%-- 검색했을때 페이지번호들 --%>
													<c:if test="${sel != null && search != null}">
														<c:if test="${startPage > pageBlock}">
															<a href="/earth/main/messageRemindList.et?pageNum=${startPage-pageBlock}&sel=${sel}&search=${search}#anc" class="pageNums">Back</a>
														</c:if>
														<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
															<a href="/earth/main/messageRemindList.et?pageNum=${i}&sel=${sel}&search=${search}#anc" class="pageNums">${i}</a>
														</c:forEach>
														<c:if test="${endPage < pageCount}">
															<a href="/earth/main/messageRemindList.et?pageNum=${startPage+pageBlock}&sel=${sel}&search=${search}#anc" class="pageNums">Next</a>
														</c:if>
													</c:if>
													
													<%-- 검색 안했을때 페이지번호들   --%> 
													<c:if test="${sel == null || search == null}">
														<c:if test="${startPage > pageBlock}">
															<a href="/earth/main/messageRemindList.et?pageNum=${startPage-pageBlock}#anc" class="pageNums">Back</a>
														</c:if>
														<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
															<a href="/earth/main/messageRemindList.et?pageNum=${i}#anc" class="pageNums">${i}</a>
														</c:forEach>
														<c:if test="${endPage < pageCount}">
															<a href="/earth/main/messageRemindList.et?pageNum=${startPage+pageBlock}#anc" class="pageNums">Next</a>
														</c:if>
													</c:if>
													
												</c:if> <%-- end:count > 0 --%>
											</nav>
											<form action="/earth/main/messageRemindList.et" >
												<select style="boder:none;" name="sel">
				     								<option value="" >-선택-</option>
													<option value="id">발신자 </option>
													<option value="ctt">내용</option>
												</select>
												<input type="text" placeholder="검색" name="search" value="" title="search"
													style="border-top:none; border-left:none; border-right:none;width:130px;height:30px; font-size:11px;"/>
												<input type="image" src="/earth/resources/bootstrap/imgs/icon.png" style="width:18px; height:18px;">
											</form>
											<br/> 
										</div>
										<br /> <br /> <br /> <br /> <br /> <br />
									</c:if>
								</div> <%--<div class="blog_details">--%>
		                	</div>		
						</div>
					</div>
				</section>
			</div>	<!-- <div class="row"> -->
		</div> <!-- <div class="container"> -->
	</section>
	<%@ include file = "../include/footer.jsp" %>
<!-- #page -->

</body>
</html>