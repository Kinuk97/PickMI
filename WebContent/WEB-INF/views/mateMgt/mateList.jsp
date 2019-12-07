<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/views/layouts/header.jsp"></jsp:include>
<!-- add styles -->
<link href="/resources/css/jquery-ui.min.css" rel="stylesheet"
	type="text/css" />
<!-- add scripts -->
<script src="/resources/js/jquery-ui.min.js"></script>

<script type="text/javascript">
$(document).ready (function() {

	$.ajax({
		type : "post",
		url : "/profileBoard/list",
		data : { "curPage" : curPage, "interestno" : "${paging.interestno}", "locationno" : "${paging.locationno}", "jobno" : "${paging.jobno}", "stateno" : "${paging.stateno}", "careerno" : "${paging.careerno}" },
		dataType : "json",
		success : function(data) {
			for (var i = 0; i < data.length; i++) {
				
				var caption = $("<div class='caption caption-profile' onclick=\"location.href='/profileBoard/view?prof_no="+data[i].prof_no +"'\"></div>");
				
				caption.append($("<h4></h4>").text(data[i].username));
// 				caption.append($("<p></p>").text(data[i].username));
				caption.append($("<p></p>").text(data[i].prof_interest));
				caption.append($("<p></p>").text(data[i].prof_loc));
				caption.append($("<p></p>").text(data[i].prof_job));
				caption.append($("<p></p>").text(data[i].prof_state));
				caption.append($("<p id='abc'></p>").text(data[i].prof_career));
				caption.append($("<p class='text-right' id='abc'></p>").text(data[i].prof_like +"❤"));
				caption.append($("<p></p>").text(data[i].prof_time+"에 작성"));
	
			
				var board = $("<div class='col-sm6 col-md-4 col-lg-3'></div>").append($("<div class='thumbnail'></div>").append(caption));
				$("#board").append(board);
			}	
		
			loading = false;
		},
		error : function(e) {
			console.log(e);
		}
	});
	
	$('.startmodal').click(function(){
		var proj_no = $(this).data("projno");
		$.ajax({
			type : "post",
			url : "/mate/view",
			data : {
				"proj_no" : proj_no
			},
			dataType : "html",
			success : function(data) {
				console.log('성공');
				$("#modalBody").html(data)
			}
		})
	}); /* 첫번째 모달 끝 */
	
	$('.showUsers').click(function() {
		var proj_no = $(this).data("proj_no");
		$.ajax({
			type : "get",
			url : "/mate/list",
			data : {
				"proj_no" : proj_no
			},
			dataType : "html",
			success : function(data) {
				console.log('신청자보기');
				$('#showPeople').html(data);
			}
		})
	}); /* 두번째모달 끝 */
	
})



</script>

<!-- 팀원목록 -->
<div class="page-header">
  <h1>팀원관리 <small>팀원을 추가하고 관리할 수 있습니다</small></h1>
</div>


<div id="mateList" class="panel panel-info">
  <!-- Default panel contents -->
	<div class="panel-heading">내가 가입한 프로젝트 목록</div>
  		<div class="panel-body">
		  		<c:forEach items="${ joinTeamList }" var="list">
<%-- 		  			<c:if test="${ list.mate eq '2' }"> --%>
	    			<p><a href='/schedule/list?proj_no=${list.proj_no }'>${ list.proj_title }</a></p>
<%-- 		  			</c:if> --%>
<%-- 		  			<c:if test="${ !leader }"> --%>
<%-- 	    			<p><a href="/projectBoard/view?proj_no=${ list.proj_no}">${ list.proj_title }</a></p> --%>
<%-- 			  		</c:if>	 --%>
						<p>
						<button data-projno="${ list.proj_no }" type="button" id="startmodal" class="btn btn-info startmodal" data-toggle="modal" data-target="#matemodal">인원보기
						</button>
						</p>   
								
					<hr>
	    		</c:forEach>
  		</div>
</div>


<!-- 인원보기 모달 -->
<div class="modal fade" id="matemodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">함께하는 팀원들</h4>
      </div>
      <div class="modal-body" id="modalBody">
      <!-- viewuser jsp로 넘어감 -->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- 팀원신청 -->
<div class="panel panel-info">
  <!-- Default panel contents -->
	<div class="panel-heading">새로운 가입 신청	
	</div>
  		<div class="panel-body">
			<c:forEach items="${ leaderlist }" var="list">
			${ list.proj_no }
    		<p><a href="/projectBoard/view?proj_no=${ list.proj_no}">${ list.proj_title }</a></p>
		<!-- 신청자보기 모달 -->
		<!-- Button trigger modal -->
			<button data-proj_no="${ list.proj_no }" id="showUsers" type="button" class="btn btn-info" data-toggle="modal" data-target="#showApplied">
			  신청자 보기
			</button>
			</c:forEach>
			
			<!-- Modal -->
			<div class="modal fade" id="showApplied" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">신청자 현황</h4>
			      </div>
			      <div class="modal-body" id="showPeople">
			        	<!-- 썸네일 -->
						<div class="row">
						  <div class="col-sm-6 col-md-4">
						    <div class="thumbnail">
						      <div class="caption">
						        <h3>${ list.username }</h3>
						        <p>...</p>
						        <p><a href="#" class="btn btn-info" role="button">수락</a> <a href="#" class="btn btn-default" role="button">거절</a></p>
						      </div>
						    </div>
						  </div>
						</div>
					</div>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
<!-- 			        <button type="button" class="btn btn-info">확인</button> -->
			      </div>
			    </div>
			  </div>
			</div>
		
		
	
		
<!-- 내프로젝트에참가신청한사람들 -->
		
</div>

<!-- 내가 신청한 프로젝트 가입 현황 -->
<div class="panel panel-info">
  <!-- Default panel contents -->
	<div class="panel-heading">프로젝트 가입 현황</div>
		<div class="panel-body">
		  <ul class="list-group">
		  	<c:forEach items="${ waitTeamList }" var="list">
			    <li class="list-group-item"><a href="/projectBoard/view?proj_no=${ list.proj_no}">${ list.proj_title }</a> 아직 기다리고 있어요!😅</li>
   			 </c:forEach>
		  </ul>
		  
  	</div>
</div>




<!-- footer -->
<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>