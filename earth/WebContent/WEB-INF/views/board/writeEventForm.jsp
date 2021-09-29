<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en-Us">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="img/favicon.png" type="image/png">
    <title>어뜨 - 지구를 살리는 작은 실천 </title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/themify-icons.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/flaticon.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/animate-css/animate.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/vendors/popup/magnific-popup.css">
    <!-- main css -->
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/style1.css">
    <link rel="stylesheet" href="/earth/resources/bootstrap/css/responsive.css">
    <script type="text/javascript" src="/earth/resources/ckeditor/ckeditor.js"></script>
</head>
<script type="text/javascript">
function readURL(input) {
   	if (input.files && input.files[0]) {
       var reader = new FileReader();
       reader.onload = function (e) {
   
       		var thumb=new Image();
       		thumb.src=reader.result;
       		thumb.onload=function(){
       			var canvas=document.createElement('canvas');
       			var ctx=canvas.getContext("2d");
       		
           	var image = new Image();
        	image.src = e.target.result;
      
 
       		canvas.width='300';
       		canvas.height='500';
       		
       		ctx.drawImage(this,0,0,canvas.width,canvas.height);
       		
       		var dataURI=canvas.toDataURL("image/jpeg");
       		
       		document.querySelector("#thumbnail").src=dataURI;
       	}
       };
       reader.readAsDataURL(input.files[0]);

   	}	
}
</script>
<script>
//유효성검사 
function checkForm() {
    var subject = document.event.subject;
    // 제목 입력 유무 체크
    if(subject.value == '' ){
        window.alert("제목을 입력해주세요");
        document.event.subject.focus();
        return false; 
    }
    var pw = document.getElementById('pw');
    // 암호 입력 유무 체크
    if(document.event.pw.value == ''){
        alert('비밀번호를 입력해주세요.');
        document.event.pw.focus();
        return false;
    }
    var ctt = document.getElementById('ctt');
    // 내용 입력 유무 체크
    if(document.event.ctt.value == ''){
        alert('내용을 입력해주세요.');
        document.event.ctt.focus();
        return false;
    }
    
    var image = document.getElementById('uploadFile');
    // 이미지 입력 유무 체크 (jpeg, jpg, png만 가능 )
    if(document.event.uploadFile.value == ''){
        alert('이미지는 필수입니다.');
        document.event.uploadFile.focus();
        return false;
    }
    var sch = document.getElementById('sch');
    // 이미지 입력 유무 체크 (jpeg, jpg, png만 가능 )
    if(document.event.sch.value == ''){
        alert('행사날짜는 필수입니다.');
        document.event.sch.focus();
        return false;
    }
}
</script>
<body>
<%@ include file="../include/header.jsp" %>
<section class="category-page area-padding">
        <div class="container">   		
		<div id="content" class="site-content">
			<div id="primary" class="content-area column full">
				<main id="main" class="site-main" role="main">
				<div id="container">
					<div id="content" role="main">
						<form name="event" action="/earth/board/writeEventPro.et" method="post" enctype="multipart/form-data" name="event" id="event" onsubmit="return checkForm();">
							<table>
								<tr>
									<td>작성자</td>
									<td align="left">${sessionScope.sid}</td>
								</tr>
								<tr>
									<td>제 목</td>
									<td align="left">
										<input type="text" name="subject" style="width:700px;" class="check" autocomplete="off"  required/>
										<p><p>
									</td>
								</tr>
								<tr>
									<td>비밀번호</td>
									<td align="left">
										<input type="password" name="pw" style="width:700px;"  required/>
									</td>
								</tr>
								<tr>
									<td>행사날짜</td>
									<td align="left">
     									 <p><input type="date" name = "sch"   required> -<input type="date" name="schend"></p>
										<%--<input type="text" name="sch" style="width:700px;" placeholder="ex)2021.09.05 - 2021.09.21" />--%>
									</td>
								</tr>
								<tr>
									<td>내 용</td>
									<td align="left">
										<textarea class="form-control" id="p_content" name="ctt" style='IME-MODE: active'   required></textarea>
										<script type="text/javascript">
											CKEDITOR.replace('p_content', {filebrowserUploadUrl:'${pageContext.request.contextPath}/fileupload.et'});
										</script>
									</td>
								</tr>
								<tr>
									<td>대표 이미지</td>
									<td align="left" ><input type="file" name="uploadFile" onchange="readURL(this)" accept="image/jpeg, image/png, image/jpg" required /><br>
										<img src="" id="thumbnail"/> 
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<input type="submit" value="저장" style="background-color:#1E88E5; color:#ffffff; float:right"/>
										<input type="button" value="목록으로" onclick="window.location='/earth/board/event.et'" style="background-color:#ffffff; color:#1E88E5; " />
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				</main>					
			</div>
		</div>
	</div>
	</section>
<%@ include file = "../include/footer.jsp"%>
</body>
</html>