<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style type="text/css">

#header {
	text-align: center;
}

#footer {
	text-align: center;
	height: 50px;
	margin-top: 35px;
	margin-bottom: 20px;
}

#wrapper {
	min-height: 600px;
	background-color: #f3f3f3;
	clear: both;
	padding-top: 25px;
    padding-bottom: 15px;
}

.jumbotron {
	padding-left: 5%;
	padding-right: 5%;
}

.border {
	outline: 2px solid black;
	outline-offset: -16px;
}

.navbar {
	margin-top: 20px;
}

#logo {
    height: 100px;
    margin-top: -38px;
}

.jumbotron {
    padding-left: 13%;
    padding-right: 13%;
}

button, input, optgroup, select, textarea {
    margin: 10px;
    font: inherit;
    color: inherit;
}

.carousel-caption {
    position: absolute;
    bottom: 20px;
    z-index: 10;
    color: #000;
    text-align: center;
}

/* 게시글 호버 */
.thumbnail:hover {
	border-color: tomato;
}

/* 각 게시판 리스트 높이 */
.caption-profile {
	height: 320px;
}
.caption-project {
	height: 350px;
}
.caption-comp {
	height: 200px;
}
.caption-free {
	height: 150px;
	-ms-user-select: none;
    -moz-user-select: -moz-none;
    -webkit-user-select: none;
    -khtml-user-select: none;
    user-select: none;
}

.list-container {
	width: 80%;
}

select {
	padding: 7px;
}

.free_content {
	height: 40px;
    
/*     display: -webkit-box; */
/* 	display: -ms-flexbox; */
/* 	display: box; */
/* 	margin-top:1px; */
/* 	max-height:88px; */
/* 	overflow:hidden; */
/* 	vertical-align:top; */
/* 	text-overflow: ellipsis; */
/* 	word-break:break-all; */
/* 	-webkit-box-orient:vertical; */
/* 	-webkit-line-clamp:3; */

}

.overtext {
	text-overflow: ellipsis;
    overflow : hidden;
    white-space: nowrap;
}

h1 {
	margin-bottom: 20px;
}

hr {
	margin-top: 10px;
	margin-bottom: 10px;
}

#compboardlist:hover {
	background: #ccc;
}

</style>
</head>
<body>

<div id="header">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/main"><img id="logo" alt="logo"
					src="/resources/logo2.png"></a>
			</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav center" >
<!-- 					<li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li> -->
						<li><a href="/profileBoard/list">프로필 게시판</a></li>
						<li><a href="/projectBoard/list">프로젝트 게시판</a></li>
						<li><a href="/compBoard/list">완성된 프로젝트 게시판</a></li>
						<li><a href="/freeboard/list">자유게시판</a></li>
					</ul>
				
					<!-- 검색 폼 -->
<!-- 					<form class="navbar-form navbar-left" role="search"> -->
<!-- 						<div class="form-group"> -->
<!-- 							<input type="text" class="form-control" placeholder="Search"> -->
<!-- 						</div> -->
<!-- 						<button type="submit" class="btn btn-default">Submit</button> -->
<!-- 					</form> -->


				<ul class="nav navbar-nav navbar-right">

 						<li role="presentation"> 					
 							<div class="dropdown">
								<button class="btn btn-default dropdown-toggle" type="button"
									id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
									Alert <span class="badge">7</span>
								</button>
								<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1" style="width: 400px;">
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#">Action</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#">Action</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#">Action</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#">Action</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#">Another action</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#">Something else here</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#">Separated link</a></li>
								</ul>
							</div>
						</li>
						
						<li role="presentation"> 					
 							<div class="dropdown">
								<button class="btn btn-default dropdown-toggle" type="button"
									id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
									Message <span class="badge">5</span>
								</button>
								<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1" style="width: 400px;">
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#">Action</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#">Action</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#">Action</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#">Action</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#">Action</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#">Another action</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#">Something else here</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#">Separated link</a></li>
								</ul>
							</div>
						</li>
						
					<li>
					<c:choose>
						<c:when test="${not login }">
							<li><a href="/login">로그인</a></li>
						</c:when>
						<c:otherwise>
							<ul>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown"><img src="/resources/pickmiBlack.jpg" class="img-circle" style="width: 50px; height: 50px;"></a>
									<ul class="dropdown-menu">
										<li><a href="/mypage"><i class="icon-arrow-up"></i>마이페이지</a></li>
										<li><a href="#"><i class="icon-arrow-down"></i>추가할거</a></li>
										<li><a href="#"><i class="icon-arrow-left"></i>추가해주세요~</a></li>
										<li><a href="/logout"><i class="icon-arrow-right"></i>로그아웃</a></li>
									</ul>
								</li>
							</ul>
						</c:otherwise>
				</c:choose>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</div>

	<div id="wrapper">