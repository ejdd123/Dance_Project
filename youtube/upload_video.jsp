<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>YouTube 동영상 업로드</title>
<style>
a {
    text-decoration: none;	/* 밑줄 제거 */
    font-size: 30px;
}
form {
	padding: 50px;
	text-align: center;
}
fieldset {
	border: 0;
	/* padding: 10px; */
	text-align: left;
	display: inline-block;
	margin-bottom: 100px;
}
p {
	height: 10px; 
	padding: 30px 0px;
}
iframe {
	min-height: 300px;
}
</style>

<!-- script - function 사용 시 필요 -->
<script src="http://madalla.kr/js/jquery-1.8.3.min.js"></script>

<script>
<!-- url 입력하고 검색 버튼 누르면 iframe에 해당 동영상 자동으로 띄우기 -->
/* 아직 url 잘못된 입력된 경우(존재하지 않는 url) 예외처리 못함 */
function search_url() {
	/* alert(document.getElementById('yt_url').value); */
	document.getElementById('find_video').src = document.getElementById('yt_url').value;
}

$(function() {
	$("#upload").on('click', function() {
		if ($('#yt_url').val().length == 0) {
			alert("url을 입력하세요");
			$("#yt_url").focus();
			return false;
		}
		if ($('#yt_password').val().length == 0) {
			alert("비밀번호를 입력하세요");
			$("#yt_password").focus();
			return false;
		}
	});
});
</script>
</head>
<body>

	<div style="background-color: black; height:80px; padding: 10px; ">
		<h2 align="center" style="color: salmon;">유튜브 영상 업로드 (Upload YouTube video)</h2>
	</div>
	
	<form id="uploadVideo" method="post" action="${pageContext.request.contextPath }/YoutubeServlet/uploadVideo.do">
		<fieldset>
			<p>가수(그룹) 입력 : <input type="text" id="singer" name="singer" size="20"></p><br>
			<p>노래 제목 입력 : <input type="text" id="title" name="title" size="30"></p><br>
			
			<p>Youtube url 입력 : <input type="text" id="yt_url" name="yt_url" size="50" required>&nbsp;
			<input type="button" id="search" onclick="javascript:search_url()" value="검색"></p>
		</fieldset>
		
		<fieldset>
			<iframe id="find_video" width="560" height="315"></iframe>
		</fieldset><br><br>
		
		비밀번호 : <input id="yt_password" name="yt_password" size="10" required><br><br>
		
		<input id="reset" type="reset" value="재입력" >
		<input id="upload" type="submit" value="영상 업로드" >
	</form>

	<br>
	<div align="center">
		<a href="../main.jsp" style="padding: 50px;">Home</a>
		<a href="${pageContext.request.contextPath }/YoutubeServlet/main_youtube.do" style="padding: 50px;">목록</a>
	</div>
	
</body>
</html>