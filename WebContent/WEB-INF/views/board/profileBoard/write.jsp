<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<!-- 스마트에디터 -->
<script src="//cdn.ckeditor.com/4.13.0/basic/ckeditor.js"></script>

<h1 class="text-center">프로필 작성✍</h1>

<form action="/profileBoard/write" method="post" enctype="multipart/form-data">
<table>
<tr>
	<td>${ username }</td>
	<td>${ userno }</td>
</tr>
<tr>
	<td><label for="interest">관심분야 :</label></td>
	<td><select name="interest">
			<option value="1">개발</option>
			<option value="2">디자인</option>
			<option value="3">스타트업</option>
			<option value="4">언어</option>
		</select>
	</td>
</tr>
<tr>
	<td><label for="location">활동 지역 : </label></td>
	<td><select name="location">
			<option value="1">서울</option>
			<option value="2">경기</option>
			<option value="3">그 외</option>
		</select>
	</td>
</tr>
<tr>
	<td><label for="job">직업 : </label></td>
	<td><select name="job">
			<option value="1">개발자</option>
			<option value="2">디자이너</option>
			<option value="3">프리랜서</option>
			<option value="4">무직</option>
		</select>
	</td>
</tr>
<tr>
	<td><label for="state">상태 : </label></td>
	<td><select name="state">
			<option value="1">구직중</option>
			<option value="2">재직중</option>
			<option value="3">프리랜서</option>
		</select>
	</td>
</tr>
<tr>
	<td><label for="career">경력 : </label></td>
	<td><select name="career">
			<option value="1">1-2년 차</option>
			<option value="2">3-4년 차</option>
			<option value="3">5-7년 차</option>
			<option value="4">8년 이상</option>
		</select>
	</td>
</tr>
<tr>
	<td><label for="content" >내용 : </label></td>
	<td><textarea name="content" id="content"></textarea></td>
</tr>
	
<tr>
	<td></td>
	<td><label>파일 : <input type="file" name="file" /></label></td>
</tr>
</table>
<button type="button" id="write">작성완료</button>
<button type="button" id="cancel">취소</button>
</form>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />

<!-- 스마트 에디터 적용코드, textarea에 스킨 입히기 -->
<script>
	CKEDITOR.replace( 'content', {
		height : "500px"
	} );
</script>