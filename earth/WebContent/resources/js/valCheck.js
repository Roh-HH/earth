// 유효성검사 함수

	// 공백확인 함수
	function checkExistData(value, dataName) {
	    if (value == "") {
	        alert(dataName + " 입력해주세요!");
	        return false;
	    }
	    return true;
	}
	
	// 이름 유효성 검사 함수
	function checkName(name) {
		if (!checkExistData(name, "이름을"))
		    return false;
		
		var nameRegExp = /^[가-힣]{1,10}$/;
		if (!nameRegExp.test(name)) {
		    alert("이름은 영어,숫자 및 특수문자 사용이 불가합니다.");
			 frm.name.value = "";
			 frm.name.focus();
		    return false;
		}
		return true; //확인이 완료되었을 때
	}
	
	// 비밀번호 유효성 검사 함수
	function checkPw(pw){
	    if (!checkExistData(pw, "비밀번호를"))
	        return false;	
	    
	    var pwRegExp = /^[a-zA-z0-9]{8,20}$/;
		if(!pwRegExp.test(pw)) {
			 alert("비밀번호는 8자리 ~ 20자리 이내로 입력해주세요. \n* 특수문자 사용은 불가합니다.");
			 frm.pw.value = "";
			 frm.pw.focus();
			 return false;
		}
		return true;
	}
	
	// 전화번호 유효성 검사 함수
	function checkPhone(phone) {
		if (!checkExistData(phone, "전화번호를"))
			return false;
		
		var phoneRegExp = /^[0-9]*$/
		if (!phoneRegExp.test(phone)) {
			alert("전화번호는 숫자만 입력해주세요.");
			 frm.phone.focus();
			return false;
		}
		return true; //확인이 완료되었을 때
	}
	
	// 이메일 유효성 검사 함수
	function checkEmail(email) {
	    if (!checkExistData(email, "이메일을"))
	        return false;
	    var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
	    if (!emailRegExp.test(email)) {
	        alert("이메일 형식이 올바르지 않습니다.");
	        frm.email.focus();
	        return false;
	    }
	    return true; //확인이 완료되었을 때
	}
	
	// 새비밀번호 유효성 검사 함수
	function checkNewPw(newpw){
	    if (!checkExistData(newpw, "변경할 비밀번호를"))
	        return false;	
	    
	    var pwRegExp = /^[a-zA-z0-9]{8,20}$/;
		if(!pwRegExp.test(newpw)) {
			 alert("비밀번호는 8자리 ~ 20자리 이내로 입력해주세요. \n* 특수문자 사용은 불가합니다.");
			 frm.newpw.value = "";
			 frm.newpw.focus();
			 return false;
		}
		return true;
	}	

