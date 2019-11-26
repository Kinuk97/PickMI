<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"></jsp:include>

<script type="text/javascript">
$(document).ready(function() {
	var curPage = 1;
	var totalPage = "${paging.totalPage}";
	var loading = false;
	
	$(window).scroll(function() {
		if (loading) {
			return;
		}
		if (curPage >= totalPage) {
			return;
		}
        
        if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
	    	curPage += 1;
	    	loading = true;
	    	
	    	$.ajax({
				type : "post",
				url : "/freeboard/list",
				data : { "curPage" : curPage, "search" : "${paging.search}", "categoryno" : "${paging.categoryno}" },
				dataType : "json",
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						
						var caption = $("<div class='caption'></div>");
						
						if (data[i].categoryno == 1) {
							caption.append($("<h2></h2>").html($("<a href='/freeboard/view?free_no=" + data[i].free_no + "'></a>").text("[아이디어] " + data[i].free_title)));
						} else if (data[i].categoryno == 2) {
							caption.append($("<h2></h2>").html($("<a href='/freeboard/view?free_no=" + data[i].free_no + "'></a>").text("[정보] " + data[i].free_title)));
						} else if (data[i].categoryno == 3) {
							caption.append($("<h2></h2>").html($("<a href='/freeboard/view?free_no=" + data[i].free_no + "'></a>").text("[공모전] " + data[i].free_title)));
						}
						
						caption.append($("<p></p>").html($("<a href='/freeboard/view?free_no=" + data[i].free_no + "'></a>").text(data[i].free_content)));
						caption.append($("<div class='text-right'></div>").text("조회수 : " + data[i].views));
						caption.append($("<div class='text-right'></div>").text("작성일 : " + data[i].free_time));
						
						var board = $("<div class='col-sm6 col-md-4 col-lg-3'></div>").append($("<div class='thumbnail'></div>").append(caption));
						
						$("#board").append(board);
					}			
					
					loading = false;
				},
				error : function(e) {
					console.log(e);
				}
			});
        }
	});
});
</script>


<style type="text/css">
select {
	padding: 7px;
}
</style>

<div id="board" class="container list-container">
	<div class="row">
		<form action="/freeboard/list" method="get">
			<div class="col-lg-1 col-xs-2">
				<select name="categoryno">
					<option value="">선택없음</option>
					<option value="1">아이디어</option>
					<option value="2">정보</option>
					<option value="3">공모전</option>
				</select>
			</div>
			<div class="col-lg-5 col-xs-5 text-left">
				<div class="input-group">
					<input type="text" class="form-control" name="search" placeholder="Search for...">
					<span class="input-group-btn">
						<button class="btn btn-default" type="submit" style="margin: 10px;">검색</button>
					</span>
				</div>
			</div>
		</form>
		<div class="col-lg-6 col-xs-4 text-right">
			<a class="btn btn-primary" href="/freeboard/write">글작성</a>
		</div>
	</div>
	<hr>
	<c:forEach var="board" items="${boardList }">
		<div class="col-sm-6 col-md-4 col-lg-3">
			<div class="thumbnail">
					<c:choose>
						<c:when test="${board.categoryno == 1 }">
							<h5><a href="/freeboard/view?free_no=${board.free_no }">[아이디어] ${board.free_title }</a></h5>
						</c:when>
						<c:when test="${board.categoryno == 2 }">
							<h2><a href="/freeboard/view?free_no=${board.free_no }">[정보] ${board.free_title }</a></h2>
						</c:when>
						<c:when test="${board.categoryno == 3 }">
							<h4><a href="/freeboard/view?free_no=${board.free_no }">[공모전] ${board.free_title }</a></h4>
						</c:when>
					</c:choose>
					<p class="content"><a href="/freeboard/view?free_no=${board.free_no }">${board.free_content }</a></p>
					<div class="text-right">${board.views }</div>
					<div class="text-right">${board.free_time }</div>
			</div>
		</div>
	</c:forEach>
</div>
<div style="clear: both;"></div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>
