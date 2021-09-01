<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>오늘의 실천</title>
	<link href="/earth/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1 align="center"> 오늘의 실천 </h1>
	
	<c:if test="${count == 0}">
		<table>
			<tr>
				<td align="center">아직 오늘의 게시글이 없습니다.</td>
			</tr>
			<tr>
				<td><button onclick="window.location='/spring/board/writeForm.do'"> 글쓰기 </button></td>
			</tr>
		</table>
	</c:if>
	
	<c:if test="${count != 0}">
		<table>
			<c:forEach var="article" items="${articleList}">
				<tr>
					<td> ${article.id} </td>
					<td align="left">
						<c:if test="${article.condition == 1}">
							텀블러 들고다니기
						</c:if>
						<c:if test="${article.condition == 2}">
							샴푸 대신 샴푸바 / 손세정제 대신 비누
						</c:if>
						<c:if test="${article.condition == 3}">
							일회용 빨대 사용하지말기
						</c:if>
						<c:if test="${article.condition == 4}">
							배달음식 그릇에 테이크아웃 해오기
						</c:if>
						<c:if test="${article.condition == 5}">
							장바구니 들고다니기
						</c:if>
						<c:if test="${article.condition == 6}">
							배달음식 시킬떄 일회용품 거절하기
						</c:if>
						<c:if test="${article.condition == 7}">
							손수건 챙기기
						</c:if>
						<c:if test="${article.condition == 8}">
							페트병에 라벨 제거하고 분리수거하기
						</c:if>
						<c:if test="${article.condition == 9}">
							스탠, 유리제품 사용하기
						</c:if>
						<c:if test="${article.condition == 10}">
							이면지 사용하기
						</c:if>
						<br/>
						${article.ctt}
					</td>
					
					<td>
						<fmt:formatDate value="${article.reg}" pattern="HH시 mm분"/>
						<br/>
						<button>신고</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
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
				<a href="/earth/board/dailyChallenge.et?pageNum=${startPage-pageBlock}&sel=${sel}&search=${search}" class="pageNums"> &lt; &nbsp;</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
				<a href="/earth/board/dailyChallenge.et?pageNum=${i}&sel=${sel}&search=${search}" class="pageNums"> &nbsp; ${i} &nbsp; </a>
			</c:forEach>
			<c:if test="${endPage < pageCount}">
				&nbsp; <a href="/earth/board/dailyChallenge.et?pageNum=${startPage+pageBlock}&sel=${sel}&search=${search}" class="pageNums"> &gt; </a>
			</c:if>
		</c:if>
		
		<%-- 검색 안했을때 페이지번호들   --%> 
		<c:if test="${sel == null || search == null}">
			<c:if test="${startPage > pageBlock}">
				<a href="/earth/board/dailyChallenge.et?pageNum=${startPage-pageBlock}" class="pageNums"> &lt; &nbsp;</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
				<a href="/earth/board/dailyChallenge.et?pageNum=${i}" class="pageNums"> &nbsp; ${i} &nbsp; </a>
			</c:forEach>
			<c:if test="${endPage < pageCount}">
				&nbsp; <a href="/earth/board/dailyChallenge.et?pageNum=${startPage+pageBlock}" class="pageNums"> &gt; </a>
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
	<br /> <br /> <br /> <br />

</body>
</html>