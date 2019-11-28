<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="//cdn.ckeditor.com/4.13.0/basic/ckeditor.js"></script>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<script type="text/javascript">
$(document).ready(function() {
	var categoryno = "${board.categoryno}"

	if (categoryno == "1") {
		$("#categoryno option").eq(1).prop("selected", true);
	} else if (categoryno == "2") {
		$("#categoryno option").eq(2).prop("selected", true);
	} else if (categoryno == "3") {
		$("#categoryno option").eq(3).prop("selected", true);
	}
	
	$("#write").click(function() {
		$("#updateForm").prepend("<input type=\"text\" name='free_no' value='${board.free_no}'></input>");
		
		$("#updateForm").submit();
	});
	
	$("#cancel").click(function () {
		history.go(-1);
	});
});
</script>

<div class="container text-center">
	<h1>게시글 수정✍</h1>
	
	<form id="updateForm" class="form-horizontal" action="/freeboard/update" method="post" enctype="multipart/form-data">
  		<div class="form-group">
    		<label for="free_title" class="col-sm-1 col-md-1 col-lg-1 control-label" style="padding-top: 16px; text-align: center;">제목 : </label>
    		<div class="col-sm-2 col-md-2 col-lg-1">
    			<select id="categoryno" name="categoryno" style="margin-left: -14px;">
	  				<option value="">선택없음</option>
	  				<option value="1">아이디어</option>
	  				<option value="2">정보</option>
	  				<option value="3">공모전</option>
  				</select>
    		</div>
    		<div class="col-sm-9 col-md-9 col-lg-10">
      			<input type="text" class="form-control" id="free_title" placeholder="제목을 입력하세요" name="free_title" maxlength="120" value="${board.free_title }">
    		</div>
  		</div>
	  	<div class="form-group">
		  	<textarea id="free_content" name="free_content">${board.free_content }</textarea>
		</div>
	  	<script>
			CKEDITOR.replace( 'free_content', {
				height : "500px"
			} );
	    </script>
	    <div class="row">
	    	<div class="col-lg-6 text-left">
		    	<input class="btn btn-info" type="file" name="uploadFile">
		    	<p class="help-block">파일은 10MB까지만 업로드 가능합니다.
		    	<c:if test="${not empty file }">
			    	<br>원본 파일 : ${file.originName } (${file.fileSize })
		    	</c:if>
		    	</p>
	    	</div>
	    	<div class="col-lg-6 text-right">
				<button class="btn btn-info" type="button" id="write">수정하기</button>
				<button class="btn btn-error" type="button" id="cancel">취소</button>
	    	</div>
	    </div>
	</form>
</div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />