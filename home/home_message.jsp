<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Message</title>
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

<body>

	<div style="background-color: black; height:80px; padding: 10px; ">
		<h2 align="center" style="color: salmon;">Message</h2><br>
	</div>
	
	<%-- 
	<img src="${pageContext.request.contextPath }/images/home/left.jpg" width="50">
	<img src="${pageContext.request.contextPath }/images/home/right.jpg" width="50" align="right" style="margin-right: 2%;">
	 --%>
	 
	<c:forEach var="message" items="${my_message }">
		<table border="1">
			<tr>
				<td>보낸 사람</td>
				<td><input type="text" value="${message.from_msg }" readonly></td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td><input id="title" name="title" type="text" value="${message.title }" readonly></td>
			</tr>
			
			<tr>
				<td>내용</td>
				<td><textarea rows="10" readonly>${message.content } </textarea></td>
			</tr>
		</table>
	</c:forEach>
	
</body>
</html>