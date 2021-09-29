<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>result popup</title>
</head>

<!-- 로그인 처리 결과 -->
<c:if test="${loginResult == 1}">
	<c:redirect url="/main/main.et" />
</c:if>

<c:if test="${loginResult == -1}">
	<script>
		alert("존재하지 않는 아이디입니다. 아이디를 확인하고 다시 시도해주세요.");
		location.href = 'http://localhost:8080/earth/user/loginForm.et';
	</script>
</c:if>

<c:if test="${loginResult == 0}">
	<script>
		alert("비밀번호를 확인하고 다시 시도해주세요.");
		location.href = 'http://localhost:8080/earth/user/loginForm.et';
	</script>
</c:if>

<!-- 회원정보 : 비밀번호 수정 처리 결과 -->
<c:if test="${pwResult == 1}">
	<script>
		alert("비밀번호가 성공적으로 변경되었습니다. 로그인 후 이용해주세요.");
		location.href = 'http://localhost:8080/earth/user/logout.et';
	</script>
</c:if>
<c:if test="${pwResult == 0}">
	<script>
		alert("현재 비밀번호가 틀렸습니다. 다시 확인하시고 시도해주세요.");
		location.href = 'http://localhost:8080/earth/user/userPwModifyForm.et';
	</script>
</c:if>
<c:if test="${pwResult == -1}">
	<script>
		alert("현재 비밀번호와 새비밀번호가 같습니다. \n같은 비밀번호로는 변경하실 수 없습니다.");
		location.href = 'http://localhost:8080/earth/user/userPwModifyForm.et';
	</script>
</c:if>

<!-- 회원정보 : 회원 탈퇴 처리 결과 -->
<c:if test="${deleteResult == 1}">
	<script>
		alert("회원탈퇴되었습니다. 이용을 원하실 경우 재가입 후 이용해주세요.");
		location.href = 'http://localhost:8080/earth/main/main.et';
	</script>
</c:if>
<c:if test="${deleteResult != 1}">
	<script>
		alert("비밀번호가 맞지 않습니다. 다시 시도해주세요.");
		location.href = 'http://localhost:8080/earth/user/userDeleteForm.et';
	</script>
</c:if>

<body>
</body>
</html> 