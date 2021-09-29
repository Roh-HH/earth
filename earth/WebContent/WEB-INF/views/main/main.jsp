%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en-Us">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
</head>
<style>

.feature_item{
 	padding: 50px 21px;
    border-radius: 10px ;
    transition: all 300ms linear 0s;
    background: #fff;
    margin-bottom: 30px;
    width:300px;
    }
.thumb-main-image {
  position:relative;
}

.thumb-main-image .main_text_earth {
  position:absolute;
  top:160px;
  left:40px;
}
.p_120 {
    padding-top: 60px;
    padding-bottom: 60px;
}
.area-padding {
    padding: 80px 0;
}
.area-heading h3 {
    color: #33353b;
    font-size: 36px;
    font-weight: 600;
    position: relative;
    margin-bottom: 23px;
}
.badgeImg{
width:18px; 
height:18px; 
margin-top:4px; 
margin-right:4px;
}
</style>
<body>
     <!--================ Start header Top Area =================-->
  <%@ include file="../include/header.jsp"%>
    <section class="editors_pick area-padding" >
        <div class="container">
            <div class="row">
                <div class="col-lg-5 col-xl-6">
                    <div class="single-blog">
                        <div class="thumb-main-image">
        				   <a href="/earth/board/about.et">
                            	<img class="img-fluid" src="/earth/resources/bootstrap/imgs/earthmain.gif" alt="" style="width:500px;">
                            </a>
                        	<div class="main_text_earth">
        				    <a href="/earth/board/about.et">
						      <p style="color:#fff;text-align:center;">어뜨가 생겨난 배경</p>
						      <h2 style="font-weight:20px;color:#fff; text-align:center;">지구 평균기온 1.5도 상승까지<br>오직 7년 10개월</h2>
						    </a>
						    </div>
                        </div>
                    </div>    
                </div>
                <div class="col-lg-7 col-xl-6">
                    <div class="single-blog row no-gutters style-four m_b_30">
                        <div class="col-12 col-sm-7">
                            <div class="short_details padd_left_0">
                                <div class="meta-top d-flex">
                                    <a href="/earth/board/event.et">> ZERO WASTE EVENT</a>
                                </div>
                                <a class="d-block" href="/earth/board/event.et">
                                    <h4 class="font-20"> 어뜨가 알려주는 행사일정 </h4>
                                </a>
                                <p>쓰레기를 줄이는 제로웨이스트 캠패인을 포함해 환경보호를 위한 많은 행사가 개최되고 있어요! 우리 같이 참여해봐요</p>
                            </div>  
                        </div>  
                        <div class="col-12 col-sm-5">
                            <div class="thumb">
                            <a href="/earth/board/event.et">
                            <img class="img-fluid" src="/earth/resources/bootstrap/imgs/wordcloud.png" alt="">
                            </a>
                            </div>
                        </div>
                    </div> 
                    <div class="single-blog row no-gutters style-four">
                        <div class="col-12 col-sm-5">
                            <div class="thumb">
                            <a href="/earth/board/shop.et">
                            <img class="img-fluid" src="/earth/resources/bootstrap/imgs/shop.png" alt="">
                            </a>
                            </div>
                        </div>
                        <div class="col-12 col-sm-7">
                            <div class="short_details padd_right_0">
                                <div class="meta-top d-flex">
                                    <a href="/earth/board/shop.et">> ZERO WASTE SHOP</a>
                                </div>
                                <a class="d-block" href="/earth/board/shop.et">
                                    <h4 class="font-20"> 제로웨이스트 상점 </h4>
                                </a>
                                <p>친환경제품만을 취급하는 상점을 소개합니다. 작은 물건부터 친환경 제품으로 바꿔보세요!  </p>
                            </div>  
                        </div>  
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--================게시판  section end =================-->    
    <section class="feature_area p_120" style="background-color:#f2f2f2">
        	<div class="container">
        		 <div class="area-heading" >
                    <h3 style="text-align:center;">earth, 어뜨</h3>
                    <p>지구를 살리는 작은 실천.</p><br><br>
                </div>
        		<div class="feature_inner row">
        			<div class="col-lg-4 col-md-6">
        				<div class="feature_item">
        				    <a href="/earth/board/dailyChallenge.et">
                           		<img class="img-fluid" src="/earth/resources/bootstrap/imgs/bear.gif" alt="" style="width:280px; height:200px;">
        					<h4>우리가 북극곰의 집을 빼앗고 있어요</h4></a>
                            <p>빠르게 증가하는 지구 온도로 인해 빙하가 녹고있어요. 우리의 작은 실천이 북극곰을 살립니다. 함께해주세요! </p>
                            <a href="/earth/board/dailyChallenge.et">>오늘의 실천 함께하기</a>
        				</div>
        			</div>
        			<div class="col-lg-4 col-md-6">
        				<div class="feature_item">
							<a href="/earth/board/tip.et">
                            	<img class="img-fluid" src="/earth/resources/bootstrap/imgs/trash2.gif" alt="" style="width:280px; height:200px;">
                            <h4>어뜨만의 꿀팁</h4></a>
							<p>잘못된 분리수거 방법, 아이스팩을 버리는 방법, 쓰레기를 줄이는 방법, 어뜨가 알려주는 꿀팁을 이용해보세요.   </p>	
							<a href="/earth/board/tip.et">>꿀팁 보러가기</a>
										
						</div>
        			</div>
        			<div class="col-lg-4 col-md-6">
        				<div class="feature_item">
							<a href="/earth/news/news.et">
        						<img class="img-fluid" src="/earth/resources/bootstrap/imgs/trash.gif" alt=""style="width:280px; height:200px;">
        					<h4>매일매일 쏟아지는 환경 이슈</h4></a>
        					<p>지구가 아파요. 이상기후, 지구온도 증가, 생태계 파괴, 심각한 바다 쓰레기 등 환경문제에 관심을 가져주세요. </p>
							<a href="/earth/news/news.et">>뉴스 보러가기</a>
        				</div>
        			</div>
        		</div>
        	</div>
        </section>
    <div class="three-block  area-padding">
        <div class="container">
            <div class="row">
                <div class="area-heading">
                    <h3 style="text-align:center;">환경일기 TOP3</h3>
                    <p> 나의 환경일기를 작성해보아요! 가장 많은 좋아요를 받은 일기를 소개합니다.</p>
                </div>
            </div>
            <div class="row">
            <c:if test="${count == 0}">	
				  <div class="image-wrap-2">
           			 <div class="image-info">
             			 <p>게시글이 없습니다. </p>			
					</div>
				  </div>
			</c:if>
			<c:if test="${count != 0}">
				<c:forEach var="diary" items="${diaryList}">
                <div class="col-lg-4">
                    <div class="single-blog style-five">
                        <div class="thumb">
							<a href="/earth/board/diaryContent.et?pageNum=${pageNum}&boardnum=${diary.boardnum}">
								<c:if test="${diary.img != null}">
									<img src="${pageContext.request.contextPath}/save/${diary.img}" style="width:290px; height:290px;"/>
								</c:if>
								<c:if test="${diary.img == null}">
									<img src="" style="width:290px; height:290px;"/>
								</c:if>	
								</a>
						</div>						                        
                        <div class="short_details">
                            <div class="meta-top d-flex">
								<img class="badgeImg" src="${pageContext.request.contextPath}/save/${diary.badgeimg}"/>		                        
								<a href="#">${diary.nickname}</a> &emsp;
                               	<a href="#" >조회수 :${diary.readcount} </a>
                            </div>
                            <a class="d-block" href="/earth/board/diaryContent.et?pageNum=${pageNum}&boardnum=${diary.boardnum}" style="height:60px;">
                                <h4>${diary.subject }</h4>
                            </a>
                            <div class="meta-bottom d-flex">
                            		<c:if test="${diary.recommend > 0}">	
                                		♥ ${diary.recommend} Like
                                	</c:if>    
                            </div>
                        </div>
                    </div> 
                </div>
                </c:forEach>
            </c:if> 
            </div>
        </div>
    </div>
      <!--================ 이달의 챌린지 section start =================-->  
    <div class="three-block  area-padding" style="background-color:#f2f2f2">
        <div class="container">
            <div class="row">
                <div class="area-heading">
                    <h3 style="text-align:center;">이달의 챌린지</h3>
                    <p>어뜨와 함께 이달의 챌린지에 동참해보세요!</p>
                </div>
            </div>
            <article class="blog_item">
            <c:if test="${count == 0}">	
				  <div class="blog_item_img">
	                   <a href="/earth/board/challengeContent.et?pageNum=${pageNum}&boardnum=${main.boardnum}">
             			 	<p>게시글이 없습니다. </p>	
             			</a>		
					</div>
			</c:if>
            <c:if test="${count != 0}">
				<c:forEach var="main" items="${mainList}">
                        <div class="blog_item_img" style="text-align:center;">
	                          <a href="/earth/board/challengeContent.et?pageNum=${pageNum}&boardnum=${main.boardnum}">
		                          <c:if test="${main.img != null}">
										<img class="card-img rounded-0" src="${pageContext.request.contextPath}/save/${main.img}" style="width:500px; height:290px;"/>
								  </c:if>
								  <c:if test="${main.img == null}">
										<img class="card-img rounded-0" style="width:290px; height:290px;"/>
								  </c:if>
							   </a>
		                         <a href="/earth/board/challengeContent.et?pageNum=${pageNum}&boardnum=${main.boardnum}" class="blog_item_date">
									<h3>챌린지</h3>
									<p style="text-align:center;">진행중</p>
								</a><br>
						        <div class="blog_details">
		                            <a class="d-inline-block" ref="/earth/board/challengeContent.et?pageNum=${pageNum}&boardnum=${main.boardnum}">
		                                <h2>${main.subject}</h2>
		                            </a>
		                            <p>이달의 챌린지 참여하고 포인트도 받아보세요!</p>
		                        </div>
		               </div>
                 	</c:forEach>
            	</c:if>
            </article>
        </div>
    </div>
    <!--================ 이달의 챌린지 section end =================--> 
 <%@ include file="../include/footer.jsp"%>
</body>
</html>
