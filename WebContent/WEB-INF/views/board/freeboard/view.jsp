<%@page import="dto.CompBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<div class="container">
	<h2 class="text-center">자유게시판</h2>
	
	<table class="table table-bordered">
		<tr>
			<td class="info">제목</td><td>${board.free_title }</td>
			<td class="info">작성자</td><td>${board.userno }</td>
		</tr>
		<tr>
			<td class="info">작성일</td><td colspan="3">${board.free_time }</td>
		</tr>

		<tr>
			<td colspan="4" style="height: 500px; background: #CCC;">${board.free_content }</td>
		</tr>
		<tr>
			<td class="info">첨부파일</td>
			<td colspan="3"><a href="/file/download?fileno=${file.fileno }">${file.originName }</a></td>
		</tr>
	</table>
	
</div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />