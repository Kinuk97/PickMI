<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="//cdn.ckeditor.com/4.13.0/basic/ckeditor.js"></script>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<script type="text/javascript">
$(document).ready(function() {
	$("#write").click(function() {
		submitContents("#write")
		$("form").submit();
	});
	
	$("#cancel").click(function () {
		history.go(-1);
	});
});
</script>

<div class="container text-center">
	<h1>게시글 작성✍</h1>
	
	<form action="/freeboard/write" method="post">
		<div class="col-lg-3"><label for="free_title">제목 : </label></div>
		<div class="col-lg-9">
			<input class="form-control" type="text" placeholder="제목을 입력하세요" id="free_title" name="free_title">
		</div>
	  	<script>
			CKEDITOR.replace( 'free_content' );
	    </script>
		<button type="button" id="write">작성완료</button>
		<button type="button" id="cancel">취소</button>
	</form>
</div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />