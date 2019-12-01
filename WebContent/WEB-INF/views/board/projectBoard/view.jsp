<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/layouts/header.jsp" />


<script type="text/javascript">

$(document).ready(function() {
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/projectBoard/list");
	});
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		$(location).attr("href", "/projectBoard/update?proj_no=${projectBoard.proj_no }");
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
				<td class="info">프로젝트 제목</td>
				<td colspan="3">${projectBoard.proj_title }</td>
				<td class="info">직업</td>
				<td>${projectBoard.proj_job }</td>
			</tr>

			<tr>
				<td class="info">프로젝트 이름</td>
				<td colspan="3">${projectBoard.proj_name }</td>
				<td class="info">진행상황</td>
				<td>${projectBoard.proj_progress }</td>
			</tr>

			<tr>
				<td class="info">작성자 이름</td>
				<td colspan="3">${projectBoard.userno }(추후 이름으로 수정)</td>
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
			
			<tr>
				<td colspan="6" style="height: 500px;">${projectBoard.proj_content }</td>
			</tr>

			<tr>
				<td colspan="6">첨부파일 <a	href="/file/download?fileno=${files.fileno}">${files.originName }</a>
				</td>
			</tr>

		</table>
		
	</div>


	<div class="box-footer">
		<c:if test="${userno eq projectBoard.userno }">
		<button id="btnUpdate" class="btn btn-warning">수정</button>
		<button id="btnDelete" class="btn btn-danger">삭제</button>
		</c:if>
		<button id="btnList" class="btn btn-primary">목록</button>
	</div>



</section>


<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />