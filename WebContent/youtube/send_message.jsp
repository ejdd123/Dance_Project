<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>send message</title>
</head>

<style>
table {
	width: 90%;
	font-family: sans-serif;
	margin: 20px;
	text-align: center;
}

input, textarea {
	width: 98%;
	border: 0;
	outline: none;
	resize: none;
	font-family: sans-serif;
}
</style>

<script>
/* textarea (au_content) 개행 처리 */
function check_content() {
	var contents = document.querySelector(".textarea").value;
	contents = contents.replaceAll(/(\n|\r\n)/g, "<br>");
}
</script>

<body>

	<div style="background-color: black; height:80px; padding: 10px; ">
		<h2 align="center" style="color: salmon;">메시지 보내기</h2><br>
	</div>
	
	<form id="sendMessage" method="post" action="${pageContext.request.contextPath }/YoutubeServlet/sendMessage.do">
		<table border="1">
			<tr>
				<td>보내는 사람</td>
				<td>
					<input id="from_msg" name="from_msg" type="text" value="${loginMember.me_id }" readonly>
					<input id="to_msg" name="to_msg" type="text" value=<%=request.getParameter("me_id") %> hidden>
				</td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td><input id="title" name="title" type="text"></td>
			</tr>
			
			<tr>
				<td>내용</td>
				<td><textarea id="content" name="content" rows="10"></textarea></td>
			</tr>
		</table>
		
		<input id="yt_id" name="yt_id" type="text" value=<%=request.getParameter("yt_id") %> hidden>
		<input type="submit" value="전송" onclick="check_content()">
	</form>
	
</body>
</html>