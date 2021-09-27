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
    <!-- main css -->
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style1.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/responsive.css">
</head>
<style>
	 .heading-section{
     	text-align:center;
     	vertical-align:middle;
     }
</style> 
<body>
  <%@ include file = "../include/header.jsp" %>
	<section class="blog_area single-post-area area-padding">
    <div class="container">
    
        <div class="row" style="width:1000px;">
        <!--================Home Gallery Area =================-->		
  			<div class="col-lg-4" style="text-align:left;">
                  <div class="blog_right_sidebar">
                      <aside class="single_sidebar_widget search_widget">
                            <div class="form-group">
                              <div class="input-group mb-3">
								<!-- 사용자 착용벳지 이미지 -->
								<img src='/earth/resources/bootstrap/imgs/${mybadge}' style="width:70px; height:70px; align:center;"/><br>
                              </div>
                            </div>
                        	<h4>${user.nickname}</h4>                      	
                        	<!--경고횟수 분기처리 : 경고횟수로만 분기처리 (신고 카운트에 따른 변화X)-->
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
                    	<div class="blog_details" style="width:600px; height:600px;"> 
                 		<h2 class="heading-section">${user.name}님의 에코백</h2>  <br><br>
                   			착용중인 뱃지<br>
                   			<img src='/earth/resources/bootstrap/imgs/${mybadge}' style="width:140px; height:140px;align:center;"/><br><br>
                   			<c:if test="${mybadge ne '새싹.png'}">
                   				<button onclick="window.location='/earth/user/releaseBadge.et'" style="border:#343a40; color:#343a40;">해제하기</button><br><br> 
                   			</c:if>	               
						<c:if test="${count == 0}">				
	        				<div class="g_item_text">
	        					<br/><br/>
								<h5>착용가능한 뱃지가 존재하지 않습니다.</h5>
							</div>
						</c:if>
						
				    	 	<div class="maintext" align="center">
				        		<div class="gallery_f_inner row imageGallery1">
					        		<c:if test="${count != 0}">
									<c:forEach var="article" items="${articleList}">
					        			<div class="col-lg-6 col-md-4 col-sm-6 brand manipul design print" style="width:100px;">
					        				<div class="h_gallery_item" style="align:center;">
					        					<div class="g_img_item" style="float:center;">
													<img src='/earth/resources/bootstrap/imgs/${article.badgeimg}' style="width:100px; height:100px;align:center;"/><br/>
					        						<a class="light" onclick="window.location='/earth/user/equipBadge.et?badgenum=${article.badgenum}'" style="color:#ffffff;">
					        							<button type="button"  style="background-color:#343a40; border:none; color:#fff;" >착용하기</button>								
					        						</a>
					        						<p style="font-size:17px;font-weight: bold ;">${article.badgename}</p>
					        					</div>
					        				</div>
					        			</div>
					        		</c:forEach>
									</c:if>
								</div>
				        	</div>
			        	</div>
        	    	</div>
        	    </div>
        	</div>
							<div align="center">
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
										
										<%-- 페이지번호 --%> 
										<c:if test="${sel == null || search == null}">
											<c:if test="${startPage > pageBlock}">
												<a class="back page-numbers" href="/earth/user/myBag.et?pageNum=${startPage-pageBlock}" class="pageNums">«Back</a>
											</c:if>
											<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
												<c:choose>
													<c:when test="${pageNum eq i}">
														<a class="current" href="/earth/user/myBag.et?pageNum=${pageNum}">${i}</a>
													</c:when>
													<c:otherwise>
														<a class="page-numbers" href="/earth/user/myBag.et?pageNum=${i}" class="pageNums">${i}</a>
													</c:otherwise>
												</c:choose>
											</c:forEach>
											
											<c:if test="${endPage < pageCount}">
												<a class="next page-numbers" href="/earth/user/myBag.et?pageNum=${startPage+pageBlock}" class="pageNums">Next»</a>
											</c:if>
										</c:if>
										
									</c:if> <%-- end:count > 0 --%>
								</nav>
							</div>
			        		<c:if test="${sessionScope.sid eq 'admin'}">
				        		<div class="more_btn">
				        			<a class="main_btn" onclick="window.location='/earth/badge/addBadge.et'" style="color:#ffffff;"> 뱃지등록</a>
				        		</div>
							</c:if>
        </section>  
      </div>
    </div>              			
   </section> 
  <%@ include file = "../include/footer.jsp" %>
</body>
</html>