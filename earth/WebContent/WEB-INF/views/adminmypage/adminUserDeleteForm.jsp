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
	<div style="margin:auto;">
		<form action="/earth/adminmypage/adminUserDeletePro.et">
			
				<h3 style="margin:auto; text-align: center;">회원을 삭제하시겠습니까?</h3>
				<br /><br />
				<input type="hidden" name="id" value="${id}"/>
				
				<input type="submit" value="확인" style="float:left; margin:10%; width:20%"/>
				<input type="button" value="취소" onclick="window.close()" style="float:right; margin:10%; width:20%; "/>
		
		</form>
	</div>
</body>
</html>
