<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자 마이페이지(회원관리)</title>
</head>

<body>
<!-- 
	<br />
	<div align="right"> 
		<c:if test="${sessionScope.sid == null}">
			<button onclick="window.location='/spring/member/loginForm.mer'">로그인</button>
		</c:if>
		<c:if test="${sessionScope.sid != null}">
			<button onclick="window.location='/spring/member/logout.mer'">로그아웃</button>
		</c:if>
	</div>
 -->	

	<c:if test="${sessionScope.sid != null}">
	

	<!-- 사이드바  -->
	
	<div id="sidebar">
	  	<h1 class="menu_name">관리자 페이지</h1>
		<div class="sidebar_menu_wrap">
			<div class="nav-wrap">
				<nav class="main-nav" role="navigation">
				    <ul class="unstyled list-hover-slide">
				      	<li class="menu"><a href="adminUserForm.jsp">회원 관리</a></li>
					    <li class="menu"><a href="adminMemberForm.jsp">사업자 관리</a></li>
					   	<li class="menu"><a href="adminReserveForm.jsp">예약 관리</a></li>
						<li class="menu"><a href="adminReviewForm.jsp">후기 관리</a></li>
					    <li class="menu"><a href="../board/list.jsp?categ=0">공지사항</a></li>
					    <li class="menu"><a href="adminMemberQnAForm.jsp">사업자 1:1 문의</a></li>
					    <li class="menu"><a href="adminUserQnAForm.jsp">일반회원 1:1 문의</a></li>
				    </ul>
				 </nav>
			</div>
		</div>
	</div>
	
	
	
	
	<!-- 콘텐츠 -->
	
	<div id="section" style="padding-left:15%; margin-left:40px;">
		<c:if test="${count == 0}">
		<% if(count == 0) { %>
			<h3 align="center">표시할 내용이 없습니다.</h3>
		</c:if>
		<c:if test="${count > 0}">
		<form action="adminUserForm.jsp" name="frmUserInfo" method="post">
		
			
			<h2>회원 관리 페이지</h2>
			<ul class="responsive-table">
			  	<li class="table-header">
				      <div class="col col-1"></div>
				      <div class="col col-2">회원아이디</div>
				      <div class="col col-3">회원명</div>
				      <div class="col col-4">연락처</div>
				      <div class="col col-5">이메일</div>
			  	</li>


					for(int i = 0; i < userList.size(); i++) {
						UsersDTO user = (UsersDTO)userList.get(i);	
%>
				    <li class="table-row">
				      <div class="col col-1" data-label="checkbox"><input type="checkbox" name="info" value="<%=user.getId()%>" /></div>
				      <div class="col col-2" data-label="userID"><%=user.getId() %></div>
				      <div class="col col-3" data-label="userName"><%=user.getUser_name() %></div>
				      <div class="col col-4" data-label="userPhone"><%=user.getUser_phone() %></div>
				      <div class="col col-5" data-label="userEmail"><%=user.getUser_email() %></div>
				    </li>	
				<%} %>
			  </ul>
			  
			  
				<input class="btn" type="button" value="삭제" onclick="chkUser();"/>
			
			<%} %>
			<br />
			<div class="pageNum">
				<%
				int pageBlock = 3;
				int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
				int startPageNum = (int)(currentPage-1)/(pageBlock) * pageBlock + 1;
				int endPageNum = startPageNum + pageBlock -1;
				if(endPageNum > pageCount) endPageNum = pageCount;
				
				if(sel != null && search != null) {
					if(startPageNum > pageBlock) {	%>			
						<a href="adminUserForm.jsp?pageNum=<%=startPageNum-pageBlock%>&sel=<%=sel%>&search=<%=search%>" class="pageNums"> &lt; </a>	&nbsp;	
					<%}
				
					for(int i = startPageNum; i <= endPageNum; i++) { %>		
						<a href="adminUserForm.jsp?pageNum=<%=i%>&sel=<%=sel%>&search=<%=search%>" class="pageNums"> &nbsp; <%=i %> &nbsp; </a>
					<%}
			
					if(endPageNum < pageCount) {	%>			
						&nbsp; <a href="adminUserForm.jsp?pageNum=<%=startPageNum+pageBlock%>&sel=<%=sel%>&search=<%=search%>" class="pageNums"> &gt; </a>
					<%}		
				}else {
					if(startPageNum > pageBlock) {	%>			
						<a href="adminUserForm.jsp?pageNum=<%=startPageNum-pageBlock%>" class="pageNums"> &lt; </a>	&nbsp;	
					<%}
				
					for(int i = startPageNum; i <= endPageNum; i++) { %>		
						<a href="adminUserForm.jsp?pageNum=<%=i%>" class="pageNums"> &nbsp; <%=i %> &nbsp; </a>
					<%}
			
					if(endPageNum < pageCount) {	%>			
						&nbsp; <a href="adminUserForm.jsp?pageNum=<%=startPageNum+pageBlock%>" class="pageNums"> &gt; </a>
					<%}%>
				<%} %>
			</div>
			<div class="search_wrap">
				<div id="sel" class="select-box">
					  <div class="select-box_current" tabindex="1">
					    <div class="select-box_value">
					      <input class="select-box_input" type="radio" id="id" value="id" name="sel" checked="checked"/>
					      <p class="select-box_input-text">회원아이디</p>
					    </div>
					    <div class="select-box_value">
					      <input class="select-box_input" type="radio" id="user_phone" value="user_phone" name="sel"/>
					      <p class="select-box_input-text">연락처</p>
					    </div>
					    <img class="select-box_icon" src="http://cdn.onlinewebfonts.com/svg/img_295694.svg" alt="Arrow Icon" aria-hidden="true"/>
					  </div>
					  <ul class="select-box_list">
					    <li>
					      <label class="select-box_option" for="id" aria-hidden="aria-hidden">회원아이디</label>
					    </li>
					    <li>
					      <label class="select-box_option" for="user_phone" aria-hidden="aria-hidden">연락처</label>
					    </li>
					  </ul>
					</div>
					<input class="search" type="text" name="search" />
					<input class="btn" type="submit" value="검색" />
					<input class="btn" type="button" value="전체보기" onclick="window.location='adminUserForm.jsp'"/>
				</div>
				<h3 class="currentPage" style="color:black">현재 페이지 : <%=pageNum%></h3>
		</form>
		</c:if>
		</div>
		
		
		<div id="footer">
     
     	
   		</div>
	</c:if>	
	</body>

</html>