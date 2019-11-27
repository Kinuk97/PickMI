<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>
<script type="text/javascript">

//form submit되면 내용을 textarea에 반영해주는 함수
function submitContents(elClickedObj) {
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		elClickedObj.form.submit(); //<form> submit 수행
	} catch(e) {}
}

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
<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<h1 class="text-center">프로필 작성✍</h1>

<form action="/profileBoard/write" method="post" enctype="multipart/form-data">
<table>
<tr>
	<td>${ name }</td>
	<td>${ userno }</td>
</tr>
<tr>
	<td><label for="interest">관심분야 :</label></td>
	<td><select name="interest">
		<option value="1"></option>
	</select></td>
</tr>
<tr>
	<td><label for="location">활동 지역 : </label></td>
	<td><input type="text" id="location" /></td>
</tr>
<tr>
	<td><label for="job">직업 : </label></td>
	<td><input type="text" id="job" /></td>
</tr>
<tr>
	<td><label for="state">상태 : </label></td>
	<td><input type="text" id="state" /></td>
</tr>
<tr>
	<td><label for="career">경력 : </label></td>
	<td><input type="text" id="career" /></td>
</tr>
<tr>
	<td style="width:100px"><label for="content" >내용 : </label></td>
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
<script type="text/javascript">
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "content", //에디터가 적용되는 <textarea>의 id
		sSkinURI: "/resources/se2/SmartEditor2Skin.html", //에디터 스킨
		fCreator: "createSEditor2"
	});
</script>