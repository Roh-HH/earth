<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>자유게시판 수정 결과</title>
</head>
<body>
	<c:if test="${result == 1}">
		<script>
			alert("게시글이 정상적으로 수정되었습니다.");
			window.location = "/earth/board/freeList.et"; 
		</script>
	</c:if>
	<c:if test="${result != 1}">
		<script>
			alert("오류가 발생했습니다. 비밀번호를 확인해주세요.");
			window.location = "/earth/board/freeList.et"; 
		</script>
	</c:if>
</body>
</html>