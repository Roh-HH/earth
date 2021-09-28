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
   	<%@ include file = "../include/header.jsp" %>
 	<section class="blog_area single-post-area area-padding">
    <div class="container">
        <div class="row" style="width:1000px; height:1200px;">        			
		      <div class="col-lg-30" style="text-align:left; width:200px;">
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
											<h2 class="heading-section">${user.name}님이 작성한 댓글</h2>
										</div>
									</div>
									<div class="center_">
										<c:if test="${count == 0}">
											<table>
												<tr>
													<td colspan="2">
														<h5>작성한 댓글이 없습니다.</h5>
														<c:if test="${sel != null && search != null}">
															<script>
																alert("검색 된 댓글이 없습니다.")
															</script>
														</c:if>
														<h6>${user.name}님! 어뜨의 다양한 게시판에 자유롭게 댓글을 남겨주세요!</h6>
													</td>
												</tr>
											</table>
										</c:if>	
										<c:if test="${count != 0}">
				                       	<p class="heading-section">총 ${count}개의 댓글을 작성하셨습니다! ${user.name}님의 더 많은 어뜨 활동을 기다립니다.</p>
										<table class="table">
											<thead class="thead-dark">
												<tr>
												      <th style="width:200px;text-align:center;">게시판</th>
												      <th style="width:350px;">댓글</th>
												      <th style="width:250px;">작성일</th>
												</tr>
											</thead>						
		                   					<tbody>
												<c:forEach var="article" items="${commentList}">
													<tr class="alert" role="alert">
														<th scope="row" style="text-align:center">
															<c:if test="${article.code == 9}">자유게시판</c:if>
															<c:if test="${article.code == 10}">환경일기</c:if>
															<c:if test="${article.code == 11}">이달의챌린지</c:if>
														</th>
														<td>
															<c:if test="${article.code == 9}">
																<a href="/earth/board/freeContent.et?boardnum=${article.boardnum}&commentPageNum=1">
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
														<td>
															<fmt:formatDate value="${article.regcomm}" pattern="yyyy/MM/dd HH:mm"/>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										</c:if>
							<br /><br />
							<%-- 페이지 번호 --%>
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
									
									<%-- 검색했을때 페이지번호 --%>
									<c:if test="${sel != null && search != null}">
										<c:if test="${startPage > pageBlock}">
											<a class="back page-numbers" href="/earth/user/myComment.et?pageNum=${startPage-pageBlock}&sel=${sel}&search=${search}" class="pageNums">«Back</a>
										</c:if>
										<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
											<c:choose>
												<c:when test="${pageNum eq i}">
													<a class="current" href="/earth/user/myComment.et?pageNum=${pageNum}&sel=${sel}&search=${search}">${i}</a>
												</c:when>
												<c:otherwise>
													<a class="page-numbers" href="/earth/user/myComment.et?pageNum=${i}&sel=${sel}&search=${search}" class="pageNums">${i}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<c:if test="${endPage < pageCount}">
											<a class="next page-numbers" href="/earth/user/myComment.et?pageNum=${startPage+pageBlock}&sel=${sel}&search=${search}" class="pageNums">Next»</a>
										</c:if>
									</c:if>
									
									<%-- 검색 안했을때 페이지번호  --%> 
									<c:if test="${sel == null || search == null}">
										<c:if test="${startPage > pageBlock}">
											<a class="back page-numbers"  href="/earth/user/myComment.et?pageNum=${startPage-pageBlock}" class="pageNums">«Back</a>
										</c:if>
										<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
											<c:choose>
												<c:when test="${pageNum eq i}">
													<a class="current" href="/earth/user/myComment.et?pageNum=${pageNum}">${i}</a>
												</c:when>
												<c:otherwise>
													<a class="page-numbers" href="/earth/user/myComment.et?pageNum=${i}" class="pageNums">${i}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<c:if test="${endPage < pageCount}">
											<a class="next page-numbers" href="/earth/user/myComment.et?pageNum=${startPage+pageBlock}" class="pageNums">Next»</a>
										</c:if>
									</c:if>
									
								</c:if> <%-- end:count > 0 --%>
								</nav>
								<br /> <br />
			            		<div class="text-align:center;" style="display:inline-block;">
									<form style="float:center;height:60px;" class="woocommerce-ordering" action="/earth/user/myComment.et" >							
										<select name="sel" style="display:none">
											<option value="commen">댓글내용</option>
										</select>
	
										<input class="form-control" type="text" placeholder="댓글 키워드로 검색해주세요." name="search" value="" title="search"
											style="border-top:none; border-left:none; border-right:none;width:200px;height:50px; display:inline-block;"/>&nbsp;&nbsp;&nbsp;
										<input type="submit" value="검색" /><br/><br/>
										
										<c:if test="${sel != null && search != null}">
											<input type="button" onclick="window.location='http://localhost:8080/earth/user/myComment.et'" value="돌아가기" />
										</c:if>
									</form>
									<br />	
                				</div>		
							</div><%--center_ --%>				
                    	</div> <%--<div class="blog_details">--%>
			</section>
		</div>
	</div>
</section>		
<%@ include file = "../include/footer.jsp" %>
</body>
</html>