<%@page import="dto.Files"%>
<%@page import="dto.CompBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

    
<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<script type="text/javascript">
$(document).ready(function(){
	
// 	목록 버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/compBoard/list");
	});
	
// 	수정 버튼 동작
	$("#btnUpdate").click(function(){
		$(location).attr("href", "/compBoard/update?comp_no=${compBoard.comp_no }");
	});
	
// 	삭제 버튼 동작
	$("#btnDelete").click(function(){
		 $(location).attr("href", "/compBoard/delete?comp_no=${compBoard.comp_no }");
	});
	
});

</script>


<h2 class="text-center"><strong>완성된 프로젝트</strong>&nbsp;&nbsp; <small>${compBoard.comp_no }</small></h2>
<br>

<div class="container">
	
	<table class="table table-bordered">

			<tr>
				<td>프로젝트 제목</td><td>${compBoard.comp_title }</td>
				<td>팀장</td><td>${compBoard.userno }</td>
			</tr>

			<tr>
				<td>팀 이름</td><td>${compBoard.comp_name }</td>
				<td>작성날짜</td><td>${compBoard.comp_date }</td>
			</tr>

			<tr>
				<td>참여인원</td><td>${compBoard.comp_member }</td>
				<td>조회수</td><td>${compBoard.comp_view }</td>
			</tr>

			<tr>
				<td>프로젝트 기간</td><td>${compBoard.comp_startdate } ~ ${compBoard.comp_enddate }</td>
				<td>찜한수</td><td>${compBoard.comp_like }</td>
			</tr>

<!-- 			<tr> -->
<!-- 				<td class="info" colspan="4">본문</td> -->
<!-- 			</tr> -->

			<tr>
				<td colspan="4" style="height: 500px;">${compBoard.comp_content }</td>
			</tr>

				<tr>
					<td colspan="4">첨부파일
					<a href ="/file/download?fileno=${files.fileno}"> ${files.originName }</a>
					</td>
				</tr>
			
		</table>
		
		<div class="row text-center">
			<button id="btnUpdate">게시글 수정</button>
			<button id="btnDelete" onclick="alert('게시글을 정말 삭제하시겠습니까?.')">게시글 삭제</button>
		</div>
	
</div>











<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />