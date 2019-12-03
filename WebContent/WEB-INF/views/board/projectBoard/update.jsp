<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="//cdn.ckeditor.com/4.13.0/basic/ckeditor.js"></script>

<jsp:include page="/WEB-INF/views/layouts/header.jsp" />

<script type="text/javascript">
	$(document).ready(function() {
		
		//알림 모달 호출 메서드
		 function warningModal(content) {
		    $(".modal-contents").text(content);
		    $("#defaultModal").modal('show');
		 }
			
		// 수정
		$("#modify").click(function() {
			
			warningModal("글을 수정하시겠습니까?")
			
			$('#ok').click(function(){    		
				$("form").submit();
		    	
			});
		});
		
		// 수정 취소
		$("#cancel").click(function() {
			
			warningModal("글 수정을 취소하시겠습니까?")
			
			$('#ok').click(function(){    		
				history.go(-1);
		    	
			});
		});

	});
</script>

<div class="container text-center">
	<h1>글 수정</h1>

	<form class="form-horizontal" action="/projectBoard/update"
		method="post" enctype="multipart/form-data">
		
		<input type="hidden" name="proj_no" value="${projectBoard.proj_no }"/>
		
		<div class="form-group">
			<label for="proj_title"
				class="col-sm-1 col-md-1 col-lg-1 control-label"
				style="padding-top: 10px; text-align: center;">프로젝트제목</label>
			<div class="col-sm-9 col-md-9 col-lg-10">
				<input type="text" class="form-control" id="comp_title"
					placeholder="제목을 입력하세요" name="proj_title" maxlength="120" value="${projectBoard.proj_title }">
			</div>
		</div>

		<div class="form-group">
			<label for="comp_name"
				class="col-sm-1 col-md-1 col-lg-1 control-label"
				style="padding-top: 16px; text-align: center;">프로젝트이름 </label>
			<div class="col-sm-9 col-md-9 col-lg-10">
				<input type="text" class="form-control" id="proj_name"
					placeholder="프로젝트 이름을 입력하세요" name="proj_name" maxlength="120" value="${projectBoard.proj_name }">
			</div>
		</div>


		<div class="form-group">
			<label for="comp_member"
				class="col-sm-1 col-md-1 col-lg-1 control-label"
				style="padding-top: 10px; text-align: center;">참여인원</label>
			<div class="col-sm-9 col-md-9 col-lg-4">
				<input type="text" class="form-control" id="proj_member"
					placeholder="프로젝트 참여 인원을 입력하세요" name="proj_member"
					style="width: 70%;" value="${projectBoard.proj_member }">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-1 col-md-1 col-lg-1 control-label"
				style="padding-top: 16px; text-align: center;">프로젝트시작날짜</label>
			<div class="col-sm-9 col-md-9 col-lg-2">
				<input type="date" class="form-control" id="proj_sdate"
					name="proj_sdate" value="${projectBoard.proj_sdate }">
			</div>
			<label class="col-sm-1 col-md-1 col-lg-1 control-label"
				style="padding-top: 16px; text-align: center;">프로젝트마감날짜</label>
			<div class="col-sm-9 col-md-9 col-lg-2">
				<input type="date" class="form-control" id="proj_ddate"
					name="proj_ddate" value="${projectBoard.proj_ddate }">
			</div>
			<label class="col-sm-1 col-md-1 col-lg-1 control-label"
				style="padding-top: 16px; text-align: center;">모집 기간</label>
			<div class="col-sm-9 col-md-9 col-lg-2">
				<input type="date" class="form-control" id="proj_rec_ddate"
					name="proj_rec_date" value="${projectBoard.proj_rec_date }">
			</div>
		</div>

		<div class="form-group">
			<label for="proj_loc" class="col-sm-1 col-md-1 col-lg-1 control-label" style="padding-top:17px;">지역</label> 
				<div class="col-sm-1 col-md-1 col-lg-2">
					<select id="proj_loc" name="proj_loc" class="form-control" style="width: 50%">
						<option value="${projectBoard.proj_loc }" selected>${projectBoard.proj_loc }</option>
						<option value="서울">서울</option>
						<option value="인천">인천</option>
						<option value="경기">경기</option>
						<option value="강원">강원</option>
						<option value="충청">충청</option>
						<option value="경상">경상</option>
						<option value="전라">전라</option>
					</select>
			</div>

			<label for="proj_progress" class="col-sm-1 col-md-1 col-lg-1 control-label" style="padding-top:17px;">진행상황</label>
				<div class="col-sm-1 col-md-1 col-lg-2">
					<select id="proj_progress" name="proj_progress" class="form-control" style="width: 65%">
						<option value="${projectBoard.proj_progress }" selected>${projectBoard.proj_progress }</option>
						<option value="설계단계">설계단계</option>
						<option value="구현단계">구현단계</option>
				</select>
			</div>
			
			<label for="proj_job" class="col-sm-1 col-md-1 col-lg-1 control-label" style="padding-top:17px;">직업</label>
				<div class="col-sm-1 col-md-1 col-lg-2">
					<select id="proj_job" name="proj_job" class="form-control" style="width: 55%">
						<option value="${projectBoard.proj_job }" selected>${projectBoard.proj_job }</option>
						<option value="개발자">개발자</option>
						<option value="프리랜서">프리랜서</option>
						<option value="디자이너">디자이너</option>
						<option value="무직">무직</option>
					</select>
			</div>
		
			<label for="proj_career" class="col-sm-1 col-md-1 col-lg-1 control-label" style="padding-top:17px;">경력</label>
				<div class="col-sm-1 col-md-1 col-lg-2">
					<select id="proj_career" name="proj_career" class="form-control" style="width: 50%">
						<option value="${projectBoard.proj_career }" selected>${projectBoard.proj_career }</option>
						<option value="1년차">1년차</option>
						<option value="3년차">3년차</option>
						<option value="5년차">5년차</option>
						<option value="7년차">7년차</option>
						<option value="8년차이상">8년차이상</option>
					</select>
			</div>
		</div>

		<div class="form-group">
			<textarea id="proj_content" name="proj_content">${projectBoard.proj_content }</textarea>
		</div>
		<script>
				CKEDITOR.replace( 'proj_content', {
					height : "500px"
				} );
		    </script>

		<div class="form-group text-left" style="margin-top: 5px;">

			현재파일 : <a	href="/file/download?fileno=${files.fileno}">${files.originName }</a>
			<input type="file" id="uploadfile" name="uploadfile">
			<p class="help-block">파일은 10MB까지만 업로드 가능합니다.</p>

		</div>
		<button class="btn btn-default" type="submit" id="modify">수정완료</button>
		<button class="btn btn-default" type="button" id="cancel">취소</button>

	</form>
</div>

<!--모달창 -->
<div class="modal fade" id="defaultModal">
	<div class="modal-dialog">
		<div class="modal-content ">
			<div class="modal-header panel-heading">
				<h4 class="modal-title">알림</h4>
			</div>
			<div class="modal-body">
				<p class="modal-contents"></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal" id="ok">확인</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- 모달창 -->

<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />