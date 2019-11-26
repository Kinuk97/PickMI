<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>


<script type="text/javascript">
$(document).ready(function() {
	var curPage = 1;
	
	$(window).scroll(function() {
	    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
	    	curPage += 1;
	    	$.ajax({
				type : "post",
				url : "/projectBoard/list",
				data : { "curPage" : curPage },
				dataType : "json",
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						
						var caption = $("<div class='caption'></div>");
						
						caption.append($("<p></p>").html($("<a></a>").text(data[i].prof_no)));
						caption.append($("<div class='text-right'></div>").text("작성일 : " + data[i].prof_time));
						
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
	<div class="col-lg-9 col-xs-12 text-right">
		<a class="btn btn-primary" href="/projectBoard/write">글작성</a>
	</div>
</div>

<hr>
<div id="board">
	<c:forEach var="board" items="${boardList }">
		<div class="col-sm-6 col-md-4 col-lg-3">
			<div class="thumbnail">
				<div class="caption">

					<h2>
						<a>${board.proj_title }</a>
					</h2>

					<p>프로젝트 이름 : ${board.proj_name }</p>
					<p>지역 : ${board.proj_loc }</p>
					<p>경력 : ${board.proj_career }</p>
					<p>참여 인원 : ${board.proj_member }</p>
					<p>시작날짜 : ${board.proj_sdate }</p>
					<p>마감날짜 : ${board.proj_ddate }</p>
					<p>모집기간 : ${board.proj_rec_date }</p>
					
					<div class="text-right">신청수 : ${board.proj_apply }</div>
					<div class="text-right">찜 개수 : ${board.proj_like }</div>
					<div class="text-right">작성시간 : ${board.proj_time }</div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<div style="clear: both;"></div>



<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />