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

<h1 class="text-center">프로필 수정✍</h1>

<form action="/profileBoard/update" method="post" enctype="multipart/form-data">
<input type="hidden" name="prof_no" value="${ profile.prof_no }" />
<table>
<tr>
	<td>${ profile.username }</td>
	<td>${ profile.userno }</td>
	<td>${ profile.prof_no }</td>
</tr>
<tr>
	<td><label for="prof_interest">관심분야 :</label></td>
	<td><select name="prof_interest">
			<option value="${profile.prof_interest }" selected>${profile.prof_interest }</option>
			<option value="개발">개발</option>
			<option value="디자인">디자인</option>
			<option value="스타트업">스타트업</option>
			<option value="언어">IT언어</option>
		</select>
	</td>
</tr>
<tr>
	<td><label for="prof_loc">활동 지역 : </label></td>
	<td><select name="prof_loc">
			<option value="${ profile.prof_loc }" selected>${ profile.prof_loc }</option>
			<option value="서울" >서울</option>
			<option value="경기">경기</option>
			<option value="그 외">그 외</option>
		</select>
	</td>
</tr>
<tr>
	<td><label for="prof_job">직업 : </label></td>
	<td><select name="prof_job">
			<option value="${ profile.prof_job }" selected>${ profile.prof_job }</option>
			<option value="개발자" >개발자</option>
			<option value="디자이너">디자이너</option>
			<option value="프리랜서">프리랜서</option>
			<option value="무직">무직</option>
		</select>
	</td>
</tr>
<tr>
	<td><label for="prof_state">상태 : </label></td>
	<td><select name="prof_state">
			<option value="${ profile.prof_state }" selected>${ profile.prof_state }</option>
			<option value="구직중">구직중</option>
			<option value="재직중">재직중</option>
			<option value="프리랜서">프리랜서</option>
		</select>
	</td>
</tr>
<tr>
	<td><label for="prof_career">경력 : </label></td>
	<td><select name="prof_career">
			<option value="${ profile.prof_career }" selected>${ profile.prof_career }</option>
			<option value="1-2년차">1-2년 차</option>
			<option value="3-4년차">3-4년 차</option>
			<option value="5-7년차">5-7년 차</option>
			<option value="8년 이상">8년 이상</option>
		</select>
	</td>
</tr>
<tr>
	<td><label for="prof_content" >내용 : </label></td>
	<td><textarea name="prof_content" id="prof_content">${ profile.prof_content }</textarea></td>
</tr>
	
<tr>
	<td>
		<c:if test="${not empty file }">
			<br>원본 파일 : ${ file.originName } (${file.fileSize })
		</c:if>
	</td>
	<td><label>파일 : <input type="file" name="file" /></label></td>
</tr>
</table>
<button type="button" id="edit">수정완료</button>
<button type="button" id="cancel">취소</button>
</form>


<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />

<script>
	CKEDITOR.replace( 'prof_content', {
		height : "500px"
	} );
</script>