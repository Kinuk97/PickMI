<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pick MI (Mate&amp;Idea)</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
	var check = false;
	$(document).ready(function() {
		
			//경고 모달 호출 메서드
		   function warningModal(content) {
		      $(".modal-contents").text(content);
		      $("#defaultModal").modal('show');
		   }
// 			//인증코드 모달 호출 메서드
// 		   function codeModal(content) {
// 		      $(".modal-contentt").text(content);
// 		      $("#codeModal").modal('show');
// 		   }
		
		
		// 가입버튼
	    $('#join-submit').click(function(){
	        
	     	// 이름 입력
	        if($("#name").val() ==''){
	        	warningModal("이름을 입력하세요");
	            $("#name").focus();
	            return false;
	        }
			
	        // 이메일 입력
	        var email = $('#email').val();
	        if(email == ''){
	            warningModal('이메일을 입력하세요');
	            $("#email").focus();
	            return false;
	        } else {
	            var emailRegex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	            if (!emailRegex.test(email)) {
	                alert('이메일 주소가 유효하지 않습니다. ex)abc@gmail.com');
	                $("#email").focus();
	                return false;
	            }
	        }
			
	        // 비밀번호 입력
	        if($("#pw").val() ==''){
	        	warningModal('비밀번호를 입력하세요');
	            $("#pw").focus();
	            return false;
	        }
			
	        // 비밀번호 확인
	        if($("#pwCheck").val() ==''){
	        	warningModal('비밀번호를 다시 한번 더 입력하세요');
	            $("#pwCheck").focus();
	            return false;
	        }
	        
	        if($("#pw").val()!== $("#pwCheck").val()){
	        	warningModal('비밀번호를 동일하게 입력하세요');
	            return false;
	        }
	        
	        // 약관 동의
	        if($("#agree").is(":checked") == false){
	        	warningModal('약관에 동의해주세요');
	            return false;      
	        }
	        
	        // 이메일 중복
	        if(!check){
	        	warningModal('중복체크를 해주세요');
	            $("#email").focus();
	            return false;
	        }
	        
	        
	    });
		
		// 가입취소
		$('#join-cancel').click(function(){
			$(location).attr("href", "/login");
		});

		// 중복체크
   		 $('#emailCheck').click(function(){
        	$.ajax({
	    	 	type:"POST",
	     		url:"/emailCheck",
	     		data:{
	            "email":$('#email').val(),
	     		},
	    		 success:function(data){
	            if(data==false){
	            	check = true;
	               if($('#email').val()!=''){ 
	            	   warningModal('사용가능한 아이디 입니다.');
	               	
	               }
	           	}else{
	            	check = false;
	               if($('#email').val()!=''){
	            	   warningModal('중복된 아이디 입니다.');
	                  $('#email').focus();	
	               }
	            }
	         }
	    });
     }); // 중복체크 끝

	});
</script>

<style type="text/css">

@font-face { 
	font-family: 'KHNPHD'; 
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/KHNPHD.woff') format('woff'); 
	font-weight: normal; 
	font-style: normal; 
}

#joinfont {
	font-family:'KHNPHD';
}
</style>

</head>
<body id="joinfont">
	<article class="container">
		<div class="page-header">
			<div class="col-md-6 col-md-offset-3">
				<h3>회원가입</h3>
			</div>
		</div>
		<div class="col-sm-6 col-md-offset-3">
			<form role="form" action="/insert" method="post">
				<div class="form-group">
					<label for="name">성명</label> <input type="text"
						class="form-control" id="name" name="name" placeholder="이름을 입력해 주세요">
				</div>
				<div class="form-group">
					<label for="email">이메일 주소</label> <input type="email"
						class="form-control" id="email" name="email" placeholder="이메일 주소를 입력해주세요">
					<br>

					<button type="button"  id ="emailCheck" class="btn btn-success">중복 체크</button>
					
					
				</div>
				<div class="form-group">
					<label for="pw">비밀번호</label> <input type="password"
						class="form-control" id="pw" name="pw" placeholder="비밀번호를 입력해주세요">
				</div>
				<div class="form-group">
					<label for="pwCheck">비밀번호 확인</label> <input
						type="password" class="form-control" id="pwCheck" name="pwCheck"
						placeholder="비밀번호를 다시한번 입력 해 주세요">
				</div>

				<div class="form-group">
					<label>약관 동의</label>
					<div>
						<input id="agree" type="checkbox" /> 
						<a href="/agree" onclick="window.open(this.href, '_blank', 'width=800px, height=700px,left=350, top=30, toolbars=no,scrollbars=no'); return false;">
						이용약관</a>에 동의합니다.
					</div>
				</div>

				<div class="form-group text-center">
					<button type="submit" id="join-submit" class="btn btn-primary">
						회원가입<i class="fa fa-check spaceLeft"></i>
					</button>
					<button type="button" id="join-cancel" class="btn btn-warning">
						가입취소<i class="fa fa-times spaceLeft"></i>
					</button>
				</div>
			</form>
		</div>

	</article>


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
					<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- 모달창 -->


</body>
</html>