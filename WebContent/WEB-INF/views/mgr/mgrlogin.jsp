<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Pick MI (Mate&Idea)</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style type="text/css">
.mgrlogin-form{
	width: 385px;
	margin: 30px auto;
}

.mgrlogin-form form{
	margin-botoom: 15px;
	background: #f7f7f7;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	padding: 30px;
}

.mgrlogin-form h2 {
	margin: 0 0 15px;
}

.mgrform-control, .login-btn {
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

#log{
	margin: 5px auto;
	width: 500px;
}
#logo{
	height: 10px;
}

</style>

</head>
<body>
	<span id="logo">
		<a href="/main">
			<img src="/resources/black.png"class="img-rounded center-block" style="height: 250px;">
		</a>
	</span>

	<div class="mgrlogin-form" id="mgrlog">
		<form action="/mgr/login" method="post">
			<h2 class="text-center">관리자 로그인</h2>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-user"></i></span>
					<input type="text" class="form-control" name="mgrid" id="mgrid" placeholder="아이디를 입력하세요" required="required">
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input type="password" class="form-control" name="mgrpw" id="mgrpw" placeholder="비밀번호를 입력하세요" required="required">
				</div>
			</div>
			
			<c:if test="${!empty login && !login }">
				<script type="text/javascript"> alert("로그인 실패")</script>
			</c:if>
			
			<div class="form-group">
				<button type="submit" class="btn btn-primary login-btn btn-block">로그인</button>
			</div>
				<div class="or-seperator">
			</div>
		</form>
		<p class="text-center text-muted small">
			부여받은 아이디와 비밀번호를 넣으신 후 로그인을 하시면 됩니다.<br>
			아이디와 비밀번호를 잊으셨을 경우 당사 담당자에게 연락해 주시기 바랍니다.		
		</p>
		
		
	</div>
<c:import url="/WEB-INF/views/layouts/footer.jsp" />