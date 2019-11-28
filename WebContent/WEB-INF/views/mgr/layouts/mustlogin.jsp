<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

$(document).ready(function() {
	var boardno = "${boardno}";
	
	if (boardno == "1") {
		$("#main").addClass("active");
	} else if (boardno == "2") {
		$("#profile").addClass("active");
	} else if (boardno == "3") {
		$("#project").addClass("active");
	} else if (boardno == "4") {
		$("#free").addClass("active");
	} else if (boardno == "5") {
		$("#comp").addClass("active");
	} else {
		$("#user").addClass("active");
	}
});
</script>

<style type="text/css">
#menubar{
	margin-top:50px;
}

#logo{
	height: 10px;
}

</style>


</head>
<body>

<c:choose>
	<c:when test="${not empty mgrlogin }">
	<div role="tabpanel">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist" id="menubar">
			<li role="presentation" id="main"><a
				href="/mgr/main" aria-controls="main" role="tab">AdminMain</a></li>
			<li role="presentation" id="profile"><a href="/mgr/profilelist"
				aria-controls="profile" role="tab">ProfileBoardList</a></li>
			<li role="presentation" id="project"><a href="/mgr/projectlist"
				aria-controls="project" role="tab">ProjectBoardList</a></li>
			<li role="presentation" id="free"><a href="/mgr/freelist"
				aria-controls="free" role="tab">FreeBoardList</a></li>
			<li role="presentation" id="comp"><a href="/mgr/complist"
				aria-controls="comp" role="tab">CompBoardList</a></li>
			<li role="presentation" id="user"><a href="/mgr/userlist"
				aria-controls="user" role="tab">UserList</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="main">...</div>
			<div role="tabpanel" class="tab-pane" id="user">...</div>
			<div role="tabpanel" class="tab-pane" id="profile"></div>
			<div role="tabpanel" class="tab-pane" id="project"></div>
			<div role="tabpanel" class="tab-pane" id="free"></div>
			<div role="tabpanel" class="tab-pane" id="comp"></div>
		</div>
	</div>
	</c:when>
	
	<c:otherwise>
		<div class="container" style="text-align:center;">
		
			<span id="logo"> 
				<a href="/mgr/login"> 
					<img src="/resources/black.png"class="img-rounded center-block" style="height: 360px;">
				</a>
			</span>
		
			<form class="form-inline" action="/mgr/login" method="post">
			  <div class="form-group">
			    <label class="sr-only" for="mgrid"></label>
			    <input type="text" class="form-control" id="mgrid" name="mgrid" placeholder="Enter ID">
			  </div>
			  <div class="form-group">
			    <label class="sr-only" for="mgrpw">Password</label>
			    <input type="password" class="form-control" id="mgrpw" name="mgrpw" placeholder="Password">
			  </div>
			  <button type="submit" class="btn btn-default">Sign in</button>
			</form>
		<hr>	
			
		</div>
		
		<div class="bottom">
			<div class="col-lg-offset-4 col-md-4">
				<ul style="color: #444a; margin-left:40px;">
					<li>이곳은 관리자전용페이지입니다. 로그인을 하셔야 이용하실 수 있습니다.</li>
					<li>부여받은 아이디와 비밀번호를 넣으신 후 로그인을 하시면됩니다.</li>
					<li>아이디와 비밀번호를 잊으셨을 경우는 당사 담당자에게 연락해 주시기 바랍니다.</li>
				</ul>
			</div>
		</div>
	</c:otherwise>
	
</c:choose>
</body>
</html>