<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Header -->
<jsp:include page="/WEB-INF/views/mgr/layouts/mgrheader.jsp"/>  

<style type="text/css">




</style>
<div class="items" style="text-align: center;">
<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<td><a class="btn btn-primary btn-block " href="/mgr/userlist" role="button">사용자 목록 </a></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td><a class="btn btn-primary btn-block" href="/mgr/profilelist" role="button">프로필게시판 목록</a></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td><a class="btn btn-primary btn-block" href="/mgr/projectlist" role="button">프로젝트게시판 목록</a></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td><a class="btn btn-primary btn-block" href="/mgr/freelist" role="button">자유게시판 목록</a></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td><a class="btn btn-primary btn-block" href="/mgr/complist" role="button">완성된게시판 목록</a></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td><a class="btn btn-primary btn-block" href="/mgr/logout" role="button">로그아웃</a></td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

	<a class="a-link" target="_self" href="/mgr/userlist"">사용자 목록 </a> 
	<a class="btn btn-default btn-block center-block" href="/mgr/profilelist" role="button" style="width:30%">프로필게시판 목록</a> 
	<a class="btn btn-default btn-block center-block" href="/mgr/projectlist" role="button" style="width:30%">프로젝트게시판 목록</a>
	<a class="btn btn-default btn-block center-block" href="/mgr/freelist" role="button" style="width:30%">자유게시판 목록</a>
	<a class="btn btn-default btn-block center-block" href="/mgr/complist" role="button" style="width:30%">완성된게시판 목록</a>
	<a class="btn btn-default btn-block center-block" href="/mgr/logout" role="button" style="width:30%">로그아웃</a>
</div>

</div> <!-- .container -->

<!-- Footer -->

<jsp:include page="/WEB-INF/views/mgr/layouts/mgrfooter.jsp"/>  