<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	
	$('#modalBody').on("click", ".deleteBtn", function(event) {
		var proj_no = $(this).data("projno");
		var userno = $(this).data("userno");
			console.log(proj_no);
			console.log(userno);
		$.ajax({
			type : "post",
			url : "/mate/delete",
			data : {
				"proj_no" : proj_no , "userno" : userno
			},
			dataType : "json",
			success : function(data) {
				if (JSON.parse(data).result == 2) {
					$("#matemodal").modal("toggle");
				} else {
					location.href = "/main";
				}
			}
		})
	})
	
$(function () {
	$('[data-toggle="popover"]').popover()
})

</script>
<c:forEach items="${ mateList }" var="mate">
	<div id="userDelete">
		<span>
			<a tabindex="0" role="button" class="btn btn-info" data-toggle="popover" data-trigger="focus" title="${ mate.username }의 간단정보☺" 
			data-content="${ profile.prof_interest }/${ profile.prof_job }/${ profile.prof_state }/${ profile.prof_loc }/${profile.prof_career}">
			${ mate.username }
			<c:if test="${mate.mate == 2 }">
				★
			</c:if>
			</a>
		</span>
	<c:choose>
		<c:when test="${mate.userno == userno }">
			<!-- 나 자신 -->
			<span><button class="label label-warning">ME!</button></span>
			<c:if test="${mate.mate != 2 }">
				<span><button class="label label-warning deleteBtn" data-projno="${ mate.proj_no }" data-userno="${mate.userno }">탈퇴</button></span>
			</c:if>
		</c:when>
		<c:when test="${leader }">
			<span><button class="label label-warning deleteBtn" data-projno="${ mate.proj_no }" data-userno="${ mate.userno }">추방</button></span>
		</c:when>
	</c:choose>
	
	<br><br>
	</div>
</c:forEach>