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
function myTeamMateList(proj_no) {
	

	$.ajax({
		type : "post",
		url : "/mate/mymate",
		data : { "proj_no" : proj_no },
		dataType : "text",
		success : function(data) {
			$("#showPeople").html("");
			var list = JSON.parse(data);
// 			var list1 = {"myTeamList" : data.myTeamList};
// 			var list2 = {"onlyName" : data.onlyName};
// 			console.log(list2);
// 			console.log(list2);
// 			$("#myTeamMateCaption")
			
			for (var i = 0; i < list.length; i++) {
				
				var caption = $("<div class='caption caption-profile' onclick=\"location.href='/profileBoard/view?prof_no="+list[i].prof_no +"'\" style='height: 300px;'\"></div>");
				
				caption.append($("<h4></h4>").text(list[i].username));
				caption.append($("<p></p>").text(list[i].prof_interest));
				caption.append($("<p></p>").text(list[i].prof_loc));
				caption.append($("<p></p>").text(list[i].prof_job));
				caption.append($("<p></p>").text(list[i].prof_state));
				caption.append($("<p id='abc'></p>").text(list[i].prof_career));
// 				caption.append($("<p class='text-right' id='abc'></p>").text(list[i].prof_like +"❤"));
// 				caption.append($("<p></p>").text(list[i].prof_time+"에 작성"));
				caption.append($("<p></p>").append($("<a href=\"/mate/accept?proj_no=" + proj_no + "&userno=" + list[i].userno + "\" class=\"btn btn-info\">수락</a> <a href=\"/mate/denied?proj_no=" + proj_no + "&userno=" + list[i].userno + "\"  class=\"btn btn-default\">거절</a>")));
			
				var board = $("<div class='col-sm-3 col-md-3 col-lg-4'></div>").append($("<div class='thumbnail'></div>").append(caption));
				$("#showPeople").append(board);
				}
			
			if (list.length < 1) {
				$("#showPeople").html("아직 팀장님의 신박한 프로젝트를 알아보는 사람이 없나봐요..😓");
			}
		
		},
			
		error : function(e) {
			console.log(e);
		}
	});
	
}

$(document).ready (function() {
	
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
		myTeamMateList($(this).data("proj_no"));
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
<%-- 			${ list.proj_no } --%>
    		<p><a href="/projectBoard/view?proj_no=${ list.proj_no }">${ list.proj_title }</a></p>
		<!-- 신청자보기 모달 -->
		<!-- Button trigger modal -->
			<button data-proj_no="${ list.proj_no }" id="showUsers" type="button" class="btn btn-info showUsers" data-toggle="modal" data-target="#showApplied">
			  신청자 보기
			</button>
			</c:forEach>
			
			<!-- Modal -->
			<div class="modal fade" id="showApplied" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog" style="width: 80%;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">신청자 현황</h4>
			      </div>
			      <div class="modal-body" id="showPeople">
					</div>
					<div style="clear: both;"></div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			      </div>
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
   			 <c:if test="${ empty waitTeamList }">
   			 	<li class="list-group-item">신청한 프로젝트가 없습니다! 도전하세요!💪</li>
   			 </c:if>
		  </ul>
		  
  	</div>
</div>
<!-- 나에게 들어온 초대 신청 -->
<div class="panel panel-info">
  <!-- Default panel contents -->
	<div class="panel-heading">나에게 온 초대 현황</div>
		<div class="panel-body">
		  <ul class="list-group">
		  	<c:forEach items="${ invitedList }" var="list">
			    <li class="list-group-item"><a href="/projectBoard/view?proj_no=${ list.sender }">${ list.sender }</a> 당신을 초대합니다!😍</li>
   			 </c:forEach>
		  </ul>
		  
  	</div>
</div>



<!-- footer -->
<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>