<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<script type="text/javascript">
var curPage = 1;
var totalPage = "${paging.totalPage}";
var loading = false;

function compLoadList(){
	$.ajax({
		type : "post",
		url : "/compBoard/list",
		data : { "curPage" : curPage, "search" : "${paging.search}", "searchno" : "${paging.searchno}" },
		dataType : "json",
		success : function(data) {
			for (var i = 0; i < data.length; i++) {
				
				var caption = $("<div class='caption caption-comp' onclick=\"location.href='/compBoard/view?comp_no=" + data[i].comp_no + "'\"></div>");
				
				//게시글 제목
				caption.append($("<h4 class='overtext'></h4>").text("프로젝트 명 : " + data[i].comp_title));
				
				//팀 이름
				caption.append($("<div class='comp_name overtext'></div>").text("팀 이름 : " + data[i].comp_name));
				
				caption.append($("<br>"));
				caption.append($("<br>"));
				
				//찜한수				
				caption.append($("<div class='text-right'></div>").text(data[i].comp_like + "👍"));
				
				//조회수
				caption.append($("<div class='text-right'></div>").text("조회수 : " + data[i].comp_view));
				
				// 작성자
				caption.append($("<div class='text-right'></div>").text("작성자 : " + data[i].username));
				 
				
				//작성일
				caption.append($("<div class='text-right'></div>").text("작성날짜 : " + data[i].comp_date));
				
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


$(document).ready(function() {
	compLoadList();
	
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
	    	
	    	compLoadList();
        }
	});
});
</script>

<script type="text/javascript">
$(document).ready(function(){
	
	//로그인을 하지 않았는데 새로운 글 작성을 눌렀을 때
	$("#btnNoLoginWrite").click(function() {
		$(location).attr("href", "/login");
	});
});

</script>


<style type="text/css">
select {
	padding: 7px;
}

</style>

<div id="board" class="container list-container">
	<h1 class="text-center">😉 완성된 프로젝트 게시판 😉</h1>
	
	<br><br>
	
	<div class="row">
		<form action="/compBoard/list" method="get">
			<div style="width: 12%; float: left; margin-left: 21px;">
				<select name="searchno" style="height: 34px;">
					<option value="">선택없음</option>
					<option value="1">프로젝트명</option>
					<option value="2">팀 이름</option>
					<option value="3">프로젝트명&amp;내용</option>
				</select>
			</div>
			
				<div class="input-group" style="width: 20%; float: left;">
					<input type="text" class="form-control" name="search" placeholder="검색어를 입력해주세요:-)">
					<span class="input-group-btn">
					<button class="btn btn-default" type="submit" style='margin: 10px;'>검색</button>
					</span>
				</div>

		</form>
		
		<c:choose>
			<c:when test="${not empty login }">
				<div style="width: 65.3%; text-align: right; float: left;">
					<!-- 로그인이 되어있으면 글 작성으로 넘어감 -->
					<button  onclick="location.href = '/compBoard/write';" 
							 class="btn btn-info">&emsp;&emsp;새로운 게시글 작성&emsp;&emsp;</button>
				</div>
			</c:when>

			<c:otherwise>
				<div style="width: 65.3%; text-align: right; float: left;">	
					<!-- 로그인되어있지 않으면 글쓰기 페이지로 넘어가지 않음 -->
					<button id="btnNoLoginWrite" onclick="alert('로그인이 필요한 서비스입니다.')"
							class="btn btn-info">&emsp;&emsp;새로운 게시글 작성&emsp;&emsp;</button>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<br>
	<hr>
	<a id="top" href="#">TOP👆</a>
</div>

<div style="clear: both;"></div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />