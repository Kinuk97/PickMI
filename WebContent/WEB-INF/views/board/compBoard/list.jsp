<%@page import="dto.CompBoard" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
		data : { "curPage" : curPage, "search" : "${paging.search}", "categoryno" : "${paging.categoryno}" },
		dataType : "json",
		success : function(data) {
			for (var i = 0; i < data.length; i++) {
				
				var caption = $("<div class='caption caption-comp'></div>");
				
				if (data[i].categoryno == 1) {
// 					caption.append($("<h2></h2>").html($("<a href='/compBoard/view?comp_no=" + data[i].comp_no + "'></a>").text("[작성자] " + data[i].comp_title)));
				} else if (data[i].categoryno == 2) {
// 					caption.append($("<h2></h2>").html($("<a href='/compBoard/view?comp_no=" + data[i].comp_no + "'></a>").text("[제목] " + data[i].comp_title)));
				} else if (data[i].categoryno == 3) {
// 					caption.append($("<h2></h2>").html($("<a href='/compBoard/view?comp_no=" + data[i].comp_no + "'></a>").text("[제목 + 내용] " + data[i].comp_title)));
				}
				
				//게시글 제목
				caption.append($("<h4 class='overtext'></h4>").text(data[i].comp_no + ". " + data[i].comp_title));
				
				//태그 제거하기
				var content = data[i].comp_content;
				
				//<br>포함하기
				content = content.replace(/<br\/>/ig, "\n");
				
				//태그 제거
				content = content.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "");
				
				//팀 이름
				caption.append($("<div class='comp_name overtext'></div>").text(data[i].comp_name));
				
				caption.append($("<br>"));
				caption.append($("<br>"));
				
				// 작성자
				caption.append($("<div class='text-right'></div>").text(data[i].userno));
				
				//조회수
				caption.append($("<div class='text-right'></div>").text(data[i].comp_view));
				
				//찜한수				
				caption.append($("<div class='text-right'></div>").text(data[i].comp_like));
				
				//작성일
				caption.append($("<div class='text-right'></div>").text(data[i].comp_date));
				
				var comp_no = data[i].comp_no;
				
				caption.on("click", function(){
					location.href="/compBoard/view?comp_no=" + comp_no;
				});
				
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

<style type="text/css">
select {
	padding: 7px;
}
</style>

<div class="container list-container">
	<h1 class="text-center">완성된 프로젝트 게시판</h1>
	
	<br><br>
	
	<div class="row">
		<form action="/compBoard/list" method="get">
			<div style="width: 10%; float: left; margin-left: 10px;">
				<select name="categoryno">
					<option value="">선택없음</option>
					<option value="1">작성자</option>
					<option value="2">제목</option>
					<option value="3">제목 + 내용</option>
				</select>
			</div>
			
				<div class="input-group" style="width: 20%; float: left;">
					<input type="text" class="form-control" name="search" placeholder="Search for...">
					<span class="input-group-btn">
					<button class="btn btn-default" type="submit" style='margin: 10px;'>검색</button>
					</span>
				</div>
		
			<div class="text-right">
				<a href="/compBoard/write" class="btn btn-info" style="margin-top: 10px;">&emsp;&emsp;새로운 글 작성&emsp;&emsp;</a>
			</div>

		</form>
	</div>
	
	<br>
	
	<!-- 상위 3개 -->
<%-- <c:forEach items="${compList }" var="compList"> --%>
<!-- 		<div class="col-sm-6 col-md-4 col-lg-4"> -->
<!-- 			<div class="thumbnail"> -->
<!-- 				<div class="caption"> -->
<!-- 					<h3>상위 3개 넣을 것 - 찜한 수</h3> -->
<!-- 					<p>...</p> -->
<!-- 					<p> -->
<%-- 						<a href="/compBoard/view?comp_no=${compList.comp_no }" class="btn btn-default" role="button">Button</a> --%>
<!-- 					</p> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<%-- </c:forEach> --%>
<!-- 	<br> -->
<!-- 	<br> -->
<!-- 	<br> -->
	
<!-- 두번째 줄 -->
<!-- <div id="board"> -->
<%-- 	<c:forEach items="${compList }" var="compList"> --%>
<!-- 			<div class="col-sm-6 col-md-4 col-lg-3"> -->
<%-- 				<div class="thumbnail" onclick="location.href='/compBoard/view?comp_no=${compList.comp_no }'"  --%>
<!-- 					 id="compboardlist" style="cursor:pointer; hover: #ccc;"> -->
<!-- 					<div class="caption caption-comp"> -->
<%-- 	<%-- 					<input type="checkbox" name="checkRow" id="checkRow" value="${compList.comp_no }"> --%>
<%-- 						<h4>${compList.comp_no}. ${compList.comp_title }</h4> --%>
<%-- 						<p>팀 이름 : ${compList.comp_name }</p> --%>
<!-- 						<br><br> -->
<%-- 						<p class="text-right" style="margin: 0 0 0px;">작성자 : ${compList.userno }</p> --%>
<%-- 						<p class="text-right" style="margin: 0 0 0px;">조회수 : ${compList.comp_view }</p> --%>
<%-- 						<p class="text-right" style="margin: 0 0 0px;">찜한수 : ${compList.comp_like }</p> --%>
<%-- 						<p class="text-right" style="margin: 0 0 0px;">작성날짜 : ${compList.comp_date }</p> --%>
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<%-- 	</c:forEach> --%>
	
<!-- </div> -->


</div>

<div style="clear: both;"></div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />