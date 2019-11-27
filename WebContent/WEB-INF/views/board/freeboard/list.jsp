<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"></jsp:include>

<script type="text/javascript">
var curPage = 1;	
var totalPage = "${paging.totalPage}";
var loading = false;

function loadList() {
	$.ajax({
		type : "post",
		url : "/freeboard/list",
		data : { "curPage" : curPage, "search" : "${paging.search}", "categoryno" : "${paging.categoryno}" },
		dataType : "json",
		success : function(data) {
			
			for (var i = 0; i < data.length; i++) {
				var caption = $("<div class='caption caption-free'></div>");
				
				// 카테고리 추가
				if (data[i].categoryno == 1) {
					caption.append($("<a href=\"/freeboard/list?categoryno=" + data[i].categoryno + "\"></a>").text("[아이디어]"));
				} else if (data[i].categoryno == 2) {
					caption.append($("<a href=\"/freeboard/list?categoryno=" + data[i].categoryno + "\"></a>").text("[정보]"));
				} else if (data[i].categoryno == 3) {
					caption.append($("<a href=\"/freeboard/list?categoryno=" + data[i].categoryno + "\"></a>").text("[공모전]"));
				}
				
				// 제목
				caption.append($("<h4 class='overtext'></h4>").text(data[i].free_title));

				// 태그 제거하기
				var content = data[i].free_content;
				// <br>포함하기
				content = content.replace(/<br\/>/ig, "\n");
				// 태그 제거
				content = content.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "");
				
				// 본문
				caption.append($("<div class='free_content overtext'></div>").text(content));
				
				// 작성자
				caption.append($("<div class='text-right'>").text("작성자 : " + data[i].userno));
				// 조회, 작성일
				caption.append($("<div></div>").html($("<span style='float: left;'>조회 : " + data[i].views + "</span><span style='float: right;'>" + data[i].free_time + "</span>")));
				
				var free_no = data[i].free_no;
				
				caption.on("click", function() {
					location.href = "/freeboard/view?free_no=" + free_no;
				})
				
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
	loadList();
	
	
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
	    	
	    	loadList();
        }
	});
});
</script>

<div id="board" class="container list-container">
	<h1 class="text-center">자유게시판</h1>
	<div class="row">
		<form action="/freeboard/list" method="get">
			<div style="width: 10%; float: left; margin-left: 20px;">
				<select name="categoryno">
					<option value="">선택없음</option>
					<option value="1">아이디어</option>
					<option value="2">정보</option>
					<option value="3">공모전</option>
				</select>
			</div>
			<div class="input-group" style="width: 30%; float: left;">
				<input type="text" class="form-control" name="search" placeholder="Search for...">
				<span class="input-group-btn">
					<button class="btn btn-default" type="submit" style="margin: 10px;">검색</button>
				</span>
			</div>
			<div style="width: 55%; text-align: right; float: left;">
				<a class="btn btn-primary" href="/freeboard/write" style="margin-top: 10px;">글작성</a>
			</div>
		</form>
	</div>
	<hr>
</div>
<div style="clear: both;"></div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>
