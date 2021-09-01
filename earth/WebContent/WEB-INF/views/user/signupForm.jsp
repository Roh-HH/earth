<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<link href="/spring/resources/css/style.css" rel="stylesheet" type="text/css" >
	<script>
		function confirmId(inputForm){  
			if(inputForm.id.value == "" || !inputForm.id.value) {
				alert("아이디를 입력하세요");
				return;
			}
			// 팝업 띄우기
			var url = "/spring/member/confirmId.do?id=" + inputForm.id.value; 
			open(url, "confirmId", "toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no, width=300, height=200"); 
		}
	</script>
</head>
<body>
	<br />
	<h1 align="center"> 회원가입 </h1>

	<form action="/web/member/signupPro.hrd" method="post" name="inputForm" enctype="multipart/form-data">
		<table>
			<tr>
				<td>ID * </td>
				<td>
					<input type="text" name="id" /> 
				</td>
			</tr>
			<tr>
				<td>아이디중복체크</td>
				<td>
					<input type="button" value="아이디 중복 확인" onclick="confirmId(this.form)" /> 
				</td>
			</tr>
			<tr>
				<td>Password * </td>
				<td>
					<input type="password" name="pw" /> 
				</td>
			</tr>
			<tr>
				<td>Password Confirm * </td>
				<td>
					<input type="password" name="pwCh" /> 
				</td>
			</tr>
			<tr>
				<td>Name * </td>
				<td>
					<input type="text" name="name" /> 
				</td>
			</tr>
			<tr>
				<td>Age </td>
				<td>
					<input type="text" name="age" />
				</td>
			</tr>
			<tr>
				<td>Email </td>
				<td>
					<input type="text" name="email" /> 
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"> 
					<input type="submit" value="가입" /> 
					<input type="reset" value="재작성" /> 
					<input type="button" value="취소" onclick="window.location='/web/member/main.hrd'"/> 
				</td>
			</tr>
		</table>
	</form>



	


</body>
</html>