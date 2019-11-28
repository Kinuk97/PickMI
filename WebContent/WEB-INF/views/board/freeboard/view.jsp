<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<script type="text/javascript">
$(document).ready(function () {
	$("#cmtBtn").popover({"show" : 500, "hide" : 100});
	
	$("#cmtBtn").on("click", function() {
		var login = "${login}"
		if (login != "" && login) {
			$(this).popover('destroy');
			
			$("form").prepend("<input type=\"hidden\" name='boardno' value='${board.free_no}'></input>");
			
			$("form").submit();
		}
	});
});
</script>

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
			<td colspan="4" style="height: 500px;">${board.free_content }</td>
		</tr>
		<c:if test="${not empty file }">
			<tr>
				<td class="info">첨부파일</td>
				<td colspan="3"><a href="/file/download?fileno=${file.fileno }">${file.originName } (${file.fileSize })</a></td>
			</tr>
		</c:if>
	</table>
	
	<hr>
	
	
	<div class="row text-right">
		<button class="btn btn-info" onclick="location.href='/freeboard/list';">목록</button>
		<c:if test="${userno eq board.userno }">
			<button class="btn btn-success" onclick="location.href='/freeboard/update?free_no=${board.free_no}';">수정</button>
			<button class="btn btn-warning" onclick="location.href='/freeboard/delete?free_no=${board.free_no}';">삭제</button>
		</c:if>
	</div>
	
	<div class="row text-right">
		<form action="/freeboard/comment/write" method="get">
			<textarea class="form-control" style="resize: none; width: 94%; display: inline; float: left; margin: 10px 0; border-top-right-radius: 0px; border-bottom-right-radius: 0px;" name="reply" required="required"></textarea>
			<button type="button" id="cmtBtn" class="btn" style="height: 54px; width: 5%; padding: 0; margin-left: 0px; float: left; border-top-left-radius: 0px; border-bottom-left-radius: 0px;"
			 data-container="body" data-placement="top" data-content="댓글을 작성하기 위해서는 로그인이 필요합니다.">작성</button>
		</form>	
	</div>
	
	<hr>
	
	<c:forEach items="${replyList }" var="reply">
		<div class="row" style="border: 1px solid rgb(221, 221, 221); margin-top: 10px;">
			<div class="col-lg-12">
				<div class="col-lg-6 text-left">
					${reply.username }
				</div>
				<div class="col-lg-6 text-right">
					작성일 : ${reply.replytime }
				</div>
			</div>
			<hr style="clear: both;">
			<div class="col-lg-12">
				<p>${reply.reply }</p>
			</div>
		</div>
	</c:forEach>
		
</div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />