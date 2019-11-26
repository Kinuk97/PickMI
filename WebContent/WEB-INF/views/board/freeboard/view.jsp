<%@page import="dto.CompBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<div class="container">
	<h2 class="text-center">완성된 프로젝트</h2>
	
	<table class="table table-bordered">
		<tr>
			<td class="info">제목</td><td>${board.free_title }</td>
			<td class="info">작성자</td><td>${board.userno }</td>
		</tr>
		<tr>
			<td class="info">작성일</td><td colspan="3">${board.free_time }</td>
		</tr>

		<tr>
			<td colspan="4">${board.free_content }</td>
		</tr>
	</table>
	
</div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />