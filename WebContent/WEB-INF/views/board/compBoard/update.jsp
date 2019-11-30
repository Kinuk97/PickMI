<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//cdn.ckeditor.com/4.13.0/basic/ckeditor.js"></script>    

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<script type="text/javascript">
$(document).ready(function() {
	$("#update").click(function() {
		
		$("form").submit();
	});
	
	$("#cancel").click(function () {
		history.go(-1);
	});
});
</script>


<div class="container text-center">
	<h1>게시글 수정</h1>
	
	<form class="form-horizontal" action="<%=response.encodeUrl("/compBoard/update") %>" method="post" enctype="multipart/form-data">
		<input type="hidden" name="comp_no" value="${compBoard.comp_no }" />
  		<div class="form-group">
    		<label for="comp_title" class="col-sm-1 col-md-1 col-lg-1 control-label" 
    			   style="padding-top: 10px; text-align: center;">프로젝트 이름</label>
    		<div class="col-sm-9 col-md-9 col-lg-10">
      			<input type="text" class="form-control" id="comp_title" value="${compBoard.comp_title }" name="comp_title" maxlength="120">
    		</div>
  		</div>
  		
  		<div class="form-group">
    		<label for="comp_name" class="col-sm-1 col-md-1 col-lg-1 control-label" 
    			   style="padding-top: 16px; text-align: center;">팀 이름 </label>
    		<div class="col-sm-9 col-md-9 col-lg-10">
      			<input type="text" class="form-control" id="comp_name" value="${compBoard.comp_name }" name="comp_name" maxlength="120">
    		</div>
  		</div>
  		
  		<div class="form-group">
    		<label for="comp_member" class="col-sm-1 col-md-1 col-lg-1 control-label" 
    			   style="padding-top: 16px; text-align: center;">참여인원</label>
    		<div class="col-sm-9 col-md-9 col-lg-2">
      			<input type="number" class="form-control" id="comp_member" value="${compBoard.comp_member }" name="comp_member" >
    		</div>
    		
    		<label class="col-sm-1 col-md-1 col-lg-1 control-label" 
    			   style="padding-top: 8px; text-align: center;">프로젝트 시작날짜</label>
    		<div class="col-sm-9 col-md-9 col-lg-3">
      			<input type="date" class="form-control" id="comp_startdate" value="${compBoard.comp_startdate }" name="comp_startdate">
    		</div>
    		<label class="col-sm-1 col-md-1 col-lg-1 control-label" 
    			   style="padding-top: 8px; text-align: center;">프로젝트 종료날짜</label>
    		<div class="col-sm-9 col-md-9 col-lg-3">
      			<input type="date" class="form-control" id="comp_enddate" value="${compBoard.comp_enddate }" name="comp_enddate">
    		</div>
    		
  		</div>
  		
	  	<div class="form-group">
		  	<textarea id="comp_content" name="comp_content">${compBoard.comp_content }</textarea>
		</div>
		  	<script>
				CKEDITOR.replace( 'comp_content', {
					height : "500px"
				} );
		    </script>
		    
		<div class="form-group text-left" style="margin-top: 5px;">
		
			 <input type="file" id="uploadfile" name="uploadfile" class="btn btn-default">
             <p class="help-block">파일은 10MB까지만 업로드 가능합니다.
             	<c:if test="${not empty files }">
             		<br>원본 파일 : ${files.originName } (${files.fileSize })
				</c:if>
				</p>
		</div> 
				<button class="btn btn-default" id="update" onclick="alert('글 수정을 완료하시겠습니까?')">수정완료</button>
				<button class="btn btn-default" type="button" id="cancel" onclick="alert('글 수정을 취소하시겠습니까?')">수정취소</button>
		
	</form>
</div>





























 
<jsp:include page="/WEB-INF/views/layouts/footer.jsp"/> 
    