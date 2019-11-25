<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	$(document).ready(function() {
		// 가입버튼
	    $('#join-submit').click(function(){
	        
	     	// 이름 입력
	        if($("#name").val() ==''){
	            alert('이름을 입력하세요');
	            $("#name").focus();
	            return false;
	        }
			
	        // 이메일 입력
	        var email = $('#email').val();
	        if(email == ''){
	            alert('이메일을 입력하세요');
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
	            alert('비밀번호를 입력하세요');
	            $("#pw").focus();
	            return false;
	        }
			
	        // 비밀번호 확인
	        if($("#pwCheck").val() ==''){
	            alert('비밀번호를 다시 한번 더 입력하세요');
	            $("#inputPasswordCheck").focus();
	            return false;
	        }
	        
	        if($("#pw").val()!== $("#pwCheck").val()){
	            alert('비밀번호를 동일하게 입력하세요');
	            return false;
	        }
	        
	        // 약관 동의
	        if($("#agree").is(":checked") == false){
	            alert('약관에 동의해주세요');
	            return false;      
	        }               
	        
	    });
		
		// 가입취소
		$('#join-cancel').click(function(){
			$(location).attr("href", "/login");
		});

	});
</script>

</head>
<body>
	<article class="container">
		<div class="page-header">
			<div class="col-md-6 col-md-offset-3">
				<h3>회원가입 Form</h3>
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
					<button type="button" class="btn btn-success">이메일 인증</button>
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
						<input id="agree" type="checkbox" /> <a href="#">이용약관</a>에 동의합니다.
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
</body>


</html>