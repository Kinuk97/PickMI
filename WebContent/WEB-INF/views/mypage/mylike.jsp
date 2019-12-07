<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<style type="text/css">

.innercon1{

   text-align: center;
   margin-bottom: 5%;
   
}

.table{

	width:80%;
	text-align: start;
	margin-left: 13%;

}


</style>



<div class="container">

	<div class="innercon1">
		<h2> 😉${name }님이 찜한 게시글😉 </h2>
	</div>
	
	<div class="innercon2">
		<table class="table table-hover">
			<thead>
			<tr>
				<th style="width: 5%">글번호</th>
				<th style="width: 50%">제목</th>
				<th style="width: 20%">작성일</th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${pflist }" var="prof">
			<tr>
				<td>${prof.prof_no}</td>
				<td><a href="/profileBoard/view?prof_no=${prof.prof_no}">${prof.prof_state }, ${prof.prof_job }, ${prof.prof_loc }</a></td>
				<td>${prof.prof_time }</td>
			</tr>
			</c:forEach>
			
			<c:forEach items="${pjlist }" var="proj">
			<tr>
				<td>${proj.proj_no}</td>
				<td><a href="/projectBoard/view?proj_no=${proj.proj_no}">${proj.proj_name }</a></td>
				<td>${proj.proj_time}</td>
			</tr>
			</c:forEach>
			
			<c:forEach items="${complist }" var="comp">
			<tr>
				<td>${comp.comp_no}</td>
				<td><a href="/compBoard/view?comp_no=${comp.comp_no}">${comp.comp_name }</a></td>
				<td>${comp.comp_date}</td>
			</tr>
			</c:forEach>	
			
			<c:forEach items="${freelist }" var="free">
			<tr>
				<td>${free.free_no}</td>
				<td><a href="/freeboard/view?free_no=${free.free_no}">${free.free_title }</a></td>
				<td>${free.free_time}</td>
			</tr>
			</c:forEach>	
			</tbody>
			
		</table>
	</div>
				<c:import url="/WEB-INF/views/mgr/layouts/mgrpaging.jsp">
   					<c:param name="url" value="${url }" />
				</c:import>
			
</div> <!-- container -->


<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />