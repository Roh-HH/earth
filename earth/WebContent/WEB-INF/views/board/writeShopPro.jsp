<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상점소개</title>
</head>
<body>
	<c:if test="${result == 1}">
		<script>
			alert("게시글이 정상적으로 등록되었습니다.");
			window.location = "/earth/board/shop.et"; 
		</script>
	</c:if>
	<c:if test="${result != 1}">
		<script>
			alert("오류가 발생했습니다. 다시 시도해주세요.");
		</script>
	</c:if>
</body>
</html>