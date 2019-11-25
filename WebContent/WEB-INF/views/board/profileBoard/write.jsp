<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>
<script type="text/javascript">
$(document).ready(function() {
	$("#write").click(function() {
		submitContents("#write")
	});
	$("form").submit();
	
	$("#cancel").click(function () {
		history.go(-1);
	});
});
</script>

<h1 class="text-center">프로필 작성</h1>

<form action="/profileBoard/write" method="post">
<table>
<tr>
	<td>${ name }</td>
	<td>${ userno }</td>
</tr>
<tr>
	<td><label for="interest">관심 분야 : </label></td>
	<td><input type="text" id="interest" /></td>
</tr>
<tr>
	<td><label for="location">활동 지역 : </label></td>
	<td><input type="text" id="location" /></td>
</tr>
<tr>
	<td><label for="job">직업 : </label></td>
	<td><input type="text" id="job" /></td>
</tr>
<tr>
	<td><label for="state">상태 : </label></td>
	<td><input type="text" id="state" /></td>
</tr>
<tr>
	<td><label for="career">경력 : </label></td>
	<td><input type="text" id="career" /></td>
</tr>
</table>
<button type="button" id="write">작성완료</button>
<button type="button" id="cancel">취소</button>
</form>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />