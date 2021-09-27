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
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
		var popUrl = "/earth/board/popupForm.et?boardnum=${article.boardnum}&code=4&uri=/earth/board/diaryList.et&pageNum=${pageNum}";
		var popOption = "width=500, height=200, resizable=no, scrollbars=no, status=no;";
		window.open(popUrl,"",popOption);
	}
	function reportArticle(boardnum){
		var popUrl = "/earth/adminmypage/reportForm.et?id=${article.id}&uri=dairy&boardnum=" + boardnum;
		var popOption = "width=500, height=500, resizable=no, scrollbars=no, status=no;";
		window.open(popUrl,"",popOption);
	}
	function reportComment(commentnum, id){
		var popUrl = "/earth/adminmypage/reportForm.et?id=" + id + "&uri=diaryComment&boardnum=" + commentnum;
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
<%@ include file = "../include/header.jsp" %>
 	
<section class="category-page area-padding">
        <div class="container">   
			<c:if test="${sessionScope.sid != null}">
        		<c:if test="${sessionScope.sid != article.id and sessionScope.sid != 'admin'}">
					<button onclick="reportArticle(${article.boardnum}); return false;" class="singo"> 👎🏻 신고</button>
				</c:if>	
			</c:if>
	<h1 align="center">환경일기 </h1>
	<table>
		<tr>
			<td width="200px"> 제목 </td>
			<td colspan="3"> <b>${article.subject}</b> </td>
			<td width="100px"> 조회수 </td>
			<td>${article.readcount} </td>
		</tr>
		<tr>
			<td width="200px"> 작성자 </td>
			<td><img class="badgeImg" src="${pageContext.request.contextPath}/save/${badgeimg}" />
			<a onclick="msgSend('${article.id}'); return false;" class="btn-reply text-uppercase" style="cursor:default;">${nickname}</a>
			</td>
		</tr>
		<tr>
			<td width="200px">  게시글 등록일자 </td>
			<td>
				<fmt:formatDate value="${article.reg}" pattern="YYYY-MM-dd HH : mm"/>
			</td>
		</tr>
		<tr>
			<td colspan="4" height="100"> ${article.ctt} </td>
		</tr>
		<tr>
			<td colspan="6" align="right">
                <c:if test="${sessionScope.sid == article.id}">
					<button onclick="window.location='/earth/board/diaryModifyForm.et?boardnum=${article.boardnum}&pageNum=${pageNum}'"
							style="background-color:#1E88E5; color:#ffffff; border:none;float:right;">수 정</button>
                </c:if> 
                <c:if test="${sessionScope.sid == article.id or sessionScope.sid == 'admin'}">
					<button onclick="popupOpen()"
							style="background-color:#ffffff; color:#1E88E5; border:none;float:right;">삭제</button>
				</c:if>
			</td>
		</tr> 
	</table>

<!-- 좋아요   ♡ ♥-->
	<script>
	// 좋아요 insert 
	function likeajax() {
            var boardnum = $("#boardnum").val();
            var pageNum = $("#pageNum").val();
 			 
			// ajax 요청 
			$.ajax({
				url : "/earth/board/likeUp.et",
				type : "post",
				data:{boardnum : boardnum, pageNum:pageNum}, 
				//data:JSON.stringify(obj),
				//contentType:'application/json; charset=UTF-8',
				success : function(data) { //응답받을 데이터를 받아줄 매개변수 
											//컨트롤러에서 넘어온 데이터가 data 매개변수에 들어간다 
				//alert(" 좋아요 등록  ");
				location.reload();
				},
				 
			}); //ajax
	}
	
	// 좋아요 cancel
	function likeajaxCancel() {
            var boardnum = $("#boardnum").val();
            var pageNum = $("#pageNum").val();
 			 
			// ajax 요청 
			$.ajax({
				url : "/earth/board/likeCancel.et",
				type : "post",
				data:{boardnum : boardnum, pageNum:pageNum}, 
				//data:JSON.stringify(obj),
				//contentType:'application/json; charset=UTF-8',
				success : function(data) { //응답받을 데이터를 받아줄 매개변수 
											//컨트롤러에서 넘어온 데이터가 data 매개변수에 들어간다 
				//alert(" 좋아요 취소  ");
				location.reload();
				},
				 
			}); //ajax
	}
	</script>
 
<c:if test="${sessionScope.sid != null}">
	<div align="center">
		<table>
			<tr>
				<td colspan="2" align="center">
					<input type="hidden" name="boardnum" id="boardnum" value="${article.boardnum}"/>
					<input type="hidden" name="pageNum" id="pageNum" value="${pageNum}"/>
			     <c:if test="${idCheck != 0}">		 
		                <button type="button" id="like" onclick="likeajaxCancel()" class="button button-contactForm"
		                        style="width:150px; height:45px; background-color:#1E88E5; border:none;float:right;">
		                        ♥ 좋아요 ${article.recommend}
		                </button>
				 </c:if>
				 <c:if test="${idCheck == 0}">
				  	<button type="button" id="like" onclick="likeajax()" class="button button-contactForm"
		                        style="width:150px; height:45px; background-color:#1E88E5; border:none;float:right;">
		                       ♡ 좋아요 ${article.recommend}
		             </button>
				  </c:if>
				</td>
			</tr>
		</table>
	</div>
</c:if>

<!-- 댓글  -->
	<script>
	function replyajax() {
            var boardnum = $("#boardnum").val();       
            var pageNum = $("#pageNum").val();
            var pageN = $("#pageN").val(); // 댓글 페이징 처리 
            var ctt = $("#ctt").val();
            var receiver = $("#receiver").val();
            
			// ajax 요청 
			$.ajax({
				url : "/earth/board/diaryReply.et",
				type : "post",
				data:{boardnum : boardnum, pageNum:pageNum, pageN:pageN, ctt:ctt, receiver:receiver},  
				//data:JSON.stringify(obj),
				//contentType:'application/json; charset=UTF-8',
				success : function(data) { //응답받을 데이터를 받아줄 매개변수 
											//컨트롤러에서 넘어온 데이터가 data 매개변수에 들어간다 
				//alert("댓글입력 성공! ");
				location.reload();
				//document.getElementById("ctt").value = "참여하시고 댓글을 올려주세요!"; // input replace
				
				},
				 
			}); 
	}
	</script>
<div>
	<c:if test="${count == 0}">
		<table>
			<tr>
				<td align="center"> 글이 존재하지 않습니다. </td>
			</tr>
		</table>
	</c:if>
	<div class="comments-area">
                    <div class="comment-list">
                    <c:if test="${count != 0}">
						<c:forEach var="replyList" items="${replyList}">
	                       <div class="single-comment justify-content-between d-flex">
	                           <div class="user justify-content-between d-flex">
	                               <div class="thumb">
	                                  <img src="${pageContext.request.contextPath}/save/${replyList.badgeimg}"  alt="" />
	                               </div>
		                              <div class="desc">
			                                  <p class="comment" style="width:800px;">
												${replyList.commen}                                    
											  </p>
		                                  <div class="d-flex justify-content-between">
		                                   <div class="d-flex align-items-center">
		                                      		<a onclick="msgSend('${replyList.writer}'); return false;" 
		                                      		class="btn-reply text-uppercase" style="cursor:default;"> <h5> ${replyList.nickname}</h5> </a>
		                                        	 <p class="date"><fmt:formatDate value="${replyList.regcomm}" pattern="YY-MM-dd HH:mm"/></p>
		                                      </div>
		                                      <div class="reply-btn" style="width:150px;">
		                                     <c:if test="${sessionScope.sid != null}">
		                                     	 <c:if test="${sessionScope.sid == replyList.writer or sessionScope.sid == 'admin'}">
		                                            <input type="hidden" name="commentnum" id="commentnum" value="${replyList.commentnum}">
		                                          	<a onclick="replydelete(); return false;" class="btn-reply text-uppercase"
		                                          		style="cursor:default;">삭제</a>
												</c:if>
												<c:if test="${sessionScope.sid != replyList.writer and sessionScope.sid != 'admin'}">
		                                          	  <button onclick="reportComment(${replyList.commentnum}, '${replyList.writer}'); return false;"
														style="background-color:#ffffff; color:#111; float:right; border:none">신고</button>
												</c:if>
											</c:if>
		                                      </div>
		                                  </div>
		                              </div>
	                           </div>
	                       </div>
							<hr>               
                        </c:forEach>       
					</c:if>	
                </div>
     </div><!-- comments-area -->
</div>	
<!-- 댓글삭제 -->	
<script>
	function replydelete() {
		var commentnum = $("#commentnum").val();
		var categ = "diary";
       	console.log(commentnum);   
       	  
		// ajax 요청 
		$.ajax({
			url : "/earth/board/replydelete.et",
			type : "post",
			data:{commentnum : commentnum, categ:categ},  
			success : function(data) {  
			
				console.log("data : " + data);
				alert(data); 
				location.reload();		 
			},
			error : function(e){
				console.log("error...");
				console.log(e);
			}	 
		}); //ajax
}
</script>

<!-- 페이징 처리후 돌아오는 부분  -->
<a href="#comment"></a>	


<!--  댓글 입력   -->
<c:if test="${sessionScope.sid != null}">
	<div class="comment-form">
           <h4>댓글달기</h4>
           <form class="form-contact comment_form" action="#" id="commentForm">
               <div class="row">
                   <div class="col-12">
                       <div class="form-group">
                        <input type="hidden" name="boardnum" id="boardnum" value="${article.boardnum}"/>
						<input type="hidden" name="pageNum" id="pageNum" value="${pageNum}"/>
						<input type="hidden" name="pageN" id="pageN" value="${pageN}"/>
						<input type="hidden" name="receiver" id="receiver" value="${article.id}"/>
                           <input class="form-control" name="ctt" id="ctt" type="text" placeholder="예쁘고 바른말을 사용합시다.">
                       </div>
                   </div>
               </div>
               <div class="form-group">
                   <button type="button" onclick="replyajax()" class="button button-contactForm"
                   		style="width:150px; height:45px; background-color:#1E88E5; border:none;float:right;">댓글달기</button>
               </div>
           </form>
	</div>
</c:if>
<!-- 페이징처리  -->	
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
					<a class="back page-numbers" href="/earth/board/diaryContent.et?pageN=${startPage-pageBlock}#comment" class="pageNums"> « </a>
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
					<a class="page-numbers" href="/earth/board/diaryContent.et?pageN=${i}&pageNum=${pageNum}&boardnum=${article.boardnum}#comment" class="pageNums">${i}</a>
				</c:forEach>
				<c:if test="${endPage < pageCount}">
					<a class="next page-numbers"href="/earth/board/diaryContent.et?pageN=${startPage+pageBlock}&pageNum=${pageNum}&boardnum=${article.boardnum}#comment" class="pageNums"> »</a>
				</c:if>
		</c:if>  
		</nav> 
	</div>
	<button onclick="window.location='/earth/board/diaryList.et?pageNum=${pageNum}'"
				style="background-color:#ffffff;color:#1E88E5; border:#1E88E5;float:center;">목록으로 </button>
	</div>
</section>
<%@ include file = "../include/footer.jsp" %>
<!-- #page -->

</body>
</html> 