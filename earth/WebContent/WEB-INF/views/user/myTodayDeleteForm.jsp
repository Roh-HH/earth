<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>나의 실천 게시글 삭제하기</title>
</head>

	<style>
	table {
		margin-top:50px;
	}
	
	input[type=button] { 
      	border: none;
      	border-radius: 6px;
      	width: 110px;
      	height:40px;	
	}
	input[type=submit] { 
      	border: none;
      	border-radius: 6px;
      	width: 110px;
      	height:40px;	
	}
    </style>

<body>
	<form action="/earth/user/myTodayDeletePro.et?boardnum=${boardnum}&id=${id}" method="post">
		<table align="center">
			<tr>
				<td>
					나의 실천 게시글을 삭제 하시겠습니까?<br />
					확인을 누를 경우, 게시글은 영구삭제 됩니다.
				</td>
			</tr>
			<tr>
				<td align="center"><br/>
					<input type="submit" value="삭제하기" />&emsp;	 
					<input type="button" value="취소" onclick='window.close()'/> 
				</td>
			</tr>
		</table> 
	</form>
</body>
</html>
