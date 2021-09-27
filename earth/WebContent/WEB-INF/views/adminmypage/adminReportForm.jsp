<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		if (r.receiver.value == "") {
			alert("수신인을 입력하지 않았습니다.")
			r.receiver.focus();
			return false;
		}
		if (r.ctt.value == "") {
			alert("내용을 입력하지 않았습니다.")
			r.ctt.focus();
			return false;
		}
	}
</script>
<body>
	<div style="text-align : center;">
		<form name="r" action="/earth/adminmypage/adminReportPro.et?sel=${sel}&search=${search}" method="post" onsubmit="return sendIt();">
			<input type="hidden" name="reportnum" value="${report.reportnum}"/>
			<input type="hidden" name="pageNum" value="${pageNum}"/>
			<input type="hidden" name="id" value="${report.receiver}"/>
			<table>
				<tr>
					<td style="width:40px; text-align:center;">신고자</td>
					<td style="width:80px; text-align:center;">${report.id}</td>
				</tr>
				<tr>
					<td style="width:40px; text-align:center;">신고 대상자</td>
					<td style="width:80px; text-align:center;">${report.receiver}</td>
				</tr>
				<tr>
					<td style="width:40px; text-align:center;">신고 유형</td>
					<td style="width:80px; text-align:center;">
						<c:if test="${report.condition == 1}">영리목적홍보</c:if>
						<c:if test="${report.condition == 2}">불법정보</c:if>
						<c:if test="${report.condition == 3}">불법촬영물</c:if>
						<c:if test="${report.condition == 4}">음란물유포</c:if>
						<c:if test="${report.condition == 5}">욕설/인신공격</c:if>
						<c:if test="${report.condition == 6}">개인정보노출</c:if>
						<c:if test="${report.condition == 7}">도배</c:if>
						<c:if test="${report.condition == 8}">기타사유</c:if>
					</td>
				</tr>
				<tr>
					<td style="width:40px; text-align:center;">세부 내용</td>
					<td style="width:80px; text-align:center;">${report.reportctt}</td>
				</tr>
				<tr>
					<td style="width:40px; text-align:center;">신고된 글 내용</td>
					<td style="width:80px; text-align:center;">${report.ctt}</td>
				</tr>
				<tr>
					<td style="width:40px; text-align:center;"><br/>조치</td>
					<td style="width:80px; text-align:center;">
						<input type="radio" name="punish" value="0"/>보류<br/>
						<input type="radio" name="punish" value="1"/>경고<br/>
						<input type="radio" name="punish" value="2"/>영구정지<br/>
					</td>
				</tr>
				<tr>
					<td align="center" colspan=2><br/>
						<input type="submit" value="확인" style="width:80px; background-color:#1E88E5; border:none; color:#ffffff; float:right" />&emsp;	 
						<input type="button" value="닫기" style="width:60px; background-color:#ffffff; border:none; color:#1E88E5; float:left" onclick="self.close();" />
					</td>
				</tr>
			</table> 
		</form>
	</div>
</body>
</html>