<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>어뜨 - 지구를 살리는 작은 실천</title>
	<link rel='stylesheet' href='/earth/resources/bootstrap/css/style.css' type='text/css' media='all'/>
	<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Oswald:400,500,700%7CRoboto:400,500,700%7CHerr+Von+Muellerhoff:400,500,700%7CQuattrocento+Sans:400,500,700' type='text/css' media='all'/>
	<link rel='stylesheet' href='/earth/resources/bootstrap/css/easy-responsive-shortcodes.css' type='text/css' media='all'/>
</head>
<script type="text/javascript">
	<%-- 유효성검사 --%>
	function sendIt(){
		if (r.condition.value == "" || r.condition.value==null) {
			alert("신고 사유를 선택해주세요.")
			return false;
		}
		if (r.reportctt.value == "") {
			alert("신고 내용을 입력해주세요")
			r.reportctt.focus();
			return false;
		}
	}
</script>
<body>
	<div style="text-align : center;">
		<form name="r" action="/earth/adminmypage/reportPro.et?uri=${uri}" method="post" onsubmit="return sendIt();">
			<input type="hidden" name="id" value="${sessionScope.sid}"/>
			<input type="hidden" name="receiver" value="${receiver}" />
			<input type="hidden" name="boardnum" value="${boardnum}"/>
			<table>
				<tr>
					<td style="width:40px; text-align:center;">신고 대상자</td>
					<td style="width:80px; text-align:center;">${receiver}</td>
				</tr>
				<tr>
					<td colspan=2 style="width:40px; text-align:center;">신고 사유</td>
				</tr>
				<tr>
					<td style="width:80px;">
						<input type="radio" name="condition" value="1"/>영리목적홍보<br/>
						<input type="radio" name="condition" value="3"/>불법촬영물<br/>
						<input type="radio" name="condition" value="5"/>욕설/인신공격<br/>
						<input type="radio" name="condition" value="7"/>도배<br/>
					</td>
					<td style="width:80px;">
						<input type="radio" name="condition" value="2"/>불법정보<br/>
						<input type="radio" name="condition" value="4"/>음란물유포<br/>
						<input type="radio" name="condition" value="6"/>개인정보노출<br/>
						<input type="radio" name="condition" value="8"/>기타사유<br/>
					</td>
				</tr>
				<tr>
					<td style="width:40px; text-align:center;" colspan=2>세부 내용</td>
				</tr>
				<tr>
					<td style="width:80px; text-align:center;" colspan=2>
						<textarea name="reportctt" style='IME-MODE: active' placeholder="내용을 입력하세요"></textarea>
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