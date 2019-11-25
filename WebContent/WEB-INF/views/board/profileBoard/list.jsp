<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>
<script type="text/javascript">
$(document).ready(function() {
	var curPage = 1;
	
	$(window).scroll(function() {
	    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
	    	curPage += 1;
	    	$.ajax({
				type : "post",
				url : "/profileBoard/list",
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
<div class="text-center">
<h1>나를 소개해보세요!😉</h1>
</div>
<hr>
<div class="text-right">
<a href="/profileBoard/write"><button class="btn btn-info">프로필 등록</button></a>
</div>
<br>
<c:forEach items="${ list }" var="pro">
	<div class="col-sm-6 col-md-4 col-lg-3">
		<div class="thumbnail">
			<div class="caption">
				<h4>${ pro.prof_no }</h4>
				<h3>${ pro.userno }</h3>
				<p>${ pro.prof_interest }</p>
				<p>${ pro.prof_loc }</p>
				<p>${ pro.prof_job }</p>
				<p>${ pro.prof_state }</p>
				<p>${ pro.prof_career }</p>
				<p class="text-right">
					<a href="#" class="btn btn-primary" role="button">${ pro.prof_like }❤찜하기</a> 
<!-- 					<a href="#" class="btn btn-default" role="button">Button</a> -->
				</p>
				<p>${ pro.prof_time }에 작성</p>
			</div>
		</div>
	</div>
</c:forEach>
<div style="clear: both;"></div>

	




<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />
