<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	
	$('#modalBody').on("click", ".label.label-warning", function(event) {
		var proj_no = $(this).data("projno2");
		var userno = $(this).data("userno");
			console.log(proj_no);
		$.ajax({
			type : "post",
			url : "/mate/delete",
			data : {
				"proj_no" : proj_no , "userno" : userno
			},
			dataType : "html",
			success : function(data) {
				console.log('작동');
				$("#userDelete").html(data)
			}
		})
	})
	$('.label label-warning').click(function(){ 
		console.log('되니?');
	})
	
	
	
$(function () {
	$('[data-toggle="popover"]').popover()
})

$("#cantdelete").click( function() {
	console.log("아이쿠");
	alert("팀장만 가능합니다");
})

</script>
<c:forEach items="${ mateList }" var="mate">
	<div id="userDelete">
		<span>
			<c:if test="${ leader }"></c:if>
			<a tabindex="0" role="button" class="btn btn-info" data-toggle="popover" data-trigger="focus" title="${ mate.username }의 간단정보☺" 
			data-content="${ profile.prof_interest }/${ profile.prof_job }/${ profile.prof_state }/${ profile.prof_loc }/${profile.prof_career}">
			${ mate.username }</a>
		</span>
	<c:if test="${ check }">
	<span><button class="label label-warning" data-projno2="${ mate.proj_no }" data-userno="${ mate.userno }">추방</button></span>
	</c:if>
	<c:if test="${ !check }">
	<span id="cantdelete"><button class="label label-warning" data-projno2="${ mate.proj_no }">추방</button></span>
	</c:if>
	<br><br>
	</div>
</c:forEach>