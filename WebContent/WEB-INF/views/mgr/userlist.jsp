<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mgr User List</title>

<!--  jQuery 2.2.4 -->
<script type="text/javascript"
 src="http://code.jquery.com/jquery-2.2.4.min.js">
</script>

<!-- checkBox -->
<script type="text/javascript">

$(document).ready(function(){
// 	//검색 버튼 클릭
// 	$("#btnSearch").click(function(){
// 		loacation.href="/mgr/pblist?search="+$("#search").val();
// 	});
	
	
    //최상단 체크박스 클릭
    $("#checkAll").click(function(){
        //클릭되었으면
//         console.log($("#checkAll").prop("checked"));
        if($("#checkAll").prop("checked")){
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
            $("input[name=checkRow]").prop("checked",true);
            //클릭이 안되있으면
        }else{
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
            $("input[name=checkRow]").prop("checked",false);
        }
    });
});

</script>

</head>
<body>

<a href="/mgr/logout"><button>로그아웃</button></a>

<table>
	<tr>
		<th><input type="checkbox" id="checkAll"/></th>
		<th>사용자번호</th>
		<th>아이디(email)</th>
		<th>이름</th>
		<th>사진저장1</th>
		<th>사진저장2</th>
	</tr>
	<c:forEach items="${userlist }" var="userboard">
	<tr>
		<td><input type="checkbox" name="checkRow" value="${userboard.userno }"/></td>
		<td>${userboard.userno }</td>
		<td>${userboard.email }</td>
		<td>${userboard.name }</td>
		<td>${userboard.photo_originname }</td>
		<td>${userboard.photo_storedname }</td>
	</tr>	
	</c:forEach>
</table>

<jsp:include page = "/WEB-INF/views/mgr/paging/userlistPaging.jsp" />

<div class="src">
	<form action="/mgr/userlist" method="get">
		<input type="text" name="search" id="search"/>
		<button id="btnSearch" class="btn">검색</button>
	</form>
</div>


</body>
</html>