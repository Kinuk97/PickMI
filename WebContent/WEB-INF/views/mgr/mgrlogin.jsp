<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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

.con2{

font-size:1em;

}
.info{
font-size:0.7666666667em;
}


</style>


</head>
<body>

	<div class="con1" style="text-align:center;">
	
		<span id="logo"> 
			<a href="/mgr/login"> 
				<img src="/resources/black.png"class="img-rounded center-block" style="height: 360px;">
			</a>
		</span>
	
		<form class="form-inline" action="/mgr/login" method="post">
		  <div class="form-group">
		    <label class="sr-only" for="mgrid"></label>
		    <input type="text" class="form-control" id="mgrid" name="mgrid" placeholder="Enter AdminID">
		  </div>
		  <div class="form-group">
		    <label class="sr-only" for="mgrpw">Password</label>
		    <input type="password" class="form-control" id="mgrpw" name="mgrpw" placeholder="Password">
		  </div>
		  <button type="submit" class="btn btn-default">Sign in</button>
		</form>
	<hr width=30% size=3 color=#44444A align=center>	
		
	</div>
	
	<div class="con2">
		<div class="col-lg-offset-4 col-md-4">
			<ul style="color: #444a; margin-left:40px;" class="info">
				<li>이곳은 관리자전용페이지입니다. 로그인을 하셔야 이용하실 수 있습니다.</li>
				<li>부여받은 아이디와 비밀번호를 넣으신 후 로그인을 하시면됩니다.</li>
				<li>아이디와 비밀번호를 잊으셨을 경우는 당사 담당자에게 연락해 주시기 바랍니다.</li>
			</ul>
		</div>
	</div>

</body>
</html>