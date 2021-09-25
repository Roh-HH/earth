<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>삭제 결과</title>
</head>
<body>
	<c:if test="${pop == 1}">
		<c:if test="${result == 1}">
			<script>
				alert("게시글이 정상적으로 삭제되었습니다.");
				opener.document.location="${uri}";
				self.close();
			</script>
		</c:if>
		<c:if test="${result == -1}">
			<script>
				alert("비밀번호가 일치하지 않습니다.");
				opener.document.location="${uri}";
				self.close();
			</script>
		</c:if>
		<c:if test="${result != 1 && result != -1}">
			<script>
				alert("오류가 발생했습니다. 다시 시도해주세요.");
				opener.document.location="${uri}";
				self.close();
			</script>
		</c:if>
	</c:if>
	
	<c:if test="${pop == 0}">
		<c:if test="${result == 1}">
			<script>
				alert("게시글이 정상적으로 삭제되었습니다.");
				document.location.href="${uri}";
			</script>
		</c:if>
		<c:if test="${result != 1}">
			<script>
				alert("오류가 발생했습니다. 다시 시도해주세요.");
				document.location.href="${uri}";
			</script>
		</c:if>
	</c:if>
	
</body>
</html>