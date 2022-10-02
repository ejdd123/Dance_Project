<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>클래스 신청자 조회</title>
</head>

<style>
input {
	border: 0;
	padding: 10px;
	font-size: 15px;
}
div {
	padding: 20px;
}
</style>

<body>

	<div style="background-color: black; height:80px; padding: 20px; ">
		<h2 align="center" style="color: salmon;">클래스 신청자</h2><br>
	</div>
	
	<div align="center">
		<c:forEach var="applicant_class" items="${class_list }">
			${applicant_class.sc_date} / ${applicant_class.sc_singer} - ${applicant_class.sc_title}
			<br><br>
			
			<!-- 신청자 있는 경우 -->
			<c:if test="${not empty class_applicant }">
				<c:forEach var="applicant" items="${class_applicant }">
					<!-- 넘버 생성 -->
					<c:set var="row" value="${row+1 }" />
						<!-- 신청자 아이디(이름) -->
						<input type="text" value="${row }. ${applicant.me_id } (${applicant.cl_ap_name })"><br>
				</c:forEach>
				
				<br><input type="text" value="신청 인원 :  ${row } / ${applicant_class.sc_num} 명">
			</c:if>
				
			<!-- 신청자 없는 경우 -->
			<c:if test="${empty class_applicant }">
				신청 가능 인원 : ${applicant_class.sc_num} 명<br><br>
				신청자 없음
			</c:if>
			
		</c:forEach>
	</div>
	
</body>
</html>