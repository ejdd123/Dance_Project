<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>오디션 등록 (Upload Audition)</title>
<style>
a {
    text-decoration: none;	/* 밑줄 제거 */
    font-size: 30px;
}
form {
	padding: 10px;
}
img {
	padding: 0px 0px 10px 0px;
}
fieldset {
	width: 350px;
	border: 0;
	text-align: center;
	padding: 10px;
}
</style>

<!-- script - function 사용 시 필요 (이미지 src 변경 및 초기화) -->
<script src="http://madalla.kr/js/jquery-1.8.3.min.js"></script>

<script>
$(function() {
	$("#au_img").on('change', function() {
   		readURL(this);
	});
	
	// 재입력 버튼 클릭 시 이미지 src 초기화
	$("#upload_reset").on('click', function() {
   		$("#upload_img").attr('src', '../images/question mark.jpg');
	});
});

function readURL(img) {
	if(img.files[0]) {
		var reader = new FileReader();
		
		// 이미지 파일 선택/변경하면 src 경로 바꿔주기
		reader.onload = function(e) {
			$("#upload_img").attr('src', e.target.result);
		}
		reader.readAsDataURL(img.files[0]);
	}
}

/* textarea (au_content) 개행 처리 */
function check_content() {
	var contents = document.querySelector(".textarea").value;
	contents = contents.replaceAll(/(\n|\r\n)/g, "<br>");
}
</script>

</head>
<body>

	<div style="background-color: black; height:80px; padding: 10px; ">
		<h2 align="center" style="color: salmon;">오디션 등록 (Upload Audition)</h2><br>
	</div>
	
	<div align="center" style="padding: 20px;">
		<form id="uploadAudition" method="post" action="${pageContext.request.contextPath }/AuditionServlet/uploadAudition.do" enctype="multipart/form-data">
			제목 : <input type="text" id="au_title" name="au_title" size="60"><br><br>
			<input type="file" id="au_img" name="au_img"><br><br>
			<img src="../images/question mark.jpg" id="upload_img" name="upload_img" width="250" height="300"><br><br>
			
			<fieldset>
				<legend>[ 상세내용 ]</legend>
				<textarea id="au_content" name="au_content" cols="70" rows="10"></textarea>
			</fieldset>
			
			email: <input type="text" id="au_email" name="au_email" size="60" value="${loginMember.me_email }"><br><br>
			password: <input type="text" id="au_password" name="au_password" size="10"><br><br>
			
			<input id="upload_reset"  type="reset" value="재입력">
			<input id="upload_audition"  type="submit" value="완료" onclick="check_content()">
		</form>
	</div>
	
	<div align="center">
		<a href="../main.jsp" style="padding: 20px;">Home</a>
		<a href="${pageContext.request.contextPath }/AuditionServlet/main_audition.do" style="padding: 20px;">목록</a>
	</div>

</body>
</html>