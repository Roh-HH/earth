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
width:18px; 
height:18px; 
margin-top:4px; 
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
				<h1 class="entry-title"> 이달의 챌린지  </h1>
				<hr>
				<p> 지구를 위한 활동이 불편한 것이 아닌, 당연한 것이 될 때까지 이달의 챌린지는 계속됩니다. </p>
				<br><br><br>
        	<div class="row">
				<c:if test="${count == 0}">	
				  <div class="image-wrap-2">
           			 <div class="image-info">
             			 <p>게시글이 없습니다. </p>			
					</div>
				  </div>
				 </c:if>
			 <!-- 다희언니용 -->	
			<c:if test="${count != 0}">
				<c:forEach var="article" items="${articleList}">
           	 		<div class="col-md-6 col-lg-4">
                		<div class="single-category">
                        	<div class="thumb">
								<a href="/earth/board/challengeContent.et?pageNum=${pageNum}&boardnum=${article.boardnum}">
								<c:if test="${article.img != null}">
									<img src="${pageContext.request.contextPath}/save/${article.img}" style="width:290px; height:290px;"/>
								</c:if>
								<c:if test="${article.img == null}">
									<img src="" style="width:290px; height:290px;"/>
								</c:if>							
								</a>
							</div>
	                    	<div class="short_details">
		                    	<div class="meta-top d-flex">
		                    		<img class="badgeImg" src="${pageContext.request.contextPath}/save/admin.png"/>
		                            <a href="#">${article.id}</a>                         
		                        </div>
		                        <a href="/earth/board/challengeContent.et?pageNum=${pageNum}&boardnum=${article.boardnum}">	 
										<c:if test="${article.dateck == 0}">[챌린지 마감]
											<c:if test="${article.ck == 1}"><span style="color:blue;">[챌린지 성공]</span></c:if>
											<c:if test="${article.ck == 0}"><span style="color:red;">[챌린지 실패]</span></c:if> 
										</c:if>	
										
										<c:if test="${article.maxcount > article.joincount}">
											<c:if test="${article.dateck == 1}"> [챌린지 진행중] </c:if>	
										</c:if>	
										<c:if test="${article.maxcount == article.joincount}">
											<c:if test="${article.dateck == 1}">[챌린지 마감]	
												<c:if test="${article.ck == 1}"><span style="color:blue;">[챌린지 성공]</span></c:if>
												<c:if test="${article.ck == 0}"><span style="color:red;">[챌린지 실패]</span></c:if> 
											</c:if>
										</c:if>
										<h4  style="height:60px;">${article.subject}</h4></a><br>
								<p><fmt:formatDate value="${article.reg}" pattern="YYYY-MM-dd HH:mm"/></p>
								
								
								
								
								
							</div>
                     	</div> 
                	</div>  
                 </c:forEach>
               </c:if>
				<div class="col-12 text-center">
				<c:if test="${sessionScope.sid == 'admin'}">
					<button type="button"class="main_btn" style="text-align:center;"
						onclick="window.location='/earth/board/challengeWriteForm.et'"> 챌린지 등록
					</button>	
					<button type="button"class="main_btn" style="text-align:center;"
						oclick="history.back();">뒤로가기 
					</button>
				</c:if>
                </div>			
 			</div>
        </div>
    </section>
                		
		<br /><br><br>
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
		</c:if> <%-- end:count > 0 --%>
			<c:if test="${startPage > pageBlock}">
				<a class="back page-numbers" href="/earth/board/challengeList.et?pageNum=${startPage-pageBlock}" class="pageNums"> «</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
				<a class="page-numbers" href="/earth/board/challengeList.et?pageNum=${i}" class="pageNums">${i}</a>
			</c:forEach>
			<c:if test="${endPage < pageCount}">
				<a class="next page-numbers" href="/earth/board/challengeList.et?pageNum=${startPage+pageBlock}" class="pageNums"> »</a>
			</c:if>
		</nav>
			<%-- <p align="center" style="color:grey"> 현재페이지 : ${pageNum} </p> --%>
	</div>
					
<%@ include file = "../include/footer.jsp" %>
<!-- #page -->

</body>
</html>