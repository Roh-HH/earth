<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>어뜨 - 공지사항</title>
	<link href="/earth/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1 align="center"> 공지사항 </h1>
	
	<c:if test="${count == 0}">
		<table>
			<tr>
				<td align="center">등록된 게시글이 없습니다.</td>
			</tr>
			<tr>
				<td><button onclick="window.location='/spring/board/writeNoticeForm.et'"> 글쓰기 </button></td>
			</tr>
		</table>
	</c:if>
	<table>
		<tr>
			<td> 등록번호 </td>
			<td> 작성자 </td>
			<td> 제목 </td>
			<td> 등록일 </td>
			<td> 조회수 </td>
		</tr>
		<c:if test="${count != 0}">
			<c:forEach var="article" items="${articleList}">
				<tr>
					<td> ${article.boardnum} </td>
					<td> ${article.id} </td>
					<td>
						<a href="/earth/board/noticeContent.et?pageNum=${pageNum}&boardnum=${article.boardnum}">${article.subject}</a>
					</td>
					<td>
						<fmt:formatDate value="${article.reg}" pattern="YYYY-MM-dd HH : mm"/>
					</td>
					<td> ${article.readcount}</td>
				</tr>
			</c:forEach>
		</c:if>
			<tr>
				<td colspan=5 align="right">
					<button onclick="window.location='/earth/board/noticeWriteForm.et'">글쓰기</button>
				</td>
			</tr>
	</table>
	<br />
	<%-- 페이지 번호 --%>
	<div align="center">
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
				<a href="/earth/board/noticeList.et?pageNum=${startPage-pageBlock}&sel=${sel}&search=${search}" class="pageNums"> &lt; &nbsp;</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
				<a href="/earth/board/noticeList.et?pageNum=${i}&sel=${sel}&search=${search}" class="pageNums"> &nbsp; ${i} &nbsp; </a>
			</c:forEach>
			<c:if test="${endPage < pageCount}">
				&nbsp; <a href="/earth/board/noticeList.et?pageNum=${startPage+pageBlock}&sel=${sel}&search=${search}" class="pageNums"> &gt; </a>
			</c:if>
		</c:if>
		
		<%-- 검색 안했을때 페이지번호들   --%> 
		<c:if test="${sel == null || search == null}">
			<c:if test="${startPage > pageBlock}">
				<a href="/earth/board/noticeList.et?pageNum=${startPage-pageBlock}" class="pageNums"> &lt; &nbsp;</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
				<a href="/earth/board/noticeList.et?pageNum=${i}" class="pageNums"> &nbsp; ${i} &nbsp; </a>
			</c:forEach>
			<c:if test="${endPage < pageCount}">
				&nbsp; <a href="/earth/board/noticeList.et?pageNum=${startPage+pageBlock}" class="pageNums"> &gt; </a>
			</c:if>
		</c:if>
		
	</c:if> <%-- end:count > 0 --%>
	</div>
	
	<h3 style="color:grey" align="center"> 현재페이지 : ${pageNum} </h3>
	
	<br /> <br />
	<%-- 작성자/내용 검색 
	<form action="/spring/board/list.do">
		<select name="sel">
			<option value="writer">작성자</option>
			<option value="content">내용</option>
		</select>
		<input type="text" name="search" />
		<input type="submit" value="검색" />
			<c:if test="${sel != null && search != null}">
		<button onclick="window.location='/spring/board/list.do'"> 전체 게시글 보기 </button> <br />
	</c:if>
	</form>
	--%>
	<br />
	
	<%-- 
	<div>
		<form action="/earth/board/uploadTodayChallenge.et" method="post">
			<table>
				<tr align="left">
					<td>
						<input type="radio" name="condition" value="1"/> 텀블러 들고다니기
					</td>
					<td>
						<input type="radio" name="condition" value="2"/> 샴푸 대신 샴푸바 / 손세정제 대신 비누
					</td>
				</tr>
				<tr align="left">	
					<td>
						<input type="radio" name="condition" value="3"/> 일회용 빨대 사용하지말기
					</td>
					<td>
						<input type="radio" name="condition" value="4"/> 배달음식 그릇에 테이크아웃 해오기
					</td>
				</tr>
				<tr align="left">
					<td>
						<input type="radio" name="condition" value="5"/> 장바구니 들고다니기
					</td>
					<td>
						<input type="radio" name="condition" value="6"/> 배달음식 시킬떄 일회용품 거절하기
					</td>
				</tr>
				<tr align="left">
					<td>
						<input type="radio" name="condition" value="7"/> 손수건 챙기기
					</td>
					<td>
						<input type="radio" name="condition" value="8"/> 페트병에 라벨 제거하고 분리수거하기
					</td>
				</tr>
				<tr align="left">
					<td>
						<input type="radio" name="condition" value="9"/> 스탠, 유리제품 사용하기
					</td>
					<td>
						<input type="radio" name="condition" value="10"/> 이면지 사용하기
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="text" name="ctt" placeholder="오늘의 실천내용을 얘기해주세요!"/><input type="submit" value="공유하기"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	 --%>
	<br /> <br /> <br /> <br />

</body>
</html>