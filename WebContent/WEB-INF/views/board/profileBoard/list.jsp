<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>hello , it's profile board list here!😁</h1>
<hr>
<table class="table table-hover table-striped">
<tr class="success">
	<th>게시판 번호</th>
	<th>작성자 이름</th>
	<th>관심 분야</th>
	<th>지역</th>
	<th>직무</th>
	<th>경력</th>
	<th>상태</th>
	<th>좋아요</th>
	<th>작성시간</th>
</tr>
<c:forEach items="${ list }" var="pro">
<tr>
	<td>${ pro.prof_no }</td>
	<td>${ pro.userno }</td>
	<td>${ pro.prof_interest }</td>
	<td>${ pro.prof_loc }</td>
	<td>${ pro.prof_job }</td>
	<td>${ pro.prof_career }</td>
	<td>${ pro.prof_state }</td>
	<td>${ pro.prof_like }</td>
	<td>${ pro.prof_time }</td>
</tr>
</c:forEach>
</table>



</body>
</html>