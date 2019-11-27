<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="//cdn.ckeditor.com/4.13.0/basic/ckeditor.js"></script>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<script type="text/javascript">
$(document).ready(function() {
	$("#write").click(function() {
		
// 		submitContents("#write");
		
		$("form").submit();
	});
	
	$("#cancel").click(function () {
		history.go(-1);
	});
});
</script>

<div class="container text-center">
	<h1>완성된 프로젝트 글 작성</h1>
	
	<form class="form-horizontal" action="/compBoard/write" method="post" enctype="multipart/form-data">
  		<div class="form-group">
    		<label for="comp_title" class="col-sm-1 col-md-1 col-lg-1 control-label" 
    			   style="padding-top: 10px; text-align: center;">프로젝트 이름</label>
    		<div class="col-sm-9 col-md-9 col-lg-10">
      			<input type="text" class="form-control" id="comp_title" placeholder="제목을 입력하세요" name="comp_title" maxlength="120">
    		</div>
  		</div>
  		
  		<div class="form-group">
    		<label for="comp_name" class="col-sm-1 col-md-1 col-lg-1 control-label" 
    			   style="padding-top: 16px; text-align: center;">팀 이름 </label>
    		<div class="col-sm-9 col-md-9 col-lg-10">
      			<input type="text" class="form-control" id="comp_name" placeholder="팀 이름을 입력하세요" name="comp_name" maxlength="120">
    		</div>
  		</div>
  		
  		<div class="form-group">
    		<label for="comp_member" class="col-sm-1 col-md-1 col-lg-1 control-label" 
    			   style="padding-top: 16px; text-align: center;">참여인원</label>
    		<div class="col-sm-9 col-md-9 col-lg-4">
      			<input type="text" class="form-control" id="comp_member" placeholder="참여인원을 입력하세요 (ex.5)" name="comp_member" >
    		</div>
    		
    		<label class="col-sm-1 col-md-1 col-lg-1 control-label" 
    			   style="padding-top: 16px; text-align: center;">프로젝트 기간</label>
    		<div class="col-sm-9 col-md-9 col-lg-2">
      			<input type="text" class="form-control" id="comp_startdate" placeholder="(ex.20191111)" name="comp_startdate">
      			- <input type="text" class="form-control" id="comp_enddate" placeholder="(ex.20191114)" name="comp_enddate">
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
		    
		<div class="form-group text-left" style="margin-top: 5px;">
		
			 <input type="file" id="uploadfile" name="uploadfile">
             <p class="help-block">파일은 10MB까지만 업로드 가능합니다.</p>

		</div> 
				<button class="btn btn-default" id="write" onclick="alert('글 작성을 완료하시겠습니까?')">작성완료</button>
				<button class="btn btn-default" type="button" id="cancel" onclick="alert('글 작성을 취소하시겠습니까?')">취소</button>
		
	</form>
</div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />