<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="path" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>오디션 지원 (apply for audition)</title>
<style>
form {
	padding: 20px;
}
table {
	border: 1;
}
</style>

<script>
/* textarea (ap_content) 개행 처리 */
function check_content() {
	var contents = document.querySelector(".textarea").value;
	contents = contents.replaceAll(/(\n|\r\n)/g, "<br>");
}
</script>
</head>
<body>
	<div style="background-color: black; height:80px; padding: 10px; ">
		<h2 align="center" style="color: salmon;">오디션 지원 (apply for audition)</h2><br>
	</div>
	
	<div>
		<form id="applyforAudition" method="post" action="${path }/AuditionMailServlet/sendMail.do?au_id=${ap_list.au_id}&au_title=${ap_list.au_title}&au_email=${ap_list.au_email}" enctype="multipart/form-data">
			<table id="applicantInfo">
				<colgroup>
					<col width="100">
					<col width="100">
				</colgroup>
				
				<tr>
					<td>제목</td>
					<td><input type="text" value="${ap_list.au_title }" disabled></td>
				</tr>
				
				<tr>
					<td>성명</td>
					<td><input id="ap_name" name="ap_name" type="text" value="${loginMember.me_name }"></td>		<!-- applicant_name -->
				</tr>
				
				<tr>
					<td>나이</td>
					<td>	<input id="ap_age" name="ap_age" type="text"></td>
				</tr>
				
				<tr>
					<td>지원 분야</td>
					<td>
						<input id="ap_field_vocal" name="ap_field" type="radio" value="보컬">보컬 &nbsp;  &nbsp;
						<input id="ap_field_rap" name="ap_field" type="radio" value="랩">랩  &nbsp;  &nbsp;
						<input id="ap_field_dance" name="ap_field" type="radio" value="댄스">댄스  &nbsp;  &nbsp;
						<input id="ap_field_etc" name="ap_field" type="radio" value="기타">기타 (ex. 연기)
					</td>
					<!-- <td>	<input id="ap_field" name="ap_field" type="text"></td> -->
				</tr>
				
				<tr>
					<td>지원 내용</td>
					<td>	<textarea id="ap_content" name="ap_content" cols=70 rows=15></textarea></td>
				</tr>
				
				<!-- 
				<tr>
					<td>지원 영상</td>
					<td>	<input id="ap_video" name="ap_video" type="file"></td>
				</tr>
				 -->
				 
				<tr>
					<td>email</td>
					<td>	<input id="ap_email" name="ap_email" type="text" value="${loginMember.me_email }"></td>
				</tr>
			</table>
			
			<input type="reset" value="재입력" style="margin-top: 50px;">
			<input type="submit" value="지원하기" style="margin-top: 50px;">
			<input type="text" value="${ap_list.au_email }" hidden>
		</form>
			
	</div>

</body>
</html>