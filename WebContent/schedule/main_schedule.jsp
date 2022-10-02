<%@page import="java.util.List"%>
<%@page import="DTO.scheduleDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>

<!-- 달력 생성을 위해 import -->
<%@page import="java.util.Date"%>
<%@page import="DAO.Schedule"%>
<%@page import="DAO.scheduleDAO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>스케줄 (schedule)</title>
</head>
	
<%
// 컴퓨터 시스템의 년, 월 받아오기
	Date date = new Date();
	int year = date.getYear() +1900;
	int month = date.getMonth() +1;

	//	오류사항 걸러주기	
	try{
		year = Integer.parseInt(request.getParameter("year"));
		month = Integer.parseInt(request.getParameter("month"));
		
		if(month>=13){
			year++;
			month =1;
		}else if(month <=0){
			year--;
			month =12;
		}
	}catch(Exception e){
		
	}
%>

<style>
table {
	width:80%;
	font-family: sans-serif;
}

tr {
	height: 80px;
}

td {
	width: 100px;
	font-size: 15pt;
	/* background-color: gainsboro; */
}

/* 타이틀 스타일 */
th#title {
	font-size: 20pt;
	font-weight: bold;
	background-color: beige;
}

/* 요일 스타일 */
td.sunday {
	text-align: center;
	font-weight: bold;
	color: red;
	background-color: beige;
}

td.saturday {
	text-align: center;
	font-weight: bold;
	color: blue;
	background-color: beige;
}

td.etcday {
	text-align: center;
	font-weight: bold;
	color: black;
	background-color: beige;
}

/* 날짜 스타일 */
td.sun {
	text-align: left;
	font-size: 15pt;
	color: red;
	vertical-align: top;
	background-color: white;
	height: 100px;
}

td.sat {
	text-align: left;
	font-size: 15pt;
	color: blue;
	vertical-align: top;
	background-color: white;
	height: 100px;
}

td.etc {
	text-align: left;
	font-size: 15pt;
	color: black;
	vertical-align: top;
	background-color: white;
	height: 100px;
}

td.redbefore {
	text-align: right;
	font-size: 12pt;
	color: lightpink;
	vertical-align: top;
}

td.before {
	text-align: right;
	font-size: 12pt;
	color: silver;
	vertical-align: top;
}

a{
    text-decoration: none;	/* 밑줄 제거 */
    font-size: 30px;
}

input {
	font-size: 12pt;
	background-color: bisque;
	border: none;	/* 테두리 제거 */
	outline: none;	/* 아웃라인 제거 */
}

div {
	padding: 10px;
}

textarea {
	border:none;	/* 테두리 제거 */
	resize: none;	/* 사이즈 조절 제거 */
	overflow: hidden;	/* 스크롤바 제거 */
	background-color: bisque;
	width: 90%;
	font-size: 12pt;
	padding: 5%;
}
</style>

<script>
/* 스케줄 등록 팝업창 띄우기 */
function schedule_popup() {
	 window.open('${pageContext.request.contextPath}/schedule/upload_class.jsp', '신청자 페이지', 'width=700, height=700, left=500, top=100');
}
</script>

<body>

	<div style="background-color: black; height:80px ">
		<h2 align="center" style="color: salmon;">스케줄 (schedule)</h2><br>
	</div>
	
	<div style="padding: 20px">
	
		<form>
		
			<!-- 달력 만들기 -->
			<table align ="center" border ="1" cellpadding="5" cellspacing="0">
				<tr>
					<!-- 이전달 버튼 만들기 -->
					<th style="background-color: bisque;">
					<%-- <a href ="?year=<%=year%>&month=<%month-1%>">이전 달</a> --%>
					<input type="button" value="← 이전 달" onclick="location.href='?year=<%=year%>&month=<%=month-1%>' ">
					</th>
					
					<!-- 제목 만들기 -->
					<th id = "title" colspan = "5">
					<%=year%>년  <%=month%>월
					</th>
					
					<!-- 다음달 버튼 만들기 -->
					<th style="background-color: bisque;">
					<%-- <a href ="?year=<%=year%>&month=<%month+1%>">다음 달</a> --%>
					<input type="button" value="다음 달 →" onclick="location.href='?year=<%=year%>&month=<%=month+1%>' ">
					
					</th>
				</tr>
				
				<!-- 요일 표시칸 만들어주기(단, 토,일요일은 색을 다르게 하기위해 구분해주기) -->
				<tr>
					<td class = "sunday">일</td>
					<td class = "etcday">월</td>
					<td class = "etcday">화</td>
					<td class = "etcday">수</td>
					<td class = "etcday">목</td>
					<td class = "etcday">금</td>
					<td class = "saturday">토</td>
				</tr>
				
				<!-- 날짜 집어 넣기 -->
				<tr>
				<%
					//	1일의 요일을 계산한다(자주 쓰이기 때문에 변수로 선언해두기)
					int first = DAO.Schedule.weekDay(year, month, 1);
						
					//	1일이 출력될 위치 전에 전달의 마지막 날짜들을 넣어주기위해 전 달날짜의 시작일을 계산한다.
					int start = 0;
					
					start = month ==1? DAO.Schedule.lastDay(year-1, 12)- first : DAO.Schedule.lastDay(year, month-1)- first;
					
					//	1일이 출력될 위치를 맞추기 위해 1일의 요일만큼 반복하여 전달의날짜를 출력한다.
					for(int i= 1; i<= first; i++){
						if(i==1){
							// 일요일(빨간색)과 다른날들의 색을 구별주기  */
							// out.println("<td class = 'redbefore'>" + (month ==1? 12 : month-1) + "/" + ++start + "</td>");
							out.println("<td class = 'redbefore'>" + "</td>");					
						}else{
							// out.println("<td class = 'before'>" + (month ==1? 12 : month-1) + "/" + ++start + "</td>");
							out.println("<td class = 'before'>" + "</td>");
						}
					}
					
					// 1일부터 달력을 출력한 달의 마지막 날짜까지 반복하며 날짜를 출력
					for(int i = 1; i <= DAO.Schedule.lastDay(year, month); i++){
						String today = "";
						today = year + "-" + month + "-" + i;
						
						// 요일별로 색깔 다르게 해주기위해 td에 class 태그걸어주기
						switch(DAO.Schedule.weekDay(year, month, i)){
							case 0 :
								// 달력에 일자 출력 (일요일)
								out.println("<td class ='sun'>" +i + "<br>");
								//out.println("<input id='sc_date' name='sc_date' type='text' value="+ today + "><td>");	// 확인용 출력
								%>
								
								<!-- DB에 있는 정보 모두 조회 -->
								<!-- sc_list : ScheduleServlet에서 list 내용 가져옴 -->
								<c:forEach var="sc" items="${sc_list }">
								
								<!-- JSTL 변수를 JSP 변수로 바꿔주기 step.1 -->
								<c:set var="class_date" value="${sc.sc_date }"/>
								
								<%
									// JSTL 변수를 JSP 변수로 바꿔주기 step.2
									String class_date = (String)pageContext.getAttribute("class_date");
									// out.println(today + " / " +class_date);			// 확인용 출력
									
									// 달력 일자와 업로드된 클래스 날짜가 같은지 비교
									if(class_date.equals(today)) {
										// System.out.println(today + " / " +class_date);		// 확인용 콘솔 출력
								%>
									<!-- 달력 일자와 업로드된 클래스 날짜가 같다면 '가수(그룹)명 - 곡명' 을 문구로 출력하고, 문구 클릭시 화면 이동 (길어서 textarea 사용) -->
									<%-- <input id="sc_title" name="sc_title" type="text" value="${sc.sc_singer } - ${sc.sc_title}" 
												onclick="location.href='${path }/Dance_Project/ScheduleServlet/checkClass.do?sc_date=<%=today%>' " style="font-size: 12pt; width: 100%;" readonly> --%>
									<textarea id="sc_title" name="sc_title" onclick="window.open('${path }/Dance_Project/ScheduleServlet/checkClass.do?sc_id=${sc.sc_id }&sc_date=<%=today%>&sc_num=${sc.sc_num }&me_id=${sc.me_id }', '클래스 신청/삭제 페이지', 'width=550, height=150 left=550 top=350') " readonly>${sc.sc_singer } - ${sc.sc_title}</textarea>
													 
								<%
									// if문 괄호 닫기
									}
								%>
								
								</c:forEach></td>
								<%
								break;
								
							case 6 :
								// 달력에 일자 출력 (토요일)
								out.println("<td class ='sat'>" +i + "<br>");
								%>
								
									<c:forEach var="sc" items="${sc_list }">
									<c:set var="class_date" value="${sc.sc_date}"/>
								
								<%
									String class_date = (String)pageContext.getAttribute("class_date");
									
									if(class_date.equals(today)) {
								%>
									<textarea id="sc_title" name="sc_title" onclick="window.open('${path }/Dance_Project/ScheduleServlet/checkClass.do?sc_id=${sc.sc_id }&sc_date=<%=today%>&sc_num=${sc.sc_num }&me_id=${sc.me_id }', '클래스 신청/삭제 페이지', 'width=550, height=150 left=550 top=350') " readonly>${sc.sc_singer } - ${sc.sc_title}</textarea>	 
								
								<%
									}
								%>
								
								</c:forEach></td>
								<%
								break;
								
							default :
								// 달력에 일자 출력 (평일)
								out.println("<td class ='etc'>" +i + "<br>");
								%>
								
									<c:forEach var="sc" items="${sc_list }">
									<c:set var="class_date" value="${sc.sc_date}"/>
								
								<%
									String class_date = (String)pageContext.getAttribute("class_date");
									
									if(class_date.equals(today)) {
								%>
									<textarea id="sc_title" name="sc_title" onclick="window.open('${path }/Dance_Project/ScheduleServlet/checkClass.do?sc_id=${sc.sc_id }&sc_date=<%=today%>&sc_num=${sc.sc_num }&me_id=${sc.me_id }', '클래스 신청/삭제 페이지', 'width=550, height=150 left=550 top=350') " readonly>${sc.sc_singer } - ${sc.sc_title}</textarea>		 
								
								<%
									}
								%>
								
								</c:forEach></td>
								<%
								break;
						}
						
						// 출력한 날짜(i)가 토요일이고 그달의 마지막 날짜이면 줄을 바꿔주기
						if(DAO.Schedule.weekDay(year, month, i) == 6 && i != DAO.Schedule.lastDay(year, month)){
							out.println("</tr><tr>");
						}
					}
					if(DAO.Schedule.weekDay(year, month, DAO.Schedule.lastDay(year, month)) !=6){
						for(int i = DAO.Schedule.weekDay(year, month, DAO.Schedule.lastDay(year, month))+1; i < 7; i++){
							out.println("<td></td>");
						}
					}
				%>
				</tr>
			</table>
		</form>
	</div>
	
	<div align="center">
		<a href="../main.jsp" style="padding: 20px;">Home</a>
		
		<c:if test="${loginMember.manage_at eq 'YES' }">
			<!-- <a href="register_schedule2.jsp" style="padding: 20px;">클래스 등록/수정</a> -->
			<input type="button" value="클래스 등록" onclick="schedule_popup()" style="padding: 20px; font-size: 30px; background-color: white; color: purple; ">
		</c:if>
	</div>

</body>
</html>