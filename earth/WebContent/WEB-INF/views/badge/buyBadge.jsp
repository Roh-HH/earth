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

<script language="javascript">
	function moveClose(num) {
		opener.location.href="/earth/badge/buyComplete.et?num=" + num;
	  	self.close();
	  	
	  	
	}
	
	function buyCheck() {
		if()
	}
</script>
<body >
	<p style="text-align:center; font-size:18px; font-weight:bold; color:#111111;">뱃지 구매하기</p>
	<div style="text-align : center;">
		<img src="/earth/resources/bootstrap/imgs/${result.badgeimg}" style="align:center;width:80px; height:80px;"/>
	</div>
	<table>
		<tr>
			<td style="width:120px; text-align:center;">벳지명</td>
			<td align="left" >${result.badgename}
			<td></td>
		</tr>
		<tr>
			<td style="width:120px; text-align:center;">가격</td>
			<td align="left" colspan="">${result.badgeprice}
		</tr>
		<tr>
			<td style="width:120px; text-align:center;">보유 포인트</td>
			<td align="left"> ${point}
		</tr>
	
		<tr>
			<td>
				<input type="button" value="닫 기" style=" width:60px; background-color:#ffffff; border:none; color:#1E88E5; float:left" onclick="self.close();" /></td>
			<td>
				<c:if test="${point gt result.badgeprice}">
					<input type="button" value="구매하기" style="width:80px; background-color:#1E88E5; border:none; color:#ffffff; float:right" onclick="moveClose(${result.badgenum});" />
				</c:if>
				<c:if test="${point lt result.badgeprice}">
					<input type="button" value="포인트부족" style="width:100px; background-color:#aeafb0; border:none; color:#ffffff; float:right" />
				</c:if>
			</td>
		</tr>
		
	</table>
</body>
</html>