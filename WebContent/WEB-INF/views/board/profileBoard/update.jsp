<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="//cdn.ckeditor.com/4.13.0/basic/ckeditor.js"></script>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<script type="text/javascript">



$(document).ready(function() {
	
	$("#edit").click(function() {
	$("form").submit();
	});
	
	$("#cancel").click(function () {
		history.go(-1);
	});
});
</script>
<div class="container text-center">

<h1 class="text-center">프로필 수정✍</h1>

<form  class="form-horizontal" action="/profileBoard/update" method="post" enctype="multipart/form-data">
<input type="hidden" name="prof_no" value="${ profile.prof_no }" />
	<div class="form-group" style="text-align: center;">
		<label for="prof_name" class="col-sm-1 col-md-1 col-lg-1 control-label" style="padding-top: 10px; text-align: center;">ID⭐ ${ profile.username }</label>
		<label for="prof_username" class="col-sm-1 col-md-1 col-lg-1 control-label" style="padding-top: 10px; text-align: center;"> NAME⭐ ${ profile.userno }</label>
<%-- 		<label for="prof_no" class="col-sm-1 col-md-1 col-lg-1 control-label" style="padding-top: 10px; text-align: center;">${ profile.prof_no }</label> --%>
	
	</div>
	
	<div class="form-group">
		<div class="col-sm-9 col-md-9 col-lg-5">
			<label for="prof_interest" class="col-sm-1 col-md-1 col-lg-3 control-label" style="padding-top: 16px; text-align: center;">관심분야 </label>
				<select name="prof_interest">
					<option value="${profile.prof_interest }" selected>${profile.prof_interest }</option>
					<option value="개발" selected>개발</option>
					<option value="디자인">디자인</option>
					<option value="스타트업">스타트업</option>
					<option value="언어">IT언어</option>
				</select>
			</div>
		</div>
		<div class="col-sm-9 col-md-9 col-lg-5">
			<label for="prof_loc" class="col-sm-1 col-md-1 col-lg-3 control-label" style="padding-top: 16px; text-align: center;">활동 지역 </label>
				<select name="prof_loc">
					<option value="${ profile.prof_loc }" selected>${ profile.prof_loc }</option>
					<option value="서울" selected>서울</option>
					<option value="경기">경기</option>
					<option value="그 외">그 외</option>
				</select>
			</div>
		<div class="col-sm-9 col-md-9 col-lg-5">
			<label for="prof_job" class="col-sm-1 col-md-1 col-lg-3 control-label" style="padding-top: 16px; text-align: center;">직업  </label>
				<select name="prof_job">
					<option value="${ profile.prof_job }" selected>${ profile.prof_job }</option>
					<option value="개발자" selected>개발자</option>
					<option value="디자이너">디자이너</option>
					<option value="프리랜서">프리랜서</option>
					<option value="무직">무직</option>
				</select>
			</div>
		<div class="col-sm-9 col-md-9 col-lg-5">
			<label for="prof_job" class="col-sm-1 col-md-1 col-lg-3 control-label" style="padding-top: 16px; text-align: center;">직업  </label>
					<select name="prof_job">
						<option value="${ profile.prof_job }" selected>${ profile.prof_job }</option>
						<option value="개발자" selected>개발자</option>
						<option value="디자이너">디자이너</option>
						<option value="프리랜서">프리랜서</option>
						<option value="무직">무직</option>
					</select>
				</div>
			<div class="col-sm-9 col-md-9 col-lg-5">
				<label for="prof_state" class="col-sm-1 col-md-1 col-lg-3 control-label" style="padding-top: 16px; text-align: center;">상태 </label>
						<select name="prof_state">
							<option value="${ profile.prof_state }" selected>${ profile.prof_state }</option>
							<option value="구직중" selected>구직중</option>
							<option value="재직중">재직중</option>
							<option value="프리랜서">프리랜서</option>
						</select>
					</div>
				<div class="col-sm-9 col-md-9 col-lg-5">
					<label for="prof_career" class="col-sm-1 col-md-1 col-lg-3 control-label" style="padding-top: 16px; text-align: center;">경력 </label>
							<select name="prof_career">
								<option value="${ profile.prof_career }" selected>${ profile.prof_career }</option>
								<option value="1-2년차" selected>1-2년 차</option>
								<option value="3-4년차">3-4년 차</option>
								<option value="5-7년차">5-7년 차</option>
								<option value="8년 이상">8년 이상</option>
							</select>
						</div>
					<div class="col-sm-9 col-md-9 col-lg-10">
						<textarea for="prof_content" name="prof_content" id="prof_content">${ profile.prof_content }</textarea>
					</div>
				<div class="col-sm-9 col-md-9 col-lg-5">
					<input type="file" name="file" />
				</div>
			<c:if test="${not empty file }">
				<br>원본 파일 : ${ file.originName } (${file.fileSize })
			</c:if>
				<label>파일 : <input type="file" name="file" /></label>
		<div class="col-sm-offset-2 col-sm-8 text-center">
			<button type="button" id="edit">수정완료</button>
			<button type="button" id="cancel">취소</button>
		</div>
</form>
</div>


<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />

<script>
	CKEDITOR.replace( 'prof_content', {
		height : "500px"
	} );
</script>