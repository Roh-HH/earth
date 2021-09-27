<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>어뜨 - 지구를 살리는 작은 실천</title>
</head>
<body>
	<c:if test="${result == 1}">
		<script>
			alert("신고가 접수되었습니다.");
			self.close();
		</script>
	</c:if>
	<c:if test="${result != 1}">
		<script>
			alert("오류가 발생했습니다. 다시 시도해주세요.");
			self.close();
		</script>
	</c:if>
</body>
</html>