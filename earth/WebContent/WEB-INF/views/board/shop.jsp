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
<body>
<%@ include file = "../include/header.jsp" %>	
<section class="category-page area-padding">
   <div class="container">
       <h3 class="page-title">상점소개</h3>
           <p> 해당 상점 페이지로 이동합니다. </p>
     		<form action="/earth/board/shop.et" >
	         		<div class="input-group" style="width:300px; float:right;">
	         			<select name="sel" style="height:30px;display:none">
							<option value="subject">행사제목</option>
						</select>
                        <input type="text" class="form-control" name="search" placeholder="상점 검색" style="border-top:none; border-left:none; border-right:none; height:30px;" onfocus="this.placeholder = '상점 검색'" onblur="this.placeholder = '상점 검색'">
                        <input type="image" src="/earth/resources/bootstrap/imgs/icon.png" style="width:18px; height:18px;">
                	</div>
            </form>    	
				<br><br><br>
            <div class="row">
 				<c:if test="${count == 0}">	
				  <div class="image-wrap-2">
           			 <div class="image-info">
             			 <p>게시글이 없습니다. </p>			
					</div>
				  </div>
				</c:if>		
				 <c:if test="${count != 0}">
				<c:forEach var="article" items="${articleList}">
                <div class="col-md-6 col-lg-4">
                    <div class="single-category">
                   
                        <div class="thumb">
							<a href="${article.hyperlink}" target="_blank">
								<img src="${pageContext.request.contextPath}/save/${article.img}" style="width:290px; height:290px; margin-bottom:0;"/>
							</a>                        </div>
                        <div class="short_details">
                           
                            <a class="d-block" href="${article.hyperlink}" target="_blank">
                               <h4>${article.subject}</h4><br>
                               <p style="float:right;"> GO -> </p>
                            </a><br><br>
                           <c:if test="${sid eq 'admin'}">
								<button type="button" style="width:150px; height:30px; align:center; "
									onclick="window.location='/earth/board/shopContent.et?pageNum=${pageNum}&boardnum=${article.boardnum}'">관리자-상점관리
								</button>	
							</c:if>
                        </div>
                     </div> 
                </div>  
                 </c:forEach>
               </c:if>
               	<c:if test="${sid eq 'admin'}">
				   <div class="col-12 text-center">
					<button type="button"class="main_btn" style="width:100px; height:50px; text-align:center;"
						onclick="window.location='/earth/board/writeShopForm.et'">글쓰기
					</button>	
                	</div>
				</c:if>
            </div>
        </div>
</section>				
				<%-- 페이지 번호 --%>
		<div align="center" class="col-12 text-center">
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
					
					<%-- 검색했을때 페이지번호들 --%>
					<c:if test="${sel != null && search != null}">
						<c:if test="${startPage > pageBlock}">
							<a class="back page-numbers"  href="/earth/board/shop.et?pageNum=${startPage-pageBlock}&sel=${sel}&search=${search}"  class="pageNums">«Back</a>
						</c:if>
						<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
							<c:choose>
								<c:when test="${pageNum eq i}">
									<a class="current" href="/earth/board/shop.et?pageNum=${i}&sel=${sel}&search=${search}">${i}</a>
								</c:when>
								<c:otherwise>
									<a class="page-numbers" href="/earth/board/shop.et?pageNum=${i}&sel=${sel}&search=${search}"  class="pageNums">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${endPage < pageCount}">
							<a class="next page-numbers" href="/earth/board/shop.et?pageNum=${startPage+pageBlock}&sel=${sel}&search=${search}" class="pageNums">Next»</a>
						</c:if>
					</c:if>
					<%-- 검색 안했을때 페이지번호들   --%> 
					<c:if test="${sel == null || search == null}">
						<c:if test="${startPage > pageBlock}">
							<a class="back page-numbers" href="/earth/board/shop.et?pageNum=${startPage-pageBlock}" class="pageNums">«Back</a>
						</c:if>

						<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
							<c:choose>
								<c:when test="${pageNum eq i}">
									<a class="current" href="/earth/board/shop.et?pageNum=${pageNum}">${i}</a>
								</c:when>
								<c:otherwise>
									<a class="page-numbers" href="/earth/board/shop.et?pageNum=${i}" class="pageNums">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<c:if test="${endPage < pageCount}">
							<a class="next page-numbers" href="/earth/board/shop.et?pageNum=${startPage+pageBlock}" class="pageNums">Next»</a>
						</c:if>
					</c:if>
				</c:if> <%-- end:count > 0 --%>
			</nav>
		</div>			
<%@ include file = "../include/footer.jsp" %>
</body>
</html>
