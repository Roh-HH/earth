<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html class="no-js" lang="en-US">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>어뜨- 지구를 살리는 작은 실천 </title>

<link rel='stylesheet' href='/earth/resources/bootstrap/css/style.css' type='text/css' media='all'/>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Oswald:400,500,700%7CRoboto:400,500,700%7CHerr+Von+Muellerhoff:400,500,700%7CQuattrocento+Sans:400,500,700' type='text/css' media='all'/>
<link rel='stylesheet' href='/earth/resources/bootstrap/css/easy-responsive-shortcodes.css' type='text/css' media='all'/>
<!-- <link rel="stylesheet" href="/earth/resources/bootstrap/css/carbon_clock.css"> -->
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<div style="text-align : center;">
	<form name="f" action="/earth/board/delete.et?boardnum=${boardnum}&code=${code}&uri=${uri}&pageNum=${pageNum}" method="post" onsubmit="return sendIt();">
		<table>
			<tr>
				<td style="width:120px; text-align:center;">게시글을 삭제하려면 비밀번호를 입력해주세요.</td>
			</tr>
			<tr>
				<td td style="width:120px; text-align:center;">
					<input type="password" name="pw" autofocus/><br/> 
				</td>
			</tr>
			<tr>
				<td align="center"><br/>
				<input type="submit" value="확인" style="width:80px; background-color:#1E88E5; border:none; color:#ffffff; float:right" />&emsp;	 
				<input type="button" value="닫 기" style=" width:60px; background-color:#ffffff; border:none; color:#1E88E5; float:left" onclick="self.close();" /></td>
			</tr>
		</table> 
	</form>
	</div>
</body>
</html>
