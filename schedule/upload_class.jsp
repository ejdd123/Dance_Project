<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>클래스 등록</title>
</head>

<style>
table {
	width:100%;
	font-family: sans-serif;
	padding: 20px;
}

tr {
	height: 50px;
}

td {
	width: 100px;
	font-size: 12pt;
	/* background-color: gainsboro; */
}
</style>

<script>
function upload_class() {
	alert('클래스가 등록되었습니다.');
}
</script>

<body>

	<div style="background-color: black; height:80px; padding: 10px; ">
		<h2 align="center" style="color: salmon;">클래스 등록</h2><br>
	</div>
	
	<form id="uploadClass" action="${path }/ScheduleServlet/uploadClassPopup.do">
		<table>
			<tr>
				<td>가수(그룹)명 </td>
				<td><input id="sc_singer" name="sc_singer" type="text"></td>
			</tr>
			
			<tr>
				<td>곡명 </td>
				<td><input id="sc_title" name="sc_title" type="text"></td>
			</tr>
			
			<tr>
				<td>강사명 </td>
				<td><input id="sc_teacher" name="sc_teacher" type="text"></td>
				<td><input id="sc_img" name="sc_img" type="file"></td>
			</tr>
			
			<tr>
				<td>클래스 날짜 </td>
				<td><input id="sc_date" name="sc_date" type="date"></td>
			</tr>
			
			<tr>
				<td>수강 인원 </td>
				<td><input id="sc_num" name="sc_num" type="number" min="1"></td>
			</tr>
			
		</table>
		
		<div style="padding: 20px;">
			<input type="reset" value="재입력">
			<input type="submit" value="등록" onclick="upload_class()">
		</div>
			
	</form>
	
</body>
</html>