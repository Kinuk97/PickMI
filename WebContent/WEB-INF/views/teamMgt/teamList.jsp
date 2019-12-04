<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/views/layouts/header.jsp"></jsp:include>
<!-- add styles -->
<link href="/resources/css/jquery-ui.min.css" rel="stylesheet"
	type="text/css" />
<!-- add scripts -->
<script src="/resources/js/jquery-ui.min.js"></script>

<script type="text/javascript">


</script>

<!-- 팀원목록 -->
<div class="page-header">
  <h1>팀원관리 <small>팀원을 추가하고 관리할 수 있습니다</small></h1>
</div>
	
	
<div id="teamList" class="panel panel-info">
  <!-- Default panel contents -->
	<div class="panel-heading">팀원 목록</div>
  		<div class="panel-body">
    		<p>프로젝트이름</p>
<!--     		<p style="text-align:right"> -->
<!--     			새로운팀원 신청<span class="badge">4</span> -->
<!--     		</p> -->
  		</div>
  <!-- Table -->
  <table class="table">
  	<tr>
    	<td><p>회원명</p></td>
    	<td><p>회원명</p></td>
    	<td><p>회원명</p></td>
    </tr>
  </table>
</div>

<!-- 팀원삭제 -->
<div class="panel panel-info">
  <!-- Default panel contents -->
  <div class="panel-heading">새로운 팀원 신청	
  </div>
  <div class="panel-body">
    <p>프로젝트 이름<span class="badge" style="position:absolute">4</span></p>
  </div>

  <!-- Table -->
  <table class="table">
    ...
  </table>
</div>

<!-- footer -->
<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>