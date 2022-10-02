<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>

<style>
form, table {
	padding: 20px;
}
td{
	padding: 0px 20px; 
}
</style>

<script>
function open_join() {
	opener.location.href='${pageContext.request.contextPath }/member/join.jsp' ;
	self.close();				// 현재 페이지(로그인 팝업) 닫기
}

function before_login() {
	if(!document.getElementById('me_id').value) {
		alert('아이디를 입력하세요.');
		document.getElementById('me_id').focus();
		
		return false;
	} else if(!document.getElementById('me_password').value) {
		alert('비밀번호를 입력하세요.');
		document.getElementById('me_password').focus();
		
		return false;
	}
	
	// 현재 페이지(로그인 팝업) 닫기
	self.close();
}
</script>

<body>

	<div style="background-color: black; height:80px; padding: 10px; ">
		<h2 align="center" style="color: salmon;">로그인</h2><br>
	</div>
	
	<!-- target : main.jsp(부모 페이지) 대신 login.jsp(자식 페이지, 현재 페이지)을 부모 페이지로 띄우기 -->
	<form id="loginMember" action="${pageContext.request.contextPath }/MemberServlet/login.do?me_id=${me_id }&me_password=${me_password }" target="login.do">
		<table align="center">
			<tr>
				<td>아이디</td>
				<td><input id="me_id" name= "me_id" type="text"></td>
			</tr>
			
			<tr>
				<td>비밀번호</td>
				<td><input id="me_password" name= "me_password" type="text"></td>
			</tr>
		</table>
		
		<p align="center">
			<input type="button" value="회원가입" onclick="open_join()">
			<input id="login" name="login" type="submit" value="로그인" onclick="return before_login()">		<!-- return : 에러가 발생하면 다음 이벤트가 동작하지 않음 -->
		</p>
	</form>

</body>
</html>