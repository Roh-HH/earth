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
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="/earth/resources/bootstrap/css/table.css"> <%--table css 추가요  --%>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<style>
	.alert{
		color:#212529;
	}
	 .center, .silchun, .heading-section{
     	text-align:center;
     	vertical-align:middle;
     }
     .center_ {
     	text-align:center; 
     	width:800px;
     }
     .right_side, .blog_details{
     	width:800px;
     	height:1200px;
     }
</style> 
<body>
    <!--================ Start header Top Area =================-->
  	<%@ include file = "../include/header.jsp" %>
	<section class="blog_area single-post-area area-padding">
	<div class="container">
        <div class="row" style="width:1000px; height:1200px;">        			
		      <div class="col-lg-30" style="text-align:left; width:200px;">
                  <div class="blog_right_sidebar">
                      <aside class="single_sidebar_widget search_widget">
                            <div class="form-group">
                              <div class="input-group mb-3">
								<!-- 사용자 착용벳지 이미지  -->
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
						<button type="button" style="float:right; background-color:#1E88E5; color:#ffffff; border-radius:5px; float:left;" onclick="window.location='/earth/user/myCheck.et'">출석체크하기</button>						
						</li>
						</ul>
                      </aside>
					</div>
				</div>
      			<section class="right_side">   
		                    <div class="blog_details">                       
									<div class="row justify-content-center">
										<div class="col-md-6 text-center mb-5">
											<h2 class="heading-section">${user.name}님의 자유게시판 작성글</h2>
										</div>
									</div>
									<div class="center_">
										<c:if test="${count == 0}">
											<table>
												<tr>
													<td colspan="2">
														<h5>자유게시판 게시글이 없습니다.</h5>
														<br/>
														<input type="button" value="${user.name}님의 글을 다른 분들과 공유해주세요!" onclick="window.location='/earth/board/freeList.et'" />
													</td>
												</tr>
											</table>
										</c:if>							
										<c:if test="${count != 0}">
			                        	<p class="heading-section">자유게시판에 총 ${count}개의 글을 작성하셨습니다! <a href="/earth/board/freeList.et">${user.name}님의 더 많은 어뜨 활동을 기다립니다.</a></p>
											<table class="table">
												<thead class="thead-dark">
													<tr>
													      <th style="width:120px;">게시판</th>
													      <th style="width:140px;">말머리</th>												      
													      <th style="width:250px;">제목</th>
													      <th style="width:190px;">등록일</th>
													      <th style="width:110px;">조회수</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="article" items="${freeArticleList}">
														<tr class="alert" role="alert">
															<th scope="row" style="text-align:center">자유게시판</th>
															<td>
																<c:if test="${article.categ == 1}">[잡담]</c:if>
																<c:if test="${article.categ == 2}">[환경문제]</c:if>
																<c:if test="${article.categ == 3}">[정보공유]</c:if>
															</td>
															<td>
																<a href="/earth/board/freeContent.et?boardnum=${article.boardnum}">${article.subject}</a>
															</td>
															<td><fmt:formatDate value="${article.reg}" pattern="yyyy/MM/dd [E]"/></td>
															<td class="center" style="color:#FFB400">${article.readcount}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</c:if>
							<br /><br />
							<%-- 페이지 번호 --%>
							<div class="center_">
								<nav class="pagination" style="float:center;">							
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
									
									<%-- 페이지 번호 --%> 
									<c:if test="${sel == null || search == null}">
										<c:if test="${startPage > pageBlock}">
											<a class="back page-numbers"  href="/earth/user/myBoard.et?pageNum=${startPage-pageBlock}" class="pageNums">«Back</a>
										</c:if>
										<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
											<c:choose>
												<c:when test="${pageNum eq i}">
													<a class="current" href="/earth/user/myBoard.et?pageNum=${pageNum}">${i}</a>
												</c:when>
												<c:otherwise>
													<a class="page-numbers" href="/earth/user/myBoard.et?pageNum=${i}" class="pageNums">${i}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<c:if test="${endPage < pageCount}">
											<a class="next page-numbers" href="/earth/user/myBoard.et?pageNum=${startPage+pageBlock}" class="pageNums">Next» </a>
										</c:if>
									</c:if>
									
								</c:if> <%-- end:count > 0 --%>
								</nav>
							</div>							
                    </div> <%--<div class="blog_details">--%>
              </div>
			</section>
		</div>
	</div>
</section>				
<%@ include file = "../include/footer.jsp" %>
</body>
</html>