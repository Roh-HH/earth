<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>쪽지 작성</title>
	<link rel='stylesheet' href='/earth/resources/bootstrap/css/style.css' type='text/css' media='all'/>
	<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Oswald:400,500,700%7CRoboto:400,500,700%7CHerr+Von+Muellerhoff:400,500,700%7CQuattrocento+Sans:400,500,700' type='text/css' media='all'/>
	<link rel='stylesheet' href='/earth/resources/bootstrap/css/easy-responsive-shortcodes.css' type='text/css' media='all'/>
</head>
<script type="text/javascript">
	<%-- 유효성검사 --%>
	function sendIt(){
		if (f.receiver.value == "") {
			alert("수신인을 입력하지 않았습니다.")
			f.receiver.focus();
			return false;
		}
		if (f.ctt.value == "") {
			alert("내용을 입력하지 않았습니다.")
			f.ctt.focus();
			return false;
		}
	}
</script>

<body>
	<div style="text-align : center;">
		<form name="f" action="/earth/main/messageWritePro.et?uri=${uri}&pageNum=${pageNum}" method="post" onsubmit="return sendIt();">
			<table>
				<tr>
					<td style="width:40px; text-align:center;">발신자</td>
					<td style="width:80px; text-align:center;">${id}</td>
				</tr>
				<tr>
					<td style="width:40px; text-align:center;">수신자</td>
					<td style="width:80px; text-align:center;">
						<input type="text" name="receiver" value="${receiver}" autofocus />
					</td>
				</tr>
				<tr>
					<td style="width:40px; text-align:center;">내용</td>
					<td style="width:80px; text-align:center;" colspan=2>
						<textarea name="ctt" style='IME-MODE: active' placeholder="내용을 입력하세요"></textarea>
					</td>
				</tr>
				<tr>
					<td align="center" colspan=2><br/>
					<input type="submit" value="확인" style="width:80px; background-color:#1E88E5; border:none; color:#ffffff; float:right" />&emsp;	 
					<input type="button" value="닫기" style="width:60px; background-color:#ffffff; border:none; color:#1E88E5; float:left" onclick="self.close();" /></td>
				</tr>
			</table> 
		</form>
	</div>
</body>
</html>