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
                        <h2>${user.name}님이 작성한 댓글</h2>
							<c:if test="${count == 0}">
								<table>
									<tr>
										<td colspan="2">
											<h5>작성한 댓글이 없습니다.</h5>
											<h6>${user.name}님! 어뜨의 다양한 게시판에 자유롭게 댓글을 남겨주세요!</h6>
										</td>
									</tr>
								</table>
							</c:if>							
							<c:if test="${count != 0}">
		                        <table>
		                        	<tr>
		                        		<td>총 ${count}개의 댓글을 작성하셨습니다!</td>
		                        	</tr>
		                        </table>
								<table>
									<tr class="head_bar">
										<td>게시판</td>
										<td>댓글</td>
										<td>등록일</td>
									</tr>
									<c:forEach var="article" items="${commentList}">
										<tr>
											<td>
												<c:if test="${article.code == 9}">자유게시판</c:if>
												<c:if test="${article.code == 10}">환경일기</c:if>
												<c:if test="${article.code == 11}">이달의챌린지</c:if>
											</td>
											<td>
												<c:if test="${article.code == 9}">
													<a href="/earth/board/freeContent.et?freenum=${article.freenum}">
														${article.commen}
													</a>
												</c:if>
												<c:if test="${article.code == 10}">
													<a href="/earth/board/diaryContent.et?boardnum=${article.boardnum}">
														${article.commen}
													</a>
												</c:if>
												<c:if test="${article.code == 11}">
													<a href="/earth/board/challengeContent.et?boardnum=${article.boardnum}">
														${article.commen}
													</a>
												</c:if>
											</td>
											<td><fmt:formatDate value="${article.regcomm}" pattern="yyyy/MM/dd HH:mm"/></td>
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
								
								<%-- 검색했을때 페이지번호들 --%>
								<c:if test="${sel != null && search != null}">
									<c:if test="${startPage > pageBlock}">
										<a class="back page-numbers" href="/earth/user/myComment.et?pageNum=${startPage-pageBlock}&sel=${sel}&search=${search}" class="pageNums"> «back</a>
									</c:if>
									<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
										<a class="page-numbers" href="/earth/user/myComment.et?pageNum=${i}&sel=${sel}&search=${search}" class="pageNums"> &nbsp; ${i} &nbsp; </a>
									</c:forEach>
									<c:if test="${endPage < pageCount}">
										<a class="next page-numbers" href="/earth/user/myComment.et?pageNum=${startPage+pageBlock}&sel=${sel}&search=${search}" class="pageNums">Next» </a>
									</c:if>
								</c:if>
								
								<%-- 검색 안했을때 페이지번호들   --%> 
								<c:if test="${sel == null || search == null}">
									<c:if test="${startPage > pageBlock}">
										<a class="back page-numbers"  href="/earth/user/myComment.et?pageNum=${startPage-pageBlock}" class="pageNums"> «back</a>
									</c:if>
									<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
										<a class="page-numbers" href="/earth/user/myComment.et?pageNum=${i}" class="pageNums"> &nbsp; ${i} &nbsp; </a>
									</c:forEach>
									<c:if test="${endPage < pageCount}">
										<a class="next page-numbers" href="/earth/user/myComment.et?pageNum=${startPage+pageBlock}" class="pageNums"> Next» </a>
									</c:if>
								</c:if>
								
							</c:if> <%-- end:count > 0 --%>
							</nav>
								<!-- 현재 페이지 표시 -->
								<p style="color:#FAEBD7;"> 현재 ${pageNum} Page </p> 							
							</div>
							
							<br /> <br />
		            		<div class="text-align:center;" style="display:inline-block;">
								<form style="float:center;height:60px;" class="woocommerce-ordering" action="/earth/user/myComment.et" >							
									<select name="sel" style="display:none">
										<option value="commen">댓글내용</option>
									</select>

									<input class="form-control" type="text" placeholder="검색 키워드를 입력해주세요." name="search" value="" title="search"
										style="border-top:none; border-left:none; border-right:none;width:200px;height:50px; display:inline-block;"/>&nbsp;&nbsp;&nbsp;
									<input type="submit" value="검색" /><br/><br/>
									
									<c:if test="${sel != null && search != null}">
										<input type="button" onclick="window.location='http://localhost:8080/earth/user/myComment.et'" value="돌아가기" />
									</c:if>
								</form>
							</div>
							<br />					
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