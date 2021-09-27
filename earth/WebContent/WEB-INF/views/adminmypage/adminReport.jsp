<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
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
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		function adminReportForm(reportnum){
			var popUrl = "/earth/adminmypage/adminReportForm.et?reportnum=" + reportnum + "&pageNum=${pageNum}&sel=${sel}&search=${search}"
			var popOption = "width=500, height=500, resizable=no, scrollbars=no, status=no;";
			window.open(popUrl,"",popOption);
		}
		
		$(document).ready(function(){
			var hash = $.trim( window.location.hash );
			if (hash) $('.your-css-selector a[href$="'+hash+'"]').trigger('click');
		});
	</script>
	
	
</head>
<body>
	<%-- 앵커태그(위치이동/지우지말것) --%>
	<a href="#list"></a>

	<!--================ Start header Top Area =================-->
	<%@ include file = "../include/header.jsp" %>
	<section class="category-page area-padding">
        <div class="container">
        <div class="row" style="width:1000px;">
        	<div class="col-lg-30" style="text-align:left; width:200px;">
				<div class="blog_right_sidebar" style="text-align:left">
                      <aside class="single_sidebar_widget post_category_widget" style="text-align:left; float:left;">
                        <h4 class="widget_title">관리자페이지</h4>
                        <ul class="list cat-list">
							<li>
								<a href="/earth/adminmypage/adminUser.et">회원관리</a>
							</li>
							<li>
								<a href="/earth/adminmypage/adminModify.et">운영자권한관리</a>
							</li>
							<li>
								<a href="/earth/adminmypage/adminBoard.et">게시글관리</a>
							</li>
							<li>
								<a href="/earth/adminmypage/adminComment.et">댓글관리</a>
							</li>
							<li>
								<a href="/earth/adminmypage/adminQuestion.et">1:1문의</a>
							</li>
							<li>
								<a href="/earth/adminmypage/adminNotice.et">공지사항</a>
							</li>
							<li>
								<a href="/earth/adminmypage/adminReport.et">신고</a>
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
                    <div class="blog_details" style="width:880px; height:900px;" id="list">
                        <c:if test="${count == 0}">
	           			 <div style="width:400px; height:30px;">
	             			 <p>신고내용이 없습니다. </p>			
					  	</div>				        
					  	</c:if>
				        <c:if test="${count != 0}">
				            <table align="center">
				            <tr>
					            <td align="center">신고자</td>
					            <td align="center">신고 대상자</td> 
					            <td align="center">신고 유형</td>
					            <td align="center">신고 내용</td>
					            <td align="center">신고 접수일</td>
					            <td align="center">확인 및 처리</td>
				       		</tr>
				       
				       		<%-- 유저정보 view, 옵션추가시 해당 tr onclick으로 유저 세부정보 팝업창 호출 --%>
				      		<c:forEach var="report" items="${reportList}" varStatus="status">
								<tr align="center">
									<td>${report.id}</td>
									<td>${report.receiver}</td>
									<td>${report.condition}</td>
									<td>${report.reportctt}</td>
									<td><fmt:formatDate value="${report.reg}" pattern="YYYY-MM-dd HH : mm"/></td>
									<c:if test="${report.process == 0}">
										<td><button onclick="adminReportForm(${report.reportnum})">내용 확인</button></td>
									</c:if>
									<c:if test="${report.process == 1}">
										<td>처리 완료</td>
									</c:if>
								</tr>
							</c:forEach>
				            </table>
				            
				            
				            <%-- 페이지번호 조정, 12345/678910 5마다 세트로 묶음 --%>
								<c:set var="pageBlock" value="3" />
								<fmt:parseNumber var="res" value="${count / pageSize}" integerOnly="true" />
								<c:set var="pageCount" value="${res + (count % pageSize == 0 ? 0 : 1)}" />
								<fmt:parseNumber var="result" value="${(currentPage-1)/pageBlock}" integerOnly="true" />
								<fmt:parseNumber var="startPage" value="${result * pageBlock + 1}"/>
								<fmt:parseNumber var="endPage" value="${startPage + pageBlock -1}" />
								<c:if test="${endPage > pageCount}">
									<c:set var="endPage" value="${pageCount}" /> 
								</c:if>
								
								<%-- 검색O 페이지번호 --%>
								<c:if test="${sel != null && search != null}">
									<c:if test="${startPage > pageBlock}">
										<a href="/earth/adminmypage/adminReport.et?pageNum=${startPage-pageBlock}&sel=${sel}&search=${search}&process=${process}#list" class="pageNums">Back</a>
									</c:if>
									<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
										<a href="/earth/adminmypage/adminReport.et?pageNum=${i}&sel=${sel}&search=${search}&process=${process}#list" class="pageNums">${i}</a>
									</c:forEach>
									<c:if test="${endPage < pageCount}">
										<a href="/earth/adminmypage/adminReport.et?pageNum=${startPage+pageBlock}&sel=${sel}&search=${search}&process=${process}#list" class="pageNums">Next</a>
									</c:if>
								</c:if>
								
								<%-- 검색X 페이지번호   --%> 
								<c:if test="${sel == null || search == null}">
									<c:if test="${startPage > pageBlock}">
										<a href="/earth/adminmypage/adminReport.et?pageNum=${startPage-pageBlock}&process=${process}#list" class="pageNums">Back</a>
									</c:if>
									<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
										<c:if test="${pagenum == i}">
											<h3 style="colorgray">${i}</h3>
										</c:if>
										<c:if test="${pagenum != i}">
											<a href="/earth/adminmypage/adminReport.et?pageNum=${i}&process=${process}#list" class="pageNums">${i}</a>
										</c:if>
									</c:forEach>
									<c:if test="${endPage < pageCount}">
										<a href="/earth/adminmypage/adminReport.et?pageNum=${startPage+pageBlock}&process=${process}#list" class="pageNums">Next</a>
									</c:if>
								</c:if>
							</c:if> 
							<br /> <br /> <br /> 
							<%-- 작성자/내용 검색 --%>
							<form action="/earth/adminmypage/adminReport.et" >								
								<select style="boder:none;" name="sel">
     								<option value="id">신고자</option>
									<option value="receiver">신고 대상자</option>
								</select>
								<input type="text" placeholder="검색" name="search" value="" title="search"
									style="border-top:none; border-left:none; border-right:none;width:130px;height:30px; font-size:11px;"/>
								<input type="image" src="/earth/resources/bootstrap/imgs/icon.png" style="width:18px; height:18px; margin-right:10px;">
								
								<c:if test="${process == 0}">
									<input type="button" value="검색조건 초기화" onclick="window.location='/earth/adminmypage/adminReport.et?process=0#list'"/>
								</c:if>
								<c:if test="${process == 1}">
									<input type="button" value="검색조건 초기화" onclick="window.location='/earth/adminmypage/adminReport.et?process=1#list'"/>
								</c:if>
									
								<c:if test="${process != 1}">
									<input type="button" value="처리된 신고목록" onclick="window.location='/earth/adminmypage/adminReport.et?process=1#list'" style="margin-left:0px"/><br/>
								</c:if> 
								<c:if test="${process == 1}">
									<input type="button" value="처리되지 않은 신고목록" onclick="window.location='/earth/adminmypage/adminReport.et?process=0#list'" style=" margin-left:0px"/><br/>
								</c:if>
							</form>
	                    </div>
	                </div>
				</div>
			</div>	
		</section>
	</div>
	</div>
	</section>

	<%@ include file = "../include/footer.jsp" %>
	<a href="#top" class="smoothup" title="Back to top"><span class="genericon genericon-collapse"></span></a>
<!-- #page -->

</body>
</html>