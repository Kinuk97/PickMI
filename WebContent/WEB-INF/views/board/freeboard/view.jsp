<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<script type="text/javascript">
var curPage = "${paging.curPage}";

// if (curPage);

function loadReply(curPage) {
	$.ajax({
		type : "post",
		url : "/freeboard/reply/list",
		data : { "curPage" : curPage, "free_no" : "${board.free_no}" },
		dataType : "json",
		success : function(data) {
			var paging = data.paging;
			var replyList = data.replyList;
			
			$("#reply").html("");
			for (var i = 0; i < replyList.length; i++) {
				var row = $("<div class='col-lg-12' style=\"border: 1px solid rgb(221, 221, 221); margin-top: 10px; padding-bottom: 8px;\"></div>");
				
				row.append($("<div class='col-lg-6 text-left'></div>").text(replyList[i].username));
				row.append($("<div class='col-lg-6 text-right'></div>").text("작성자 : " + replyList[i].replytime));
				row.append($("<div class='col-lg-12'></div>").text(replyList[i].reply));
				row.append($("<div style='clear: both;'></div>"));
				
				var userno = "${userno}";
				
				// 내가 작성한 게시글이라면
				if (userno == replyList[i].userno) {
					var btn = $("<div class='btns text-right'></div>");
					btn.append($("<button class='replyFormBtn btn btn-info' data-replyno='" + replyList[i].replyno + "'></button>").text("수정"));	
					btn.append($("<button class='btn btn-warning' onclick='location.href=\"/freeboard/reply/delete?replyno=" + replyList[i].replyno + "&boardno=${board.free_no}&curPage=" + curPage + "\";'>삭제</button>"));	
					
					row.append(btn);
				}
				
				$("#reply").append(row);
			}
			
			// 수정버튼 누르면 수정 폼 나오게 하기
			$(".replyFormBtn").on("click", function() {
				console.log("test");
				$("#modifyDiv").collapse();
				$("#modifyDiv").remove();
				
				var modifyForm = $("<div class='collapse' id='modifyDiv' style='margin-top:10px;'></div>");
				var well = $("<div class='well'></div>");
				var formTag = $("<form action=\"/freeboard/reply/modify\" method='post' id='modifyForm'></form>")
				
				formTag.append($("<textarea class='form-control reply-content' name='reply' required='required'></textarea>"));
				formTag.append($("<button type='button' id='sendReply' class='btn btn-defualt btn-reply' data-replyno='" + $(this).data("replyno") + "'>수정</button>"));
				formTag.append($("<div style='clear: both;'></div>"));
				
				modifyForm.append(well.append(formTag));
				
				$(this).parent().append(modifyForm);
				
				$("#sendReply").on("click", function() {
					$("#modifyForm").append($("<input type='hidden' value='${board.free_no}' name='boardno' />"));
					$("#modifyForm").append($("<input type='hidden' value='" + $(this).data("replyno") + "' name='replyno' />"));
					$("#modifyForm").append($("<input type='hidden' value='" + curPage + "' name='curPage' />"));
					$("#modifyForm").submit();
				});
				
				$("#modifyDiv").collapse();
			});
			
			if (replyList.length != 0) {
				// 페이징 생성하는 스크립트
				var pagingUl = $("<ul class='pagination pagination-sm'></ul>");
				
				// 맨앞 생성
				if (paging.curPage != 1) {
					pagingUl.append($("<li data-page='first'><a>&larr;맨앞</a></li>"))
				}
				
				// 이전 페이징 리스트로 가기
				if (paging.startPage > paging.pageCount) {
					pagingUl.append($("<li data-page='prev'><a>&laquo;이전</a></li>"))
				}
				
				// 페이징 리스트
				for (i = paging.startPage; i <= paging.endPage; i++) {
					if (paging.curPage == i) {
						pagingUl.append($("<li class='active'><a>" + i + "</a></li>"))
					} else {
						pagingUl.append($("<li data-page='page'><a>" + i + "</a></li>"))
					}
				}
				
				// 다음 페이징 리스트 생성
				if (paging.endPage != paging.totalPage) {
					pagingUl.append($("<li data-page='next'><a>다음&raquo;</a></li>"))
				}
				
				// 맨뒤
				if (paging.curPage != paging.totalPage) {
					pagingUl.append($("<li data-page='end'><a>맨뒤&rarr;</a></li>"))
				}
				
				// 본문에 넣기
				$(".container > #paging").html(pagingUl);
				
				// 이벤트 추가
				$("li[data-page='first']").on("click", function() {
					loadReply(1);
					curPage = 1;
				});
				$("li[data-page='prev']").on("click", function() {
					loadReply(paging.startPage - paging.pageCount);
					curPage = paging.startPage - paging.pageCount;
				});
				$("li[data-page='page']").on("click", function() {
					loadReply($(this).text());
					curPage = $(this).text();
				});
				$("li[data-page='next']").on("click", function() {
					loadReply(paging.startPage + paging.pageCount);
					curPage = paging.startPage + paging.pageCount;
				});
				$("li[data-page='end']").on("click", function() {
					loadReply(paging.totalPage);
					curPage = paging.totalPage;
				});
			}
			
		},
		error : function(e) {
			console.log(e);
		}
	});
	
}

$(document).ready(function () {
	loadReply(1);
	
	$("#cmtBtn").popover({"show" : 500, "hide" : 100});
	
	$("#cmtBtn").on("click", function() {
		var login = "${login}"
		if (login != "" && login) {
			$(this).popover('destroy');
			
			$("form").prepend("<input type=\"hidden\" name='boardno' value='${board.free_no}'></input>");
			
			$("form").submit();
		}
	});
	
});
</script>

<div class="container">
	<h2 class="text-center">자유게시판</h2>
	
	<table class="table table-bordered">
		<tr>
			<td class="info">제목</td>
			<td>
			<c:choose>
				<c:when test="${board.categoryno == 1}">
					[아이디어]
				</c:when>
				<c:when test="${board.categoryno == 2}">
					[정보]
				</c:when>
				<c:when test="${board.categoryno == 3}">
					[공모전]
				</c:when>
				<c:otherwise>
					[기타]
				</c:otherwise>
			</c:choose>
				${board.free_title }
			</td>
			<td class="info">작성자</td><td>${board.userno }</td>
		</tr>
		<tr>
			<td class="info">작성일</td><td colspan="1">${board.free_time }</td>
			<td class="info">조회수</td><td colspan="1">${board.views }</td>
		</tr>

		<tr>
			<td colspan="4" style="height: 500px;">${board.free_content }</td>
		</tr>
		<c:if test="${not empty file }">
			<tr>
				<td class="info">첨부파일</td>
				<td colspan="3"><a href="/file/download?fileno=${file.fileno }">${file.originName } (${file.fileSize })</a></td>
			</tr>
		</c:if>
	</table>
	<hr>
	<div class="row text-right">
		<button class="btn btn-info" onclick="location.href='/freeboard/list';">목록</button>
		<c:if test="${userno eq board.userno }">
			<button class="btn btn-success" onclick="location.href='/freeboard/update?free_no=${board.free_no}';">수정</button>
			<button class="btn btn-warning" onclick="location.href='/freeboard/delete?free_no=${board.free_no}';">삭제</button>
		</c:if>
	</div>
	
	<form action="/freeboard/reply/write" method="get">
		<textarea class="form-control reply-content" name="reply" required="required"></textarea>
		<button type="button" id="cmtBtn" class="btn btn-defualt btn-reply" data-container="body" data-placement="top" data-content="댓글을 작성하기 위해서는 로그인이 필요합니다.">작성</button>
	</form>	
	
	<hr>
	
	<div id="reply"></div>
	<div id="paging" class="text-center"></div>
	
</div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />