<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>


<script type="text/javascript">
$(document).ready(function() {
	var curPage = 1;
	var loading = false;
	var totalPage = "${paging.totalPage}";
	
	$(window).scroll(function() {
		if (loading) {
			return;
		}
		if (curPage >= totalPage) {
			return;
		}
		
		if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
	    	loading = true;
	    	curPage += 1;
	    	$.ajax({
				type : "post",
				url : "/projectBoard/list",
				data : { "curPage" : curPage },
				dataType : "json",
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						
						var caption = $("<div class='caption caption-project' onclick=\"location.href='/projectBoard/view?proj_no="+data[i].proj_no+"'\"></div>");
						
						caption.append($("<h2></h2>").text(data[i].proj_title));
						caption.append($("<p></p>").text("프로젝트 이름 : " + data[i].proj_name));
						caption.append($("<p></p>").text("지역 : " + data[i].proj_loc));
						caption.append($("<p></p>").text("경력 : " + data[i].proj_career));
						caption.append($("<p></p>").text("참여 인원 : " + data[i].proj_member));
						caption.append($("<p></p>").text("시작 날짜 : " + data[i].proj_sdate));
						caption.append($("<p></p>").text("마감 날짜 : " + data[i].proj_ddate));
						caption.append($("<p></p>").text("모집 기간 : " + data[i].proj_rec_date));
						caption.append($("<p class='text-right'></p>").text("신청수 : "+data[i].proj_apply));
						caption.append($("<p class='text-right'></p>").text("찜개수 : "+data[i].proj_like));
						caption.append($("<p class='text-right'></p>").text("작성시간 : "+data[i].proj_time));
						
					
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

#filterBtn {
  background-color: #66CCFF;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
}

#filter {
  position: relative;
  display: inline-block;
}

#filter-list {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

#filter-list a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

#filter-list a:hover {background-color: #ddd;}
#filter:hover #filter-list {display: block;}
#filter:hover #filterBtn {background-color: #CEE3F6;}


</style>


<div id="board" class="container list-container">
	<h1 class="text-center">😉프로젝트게시판😉</h1>
	<div id="filtersystem">
	<div id="filter">
	<button class="btn btn-info" id="filterBtn">관심</button>
	<div id="filter-list">
		<a href="#">java</a>
		<a href="#">algorithm</a>
		<a href="#">html/css</a>
		<a href="#">design</a>
	</div>
	</div>
	<button class="btn btn-info" id="filterBtn">지역</button>
	<div id="filter-list">
		<a href="#">서울</a>
		<a href="#">경기</a>
		<a href="#">그외</a>
	</div>
	<button class="btn btn-info" id="filterBtn">직업</button>
	<div id="filter-list">
		<a href="#">개발자</a>
		<a href="#">프리랜서</a>
		<a href="#">디자이너</a>
		<a href="#">무직</a>
	</div>
	<button class="btn btn-info" id="filterBtn">상태</button>
	<div id="filter-list">
		<a href="#">구직중</a>
		<a href="#">재직중</a>
		<a href="#">프리랜서</a>
	</div>
	<button class="btn btn-info" id="filterBtn">경력</button>
	<div id="filter-list">
		<a href="#">1-2년차</a>
		<a href="#">3-4년차</a>
		<a href="#">5-7년차</a>
		<a href="#">8년차이상</a>
	</div>
</div>
	<div class="text-right" style="padding: 0 40px;">
			<a class="btn btn-primary" href="/projectBoard/write">글작성</a>
	</div>
	<hr>
	<c:forEach var="board" items="${boardList }">
		<div class="col-sm-6 col-md-4 col-lg-3">
			<div class="thumbnail" onclick="location.href='/projectBoard/view?proj_no=${board.proj_no }'">
				<div class="caption caption-project">

					<h2>
						${board.proj_title }
					</h2>

					<p>프로젝트 이름 : ${board.proj_name }</p>
					<p>지역 : ${board.proj_loc }</p>
					<p>경력 : ${board.proj_career }</p>
					<p>참여 인원 : ${board.proj_member }</p>
					<p>시작 날짜 : ${board.proj_sdate }</p>
					<p>마감 날짜 : ${board.proj_ddate }</p>
					<p>모집 기간 : ${board.proj_rec_date }</p>
					
					<div class="text-right">신청수 : ${board.proj_apply }</div>
					<div class="text-right">찜개수 : ${board.proj_like }</div>
					<div class="text-right">작성시간 : ${board.proj_time }</div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<div style="clear: both;"></div>



<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />