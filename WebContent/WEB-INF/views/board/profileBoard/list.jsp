<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>
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
	    	loading = true;
	    	curPage += 1;
	    	$.ajax({
				type : "post",
				url : "/profileBoard/list",
				data : { "curPage" : curPage },
				dataType : "json",
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						
						var caption = $("<div class='caption caption-profile'></div>");
						
						caption.append($("<h4></h4>").text(data[i].prof_no));
						caption.append($("<h3></h3>").text(data[i].userno));
						caption.append($("<p></p>").text(data[i].prof_interest));
						caption.append($("<p></p>").text(data[i].prof_loc));
						caption.append($("<p></p>").text(data[i].prof_job));
						caption.append($("<p></p>").text(data[i].prof_state));
						caption.append($("<p></p>").text(data[i].prof_career));
						caption.append($("<p class='text-right'></p>").text(data[i].prof_like +"❤"));
						caption.append($("<p></p>").text(data[i].prof_time+"에 작성"));
					
						var board = $("<div class='col-sm6 col-md-4 col-lg-3'></div>").append($("<div class=\"thumbnail\" onclick=\"location.href='/profile/view?profileno="+data[i].prof_no +"'\"></div>").append(caption));
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

/* .thumbnail:hover { */
/* 	background: #D6F0FF; */
/* } */
a#top {
    position: fixed;
    right: 5%;
    bottom: 50px;
    z-index: 999;
    font-size: 20px;
}

</style>
<div class="text-center">
<h1>나를 소개해보세요!😉</h1>
</div>
<hr>
<c:if test="${ login }">
<div class="text-right" style="padding: 0 150px;">
<a href="/profileBoard/write"><button class="btn btn-info">프로필 등록</button></a>
</div>
</c:if>
<div id=filtersystem style="padding:0 150px;">
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
<br>
<br>
<a id="top" href="#">TOP👆</a>
<div id="board" class="container list-container">
	<c:forEach items="${ list }" var="pro">
			<div class="col-sm-6 col-md-4 col-lg-3">
				<div class="thumbnail" onclick="location.href='/profile/view?prof_no=${pro.prof_no }'">
					<div class="caption caption-profile">
						<h4>${ pro.prof_no }</h4>
						<h3>${ pro.userno }</h3>
						<p>${ pro.prof_interest }</p>
						<p>${ pro.prof_loc }</p>
						<p>${ pro.prof_job }</p>
						<p>${ pro.prof_state }</p>
						<p>${ pro.prof_career }</p>
						<p class="text-right">${ pro.prof_like }❤</p>
						<p>${ pro.prof_time }에 작성</p>
					</div>
				</div>
			</div>
	</c:forEach>
</div>
<div style="clear: both;"></div>







<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />
