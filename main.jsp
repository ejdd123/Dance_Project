<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Let's Dance</title>
</head>

<style>
nav div a {
	font-family : sans-serif;
	padding : 20px;
    text-decoration: none;	/* 하이퍼링크 밑줄 제거 */
    color : salmon;	/* yellow */
}
.home-image {
	width: 20%;
	height: 200px;
	margin: 50px;
	box-shadow: 5px 5px 5px grey;
}
.home {
	width: 60px;
	height: 40px;
	margin-right: 20px;
	cursor: pointer;
}
</style>

<script>
function login_popup() {
	window.name="login.do";	// main.jsp(부모 페이지, 현재 페이지) 대신 login.jsp(자식 페이지)을 부모 페이지로 띄우기
	window.open('${pageContext.request.contextPath }/member/login.jsp', '로그인 페이지', 'width=550, height=300');
}
/* 
function join_popup() {
	window.open('${pageContext.request.contextPath }/member/join.jsp', '회원가입 페이지', 'width=600, height=500');
} 
*/

function no_message() {
	alert('새 메시지가 없습니다.');
}

/* 유튜브 영상 관련 message */
function open_message() {
	window.open('${pageContext.request.contextPath }/HomeServlet/home_message.do','메시지', 'width=500, height=500 left=600 top=150');
}

/* 신청한 클래스 list */
function open_class_list() {
	window.open('${pageContext.request.contextPath }/HomeServlet/home_class_list.do','신청 클래스', 'width=500, height=500 left=600 top=150');
}
</script>

<body>

	<nav>
		<div>
			<h1 style="padding: 0px 20px; font-size: 30px;">Let's Dance</h1>
			
			<c:if test="${not empty loginMember.me_id }">
				<div align="right">
				
					<c:choose>
						<c:when test="${empty my_message }">
							<img class="home" src="${pageContext.request.contextPath }/images/home/home-message.jpg" onclick="no_message()">
						</c:when>
						
						<c:otherwise>
							<img class="home" src="${pageContext.request.contextPath }/images/home/new_message.jpg" onclick="open_message()">
						</c:otherwise>
					</c:choose>
				
					<img class="home" src="${pageContext.request.contextPath }/images/home/class_list.jpg" onclick="open_class_list()">
				</div>
			</c:if>
			
			<c:if test="${empty loginMember.me_id }">
				<p align="right">
						<a href="${pageContext.request.contextPath }/member/join.jsp">회원가입</a>
						<a href="" onclick="login_popup()">로그인</a>
				</p>
			</c:if>
			
			<c:if test="${not empty loginMember.me_id }">
				<p align="right">
						<input id="userId" name="userId" type="text" value="${loginMember.me_name }" 
									style="font-family : sans-serif; font-size: 15pt; border:none; outline:none; text-align: right" readonly> 님 환영합니다.
						<a href="${pageContext.request.contextPath }/MemberServlet/logout.do" >로그아웃</a>
				</p>
			</c:if>
		</div>
		
		<div class="container" style="background: black; padding: 20px 10px; font-size: 15px;">
			<a href="${pageContext.request.contextPath }/ScheduleServlet/main_schedule.do">스케줄(schedule)</a>
			<a href="${pageContext.request.contextPath }/YoutubeServlet/main_youtube.do">유튜브(YouTube)</a>
			<a href="${pageContext.request.contextPath }/AuditionServlet/main_audition.do">오디션(audition)</a>
			<a>찾아오시는 길(place)</a>
		</div>
	</nav>
	
	<div align="center" style="margin-top: 50px;">
		<img class="home-image" src="${pageContext.request.contextPath }/images/HOME-EJ.png">
		<img class="home-image" src="${pageContext.request.contextPath }/images/HOME-SM.jpg">
		<img class="home-image" src="${pageContext.request.contextPath }/images/HOME-STARSHIP.jpg">
		<img class="home-image" src="${pageContext.request.contextPath }/images/HOME-WAKEONE.jpg">
		<img class="home-image" src="${pageContext.request.contextPath }/images/HOME-YG.jpg">
		<img class="home-image" src="${pageContext.request.contextPath }/images/HOME-KAKAO.jpg">
	</div>
		
</body>
</html>