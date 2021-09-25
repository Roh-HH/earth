<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>비밀번호 입력</title>
	<link href="/earth/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<script type="text/javascript">
	<%-- 유효성검사 --%>
	function sendIt(){
		if (f.pw.value == "") {
			alert("비밀번호를 입력하지 않았습니다.")
			f.pw.focus();
			return false;
		}
	}
</script>
<body>
	<form name="f" action="/earth/board/delete.et?boardnum=${boardnum}&code=${code}&uri=${uri}&pageNum=${pageNum}" method="post" onsubmit="return sendIt();">
		<table>
			<tr>
				<td>비밀번호를 입력해주세요.</td>
			</tr>
			<tr>
				<td><input type="password" name="pw" autofocus/><br/> </td>
			</tr>
			<tr>
				<td align="center"><br/>
				<input type="submit" value="확인" />&emsp;	 
				<input type="button" value="취소" onclick='window.close()'/> </td>
			</tr>
		</table> 
	</form>
</body>
</html>
