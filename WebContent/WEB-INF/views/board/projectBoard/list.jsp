<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>

    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>


<script type="text/javascript">
	
	var curPage = 1;
	var loading = false;
	var totalPage = "${paging.totalPage}";
	
	function projectList(){
	    	$.ajax({
				type : "post",
				url : "/projectBoard/list",
				data : { "curPage" : curPage, "proj_loc" : "${paging.proj_loc}", "proj_progress" : "${paging.proj_progress}",
					"proj_job" : "${paging.proj_job}", "proj_career" : "${paging.proj_career}" }, 
				dataType : "json",
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						
						var caption = $("<div class='caption caption-project' onclick=\"location.href='/projectBoard/view?proj_no="+data[i].proj_no+"'\"></div>");
						
						caption.append($("<h2></h2>").text(data[i].proj_title));
						caption.append($("<p></p>").text("지역 : " + data[i].proj_loc));
						caption.append($("<p></p>").text("경력 : " + data[i].proj_career));
						caption.append($("<p></p>").text("직업 : " + data[i].proj_job));
						caption.append($("<p></p>").text("진행상황 : " + data[i].proj_progress));
						
						caption.append($("<p class='text-right'></p>").text("신청수 : "+data[i].proj_apply));
						caption.append($("<p class='text-right'></p>").text("찜개수 : "+data[i].proj_like));
						caption.append($("<p class='text-right'></p>").text(data[i].proj_time));
						
					
						var board = $("<div class='col-sm6 col-md-4 col-lg-3'></div>").append($("<div class='thumbnail'></div>").append(caption));
						
						$("#board").append(board);
					}	
					
					loading = false;
				},
				error : function(e) {
					console.log(e);
				}
			});
	} // project end
	
	$(document).ready(function() {
		
		projectList();
		var proj_loc = "${paging.proj_loc}";
		var proj_progress = "${paging.proj_progress}";
		var proj_job = "${paging.proj_job}";
		var proj_career = "${paging.proj_career}";
		
		if(proj_loc != null && proj_loc != ""){
			$("#locBtn").addClass("ative");
		} else if(proj_progress != null && proj_progress != ""){
			$("#progressBtn").addClass("active");
		} else if(proj_job != null && proj_job != ""){
			$("#jobBtn").addClass("active");
		} else if(proj_career != null && proj_career != ""){
			$("#careerBtn").addClass("active");
		}
		
		$("#locBtn").on("click",function(){
			location.href = "/projectBoard/list";
		});
		$("#progressBtn").on("click",function(){
			location.href = "/projectBoard/list";
		});
		$("#jobBtn").on("click",function(){
			location.href = "/projectBoard/list";
		});
		$("#careerBtn").on("click",function(){
			location.href = "/projectBoard/list";
		});
		
		
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
		    	projectList();
			}
		}); // scroll end
	
}); // document end
</script>

