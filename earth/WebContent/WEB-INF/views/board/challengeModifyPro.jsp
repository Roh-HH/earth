<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>수정 결과</title>
</head>
<body>
	<c:if test="${result == 1}">
		<script>
			alert("게시글이 정상적으로 수정되었습니다.");
			window.location = "/earth/board/challengeList.et"; 
		</script>
	</c:if>
	<c:if test="${result != 1}">
		<script>
		alert("비밀번호가 맞지 않습니다.");
		history.go(-1);
	</script>
	</c:if>
</body>
</html>