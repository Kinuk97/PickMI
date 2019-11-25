<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mgr ProfileBoard List</title>

<script type="text/javascript">

$(document).ready(function(){
	// 최상단 체크박스 클릭
	$("#checkAll").click(function(){
		//클릭 되었으면
		console.log($("#checkAll").prop("checked"));
		if($("#checkAll").prop("check")){
			//input태그의 name이 checkRow인 태그들을 찾아서 checked옵션을 true로 정의
			$("input[name=checkRow]").prop("checked",true);
			//클릭이 안돼있으면
		}else{
			//input태그의 name이 checkRow인 태그들을 찾아서 checked옵션을 false로 정의
			$("input[name=checkRow]").prop("checked",false);
		}
	};
})
</script>

</head>
<body>

<table>
	<tr>
		<th><input type="checkbox" id="checkAll"/></th>
		<th>글번호</th>
		<th>사용자번호</th>
		<th>작성시간</th>
		<th>직무</th>
		<th>상태</th>
		<th>지역</th>
	</tr>
	<c:forEach items="${pblist }" var="pbboard">
	<tr>
		<td><input type="checkbox" id="checkRow" value= "${pbboard.prof_no }"/></td>
		<td>${pbboard.prof_no }</td>
		<td>${pbboard.userno }</td>
		<td>${pbboard.prof_time }</td>
		<td>${pbboard.prof_job }</td>
		<td>${pbboard.prof_state }</td>
		<td>${pbboard.prof_loc }</td>
	</tr>	
	</c:forEach>
</table>

</body>
</html>