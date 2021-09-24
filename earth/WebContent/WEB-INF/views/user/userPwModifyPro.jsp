<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>

<c:if test="${result == 1}">
	<script>
		alert("비밀번호가 성공적으로 변경되었습니다. 로그인 후 이용해주세요.");
		location.href = 'http://localhost:8080/earth/user/loginForm.et';
	</script>
</c:if>
<c:if test="${result == 0}">
	<script>
		alert("현재 비밀번호가 틀렸습니다. 다시 확인하시고 시도해주세요.");
		history.go(-1);
	</script>
</c:if>
<c:if test="${result == -1}">
	<script>
		alert("현재 비밀번호와 새비밀번호가 같습니다. 같은 비밀번호로는 변경하실 수 없습니다.");
		history.go(-1);
	</script>
</c:if>

<body>

</body>
</html>