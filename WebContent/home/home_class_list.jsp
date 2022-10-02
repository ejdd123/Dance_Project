<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Class List</title>
</head>
<body>

	<div style="background-color: black; height:80px; padding: 10px; ">
		<h2 align="center" style="color: salmon;">신청 클래스</h2><br>
	</div>
	
	<div style="margin: 20px; padding: 20px;">
		<!-- 신청한 클래스가 없는 경우 -->
		<c:if test="${empty my_class == true }">
			아직 신청한 클래스가 없습니다.
		</c:if>
		
		<c:forEach var="my" items="${my_class }">
			<!-- 넘버 생성 -->
			<c:set var="row" value="${row+1 }" />
				${row }. ${my.sc_date } / ${my.sc_singer } - ${my.sc_title } <br>
				
		</c:forEach>
	</div>
	
</body>
</html>