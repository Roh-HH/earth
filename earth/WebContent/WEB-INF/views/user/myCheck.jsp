<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="earth.user.dto.AttendDTO"%>
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
    <!--출석  calendar -->
	<link href='/earth/resources/bootstrap/js/main.css' rel='stylesheet' />
	<link href='/earth/resources/bootstrap/js/main.min.css' rel='stylesheet' />
	<script src='/earth/resources/bootstrap/js/main.min.js'></script>
	<script src='/earth/resources/bootstrap/js/main.js'></script>
	<script src='/earth/resources/bootstrap/js/locales-all.js'></script>
	<script src='/earth/resources/bootstrap/js/locales-all.min.js'></script>
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
<script type="text/javascript">
	document.addEventListener('DOMContentLoaded', function() {
	    var calendarEl = document.getElementById('calendar');
	    var calendar = new FullCalendar.Calendar(calendarEl, {

			initialView: 'dayGridMonth',
	    	//navLinks: true, // 날짜를 선택하면 Day 캘린더나 Week 캘린더로 링크
	    	nowIndicator: true, // 현재 시간 마크
	    	locale: 'ko', // 한국어 설정
	    	dateClick:true,
	    	headerToolbar: {
	    	      left: 'prev,next',
	    	      center: 'title',
	    	      right: 'custom1'
	    	      },
	    	eventColor: '#1E88E5',
	    	customButtons: {
	    	 		   
	    		custom1: {
	    		text: '출석도장찍기',
	    		click: function() {
	    		            
	           var today = new Date();
	           var year = today.getFullYear();
	           var month = ('0' + (today.getMonth() + 1)).slice(-2);
	           var day = ('0' + today.getDate()).slice(-2);

	           var date = year + '-' + month  + '-' + day;

	           console.log(date);
    	      	// ajax 요청 
	 			$.ajax({
	 				url : "/earth/user/ajaxCkPo.et",
	 				type : "post",
	 				data : {date : date},
	 				//contentType:'application/json; charset=UTF-8',
	 				success : function(data) { 
		    	        console.log(date);
		    	        
	 					if (data == "1" ){
 		    	       	calendar.addEvent({
	    	               title: '출석체크 완료',
	    	               start: date,
	    	               allDay: true
	    	             });
	 		    	     alert("출석체크 완료!! ");
		 				}else {
		 					alert("오늘은 이미 도장을 찍으셨어요! ");
		 				}
	 				} //success
	 			}); //ajax	 			
	         },// 버튼 
	    	}
	    }
	    });

	    	   calendar.render();
	    	      
	    	   $.ajax({
					url : "/earth/user/attend.et",
					type : "post",
					data : {id:'id'},
					dataType:"json",
					//contentType:'application/json; charset=UTF-8',
					success : function(data) { 
						
						//alert(data);
						 
						    var attend = Array();
					    
						    for (var i=0; i < data.length; i++){
						    //	var attendList[i] = data;
						    	console.log(attend);
						    	
						    var ob = new Object();
							    ob.title = '출석체크완료';
							    ob.start = data[i];
							    attend.push(ob);
							    calendar.addEvent(attend[i]);
						    }
					}
				}); //ajax  	   
	    	 });
	</script>
	<%@ include file = "../include/header.jsp" %>
		<section class="blog_area single-post-area area-padding">
    		<div class="container">
        		<div class="row" style="width:1000px;">        			
		      		<div class="col-lg-30" style="text-align:left; width:200px;">
                  		<div class="blog_right_sidebar">
                      		<aside class="single_sidebar_widget search_widget">
                            		<div class="form-group">
		                              <div class="input-group mb-3">
										<!-- 사용자 착용벳지 이미지 -->
										<img src='${pageContext.request.contextPath}/save/${mybadge}' style="width:70px; height:70px; align:center;"/><br>
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
                      					<button type="button" style="float:right; background-color:#1E88E5; color:#ffffff; border-radius:5px; margin-right:70px;" onclick="window.location='/earth/user/myCheck.et'">출석체크하기</button>						
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
										</ul>
				                      </aside>
						</div>
					</div>	
					<section class="right_side">   
		                    <div class="blog_details">                       
									<div class="row justify-content-center">
										<div class="col-md-6 text-center mb-5">
											<h2 class="heading-section">매일매일 출석체크</h2><br>
											<p style="color:blue;">매일 어뜨에 출첵하고 포인트 받아가세요!</p>
										</div>
									</div>
									<div class="center_">
				                       <div class="container calendar-container" style="width:700px;">
											<div id="calendar" style="max-width:600px; margin:40px auto;">
											</div>
									   </div>
									</div>
				            </div>
				    </section>
		</div>
	</div>
</section>
	<%@ include file = "../include/footer.jsp" %>
</body>
</html>