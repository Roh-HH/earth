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
<style>
	.badgeImg{
		width:20px; 
		height:20px; 
		margin-bottom:2px;
		margin-right:4px;
	}
	.singo{
		float:right; 
		background-color:#f3f3f3; 
		border:none; 
		border-radius:3px;
		margin-bottom:15px;
	}
</style>
<script type="text/javascript">
	<%-- 유효성검사 --%>
	function sendIt(){
		if (f.ctt.value == "" && f.condition.value == "") {
			alert("내용과 실천사항을 입력하지 않았습니다.")
			f.ctt.focus();
			return false;
		}
		
		if (f.ctt.value == "") {
			alert("내용을 입력하지 않았습니다.")
			f.ctt.focus();
			return false;
		}
		
		if (f.condition.value == "") {
			alert("실천사항을 체크하지 않았습니다.")
			return false;
		}
	}
	
	function reportComment(commentnum, id){
		var popUrl = "/earth/adminmypage/reportForm.et?id=" + id + "&uri=today&boardnum=" + commentnum;
		var popOption = "width=500, height=500, resizable=no, scrollbars=no, status=no;";
		window.open(popUrl,"",popOption);
	}
	
	function msgSend(receiver){
		var popUrl = "/earth/main/messageWriteForm.et?receiver=" + receiver;
		var popOption = "width=500, height=340, resizable=no, scrollbars=no, status=no;";
		window.open(popUrl,"",popOption);
	}
	
	$(document).ready(function(){
		var hash = $.trim( window.location.hash );
		if (hash) $('.your-css-selector a[href$="'+hash+'"]').trigger('click');
	});
