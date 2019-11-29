<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<script type="text/javascript">
$(document).ready(function() {
	
	$("#unlike").click( function(){
		console.log("된다");
		$("#unlike").hide();
		$("#like").show();
		$.ajax({
			url: "/profileBoard/like"
			, type: "GET"
			, data: {
				prof_no : '${profile.prof_no}',
				boardno : '${profile.prof_no}',
				userno : '${profile.userno}',
				postno : '${ 1 }'
			}
			, dataType : "json"
			, success : like
			, error : function() {
				console.log("ajax fail")
			}
		})
	})
	
		$("#like").click( function(){
		console.log("된다");
		$("#like").hide();
		$("#unlike").show();
		$.ajax({
			url: "/profileBoard/like"
			, type: "GET"
			, data: {
				prof_no : '${profile.prof_no}',
				boardno : '${profile.prof_no}',
				userno : '${profile.userno}',
				postno : '${ 1 }'
			}
			, dataType : "json"
			, success : like
			, error : function() {
				console.log("ajax fail")
			}
		})
	})

function like(data) {
		console.log("좋아요되랏")
	$("#countLike").html(data.countLike)
}
	
})
</script>

<h1>${ profile.userno }</h1>

<table class="table table-bordered">
<tr>
	<td class="info">번호</td><td>${ profile.prof_no }</td>
</tr>
<tr>
	<td class="info">유저이름</td><td>${ profile.username }</td>
</tr>
<tr>
	<td class="info">작성일</td><td>${ profile.prof_time }</td>
</tr>
<tr>
	<td class="info">관심</td><td>${ profile.prof_interest }</td>
</tr>
<tr>
	<td class="info">지역</td><td>${ profile.prof_loc }</td>
</tr>
<tr>
	<td class="info">직업</td><td>${ profile.prof_job }</td>
</tr>
<tr>
	<td class="info">상태</td><td>${ profile.prof_state }</td>
</tr>
<tr>
	<td class="info">경력</td><td>${ profile.prof_career }</td>
</tr>
<tr>
	<td class="info">찜받은수</td><td><span id="countLike">${ countLike }</span>
		<c:if test="${ login }">
			<c:if test="${ canLike }">
				<button id="like">LIKE</button>
				<button id="unlike" style="display: none;">UNLIKE</button>
			</c:if>
			<c:if test="${ !canLike }">
				<button id="like" style="display: none;">LIKE</button>
				<button id="unlike">UNLIKE</button>
			</c:if>
		</c:if>
	</td>
</tr>
<tr>
	<td class="info">내용</td><td>${ profile.prof_content }</td>
</tr>
<tr>
	<td class="info">첨부파일</td>
	<td><a href="/file/download?fileno=${file.fileno }">${file.originName }</a></td>
</tr>
</table>


<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />