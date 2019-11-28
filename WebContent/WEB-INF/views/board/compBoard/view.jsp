<%@page import="dto.CompBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% CompBoard compBoard = (CompBoard) request.getAttribute("compBoard"); %>

    
<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<script type="text/javascript">
$(document).ready(function(){
	
	//목록 버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/compBoard/list");
	});
	
	//수정 버튼 동작
// 	$("#btnUpdate").click(function(){
// 		$(location).attr("href", "/compBoard/update?comp_no=${compBoard.comp_no }");
// 	});
	
	//삭제 버튼 동작
// 	$("#btnDelete").click(function(){
// 		 $(location).attr("href", "/compBoard/delete?comp_no=${compBoard.comp_no }");
// 	});
	
});

</script>


<h2 class="text-center">완성된 프로젝트</h2>

<div class="container">
	
	<table class="table table-bordered">

			<tr>
				<td class="info">프로젝트 제목</td><td><%=compBoard.getComp_no() %>.<%=compBoard.getComp_title() %></td>
				<td class="info">팀장</td><td><%=compBoard.getUserno() %></td>
			</tr>

			<tr>
				<td class="info">팀 이름</td><td><%=compBoard.getComp_name()%></td>
				<td class="info">작성날짜</td><td><%=compBoard.getComp_date()%></td>
			</tr>

			<tr>
				<td class="info">참여인원</td><td><%=compBoard.getComp_member()%></td>
				<td class="info">조회수</td><td><%=compBoard.getComp_view()%></td>
			</tr>

			<tr>
				<td class="info">프로젝트 기간</td><td><%=compBoard.getComp_startdate()%> ~ <%=compBoard.getComp_enddate() %></td>
				<td class="info">찜한수</td><td><%=compBoard.getComp_like()%></td>
			</tr>

			<tr>
				<td class="info" colspan="4">본문</td>
			</tr>

			<tr>
				<td colspan="4"><%=compBoard.getComp_content() %></td>
			</tr>

		</table>
	
</div>











<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />