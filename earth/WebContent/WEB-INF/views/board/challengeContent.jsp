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
	
	<!-- 프로그래스 바  -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<style type="text/css">
		progress {
			height: 60px;
			width: 60%;
		}
	</style>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.7.1/gsap.min.js"></script>
		<script type="text/javascript">
			window.onload = function() {
				var dis = document.getElementsByClassName("display");
				gsap.to("progress", {
					duration: 2,
					// value: 'random(0, 100)',
					value: function(i, v) {
							var arrValue = [];
						for(var i = 0; i < dis.length; i++) {
							// arrValue.push((this._targets[i]).getAttribute("data-limit"));
							// console.log(i);
							return v.getAttribute("data-limit"); 
						} 
					},
					onUpdate:function() {
						//값이 바뀔때마다 실행
						console.log(this._targets[0].value);
						dis[0].innerText = '참여도 ' + Math.floor(this._targets[0].value) + '%';
						//dis[0].innerText = Math.floor(100-(this._targets[0].value)) + '%';
					}
				});
			}
		</script>
	<script>
		function popupOpen(){
			var popUrl = "/earth/board/popupForm.et?boardnum=${article.boardnum}&code=4&uri=/earth/board/challengeList.et&pageNum=${pageNum}";
			var popOption = "width=500, height=200, resizable=no, scrollbars=no, status=no;";
			window.open(popUrl,"",popOption);
		}
		
		function reportComment(commentnum, id){
			var popUrl = "/earth/adminmypage/reportForm.et?id=" + id + "&uri=challengecomment&boardnum=" + commentnum;
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
 
</head>
<style>
.badgeImg{
width:20px; 
height:20px; 
margin-bottom:2px;
margin-right:4px;
}
</style>
<body>


<!--================ Start header Top Area =================-->
<%@ include file = "../include/header.jsp" %>   
    
<section class="category-page area-padding">
        <div class="container">   	
      <h3 class="page-title">
					커뮤니티 
				</h3>	
	<h1 class="entry-title"> 이달의 챌린지 </h1>
	<hr>
	<p  style ="text-align: center;" >  
	‘이달의 챌린지’에 참여한 후, 한 달간 도전하며 작은 성취를 느껴보세요 ! 
	 </p>
	 
<!-- 상단 컨텐츠  -->	
	<table>
		<tr>
			<td colspan="2" style ="text-align: center;" > <b style="font-size: 30px;">${article.subject}</b> <br />		 
			</td>
		</tr>
		<tr>
			<td style ="text-align: right;"> 작성자: <img class="badgeImg" src="${pageContext.request.contextPath}/save/admin.png" />${nickname} <br />
			 등록일:  <fmt:formatDate value="${article.reg}" pattern="YYYY-MM-dd HH:mm"/> 
			</td>
		</tr>
	<c:if test="${article.img != null}">
		<tr>	
			 <td colspan="2" height="100" style ="text-align: center;"> <img src="${pageContext.request.contextPath}/save/${article.img}" style="max-width: 80%;" /> </td>
		</tr>
	</c:if>
		<tr>
			<td colspan="2" height="100" style ="text-align: center;"> ${article.ctt} </td>
		</tr>
		
	</table>
<br /> <br />	
<!-- 첼린지 참여  -->
<div align="center"style="text-align:center">
				<p style="font-size:17px; font-weight:bold; color:black;"><span>목표인원 ${article.maxcount}</span> / <span style="color:red;"> 참여인원 ${article.joincount} </span>
</div>
<!-- 프로그래스바  -->
<div align="center">
<c:set var="maxcount" value="${article.maxcount}" />
<c:set var="joincount" value="${article.joincount}" />
<fmt:parseNumber var="joinpercent" value="${joincount/maxcount*100}"/>
<fmt:parseNumber var="rest" value="${100-joinpercent}"/>
	<progress value="0" max="100" data-limit="${joinpercent}" > </progress>
 	 <div class="display" style="color: black; font-size:17px;" >  </div>
 	 		 
</div>

<br><br>
<!--  챌린지참여 에이작스  
article.dateck == 1 : 데이트 마감 안됨 
-->
<div align="center">
<c:if test="${sessionScope.sid != null}">
	<c:if test="${article.dateck == 1}">
		 <c:if test="${article.maxcount > article.joincount}">
		 <p> 챌린지 진행중 	</p>
			<c:if test="${joinidCheck == 0}">
				<td colspan="2" align="center">
					<input type="hidden" name="boardnum" id="boardnum" value="${article.boardnum}"/>
					<input type="hidden" name="pageNum" id="pageNum" value="${pageNum}"/>
				
					<button type="button" onclick="chajax()"
					style="background-color:#1E88E5; color:#ffffff; border:none;float:center;"> 챌린지 참여하기 </button>
				</td>	 
			</c:if>
			<c:if test="${joinidCheck != 0}">
				 <button type="button" 
				 		style="background-color:#ffffff;color:#1E88E5; border:#1E88E5;float:center;">챌린지에 참여하셨습니다! </button>
			</c:if> <!-- 참여여부  -->
		</c:if><!-- 목표인원 보다 참여자가 적을때  -->	
		<c:if test="${article.maxcount == article.joincount}">
			<p> 챌린지가 마감되었습니다!  </p>
			<p>   챌린지 성공 ! 	</p>
		</c:if>
	</c:if>

<c:if test="${article.dateck == 0}">
		<p> 챌린지가 마감되었습니다!  </p>
		<c:if test="${article.maxcount ==  article.joincount}">
			<p>  챌린지 성공 ! </p>
		</c:if>
		<c:if test="${article.maxcount > article.joincount}">
			<p>  챌린지 실패 ! </p>	
		</c:if>	 
	</c:if>	
</c:if>	

<!-- 비로그인   -->
<c:if test="${sessionScope.sid == null}">
	<c:if test="${article.dateck == 0}">
		<p> 챌린지가 마감되었습니다!  </p>
		<c:if test="${article.maxcount == article.joincount}">
			<p> 챌린지 성공 ! 	</p>
		</c:if>
		<c:if test="${article.maxcount > article.joincount}">
			<p> 챌린지 실패 ! 	</p>
		</c:if>
	</c:if>
	
	<c:if test="${article.dateck == 1}">
		<c:if test="${article.maxcount > article.joincount}">
			<p> 챌린지 진행중 	</p>
		</c:if>
		<c:if test="${article.maxcount == article.joincount}">
			<p>  챌린지 성공 ! 	</p>
			<p> 챌린지가 마감되었습니다. </p>
		</c:if>
	</c:if>
</c:if>
</div>
 
<!-- 챌린지 참여 받기  -->
	<script>
	//문서가 준비되면 function안에 있는거를 실행해라 
	function chajax() {
            var boardnum = $("#boardnum").val();
            var pageNum = $("#pageNum").val();
 
			// ajax 요청 
			$.ajax({
				url : "/earth/board/challengeJoin.et",
				type : "post",
				data:{boardnum : boardnum, pageNum:pageNum},  
				//data:JSON.stringify(obj),
				//contentType:'application/json; charset=UTF-8',
				success : function(data) { //응답받을 데이터를 받아줄 매개변수 
											//컨트롤러에서 넘어온 데이터가 data 매개변수에 들어간다 
		
				alert("챌린지에 참여하셨습니다!");
				location.reload();
				},
				 
			}); //ajax
	}
	</script>
<br /> <br />
<!-- 코멘트 리스트   -->

	<script>
	//문서가 준비되면 function안에 있는거를 실행해라 
	function replyajax() {
            var boardnum = $("#boardnum").val();
            var pageNum = $("#pageNum").val();
            var pageN = $("#pageN").val(); // 댓글 페이징 처리 
            var ctt = $("#ctt").val();
 
			// ajax 요청 
			$.ajax({
				url : "/earth/board/challengeReply.et",
				type : "post",
				data:{boardnum : boardnum, pageNum:pageNum, pageN:pageN, ctt:ctt},  
				//data:JSON.stringify(obj),
				//contentType:'application/json; charset=UTF-8',
				success : function(data) { //응답받을 데이터를 받아줄 매개변수 
											//컨트롤러에서 넘어온 데이터가 data 매개변수에 들어간다 
		
				//alert("댓글 성공");
				location.reload();
				},
				 
			}); //ajax
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
			                                             <button onclick="replydelete(); return false;" class="btn-reply text-uppercase"
		                                          		style="background-color:#ffffff; color:#111; float:right; border:none; cursor:default;">삭제</button>
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
		 var categ = "challenge";
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
<!--  코멘트 쓰기 부분 에이작스로 처리  -->
<c:if test="${joinidCheck != 0}">
<div class="comment-form">
           <h4>댓글달기</h4>
           <form class="form-contact comment_form" action="#" id="commentForm">
               <div class="row">
                   <div class="col-12">
                       <div class="form-group">
                        <input type="hidden" name="boardnum" id="boardnum" value="${article.boardnum}"/>
						<input type="hidden" name="pageNum" id="pageNum" value="${pageNum}"/>
						<input type="hidden" name="pageN" id="pageN" value="${pageN}"/>
                        <input class="form-control" name="ctt" id="ctt" type="text" placeholder="예쁘고 바른말을 사용합시다.">
                       </div>
                   </div>
               </div>
               <div class="form-group">
                   <button type="button" onclick="replyajax()" class="button button-contactForm"
                   		style="width:150px; height:45px; background-color:#1E88E5; border:none;float:right;"
                   >댓글달기</button>
               </div>
           </form>
	</div>
</c:if>
	<c:if test="${sessionScope.sid != null}">
			<c:if test="${joinidCheck == 0}">
				<p> 챌린지에 참여하시고 댓글을 올려주세요</p>
			</c:if>
	</c:if>	
		<br>
<%-- 앵커태그(위치이동/지우지말것) --%>
<a href="#comment"></a>

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
				<a class="back page-numbers" href="/earth/board/challengeContent.et?pageN=${startPage-pageBlock}&pageNum=${pageNum}#comment" class="pageNums">«Back</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
				<c:choose>
					<c:when test="${pageN eq i}">
						<a class="current"  href="/earth/board/challengeContent.et?pageN=${pageN}&pageNum=${pageNum}&boardnum=${article.boardnum}#comment"> ${i}</a>
					</c:when>
					<c:otherwise>
						<a class="page-numbers" href="/earth/board/challengeContent.et?pageN=${i}&pageNum=${pageNum}&boardnum=${article.boardnum}#comment" class="pageNums">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${endPage < pageCount}">
				<a class="next page-numbers" href="/earth/board/challengeContent.et?pageN=${startPage+pageBlock}&pageNum=${pageNum}&boardnum=${article.boardnum}#comment" class="pageNums">Next»</a>
			</c:if>
		</c:if> 
		</nav>
	</div>
 <br /> <br />
<div id="button" align="center">	
		<!-- 관리자 -->
		<c:if test="${sessionScope.sid == 'admin'}">
		
		<button onclick="window.location='/earth/board/challengeModifyForm.et?boardnum=${article.boardnum}&pageNum=${pageNum}'"
				style="background-color:#ffffff;color:#1E88E5; border:#1E88E5;float:center;">수정 </button>	
		<button onclick="popupOpen()"
				style="background-color:#ffffff;color:#1E88E5; border:#1E88E5;float:center;">삭제</button> <br>
				
		</c:if><br>
		<!-- 모두 보기  -->	
		<button onclick="window.location='/earth/board/challengeList.et?pageNum=${pageNum}'"class="main_btn">지난챌린지 보기 </button>	
</div>  
</div>
</section>
<%@ include file = "../include/footer.jsp" %>
<!-- #page -->

</body>
</html>