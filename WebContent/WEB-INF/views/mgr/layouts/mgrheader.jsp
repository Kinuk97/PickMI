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
	console.log(boardno);
	if (boardno == "1") {
		$("#main").addClass("active");
	} else if (boardno == "2") {
		$("#profile").addClass("active");
	} else if (boardno == "3") {
		$("#project").addClass("active");
	} else if (boardno == "4") {
		$("#comp").addClass("active");
	} else if (boardno == "5") {
		$("#free").addClass("active");
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

	<div role="tabpanel">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist" id="menubar">
			<li role="presentation" id="main"><a href="/mgr/main" aria-controls="main" role="tab">AdminMain</a></li>
			<li role="presentation" id="profile"><a href="/mgr/profilelist" aria-controls="profile" role="tab">ProfileBoard</a></li>
			<li role="presentation" id="project"><a href="/mgr/projectlist" aria-controls="project" role="tab">ProjectBoard</a></li>
			<li role="presentation" id="comp"><a href="/mgr/complist" aria-controls="comp" role="tab">CompBoard</a></li>
			<li role="presentation" id="free"><a href="/mgr/freelist" aria-controls="free" role="tab">FreeBoard</a></li>
			<li role="presentation" id="user"><a href="/mgr/userlist" aria-controls="user" role="tab">User</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="main"></div>
			<div role="tabpanel" class="tab-pane" id="profile"></div>
			<div role="tabpanel" class="tab-pane" id="project"></div>
			<div role="tabpanel" class="tab-pane" id="comp"></div>
			<div role="tabpanel" class="tab-pane" id="free"></div>
			<div role="tabpanel" class="tab-pane" id="user"></div>
		</div>
	</div>