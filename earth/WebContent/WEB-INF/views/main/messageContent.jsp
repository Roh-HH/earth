<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>쪽지</title>
	<link rel='stylesheet' href='/earth/resources/bootstrap/css/style.css' type='text/css' media='all'/>
	<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Oswald:400,500,700%7CRoboto:400,500,700%7CHerr+Von+Muellerhoff:400,500,700%7CQuattrocento+Sans:400,500,700' type='text/css' media='all'/>
	<link rel='stylesheet' href='/earth/resources/bootstrap/css/easy-responsive-shortcodes.css' type='text/css' media='all'/>
</head>
<body>
	<div style="text-align : center;">
		<table>
			<tr>
				<td style="width:40px; text-align:center;">발신자</td>
				<td style="width:80px; text-align:center;">${message.id}</td>
			</tr>
			<tr>
				<td style="width:40px; text-align:center;">수신자</td>
				<td style="width:80px; text-align:center;">${message.receiver}</td>
			</tr>
			<tr>
				<td style="width:40px; text-align:center;">내용</td>
				<td style="width:80px; text-align:center;" colspan=2>${message.ctt}</td>
			</tr>
			<tr>
				<td align="center" colspan=2><br/>
				<c:if test="${uri != null}">
					<input type="button" value="답장" style="width:80px; background-color:#1E88E5; border:none; color:#ffffff; float:right" onclick="window.location='/earth/main/messageWriteForm.et?receiver=${message.id}&pageNum=${pageNum}&uri=${uri}'"/>&emsp;
				</c:if>	 
				<input type="button" value="닫기" style="width:60px; background-color:#ffffff; border:none; color:#1E88E5; float:left" onclick="self.close();" /></td>
			</tr>
		</table> 
	</div>
</body>
</html>