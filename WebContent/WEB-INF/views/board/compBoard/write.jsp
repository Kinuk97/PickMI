<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="//cdn.ckeditor.com/4.13.0/basic/ckeditor.js"></script>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<script type="text/javascript">
$(document).ready(function() {
	$("#write").click(function() {
// 		submitContents("#write")
		$("form").submit();
	});
	
	$("#cancel").click(function () {
		history.go(-1);
	});
});
</script>

<div class="container text-center">
	<h1>완성된 프로젝트 글 작성</h1>
	
	<form class="form-horizontal" action="/compBoard/write" method="post">
  		<div class="form-group">
    		<label for="comp_title" class="col-sm-1 col-md-1 col-lg-1 control-label" style="padding-top: 16px; text-align: center;">제목 : </label>
    		<div class="col-sm-9 col-md-9 col-lg-10">
      			<input type="text" class="form-control" id="comp_title" placeholder="제목을 입력하세요" name="comp_title" maxlength="120">
    		</div>
  		</div>
  		
  		<div class="form-group">
    		<label for="comp_name" class="col-sm-1 col-md-1 col-lg-1 control-label" style="padding-top: 16px; text-align: center;">팀 이름 : </label>
    		<div class="col-sm-9 col-md-9 col-lg-10">
      			<input type="text" class="form-control" id="comp_name" placeholder="팀 이름을 입력하세요" name="comp_name" maxlength="120">
    		</div>
  		</div>
  		
	  	<div class="form-group">
		  	<textarea id="comp_content" name="comp_content"></textarea>
			</div>
		  	<script>
				CKEDITOR.replace( 'comp_content', {
					height : "500px"
				} );
		    </script>
	 	<div class="form-group">
	    	<div class="col-sm-offset-2 col-sm-8 text-center">
				<button class="btn btn-default" type="button" id="write">작성완료</button>
				<button class="btn btn-default" type="button" id="cancel">취소</button>
	    	</div>
	  	</div>
	</form>
</div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />