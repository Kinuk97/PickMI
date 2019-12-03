<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>
<script type="text/javascript">
$(document).ready(function() {
	
	//필터 
	profileList();
	var interestno = "${paging.interestno}";
	var locationno = "${paging.locationno}";
	var jobno = "${paging.jobno}";
	var stateno = "${paging.stateno}";
	var careerno = "${paging.careerno}";

	if (interestno != "0") {
		$("#interestBtn").addClass("active");
	} else if (locationno != "0") {
		$("#locationBtn").addClass("active");
	} else if (jobno != "0") {
		$("#jobBtn").addClass("active");
	} else if (stateno != "0") {
		$("#stateBtn").addClass("active");
	} else if (careerno != "0"){
		$("#careerBtn").addClass("active");
	} else {
	}

	$("#interestBtn").on("click", function() {
		location.href= "/profileBoard/list";
	})
	$("#locationBtn").on("click",function() {
		location.href= "/profileBoard/list";
	})
	$("#jobBtn").on("click",function() {
		location.href= "/profileBoard/list";
	})
	$("#stateBtn").on("click",function() {
		location.href= "/profileBoard/list";
	})
	$("#careerBtn").on("click",function() {
		location.href= "/profileBoard/list";
	})
	

	
	
	
	
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
    	profileList();
    	
	}
});	

function profileList() {
  	$.ajax({
		type : "post",
		url : "/profileBoard/list",
		data : { "curPage" : curPage, "interestno" : "${paging.interestno}", "locationno" : "${paging.locationno}", "jobno" : "${paging.jobno}", "stateno" : "${paging.stateno}", "careerno" : "${paging.careerno}" },
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
};


	
$("#nologin").click( function loginplz(){
	alert("로그인 해주세요");
 	$(location).attr("href","/login");
	});
$("#write").click( function loginplz(){
 	alert("로그인 해주세요");
 		$(location).attr("href","/login");
	});
	
	
}); //document end


</script>
<style type="text/css">
#filterBtn {
  background-color: #A9D0F5;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
}

#interest {
  position: relative;
  display: inline-block;
}

#location {
  position: relative;
  display: inline-block;
}

#job {
  position: relative;
  display: inline-block;
}

#state {
  position: relative;
  display: inline-block;
}

#career {
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
#interest:hover #filter-list {display: block;}
#interest:hover #filterBtn {background-color: #CEE3F6;}

#location:hover #filter-list {display: block;}
#location:hover #filterBtn {background-color: #CEE3F6;}

#job:hover #filter-list {display: block;}
#job:hover #filterBtn {background-color: #CEE3F6;}

#state:hover #filter-list {display: block;}
#state:hover #filterBtn {background-color: #CEE3F6;}

#career:hover #filter-list {display: block;}
#career:hover #filterBtn {background-color: #CEE3F6;}


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

<h1 align="center"> 나를 소개해보세요❣❣😄 </h1>
<c:if test="${ login }">
	<div class="text-right" style="padding: 0 120px; margin-right: 50px;">
		<a href="/profileBoard/write"><button class="btn btn-info">프로필 등록</button></a>
	</div>
</c:if>
<c:if test="${ !login }">
	<div class="text-right" style="padding: 0 120px; margin-right: 50px;">
		<button class="btn btn-info" id="write">프로필 등록</button>
	</div>
</c:if>

<form action="/profileBoard/list" method="get">
<input type="hidden" value="${paging.interestno }" name="interestno">
<input type="hidden" value="${paging.locationno }" name="locationno">
<input type="hidden" value="${paging.jobno }" name="jobno">
<input type="hidden" value="${paging.stateno }" name="stateno">
<input type="hidden" value="${paging.careerno }" name="careerno">

<div id=filtersystem style="padding:0 100px; margin-left: 60px;">
	<div id="interest">
		<button class="btn btn-info" id="interestBtn">관심</button>
		<div id="filter-list">
			<a href="/profileBoard/list?interestno=1" id="interest1">개발</a>
			<a href="/profileBoard/list?interestno=2" id="interest2">디자인</a>
			<a href="/profileBoard/list?interestno=3" id="interest3">스타트업</a>
			<a href="/profileBoard/list?interestno=4" id="interest4">IT언어</a>
		</div>
	</div>
	<div id="location">
		<button class="btn btn-info" id="locationBtn">지역</button>
		<div id="filter-list">
			<a href="/profileBoard/list?locationno=1" id="location1">서울</a>
			<a href="/profileBoard/list?locationno=2" id="location2">경기</a>
			<a href="/profileBoard/list?locationno=3" id="location3">그외</a>
		</div>
	</div>
	<div id="job">
		<button class="btn btn-info" id="jobBtn">직업</button>
		<div id="filter-list">
			<a href="/profileBoard/list?jobno=1" id="job1">개발자</a>
			<a href="/profileBoard/list?jobno=2" id="job2">프리랜서</a>
			<a href="/profileBoard/list?jobno=3" id="job3">디자이너</a>
			<a href="/profileBoard/list?jobno=4" id="job4">무직</a>
		</div>
	</div>
	<div id="state">
		<button class="btn btn-info" id="stateBtn">상태</button>
		<div id="filter-list">
			<a href="/profileBoard/list?stateno=1" id="state1">구직중</a>
			<a href="/profileBoard/list?stateno=2" id="state2">재직중</a>
			<a href="/profileBoard/list?stateno=3" id="state3">프리랜서</a>
		</div>
	</div>
	<div id="career">
		<button class="btn btn-info" id="careerBtn">경력</button>
		<div id="filter-list">
			<a href="/profileBoard/list?careerno=1" id="career1">1-2년차</a>
			<a href="/profileBoard/list?careerno=2" id="career2">3-4년차</a>
			<a href="/profileBoard/list?careerno=3" id="career3">5-7년차</a>
			<a href="/profileBoard/list?careerno=4" id="career4">8년차이상</a>
		</div>
	</div>
</div>
</form>
<br>

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
