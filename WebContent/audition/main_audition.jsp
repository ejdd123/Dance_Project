<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>오디션 정보 (audition)</title>
<style>
a  {
    text-decoration: none;	/* 밑줄 제거 */
    font-size: 30px;
}
img {
	width: 90%;
	height: 450px;
	margin-top: 10px;
	/* padding: 0px 0px 30px 0px; */
}
fieldset {
	width: 30%;
	display: inline-block;
	padding: 50px 10px 30px 10px;
	border: 0;
}
p {
	/* padding: 0px 20px; */
	position: relative;
}
.auditionList {
	padding: 0px 20px;
	text-align: center;
}
div {
	padding: 20px;
}
#upload {
	padding: 180px 12px;
	width: 30%;
	height: 280px;
}

/* 업로드 이미지에 커서 올리면 스타일 변경 */
#upload:hover {
	border: 2px solid salmon;
}
pre {
	width: 85%;
	height:100px;
	max-height:200px;
	overflow: auto;
	background : salmon;
	padding: 5px 10px;
	margin-left: 5%;
	font-family : sans-serif;
}
</style>

<script>
function focus_upload() {
	document.getElementById("upload").focus();
}
$(document).ready(function() {
	window.location.reload();
});
</script>

</head>
<body>

	<div style="background-color: black; height:80px; padding: 10px; ">
		<h2 align="center" style="color: salmon;">오디션 정보 (audition)</h2><br>
	</div>
	
	<div style="margin-left: 85%; padding: 30px 0px 0px 0px; ">
		<input type="button" value="업로드 버튼으로 이동" onclick="focus_upload()">
	</div>
		
	<div class="auditionList">
		<!-- DB에 있는 정보 모두 조회 -->
		<!-- au_list : AuditionServlet에서 list 내용 가져옴 -->
		<c:forEach var="audition" items="${au_list }">
			<fieldset>
				<p>[ 제목 : ${audition.au_title } ]</p>
				<img src="../images/audition/${audition.au_img }"> <br>
				
				<!-- 상세내용 띄어쓰기 그대로 출력되도록 c:out, style 적용하기 위해 pre 사용 -->
				<pre>${audition.au_content }</pre>
				<%-- <pre><c:out value="${audition.au_content } " /></pre> --%>
				<%-- <div style="white-space:pre;">[ 상세 내용 ]<pre><c:out value="${audition.au_content } " /></pre></div> --%>
				
				<input id="mail" name="mail" type="button" value="지원하기" onclick="location.href='${path}/AuditionMailServlet/checkapplyforAudition.do?au_id=${audition.au_id }&au_title=${audition.au_title }&au_email=${audition.au_email }' ">
				
				<!-- 본인이 작성한 게시물만 삭제할 수 있음 -->
				<c:set var="id" value="${audition.me_id}" />
				<c:if test="${loginMember.me_id eq id }">
					<input id="del" name="del" type="button" value="삭제" onclick="location.href='${path }/AuditionServlet/checkDeleteAudition.do?au_id=${audition.au_id }&au_password=${audition.au_password }' ">
				</c:if>
				
				<input type="text" value="${audition.au_email }" hidden>
				<input type="text" value="${audition.au_password }" hidden>
			</fieldset>
		</c:forEach>
		
		<img id="upload" name="upload" border=1 src="../images/upload.jpg"  tabindex=0 onclick="location.href='${path}/audition/upload_audition.jsp' ">
		
	</div>
	
	<div align="center">
		<a href="../main.jsp" style="padding: 50px;">Home</a>
	</div>
	
</body>
</html>