</script>
<body>
	<%-- 앵커태그(위치이동/지우지말것) --%>
	<a href="#list"></a>
	<a href="#upload"></a>
	<!--================ Start header Top Area =================-->
    <%@ include file = "../include/header.jsp" %>
	<section class="category-page area-padding">
        <div class="container">   	
    		<h3 class="page-title" id="list">커뮤니티</h3>
			<h1 class="entry-title">오늘의 실천 </h1>
			<%-- 글이 하나도 없을때 --%>
			<c:if test="${count == 0}">
				<p align="center"> 아직 오늘의 실천 게시글이 없습니다.</p>
			</c:if>	
			
			<%-- 글이 하나 이상 있을때 --%>
			<div class="comments-area">
			    <div class="comment-list">			
					<c:if test="${count != 0}">
						<c:forEach var="article" items="${articleList}">
							<div class="single-comment justify-content-between d-flex">
								<div class="user justify-content-between d-flex">
									<div class="thumb">
										<img src='${pageContext.request.contextPath}/save/${article.badgeimg}' style="width:40px; height:40px;"/><br>
									</div>
									<div class="desc">
										<p class="comment" style="color:#1E88E5; font-weight:bold; width:800px;">
											<c:if test="${article.condition == 1}">
												텀블러 들고다니기
											</c:if>
											<c:if test="${article.condition == 2}">
												샴푸 대신 샴푸바 / 손세정제 대신 비누
											</c:if>
											<c:if test="${article.condition == 3}">
												일회용 빨대 사용하지말기
											</c:if>
											<c:if test="${article.condition == 4}">
												배달음식 그릇에 테이크아웃 해오기
											</c:if>
											<c:if test="${article.condition == 5}">
												장바구니 들고다니기
											</c:if>
											<c:if test="${article.condition == 6}">
												배달음식 시킬떄 일회용품 거절하기
											</c:if>
											<c:if test="${article.condition == 7}">
												손수건 챙기기
											</c:if>
											<c:if test="${article.condition == 8}">
												페트병에 라벨 제거하고 분리수거하기
											</c:if>
											<c:if test="${article.condition == 9}">
												스탠, 유리제품 사용하기
											</c:if>
											<c:if test="${article.condition == 10}">
												이면지 사용하기
											</c:if>
											<br/>
										</p>
										<p class="comment" style=" width:800px;">${article.ctt}</p>
										<div class="d-flex justify-content-between">
											<div class="d-flex align-items-center">
												${article.nickname}
												<c:if test="${sessionScope.sid != null}">
												    <a onclick="msgSend('${article.id}'); return false;" class="btn-reply text-uppercase" style="cursor:default;">
												    <img class="badgeImg" src="${pageContext.request.contextPath}/save/msg.png" />
												    </a>
                                                </c:if>
												<fmt:formatDate value="${article.reg}" pattern="HH시 mm분"/><br/>
												
												<%-- 신고 : 본인 외 표시 --%>
												<c:if test="${sessionScope.sid != comment.writer && (sesseionScope.sid != null || sessionScope.sid != '')}">					
													<button onclick="reportComment(${article.boardnum}, '${article.id}'); return false;"
														style="background-color:#ffffff;color:#111; border:none;">신고</button>
												</c:if>
												<%-- 삭제 : 접속 아이디(${sessionScope.sid})와 게시글 작성자(${article.id})가 일치하는  경우 또는 관리자인 경우 삭제버튼 표시(${article.boardnum} 파라미터로 보내기) --%>
												<c:if test="${sessionScope.sid == article.id || sessionScope.sid == 'admin'}">
													<button onclick="window.location='/earth/board/delete.et?boardnum=${article.boardnum}&code=5&uri=/earth/board/dailyChallenge.et&pageNum=${pageNum}'"
													style="background-color:#ffffff;color:#111; border:none;">삭제</button>
												</c:if>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
			<br /><br />
			<%-- 페이지 번호 세팅 --%>
			<div align="center">
				<nav class="pagination">
					<c:if test="${count > 0}">
						<c:set var="pageBlock" value="3" />
						<fmt:parseNumber var="res" value="${count / pageSize}" integerOnly="true" />
						<c:set var="pageCount" value="${res + (count % pageSize == 0 ? 0 : 1)}" />
						<fmt:parseNumber var="result" value="${(currentPage-1)/pageBlock}" integerOnly="true" />
						<fmt:parseNumber var="startPage" value="${result * pageBlock + 1}"/>
						<fmt:parseNumber var="endPage" value="${startPage + pageBlock -1}" />
						<c:if test="${endPage > pageCount}">
							<c:set var="endPage" value="${pageCount}" /> 
						</c:if>
						
						<%-- 페이지번호 --%>
						<c:if test="${startPage > pageBlock}">
							<a class="back page-numbers" href="/earth/board/dailyChallenge.et?pageNum=${startPage-pageBlock}#list" class="pageNums">«Back</a>
						</c:if>
						<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
							<c:choose>
								<c:when test="${pageNum eq i}">
									<a class="current" href="/earth/board/dailyChallenge.et?pageNum=${pageNum}#list">${i}</a>
								</c:when>
								<c:otherwise>
									<a class="page-numbers" href="/earth/board/dailyChallenge.et?pageNum=${i}#list" class="pageNums">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${endPage < pageCount}">
							<a class="next page-numbers" href="/earth/board/dailyChallenge.et?pageNum=${startPage+pageBlock}#list" class="pageNums">Next»</a>
						</c:if>
					</c:if> <%-- end:count > 0 --%>
				</nav>
			</div>
			<br /> <br />
			<c:if test="${sessionScope.sid != null}">
				<div id="upload" class="single-element-widget">
					<h4 class="mb-30 title_color">오늘의 실천 작성하기 </h4>
					<form name="f" action="/earth/board/uploadTodayChallenge.et#list" method="post" onsubmit="return sendIt();"
				 	class="form-contact comment_form" action="#" id="commentForm">
	               		<div class="row">
	                   		<div class="col-12" >
								<input type="radio" name="condition" value="1"/> 텀블러 들고다니기<br/>
								<input type="radio" name="condition" value="2"/> 샴푸 대신 샴푸바 / 손세정제 대신 비누<br/>				
								<input type="radio" name="condition" value="3"/> 일회용 빨대 사용하지말기<br/>
								<input type="radio" name="condition" value="4"/> 배달음식 그릇에 테이크아웃 해오기<br/>
								<input type="radio" name="condition" value="5"/> 장바구니 들고다니기<br/>
								<input type="radio" name="condition" value="6"/> 배달음식 시킬떄 일회용품 거절하기<br/>
								<input type="radio" name="condition" value="7"/> 손수건 챙기기<br/>
								<input type="radio" name="condition" value="8"/> 페트병에 라벨 제거하고 분리수거하기<br/>
								<input type="radio" name="condition" value="9"/> 스탠, 유리제품 사용하기<br/>
								<input type="radio" name="condition" value="10"/> 이면지 사용하기<br/>
							</div>
						</div>
						<div class="comment-form">
							<input class="form-control" name="ctt" id="ctt" type="text" placeholder="오늘의 실천내용을 작성해주세요!"/>
							<br/>
							<input type="submit" value="공유하기" 
	                  			style="width:150px; height:45px; background-color:#1E88E5; border-radius:8px; border:none;float:right; color:#fff;"/>
						</div>
					</form>
				</div>
			</c:if>
			<br /> <br /> <br /> <br />
		</div>
		<!-- #container -->
	</section>
	<%@ include file = "../include/footer.jsp" %>
<!-- #page -->

</body>
</html>
