<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>YouTube 영상</title>
<style>
a {
    text-decoration: none;	/* 밑줄 제거 */
    font-size: 30px;
}
.play_video {
	padding: 30px;
	text-align: center;
}
iframe {
	/* padding: 0px 10px 30px 0px; */
	width: 100%;
}
/* 
video{
	padding: 20px 20px;
	width: 30%;
}
 */
p {
	position: relative;
}
#upload {
	padding: 30px 10px;
	width: 30%;
	height: 280px;
}
fieldset {
	width: 30%;
	display: inline-block;
	padding: 10px 10px 50px 10px;
	border: 0;
}

/* 업로드 이미지에 커서 올리면 스타일 변경 */
#upload:hover {
	border: 2px solid salmon;
}
</style>

<script>
function focus_upload() {
	document.getElementById("upload").focus();
}
</script>

<!-- 
<script>
function upload() {
	var input = prompt('추가할 YouTube 동영상 url을 입력하세요', '메시지를 입력해주세요');
    alert(input);
}
</script>
 -->
</head>
<body>

	<div style="background-color: black; height:80px; padding: 10px; ">
		<h2 align="center" style="color: salmon;">유튜브 영상 (YouTube video)</h2><br>
	</div>

	<div style="margin-left: 85%; padding: 30px; ">
		<input type="button" value="업로드 버튼으로 이동" onclick="focus_upload()">
	</div>
	
	<div class="play_video">
		<!-- DB에 있는 정보 모두 조회 -->
		<!-- yt_list : YoutubeServlet에서 list 내용 가져옴 -->
		<c:forEach var="yt" items="${yt_list }" >
			<fieldset>
				<legend>${yt.singer } - ${yt.title } </legend>
				<iframe width="560" height="315" src="${yt.yt_url }" frameborder="0" 
							allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>${yt.title }</iframe>
				
				<input id="yt_password" name="yt_password" type="text" value="${yt.yt_password }" size="10" readonly hidden>
				<input id="msg" name="msg" type="button" value="Message" onclick="location.href='${path }/YoutubeServlet/confirmMessage.do?yt_id=${yt.yt_id }&me_id=${yt.me_id }' ">
				
				<!-- 본인이 작성한 게시물만 삭제할 수 있음 -->
				<c:set var="id" value="${yt.me_id}" />
				<c:if test="${loginMember.me_id eq id }">
					<input id="del" name="del" type="button" value="삭제" onclick="location.href='${path }/YoutubeServlet/checkDeleteVideo.do?yt_id=${yt.yt_id }&yt_password=${yt.yt_password }' ">
				</c:if> 
			</fieldset>
		</c:forEach>
		
		<!-- 동영상 추가 -->
		<!-- <video src="../images/youtube/paper.mp4" controls></video> -->
		<!-- <img name="" width="30%" height="315" border=1 src="../images/youtube/plus_video.jpg" onclick="javascript:upload()"> -->
		<img id="upload" name="upload" border=1 src="../images/upload.jpg"  tabindex=0 style="cursor:hand" onclick="location.href='${path}/youtube/upload_video.jsp'">
		
	</div>

	<div align="center">
		<a href="../main.jsp" style="padding: 50px;">Home</a>
	</div>
	
</body>
</html>