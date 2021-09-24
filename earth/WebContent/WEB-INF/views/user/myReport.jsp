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

	<style>
	
	tr.head_bar {background-color:#CCFFCC;}
	
	</style>        
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<script>
	</script>

</head>
<body>

     <!--================ Start header Top Area =================-->
   	<%@ include file = "../include/header.jsp" %>
    <!-- 영인이 여기부터 수정가능.  -->   
	<section class="blog_area single-post-area area-padding">
	<%-- // check == 0 : 비로그인  check == 1 : 로그인
	<c:if test="${check == 0}">
		<script>
			alert("로그인후 이용해주세요"); 
			window.location = "/earth/user/loginForm.et";
		</script>
	</c:if>
	
	<c:if test="${check != 0}">
	 --%>
			
    <div class="container">
        <div class="row" style="width:1000px;">        			
		<!-- -------------------------------------------------------- -->
		<!-- 영인아여기 사이드 바라서 경로 잘못됐거나 바꿔야되면 여기서 바꾸면돼!!!! -->	
		      <div class="col-lg-30" style="text-align:left; width:200px;">
                  <div class="blog_right_sidebar">
                      <aside class="single_sidebar_widget search_widget">
                            <div class="form-group">
                              <div class="input-group mb-3">
                              
								<!--  여기 벳지 받아오는 이미지 입니다.  -->
								<img src='/earth/resources/bootstrap/imgs/${mybadge}' style="width:70px; height:70px; align:center;"/><br>
                              </div>
                            </div>
                        	<h4>${user.nickname}</h4>                      	
                        	<!--경고횟수 분기처리 : 일단 경고횟수로만 분기처리 (신고 카운트에 따른 변화X)-->
                        	<c:if test="${user.warncount != 0}">            	
	                        	<p> 
	                        		<a href="/earth/user/mypage.et" style="color:red;"> 
			                        	<c:if test="${user.warncount == 1}">
			                        		경고 - 1회 
			                        	</c:if>
			                        	<c:if test="${user.warncount == 2}">
			                        		경고 - 2회 
			                        	</c:if> 
			                        	<c:if test="${user.warncount == 3}">
			                        		경고 - 3회 
			                        	</c:if> 			                        				                        	 
	                        		</a> 
	                        	</p>
                        	</c:if>      
                      </aside>

                      <aside class="single_sidebar_widget post_category_widget" style="text-align:left; float:left;">
                        <h4 class="widget_title">마이페이지</h4>
                        <ul class="list cat-list">
						<li>
						<a href="/earth/user/userModifyForm.et">내 프로필 보기</a>
						</li>
						<li>
						<a href="/earth/user/myToday.et">나의 실천</a>
						</li>
						<li>
						<a href="/earth/user/myDiary.et">나의 환경일기</a>
						</li>
						<li>
						<a href="/earth/user/myBoard.et">내 게시글 보기</a>
						</li>
						<li>
						<a href="/earth/user/myComment.et">내가 작성한 댓글</a>
						</li>
						<li>
						<a href="/earth/user/myOnetoOne.et">1:1문의</a>
						</li>
						<li>
						<a href="/earth/user/myReport.et">신고내역</a>
						</li>						        
						<li>
						<a href="/earth/user/myBag.et">나의 에코백</a>
						</li>
						<li>
						<button type="button" style="float:right; background-color:#1E88E5; color:#ffffff; border-radius:5px; float:left;" onclick="window.location='/earth/user/myCheck.et'">출석체크하기</button><br><br>	
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
                        <h2>${user.name}님의 신고내역</h2>
                        <table>
                        	<tr>
                        		<td>타 회원 대상 신고처리는 관리자의 판단으로 신고 보류 처리 될 수 있습니다</td>
                        	</tr>
                        </table>
							<c:if test="${count == 0}">
								<table>
									<tr>
										<td colspan="2">
											<h5>신고내역이 없습니다.</h5>
										</td>
									</tr>
								</table>
							</c:if>							
							<c:if test="${count != 0}">
								<table>
									<tr class="head_bar">
										<td>신고아이디</td>
										<td>신고내용</td>
										<td>신고일</td>
										<td>신고처리상태</td>
									</tr>
									<c:forEach var="article" items="${rpArticleList}">
										<tr>
											<td>${article.receiver}</td>
											<td>
												<c:if test="${article.condition == 1}">영리목적/홍보성</c:if>
												<c:if test="${article.condition == 2}">불법정보</c:if>
												<c:if test="${article.condition == 3}">불법촬영물</c:if>
												<c:if test="${article.condition == 4}">음란/선정성</c:if>
												<c:if test="${article.condition == 5}">욕설/인신공격</c:if>
												<c:if test="${article.condition == 6}">개인정보노출</c:if>
												<c:if test="${article.condition == 7}">게시글 반복게시(도배 목적)</c:if>
												<c:if test="${article.condition == 8}">폭력적 또는 혐오스러운 콘텐츠</c:if>
												<c:if test="${article.condition == 9}">기타</c:if>
											</td>
											<td><fmt:formatDate value="${article.reg}" pattern="yyyy.MM.dd HH:mm"/></td>
											<td>
												<c:if test="${article.process == 0}">처리중</c:if>
												<c:if test="${article.process == 1}">처리완료</c:if>
											</td>
										</tr>
									</c:forEach>
								</table>
							</c:if>
							<br /><br />
							<%-- 페이지 번호 --%>
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

								<%-- 페이지번호  --%> 
								<c:if test="${sel == null || search == null}">
									<c:if test="${startPage > pageBlock}">
										<a class="back page-numbers"  href="/earth/user/myReport.et?pageNum=${startPage-pageBlock}" class="pageNums"> «back</a>
									</c:if>
									<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
										<a class="page-numbers" href="/earth/user/myReport.et?pageNum=${i}" class="pageNums"> &nbsp; ${i} &nbsp; </a>
									</c:forEach>
									<c:if test="${endPage < pageCount}">
										<a class="next page-numbers" href="/earth/user/myReport.et?pageNum=${startPage+pageBlock}" class="pageNums">Next» </a>
									</c:if>
								</c:if>
								
							</c:if> <%-- end:count > 0 --%>
							</nav>
								<!-- 현재 페이지 표시 -->
								<p style="color:#FAEBD7;"> 현재 ${pageNum} Page </p> 							
							</div>					
                    </div> <%--<div class="blog_details">--%>
                </div>		
			</div>
		</div>
		</section>
			<!--------------------------------------------------------------------------------------- #masthead 
		여러분 여기서부터는 건들거 없습니다!!!! -->

		<!-- #content -->
		</div>	<!-- <div class="row"> -->
	</div> 		<!-- <div class="container"> -->
</section>
<!-- - 여기서부터는 건들지 마세요.  -->
<%@ include file = "../include/footer.jsp" %>
</body>
<%-- </c:if> --%>
</html>