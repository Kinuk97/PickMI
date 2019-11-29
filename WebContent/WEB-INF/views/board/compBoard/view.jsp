<%@page import="dto.Files"%>
<%@page import="dto.CompBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

    
<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<script type="text/javascript">
$(document).ready(function(){
	
// 	목록 버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/compBoard/list");
	});
	
// 	수정 버튼 동작
	$("#btnUpdate").click(function(){
		$(location).attr("href", "/compBoard/update?comp_no=${compBoard.comp_no }");
	});
	
// 	삭제 버튼 동작
	$("#btnDelete").click(function(){
		 $(location).attr("href", "/compBoard/delete?comp_no=${compBoard.comp_no }");
	});
	
});

</script>

<script type="text/javascript">
$(document).ready(function() {
	
	$("#unlike").click( function(){
		console.log("된다");
		$("#unlike").hide();
		$("#like").show();
		$.ajax({
			url: "/compBoard/like"
			, type: "GET"
			, data: {
				prof_no : '${compBoard.comp_no}',
				boardno : '${compBoard.comp_no}',
				userno : '${compBoard.userno}',
				postno : '${ 4 }'
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
			url: "/compBoard/like"
			, type: "GET"
			, data: {
				prof_no : '${compBoard.comp_no}',
				boardno : '${compBoard.comp_no}',
				userno : '${compBoard.userno}',
				postno : '${ 4 }'
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

<h2 class="text-center"><strong>완성된 프로젝트</strong>&nbsp;&nbsp;</h2>
<br>

<div class="container">
	
	<table class="table table-bordered">

			<tr>
				<td>프로젝트 제목</td><td>${compBoard.comp_title }</td>
				<td>팀장</td><td>${compBoard.userno }</td>
			</tr>

			<tr>
				<td>팀 이름</td><td>${compBoard.comp_name }</td>
				<td>작성날짜</td><td>${compBoard.comp_date }</td>
			</tr>

			<tr>
				<td>참여인원</td><td>${compBoard.comp_member }</td>
				<td>조회수</td><td>${compBoard.comp_view }</td>
			</tr>

			<tr>
				<td>프로젝트 기간</td><td>${compBoard.comp_startdate } ~ ${compBoard.comp_enddate }</td>
				<td>찜받은수</td><td><span id="countLike">${ countLike }</span>
					<c:if test="${ login }">
						<c:if test="${ canLike }">
							<button id="like" class="btn btn-default" style="padding: 3px;">LIKE</button>
							<button id="unlike" style="display: none; padding:3px;" class="btn btn-default">UNLIKE</button>
						</c:if>
						
						<c:if test="${ !canLike }">
							<button id="like" style="display: none;" class="btn btn-default" style="padding: 3px;">LIKE</button>
							<button id="unlike" class="btn btn-default" style="padding: 3px;">UNLIKE</button>
						</c:if>
					</c:if>
				</td>
			</tr>

<!-- 			<tr> -->
<!-- 				<td class="info" colspan="4">본문</td> -->
<!-- 			</tr> -->

			<tr>
				<td colspan="4" style="height: 500px;">${compBoard.comp_content }</td>
			</tr>

				
<!-- 					<td>찜하기<button class="btn btn-default text-center" >찜하기</button></td><td></td> -->
				
			
		</table>
		첨부파일<a href ="/file/download?fileno=${files.fileno}"> ${files.originName }</a>
		
		<div class="row text-center">
			<button id="btnUpdate" class="btn btn-default">게시글 수정</button>
			<button id="btnDelete" class="btn btn-default" onclick="alert('게시글을 정말 삭제하시겠습니까?.')">게시글 삭제</button>
		</div>
	
</div>


<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />