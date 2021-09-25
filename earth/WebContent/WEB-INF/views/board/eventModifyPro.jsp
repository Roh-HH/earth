<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>행사일정수정</title>
</head>
<body>
	<c:if test="${result == 1}">
		<script>
			alert("게시글이 정상적으로 수정되었습니다.");
			window.location = "/earth/board/event.et"; 
		</script>
	</c:if>
	<c:if test="${result != 1}">
		<script>
			alert("오류가 발생했습니다. 다시 시도해주세요.");
			history.go(-1);
		</script>
	</c:if>
</body>
</html>