<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Pick MI (Mate&amp;Idea)</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.2.4.min.js"></script>


<style type="text/css">
.find-form {
	width: 385px;
	margin: 30px auto;
}

.find-form form {
	margin-bottom: 15px;
	background: #f7f7f7;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	padding: 30px;
}

.find-form h2 {
	margin: 0 0 15px;
}

.form-control, .login-btn {
	min-height: 38px;
	border-radius: 2px;
}

.input-group-addon .fa {
	font-size: 18px;
}

.login-btn {
	font-size: 15px;
	font-weight: bold;
}

.or-seperator {
	margin-top: 20px;
	text-align: center;
	border-top: 1px solid #ccc;
}

.or-seperator i {
	padding: 0 10px;
	background: #f7f7f7;
	position: relative;
	top: -11px;
	z-index: 1;
}

#log {
	margin: 5px auto;
	width: 500px;
}

#logo {
	height: 10px;
}
</style>

<script type="text/javascript">

$(document).ready(function() {
	
	
	// 찾기 버튼 동작
	$("#find").click(function() {
// 		if($("email").val() != "${USER.email}" || $("name").val() != "${USER.name}"){
// 			alert("회원이 아닙니다.");
// 		} else{
// 		$.ajax({
// 	    	 	type:"POST",
// 	     		url:"/pwFind",
// 	     		data:{
// 	            "email":$('#email').val(),
// 	            "name":$('#name').val()
	   			
// 	     		},
// 	    		 success:function(data){
// 	    			 console.log(data)
// // 	            if(($('#email').val() == ${USER.email}) || $('#name').val() == ${USER.name}){
	            	$("#hidden").show();
	               
// // 	           	}else{
	           		
// // 	           		alert("회원이 아닙니다.");
	               
// // 	            }
// 	         }
// 	    });
			
			
// 		}
	});

	
});

</script>
</head>
<body>

	<a href="/main"> <img src="/resources/black.png"
		class="img-rounded center-block" style="height: 170px;">
	</a>


	<div class="find-form" id="log">
		<form action="/pwFind" method="post">
			<h2 class="text-center">비밀번호 찾기</h2>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-user"></i></span> <input
						type="email" class="form-control" name="email" id="email"
						placeholder="이메일을 입력하세요" required="required">
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-user"></i></span> <input
						type="text" class="form-control" name="name" id="name"
						placeholder="이름을 입력하세요" required="required">
						
				</div>
			</div>
			
			<div class="form-group">
				<button type="submit" class="btn btn-primary login-btn btn-block"
					id="find">찾기</button>
			</div>
			
			<div id="hidden" style="display: none;">
			<div class="or-seperator" type="hidden">
				<i>비밀번호 변경</i>
			</div>
			
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span> <input
						type="password" class="form-control" name="pw1" id="pw1"
						placeholder="패스워드를 입력하세요" required="required">
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span> <input
						type="password" class="form-control" name="pw2" id="pw2"
						placeholder="패스워드를 다시 입력하세요" required="required">
				</div>
			</div>

			<div class="form-group">
				<button type="submit" class="btn btn-primary login-btn btn-block"
					id="change">변경</button>
			</div>
			</div>
		</form>

	</div>

</body>
</html>