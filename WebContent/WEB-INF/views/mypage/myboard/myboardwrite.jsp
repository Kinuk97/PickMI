<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>내가 쓴 게시물</h1>
<hr>
<div class="container" >
	<form action="/mgr/profileboard/delete" method="get">
	<table class="table table-condensed">
		<tr class="info">
			<th style="width: 5%"><input type="checkbox" id="checkAll"/></th>
			<th style="width: 5%">글번호</th>
			<th style="width: 10%">사용자번호</th>
			<th style="width: 10%">작성시간</th>
			<th style="width: 10%">직무</th>
			<th style="width: 10%">상태</th>
			<th style="width: 10%">지역</th>
		</tr>
		<c:forEach items="${pflist }" var="pbboard">
		<tr>
			<td><input type="checkbox" name="checkRow" value="${pbboard.prof_no }"/></td>
			<td><a href="/mgr/profileview?prof_no=${pbboard.prof_no}">${pbboard.prof_no}</a></td>
			<td>${pbboard.userno }</td>
			<td>${pbboard.prof_time }</td>
			<td>${pbboard.prof_job }</td>
			<td>${pbboard.prof_state }</td>
			<td>${pbboard.prof_loc }</td>
		</tr>	
		</c:forEach>
	</table>
		<button>삭제</button>
	</form>
</div>


<div class="container" >
	<form action="/mgr/compboard/delete" method="get">
	<table class="table table-condensed">
		<tr class="info">
			<th style="width: 5%"><input type="checkbox" id="checkAll" /></th>
			<th style="width: 5%">게시글 번호</th>
			<th style="width: 5%">사용자 번호</th>
			<th style="width: 20%" >제목</th>
			<th style="width: 10%">팀이름</th>
			<th style="width: 10%">작성시간</th>
		</tr>
		<c:forEach items="${ complist }" var="comp">
		<tr>
			<td><input type="checkbox" name="checkRow" value="${comp.comp_no }"/></td>
			<td>${comp.comp_no }</td>
			<td>${comp.userno }</td>
			<td><a href="/mgr/compview?comp_no=${ comp.comp_no }">${comp.comp_title }</a></td>
			<td>${comp.comp_name }</td>
			<td>${comp.comp_date }</td>
		</tr>	
		</c:forEach>
	</table>	
		<button>삭제</button>
	</form>	
</div>

</body>
</html>