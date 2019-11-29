<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- Header -->
<jsp:include page="/WEB-INF/views/mgr/layouts/mgrheader.jsp"/>  
    
<script type="text/javascript">

$(document).ready(function() {

	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/mgr/projectlist");
	});

	//삭제버튼 동작
	$("#btnDelete").click(function() {
		$(location).attr("href", "/projectBoard/delete?proj_no=${projectBoard.proj_no }");
	});
	
});

</script>

<section class="content container-fluid">

	<div class="box box-primary">
		<div class="box-header with-border">
			<h2 class="box-title">프로젝트</h2>
		</div>
	
		<table class="table table-bordered">
			<tr>
				<td class="info">프로젝트 번호</td>
				<td colspan="3">${projectBoard.proj_no }</td>
			</tr>

			<tr>
				<td class="info">프로젝트 제목</td>
				<td colspan="3">${projectBoard.proj_title }</td>
				<td class="info">직업</td>
				<td>${projectBoard.proj_job }</td>
			</tr>

			<tr>
				<td class="info">프로젝트 이름</td>
				<td colspan="3">${projectBoard.proj_name }</td>
				<td class="info">작성시간</td>
				<td>${projectBoard.proj_time }</td>
				
			</tr>

			<tr>
				<td class="info">경력</td>
				<td>${projectBoard.proj_career }</td>
				<td class="info">참여인원</td>
				<td>${projectBoard.proj_apply }</td>
				<td class="info">찜개수</td>
				<td>${projectBoard.proj_like }</td>
				
			</tr>

			<tr>
				<td class="info">시작날짜</td>
				<td>${projectBoard.proj_sdate }</td>
				<td class="info">마감날짜</td>
				<td>${projectBoard.proj_ddate }</td>
				<td class="info">모집기간</td>
				<td>${projectBoard.proj_rec_date }</td>
			</tr>


		</table>

		<div class="form-group">
			<label>내용</label> <input type="text" class="form-control"
				readonly="readonly" style="height: 300px;" value="${projectBoard.proj_content }"/>
		</div>
	</div>


	<div class="box-footer">
		<button id="btnDelete" class="btn btn-danger">삭제</button>
		<button id="btnList" class="btn btn-primary">목록</button>
	</div>



</section>

</body>
</html>