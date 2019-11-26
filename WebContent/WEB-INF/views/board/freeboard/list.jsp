<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	var curPage = 1;
	var totalPage = "${paging.totalPage}";
	
	$(window).scroll(function() {
		if (curPage >= totalPage) {
			return;
		}
		
		let $window = $(this);
        let scrollTop = $window.scrollTop();
        let windowHeight = $window.height();
        let documentHeight = $(document).height();
        
        // scrollbar의 thumb가 바닥 전 30px까지 도달 하면 리스트를 가져온다.
        if( scrollTop + windowHeight + 30 > documentHeight ) {
        		
	    	curPage += 1;
	    	$.ajax({
				type : "post",
				url : "/freeboard/list",
				data : { "curPage" : curPage, "search" : "${paging.search}", "categoryno" : "${paging.categoryno}" },
				dataType : "json",
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						
						var caption = $("<div class='caption'></div>");
						
						if (data[i].categoryno == 1) {
							caption.append($("<h2></h2>").html($("<a></a>").text("[아이디어] " + data[i].free_title)));
						} else if (data[i].categoryno == 2) {
							caption.append($("<h2></h2>").html($("<a></a>").text("[정보] " + data[i].free_title)));
						} else if (data[i].categoryno == 3) {
							caption.append($("<h2></h2>").html($("<a></a>").text("[공모전] " + data[i].free_title)));
						}
						
						caption.append($("<p></p>").html($("<a></a>").text(data[i].free_content)));
						caption.append($("<div class='text-right'></div>").text("조회수 : " + data[i].views));
						caption.append($("<div class='text-right'></div>").text("작성일 : " + data[i].free_time));
						
						var board = $("<div class='col-sm6 col-md-4 col-lg-3'></div>").append($("<div class='thumbnail'></div>").append(caption));
						
						$("#board").append(board);
					}					
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

<div class="row">
	<form action="/freeboard/list" method="get">
		<div class="col-lg-1 col-xs-3">
			<select name="categoryno">
				<option value="1">아이디어</option>
				<option value="2">정보</option>
				<option value="3">공모전</option>
			</select>
		</div>
		<div class="col-lg-2 col-xs-5">
			<div class="input-group">
				<input type="text" class="form-control" name="search" placeholder="Search for...">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button">검색</button>
				</span>
			</div>
		</div>
	</form>
	<div class="col-lg-9 col-xs-4 text-right">
		<a class="btn btn-primary" href="/freeboard/write">글작성</a>
	</div>
</div>

<hr>
<div id="board">
	<c:forEach var="board" items="${boardList }">
		<div class="col-sm-6 col-md-4 col-lg-3">
			<div class="thumbnail">
				<div class="caption">
					<c:choose>
						<c:when test="${board.categoryno == 1 }">
							<h2><a>[아이디어] ${board.free_title }</a></h2>
						</c:when>
						<c:when test="${board.categoryno == 2 }">
							<h2><a>[정보] ${board.free_title }</a></h2>
						</c:when>
						<c:when test="${board.categoryno == 3 }">
							<h2><a>[공모전] ${board.free_title }</a></h2>
						</c:when>
					</c:choose>
					<p><a>${board.free_content }</a></p>
					<div class="text-right">${board.views }</div>
					<div class="text-right">${board.free_time }</div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<div style="clear: both;"></div>
