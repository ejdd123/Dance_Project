<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>

<style>
form, table, div {
	padding: 20px;
}
tr {
	height: 30px;
}
td{
	padding: 10px 30px; 
}
a {
    text-decoration: none;	/* 밑줄 제거 */
    font-size: 30px;
}
</style>

<!-- script - function 사용 시 필요 -->
<script src="http://madalla.kr/js/jquery-1.8.3.min.js"></script>

<script>
function checkId() {
	window.open('${pageContext.request.contextPath }/MemberServlet/joinIdCk.do?me_id='+document.getElementById('me_id').value);
}

function beforeJoin() {
	if(!document.getElementById('me_name').value) {
		alert('이름을 입력해주세요.');	
		document.getElementById('me_name').focus();
		
		return false;
	} else if (!document.getElementById('me_id').value) {
		alert('아이디를 입력해주세요.');	
		document.getElementById('me_id').focus();
		
		return false;
	} else if(!document.getElementById('me_password').value) {
		alert('비밀번호를 입력해주세요.');
		document.getElementById('me_password').focus();
	
		return false;
	} else if(!document.getElementById('me_password_ck').value) {
		alert('비밀번호를 한번 더 입력해주세요.');
		document.getElementById('me_password_ck').focus();
	
		return false;
	} else if (!document.getElementById('me_email').value) {
		alert('이메일을 입력해주세요.');	
		document.getElementById('me_email').focus();
		
		return false;
	} else if(document.getElementById('idcheckAt').value != '확인') {
		alert('아이디 중복 체크해주세요.');
		
		return false;
	} else if(document.getElementById('pwCheckAt').value != '맞음') {
		alert('비밀번호를 다시 확인해주세요.');
		document.getElementById('me_password_ck').focus();
	
		return false;
	} 
	alert('회원가입 완료! \n다시 로그인해주세요');
}

$(function() {
	// 비밀번호 확인란을 입력했을 때
	$('#me_password_ck').on('keyup', function() {
		// 비밀번호란이 비어있지 않은 경우
		if( document.getElementById('me_password').value.length != 0 ) {
			// 비밀번호 값과 비밀번호 확인 값이 다른 경우
			if(document.getElementById('me_password').value != document.getElementById('me_password_ck').value) {
				document.getElementById('pwCheckAt').value = '맞지 않음';
				document.getElementById('pwCheckAt').style.color = 'red';
			}
			// 비밀번호 값과 비밀번호 확인 값이 같은 경우
			else {
				document.getElementById('pwCheckAt').value = '맞음';
				document.getElementById('pwCheckAt').style.color = 'green';
			}
		}
	});
});
</script>
<body>

	<div style="background-color: black; height:80px; padding: 10px; ">
		<h2 align="center" style="color: salmon;">회원가입</h2><br>
	</div>

	<form id="joinMember" method="post" action="${pageContext.request.contextPath }/MemberServlet/joinMember.do">
		<table align="center">
			<tr>
				<td>이름</td>
				<td><input id="me_name" name= "me_name" type="text"></td>
			</tr>
			
			<tr>
				<td>아이디</td>
				<td>
					<input id="me_id" name= "me_id" type="text">
					<input type="button" value="사용 여부" onclick="checkId()">
					<input id="idcheckAt" name="checkAt" type="text" value="확인 안함" hidden>
				</td>
			</tr>
			
			<tr>
				<td>비밀번호</td>
				<td><input id="me_password" name= "me_password" type="text"></td>
			</tr>
			
			<tr>
				<td>비밀번호 확인</td>
				<td>
					<input id="me_password_ck" name= "me_password_ck" type="text">
					<input id="pwCheckAt" type="text" value="" size="5" style="border: none; outline: none;" tabindex="-1" readonly>
				</td>
			</tr>
			
			<tr>
				<td>email</td>
				<td><input id="me_email" name= "me_email" type="text"></td>
			</tr>
		</table>
		
		<p align="center">
			<input type="reset" value="재입력" style="margin-right: 20px;">
			<input type="submit" value="회원가입" onclick="return beforeJoin()">			<!-- return : 에러가 발생하면 다음 이벤트가 동작하지 않음 -->
		</p>
	</form>

	<div align="center">
		<a href="../main.jsp" style="padding: 20px;">Home</a>
	</div>
	
</body>
</html>