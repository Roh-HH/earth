<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en-Us">
<head>
<link rel='stylesheet' href='/earth/resources/bootstrap/css/style.css' type='text/css' media='all'/>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Oswald:400,500,700%7CRoboto:400,500,700%7CHerr+Von+Muellerhoff:400,500,700%7CQuattrocento+Sans:400,500,700' type='text/css' media='all'/>
<link rel='stylesheet' href='/earth/resources/bootstrap/css/easy-responsive-shortcodes.css' type='text/css' media='all'/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자 일반회원 삭제</title>
</head>
<body>
	<br/><br/><br/><br/>
	<form action="adminUserDeletePro.et" method="post">
		<c:foreach var="i" items="${info}" varStatus="status" >	
			<input type="hidden" name="check" value="${info}" /> 		
		</c:foreach>	
	
		<table align="center">
			<tr align="center">
				<td>회원을 삭제하시겠습니까? <br /><br /></td>
			</tr> 	
			<tr>
				<td><br/>
					<input type="submit" value="확인" />&emsp;
					<input type="button" value="취소" onclick="window.close()"/>
				</td>
			</tr>	
		</table>	
	</form>
</body>
</html>
