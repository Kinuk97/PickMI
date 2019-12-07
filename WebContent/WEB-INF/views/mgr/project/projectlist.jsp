<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     



<!-- Header -->
<jsp:include page="/WEB-INF/views/mgr/layouts/mgrheader.jsp"/>  

<!-- checkBox -->
<script type="text/javascript">

$(document).ready(function(){
//검색 버튼 클릭
$("#btnSearch").click(function(){
loacation.href="/mgr/pblist?search="+$("#search").val();
});
	
	
//최상단 체크박스 클릭
$("#checkAll").click(function(){
//클릭되었으면
console.log($("#checkAll").prop("checked"));
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

<style type="text/css">

table th {
width: auto !important; 
}

</style>
</head>
<body> 


<div class="container" >
<div class="table-responsive">
<form action="/mgr/projectboard/delete" method = "get">
<table class="table table-condensed table-hover">
<tr class="info">
<th style="width: 5%"><input type="checkbox" id="checkAll"/></th>
<th style="width: 5%">글번호</th>
<th style="width: 5%">사용자</th>
<th style="width: 20%">제목</th>
<th style="width: 10%">작성시간</th>
</tr>
<c:forEach items="${list }" var="pjboard">
<tr>
<td><input type="checkbox" name="checkRow" value="${pjboard.proj_no }"/></td>
<td>${pjboard.proj_no }</td>
<td>${pjboard.proj_name }</td>
<td><a href="/mgr/projectview?proj_no=${pjboard.proj_no }">${pjboard.proj_title }</a></td>
<td>${pjboard.proj_time }</td>
</tr>
</c:forEach>
</table>
<button>삭제</button>
</form>
</div>

<div class="src" style="text-align: center;">
<form action="/mgr/projectlist" method="get">
<input type="text" name="search" id="search"/>
<button id="btnSearch" class="btn btn-primary">검색</button>
</form>
</div>
</div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />

