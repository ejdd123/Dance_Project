<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>클래스 신청/삭제</title>
</head>
<style>
button {
	width: 150px;
	height: 80px;
	margin: 30px;
}
</style>

<!-- script - function 사용 시 필요 -->
<script src="http://madalla.kr/js/jquery-1.8.3.min.js"></script>

<script>
// 본인이 등록한 스케줄은 삭제 가능하도록
$(function() {
	if(document.getElementById('upload_id').value == document.getElementById('login_id').value) {
		document.getElementById('apply_for_class').hidden = true;
		document.getElementById('del_class').hidden = false;
	}
});

// 신청자 조회창 오픈
function select_applicant() {
	// window.close(); // (클래스 신청/삭제, 신청자 조회) 버튼창 닫기
	window.open('${pageContext.request.contextPath }/ScheduleServlet/selectClassApplicant.do?sc_id=<%=request.getParameter("sc_id") %>&sc_date=<%=request.getParameter("sc_date")%>' , '클래스 신청자', 'width=500, height=700 left=600 top=100');
}

// 클래스 신청 전 로그인 확인
function before_apply_for_class() {
	window.location.href='${pageContext.request.contextPath }/ScheduleServlet/applyforClass.do?sc_id=<%=request.getParameter("sc_id") %>&sc_date=<%=request.getParameter("sc_date")%>&sc_num=<%=request.getParameter("sc_num")%>&me_id=<%=request.getParameter("me_id") %>' ;
}

// 삭제 전 확인
function before_delete() {
	var check = confirm('클래스를 삭제하시겠습니까?');
	
	if(check == true) {
		window.location.href='${pageContext.request.contextPath }/ScheduleServlet/delete_class.do?sc_id=<%=request.getParameter("sc_id") %>' ;
	}
	else {
		return false;
	}
}
</script>

<body>

	<div align="center">
		<input id="upload_id" name="upload_id" type="text" value=<%=request.getParameter("me_id") %> hidden>
		<input id="login_id" name="login_id" type="text" value="${loginMember.me_id }" hidden>
		
		<button id="select_class_applicant" name="select_class_applicant" onclick="select_applicant()">신청자 조회</button>
		<button id="apply_for_class" name="apply_for_class" onclick="before_apply_for_class()">클래스 신청</button>
		<button id="del_class" name="del_class" onclick="return before_delete()" hidden>클래스 삭제</button>
	</div>
	
</body>
</html>