<script type="text/javascript">
$(document).ready(function(){
	
	//경고 모달 호출 메서드
	 function warningModal(content) {
	    $(".modal-contents").text(content);
	    $("#defaultModal").modal('show');
	   }
	
	//로그인을 하지 않았는데 새로운 글 작성을 눌렀을 때
	$("#btnNoLogWrite").click(function() {
		warningModal("로그인이 필요합니다.");
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


</style>


<div id="board" class="container list-container">
	<h1 class="text-center">😉프로젝트게시판😉</h1>
	
<form action="/projectBoard/list" method="get">

<input type="hidden" value="${paging.proj_loc }" name="proj_loc"/>
<input type="hidden" value="${paging.proj_progress }" name="proj_progress"/>
<input type="hidden" value="${paging.proj_job }" name="proj_job"/>
<input type="hidden" value="${paging.proj_career }" name="proj_career"/>

	<div id="filtersystem">
	<div id="filter">
	<button class="btn btn-info" id="locBtn">지역</button>
	<div id="filter-list">
		<a href="/projectBoard/list?proj_loc=서울">서울</a>
		<a href="/projectBoard/list?proj_loc=인천">인천</a>
		<a href="/projectBoard/list?proj_loc=경기">경기</a>
		<a href="/projectBoard/list?proj_loc=강원">강원</a>
		<a href="/projectBoard/list?proj_loc=충청">충청</a>
		<a href="/projectBoard/list?proj_loc=경상">경상</a>
		<a href="/projectBoard/list?proj_loc=전라">전라</a>
	</div>
	</div>
	
	<div id="filter2">
	<button class="btn btn-info" id="progressBtn">진행상황</button>
	<div id="filter-list">
		<a href="/projectBoard/list?proj_progress=설계단계">설계단계</a>
		<a href="/projectBoard/list?proj_progress=구현단계">구현단계</a>
	</div>
	</div>
	
	<div id="filter3">
	<button class="btn btn-info" id="jobBtn">직업</button>
	<div id="filter-list">
		<a href="/projectBoard/list?proj_job=개발자">개발자</a>
		<a href="/projectBoard/list?proj_job=프리랜서">프리랜서</a>
		<a href="/projectBoard/list?proj_job=디자이너">디자이너</a>
		<a href="/projectBoard/list?proj_job=무직">무직</a>
	</div>
	</div>
	
	<div id="filter3">
	<button class="btn btn-info" id="careerBtn">경력</button>
	<div id="filter-list">
		<a href="/projectBoard/list?proj_career=1년차">1년차</a>
		<a href="/projectBoard/list?proj_career=3년차">3년차</a>
		<a href="/projectBoard/list?proj_career=5년차">5년차</a>
		<a href="/projectBoard/list?proj_career=7년차">7년차</a>
		<a href="/projectBoard/list?proj_career=8년차이상">8년차이상</a>
	</div>
	</div>

<c:choose>
	<c:when test="${not empty login }">
		<button  type= "button" onclick="location.href = '/projectBoard/write';" class="btn btn-info" style="float:right; margin-top:30px; margin-right:15px;">&emsp;&emsp;새로운 게시글 작성&emsp;&emsp;</button>
	</c:when>
	
	<c:otherwise>
		<button id="btnNoLogWrite" type="button" class="btn btn-info" style="float:right; margin-top:30px; margin-right:15px;">&emsp;&emsp;새로운 게시글 작성&emsp;&emsp;</button>
	</c:otherwise>
</c:choose>
</div>
</form>
	
<br>	
<hr>
<%-- 	<c:forEach var="board" items="${boardList }"> --%>
<!-- 		<div class="col-sm-6 col-md-4 col-lg-3"> -->
<%-- 			<div class="thumbnail" onclick="location.href='/projectBoard/view?proj_no=${board.proj_no }'"> --%>
<!-- 				<div class="caption caption-project"> -->
<!-- 					<h2> -->
<%-- 						${board.proj_title } --%>
<!-- 					</h2> -->

<%-- 					<p>지역 : ${board.proj_loc }</p> --%>
<%-- 					<p>경력 : ${board.proj_career }</p> --%>
<%-- 					<p>직업 : ${board.proj_job }</p> --%>
<%-- 					<p>진행상황 : ${board.proj_progress }</p> --%>
					
<%-- 					<div class="text-right">신청수 : ${board.proj_apply }</div> --%>
<%-- 					<div class="text-right">찜개수 : ${board.proj_like }</div> --%>
<%-- 					<div class="text-right">${board.proj_time }</div> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<%-- 	</c:forEach> --%>
</div>
<div style="clear: both;"></div>

<!-- 경고 모달창 -->
            <div class="modal fade" id="defaultModal">
               <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header panel-heading">
                            <h4 class="modal-title">알림</h4>
                        </div>
                        <div class="modal-body">
                            <p class="modal-contents"></p>
                        </div>
                        <div class="modal-footer">
                           <button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
       
            <!--// 경고 모달창 -->


<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />