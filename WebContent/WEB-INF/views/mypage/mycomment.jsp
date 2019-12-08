<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<script type="text/javascript">

function loadReply(curPage) {
	$.ajax({
		type : "get",
		url : "/mycomment/list",
		data : { "curPage" : curPage, "replyno" : "${reply.replyno}" },
		dataType : "json",
		success : function(data) {
			var paging = data.paging;
			var replyList = data.replyList;
			
			$("#reply").html("");

			
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
			
		

</script>



<style type="text/css">
.innercon1{

   text-align: center;
   margin-bottom: 5%;
   
}

.table{

	width:80%;
	text-align: start;
	margin-left: 13%;

}


</style>

<div class="container">

	<div class="innercon1">
		<h2> 😉${name }님이 작성한 댓글😉 </h2>
	</div>
	
	<div class="innercon2">
		<table class="table table-hover">
			<thead>
			<tr>
				<th style="width: 50%">댓글내용</th>
				<th style="width: 10%">작성일</th>
			</tr>
			</thead>
			
			<tbody>			
			<c:forEach items="${complist }" var="comp">
			<tr>
				<td><a href="/compBoard/view?comp_no=${comp.boardno}">${comp.reply}</a></td>
				<td>${comp.replytime}</td>
			</tr>
			</c:forEach>	
			
			<c:forEach items="${freelist }" var="free">
			<tr>
				<td><a href="/freeboard/view?free_no=${free.boardno}">${free.reply }</a></td>
				<td>${free.replytime}</td>
			</tr>
			</c:forEach>	
			</tbody>
			
		</table>
	</div>

			
</div> <!-- container -->


<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />