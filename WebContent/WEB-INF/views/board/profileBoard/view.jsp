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
	
	$("#loginplz").click( function loginplz(){
		alert("로그인 해주세요");
	 	$(location).attr("href","/login");
		});
	$("#loginplz2").click( function loginplz(){
		alert("로그인 해주세요");
	 	$(location).attr("href","/login");
		});
	$("#loginplz3").click( function loginplz(){
		alert("로그인 해주세요");
	 	$(location).attr("href","/login");
		});
	
	
	$("#invite").click(function (){
		alert("초대되었습니다!");
	})
	
})// document end
</script>


<div class="container">
<h2 class="text-center"> 프로필 게시판 </h2>
<table class="table table-bordered">
<tr>
	<td class="info">번호</td><td>${ profile.prof_no }</td>
	<td class="info">유저이름</td><td>${ profile.username }</td>
	<td class="info">작성일</td><td>${ profile.prof_time }</td>
</tr>
<tr>
	<td class="info">관심</td><td>${ profile.prof_interest }</td>
	<td class="info">지역</td><td>${ profile.prof_loc }</td>
	<td class="info">직업</td><td>${ profile.prof_job }</td>
</tr>
<tr>
	<td class="info">상태</td><td>${ profile.prof_state }</td>
	<td class="info">경력</td><td>${ profile.prof_career }</td>

	<td class="info">찜받은수</td><td><span id="countLike">${ countLike }</span>
		<c:if test="${ login }">
			<c:if test="${ canLike }">
				<button id="like" style="color: red;">
					<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
				</button>
				<button id="unlike" style="display: none; color:blue;">
					<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
				</button>
			</c:if>
			<c:if test="${ !canLike }">
				<button id="like" style="display: none; color:red;">
					<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
				</button>
				<button id="unlike" style="color:blue;">
					<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
				</button>
			</c:if>
		</c:if>
		<c:if test="${ !login }">
			<c:if test="${ canLike }">
				<button id="loginplz" style="color:red;" >
					<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
				</button>
				<button id="unlike" style="display: none; color:blue;">
					<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
				</button>
			</c:if>
			<c:if test="${ !canLike }">
				<button id="like" style="display: none; color:red;">
					<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
				</button>
				<button id="loginplz" style="color:blue;">
					<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
				</button>
			</c:if>
		</c:if>
	</td>
</tr>
<tr>
	<td colspan="6" style="height: 500px;">${profile.prof_content }</td>
</tr>
<tr>
	<td class="info">첨부파일</td>
	<td><a href="/file/download?fileno=${file.fileno }">${file.originName }</a></td>
	
</tr>
</table>
</div>
<div class="text-center">
<c:if test="${ login }">
	<a id="edit" role="button" class="btn btn-info" href="/profileBoard/update?prof_no=${profile.prof_no}">수정</a>
</c:if>
<c:if test="${ !login }">
	<a id="loginplz2" role="button" class="btn btn-info">수정</a>			
</c:if>
<c:if test="${ login }">
	<a id="delete" role="button" class="btn btn-info" href="/profileBoard/delete?prof_no=${profile.prof_no}">삭제</a>			
	<a id="invite" href="/alert/fromprofile?prof_no=${profile.prof_no}" role="button" class="btn btn-info" data-prof_no="${ profile.prof_no }" data-userno="${ profile.userno }">우리팀으로 초대하기💌</a>			
	
</c:if>
<c:if test="${ !login }">
	<a id="loginplz3" role="button" class="btn btn-info">삭제</a>			
</c:if>
</div>



<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />