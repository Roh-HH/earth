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

<script>
	function popupOpen(){
		var popUrl = "/earth/board/popupForm.et?boardnum=${article.boardnum}&code=2&uri=/earth/board/freeList.et&pageNum=${pageNum}";
		var popOption = "width=500, height=200, resizable=no, scrollbars=no, status=no;";
		window.open(popUrl,"",popOption);
	}
	
	function reportArticle(boardnum){
		var popUrl = "/earth/adminmypage/reportForm.et?id=${article.id}&uri=free&boardnum=" + boardnum;
		var popOption = "width=500, height=500, resizable=no, scrollbars=no, status=no;";
		window.open(popUrl,"",popOption);
	}
	
	function reportComment(commentnum, id){
		var popUrl = "/earth/adminmypage/reportForm.et?id=" + id + "&uri=freeComment&boardnum=" + commentnum;
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

	<!--================ Start header Top Area =================-->
	<%@ include file = "../include/header.jsp" %>

	<section class="category-page area-padding">
	<div class="container">   	
    
	<h1 align="center"> 자유게시판 </h1>
	
	<%-- 앵커태그(위치이동/지우지말것) --%>
	<a href="#comment"></a>
	
	<table>
		<tr>
			<td> 제목 </td>
			<td colspan="3"> <b>${article.subject}</b> </td>
		</tr>
		<tr>
			<td> 작성자 </td>
			<td>
				<a onclick="msgSend('${article.id}'); return false;" class="btn-reply text-uppercase" style="cursor:default;">${article.nickname}</a>
			</td>
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
				<button onclick="window.location='/earth/board/freeModifyForm.et?boardnum=${article.boardnum}&pageNum=${pageNum}'"
						style="background-color:#1E88E5; color:#ffffff; border:none;float:right;">수 정</button>
				<%-- 관리자 접근 시
				<button onclick="window.location='/earth/board/delete.et?boardnum=${article.boardnum}&code=2&uri=/earth/board/freeList.et&pageNum=${pageNum}'">삭제</button>
				 --%>
				<button onclick="popupOpen()"
						style="background-color:#ffffff; color:#1E88E5; border:none;float:right;">삭제</button>
				<c:if test="${sessionScope.sid != article.id}">
					<button onclick="reportArticle(${article.boardnum}); return false;"
							class="singo">신고</button>
				</c:if>
				<button onclick="window.location='/earth/board/freeList.et?pageNum=${pageNum}'"
						style="background-color:#ffffff; color:#1E88E5; border:none;">목록으로</button>
			</td>
		</tr>
	</table>
	<br/>
	<div class="comments-area">
	    <div class="comment-list" id="comment">
		<div> <!-- 댓글 없을때 레이아웃 통일화를 위하여 넣습니다.-김하영-  -->
			<c:if test="${count == 0}">
				<p align="center"> 댓글이 존재하지 않습니다. </p>
			</c:if>	
			
			<%-- 댓글이 하나 이상 있을때 --%>
			<div class="comments-area">
		       <div class="comment-list">
					<c:if test="${count != 0}">
						<c:forEach var="comment" items="${comment}">
						 <div class="single-comment justify-content-between d-flex">
			                 <div class="user justify-content-between d-flex">
			                    <div class="thumb">
			                    	<img src="/earth/resources/bootstrap/imgs/logo.png" alt="">
			                    </div>
				                <div class="desc">
					              <p class="comment" style="width:800px;">
										${comment.ctt}
								 </p>
				                  <div class="d-flex justify-content-between">
				                    <div class="d-flex align-items-center">
				                    	<a onclick="msgSend('${comment.writer}'); return false;" class="btn-reply text-uppercase" style="cursor:default;">${comment.nickname}</a>
										<p class="date"><fmt:formatDate value="${comment.reg}" pattern="YY-MM-dd HH : mm"/></p>
									</div>
				                  <div class="reply-btn" style="width:150px;">
									<%-- 신고버튼 : 본인 외 표시, 삭제버튼 : 작성자 본인 또는 관리자 표시 --%>
									<c:if test="${sessionScope.sid != comment.writer && sesseionScope.sid != null}">
					                    <button onclick="reportComment(${comment.commentnum}, '${comment.writer}'); return false;"
											style="background-color:#ffffff; color:#111; float:right; border:none">신고</button>
									</c:if>

									<%-- 삭제버튼 : 접속 아이디(${sessionScope.sid})와 댓글 작성자(${comment.writer})가 일치하는  경우 또는 접속자가 관리자인 경우 삭제버튼 표시(${article.boardnum} 파라미터로 보내기) --%>
									<c:if test="${sessionScope.sid == comment.writer || sessionScope.sid == 'admin'}">
										<button onclick="window.location='/earth/board/delete.et?boardnum=${comment.commentnum}&code=9&uri=/earth/board/freeContent.et&pageNum=${pageNum}&boardNum=${article.boardnum}'"
										style="background-color:#ffffff; color:#111; float:right; border:none">삭제</button>
									</c:if>
							 	   </div>
				         		  </div>
				                 </div>
			                  </div>
			              </div>
		                </c:forEach>       
					</c:if>	
		       </div>
		    </div><!-- comments-area -->

			</div>
			</div>
			<%-- 페이지 번호 --%>
		 <div align="center">
				<nav class="pagination">
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
					
					<c:if test="${startPage > pageBlock}">
						<a class="back page-numbers" href="/earth/board/freeContent.et?pageNum=${startPage-pageBlock}#comment" class="pageNums">«Back</a>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
						<c:choose>
							<c:when test="${pageNum eq i}">
								<a class="current" href="/earth/board/freeContent.et?pageNum=${pageNum}#comment">${i}</a>
							</c:when>
							<c:otherwise>
								<a class="page-numbers" href="/earth/board/freeContent.et?pageNum=${i}#comment" class="pageNums">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${endPage < pageCount}">
						<a class="next page-numbers" href="/earth/board/freeContent.et?pageNum=${startPage+pageBlock}#comment" class="pageNums">Next»</a>
					</c:if>
				</c:if> <%-- end:count > 0 --%>
				</nav> 
			</div>
			<br/>
				<button onClick="javascript:window.scrollTo({top:0, left:0, behavior:'smooth'})" 
						style="background-color:#1E88E5; color:#ffffff; border-radius:60px; float:right; font-size:20px;">▲</button>
					
			
			<%-- sessinScope.sid로 로그인 시에만 댓글쓸 수 있게 분기처리 필요 --%>
			<c:if test="${sessionScope.sid != null}">
			<div class="comment-form">
		           <h4>댓글달기</h4>
		           <form class="form-contact comment_form" action="/earth/board/uploadFreeComment.et?pageNum=${pageNum}&boardnum=${article.boardnum}" id="commentForm">
		               <div class="row">
		                   <div class="col-12">
		                       <div class="form-group">
									<input type="hidden" name="boardnum" value="${article.boardnum}"/>
									<input type="hidden" name="pageNum" value="${pageNum}"/>
									<input type="hidden" name="receiver" value="${article.id}"/>
									<input type="hidden" name="freenum" value="${article.boardnum}"/>
									<input type="hidden" name="ref" value="${ref}"/>
		                         	<input class="form-control" name="ctt" id="ctt" type="text" placeholder="예쁘고 바른말을 사용합시다.">
								</div>
							</div>
		               </div>
		               <div class="form-group">
		                   <input type="submit" class="button button-contactForm" value="댓글달기"
		                          style="width:150px; border-radius:5px;height:45px; background-color:#1E88E5; border:none;float:right;"
		                   />
						</div>				
					</form>
			</div>
			</c:if>
			
		</div>			<!-- #container -->
		</div>
	</section>
	<%@ include file = "../include/footer.jsp" %>
<!-- #page -->

</body>
</html>
