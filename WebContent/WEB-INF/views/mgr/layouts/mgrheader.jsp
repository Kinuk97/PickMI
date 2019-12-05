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
	margin-top:9px;
	margin-bottom:65px;
}

#logo{
	height: 10px;
}

@font-face { 
	font-family: 'KHNPHD'; 
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/KHNPHD.woff') format('woff'); 
	font-weight: normal; 
	font-style: normal; 
}

#mgrheaderfont {
	font-family:'KHNPHD';
}

</style>


</head>
<body id="mgrheaderfont">

	<div role="tabpanel">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist" id="menubar">
			<li id="main"><a href="/mgr/main" aria-controls="main" role="tab">AdminMain</a></li>
			<li id="profile"><a href="/mgr/profilelist" role="tab">ProfileBoard</a></li>
			<li id="project"><a href="/mgr/projectlist"role="tab">ProjectBoard</a></li>
			<li id="comp"><a href="/mgr/complist" aria-controls="comp" role="tab">CompBoard</a></li>
			<li id="free"><a href="/mgr/freelist" aria-controls="free" role="tab">FreeBoard</a></li>
			<li id="user"><a href="/mgr/userlist" aria-controls="user" role="tab">User</a></li>
		</ul>

	</div>