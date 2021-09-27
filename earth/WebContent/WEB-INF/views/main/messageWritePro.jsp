<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>쪽지 전송 결과</title>
</head>
<body>
	<c:if test="${result == 1}">
		<script>
			alert("쪽지가 전달되었습니다.");
			//opener.document.location="${uri}";
			self.close();
		</script>
	</c:if>
	<c:if test="${result != 1}">
		<script>
			alert("오류가 발생했습니다. 다시 시도해주세요.");
			//opener.document.location="${uri}";
			self.close();
		</script>
	</c:if>
</body>
</html>