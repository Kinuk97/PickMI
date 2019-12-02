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
						caption.append($("<p></p>").text(data[i].username));
						caption.append($("<p></p>").text(data[i].prof_interest));
						caption.append($("<p></p>").text(data[i].prof_loc));
						caption.append($("<p></p>").text(data[i].prof_job));
						caption.append($("<p></p>").text(data[i].prof_state));
						caption.append($("<p></p>").text(data[i].prof_career));
						caption.append($("<p class='text-right'></p>").text(data[i].prof_like +"❤"));
						caption.append($("<p></p>").text(data[i].prof_time+"에 작성"));
			
					
						var board = $("<div class='col-sm6 col-md-4 col-lg-3'></div>").append($("<div class=\"thumbnail\" onclick=\"location.href='/profileBoard/view?profileno="+data[i].prof_no +"'\"></div>").append(caption));
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
	
$("#nologin").click( function loginplz(){
	alert("로그인 해주세요");
 	$(location).attr("href","/login");
	});
$("#write").click( function loginplz(){
 	alert("로그인 해주세요");
 		$(location).attr("href","/login");
	});
});


</script>
<style type="text/css">
#filterBtn {
  background-color: #A9D0F5;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
}

#filter {
  position: relative;
  display: inline-block;
}

#filter2 {
  position: relative;
  display: inline-block;
}

#filter3 {
  position: relative;
  display: inline-block;
}

#filter4 {
  position: relative;
  display: inline-block;
}

#filter5 {
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

#filter2:hover #filter-list {display: block;}
#filter2:hover #filterBtn {background-color: #CEE3F6;}

#filter3:hover #filter-list {display: block;}
#filter3:hover #filterBtn {background-color: #CEE3F6;}

#filter4:hover #filter-list {display: block;}
#filter4:hover #filterBtn {background-color: #CEE3F6;}

#filter5:hover #filter-list {display: block;}
#filter5:hover #filterBtn {background-color: #CEE3F6;}


.thumbnail:hover { 
	background: #EFF8FB;
}
a#top {
    position: fixed;
    right: 5%;
    bottom: 50px;
    z-index: 999;
    font-size: 20px;
}

</style>

<div class="text-center">
	<h1>나를 소개해보세요!😉<small>프로필 게시판</small></h1>
</div>


<div id="filtersystem" style="padding:0 150px; margin-left: 60px; padding-right: 213px;">
	<div id="filter">
		<button class="btn btn-info" id="filterBtn">관심</button>
			<div id="filter-list">
				<a href="#">java</a>
				<a href="#">algorithm</a>
				<a href="#">html/css</a>
				<a href="#">design</a>
			</div>
	</div>
	
	<div id="filter">
		<button class="btn btn-info" id="filterBtn">지역</button>
			<div id="filter-list">
				<a href="#">서울</a>
				<a href="#">경기</a>
				<a href="#">그외</a>
			</div>
	</div>
	
	<div id="filter">
		<button class="btn btn-info" id="filterBtn">직업</button>
			<div id="filter-list">
				<a href="#">개발자</a>
				<a href="#">프리랜서</a>
				<a href="#">디자이너</a>
				<a href="#">무직</a>
			</div>
	</div>
	
	<div id="filter">
		<button class="btn btn-info" id="filterBtn">상태</button>
			<div id="filter-list">
				<a href="#">구직중</a>
				<a href="#">재직중</a>
				<a href="#">프리랜서</a>
			</div>	
	</div>
	
	<div id="filter">
		<button class="btn btn-info" id="filterBtn">경력</button>
			<div id="filter-list">
				<a href="#">1-2년차</a>
				<a href="#">3-4년차</a>
				<a href="#">5-7년차</a>
				<a href="#">8년차이상</a>
			</div>
	</div>
			
		<c:if test="${ login }">
<!-- 		<div class="text-right" style="padding: 0 150px; margin-right: 64px;"> -->
			<a href="/profileBoard/write"><button class="btn btn-info" style="float: right; margin-top: 30px;">&emsp;&emsp;새로운 프로필 등록&emsp;&emsp;</button></a>
<!-- 		</div> -->
		</c:if>
</div>

<br>
<hr>

<c:if test="${ login }">
<div class="text-right" style="padding: 0 120px; margin-right: 50px;">
<a href="/profileBoard/write"><button class="btn btn-info">프로필 등록</button></a>
</div>
</c:if>
<c:if test="${ !login }">
<div class="text-right" style="padding: 0 120px; margin-right: 50px;">
<a href="/login"><button class="btn btn-info" id="write">프로필 등록</button></a>
</div>
</c:if>

<div id=filtersystem style="padding:0 100px; margin-left: 60px;">
	<div id="filter">
		<button class="btn btn-info" id="filterBtn">관심</button>
		<div id="filter-list">
			<a href="#">개발</a>
			<a href="#">디자인</a>
			<a href="#">스타트업</a>
			<a href="#">IT언어</a>
		</div>
	</div>
	<div id="filter2">
		<button class="btn btn-info" id="filterBtn">지역</button>
		<div id="filter-list">
			<a href="#">서울</a>
			<a href="#">경기</a>
			<a href="#">그외</a>
		</div>
	</div>
	<div id="filter3">
		<button class="btn btn-info" id="filterBtn">직업</button>
		<div id="filter-list">
			<a href="#">개발자</a>
			<a href="#">프리랜서</a>
			<a href="#">디자이너</a>
			<a href="#">무직</a>
		</div>
	</div>
	<div id="filter4">
		<button class="btn btn-info" id="filterBtn">상태</button>
		<div id="filter-list">
			<a href="#">구직중</a>
			<a href="#">재직중</a>
			<a href="#">프리랜서</a>
		</div>
	</div>
	<div id="filter5">
		<button class="btn btn-info" id="filterBtn">경력</button>
		<div id="filter-list">
			<a href="#">1-2년차</a>
			<a href="#">3-4년차</a>
			<a href="#">5-7년차</a>
			<a href="#">8년차이상</a>
		</div>
	</div>
</div>
<br>
<br>
>>>>>>> refs/heads/dongs
<a id="top" href="#">TOP👆</a>
<div id="board" class="container list-container">
	<c:forEach items="${ list }" var="pro">
		<div class="col-sm-6 col-md-4 col-lg-3">
				<div class="thumbnail" onclick="location.href='/profileBoard/view?prof_no=${pro.prof_no }'">
					<div class="caption caption-profile">
						<h4>${ pro.prof_no }</h4>
						<p>${ pro.username }</p>
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
