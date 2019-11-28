<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<style type="text/css">
table {
text-align:center;
}

</style>
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
	<td><label for="prof_interest">관심분야 :</label></td>
	<td><select name="prof_interest">
			<option value="none">===선택===</option>
			<option value="개발" selected>개발</option>
			<option value="디자인">디자인</option>
			<option value="스타트업">스타트업</option>
			<option value="언어">언어</option>
		</select>
	</td>
</tr>
<tr>
	<td><label for="prof_loc">활동 지역 : </label></td>
	<td><select name="prof_loc">
			<option value="none">===선택===</option>
			<option value="서울" selected>서울</option>
			<option value="경기">경기</option>
			<option value="그 외">그 외</option>
		</select>
	</td>
</tr>
<tr>
	<td><label for="prof_job">직업 : </label></td>
	<td><select name="prof_job">
<!-- 			<option value="none">===선택===</option> -->
			<option value="개발자" selected>개발자</option>
			<option value="디자이너">디자이너</option>
			<option value="프리랜서">프리랜서</option>
			<option value="무직">무직</option>
		</select>
	</td>
</tr>
<tr>
	<td><label for="prof_state">상태 : </label></td>
	<td><select name="prof_state">
<!-- 			<option value="none">===선택===</option> -->
			<option value="구직중" selected>구직중</option>
			<option value="재직중">재직중</option>
			<option value="프리랜서">프리랜서</option>
		</select>
	</td>
</tr>
<tr>
	<td><label for="prof_career">경력 : </label></td>
	<td><select name="prof_career">
<!-- 			<option value="none">===선택===</option> -->
			<option value="1-2년차" selected>1-2년 차</option>
			<option value="3-4년차">3-4년 차</option>
			<option value="5-7년차">5-7년 차</option>
			<option value="8년 이상">8년 이상</option>
		</select>
	</td>
</tr>
<tr>
	<td><label for="prof_content" >내용 : </label></td>
	<td><textarea name="prof_content" id="prof_content"></textarea></td>
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
	CKEDITOR.replace( 'prof_content', {
		height : "500px"
	} );
</script>