<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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

<!-- <script -->
<!-- 	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> -->

<script type="text/javascript" src="/resources/js/dropdown.js"></script>
<script type="text/javascript" src="/resources/js/carousel.js"></script>
<script type="text/javascript" src="/resources/js/collapse.js"></script>
<script type="text/javascript" src="/resources/js/tooltip.js"></script>
<script type="text/javascript" src="/resources/js/popover.js"></script>
<script type="text/javascript" src="/resources/js/modal.js"></script>

<!--Start of Tawk.to Script-->
<!-- <!-- <script type="text/javascript"> -->
<!-- 	var Tawk_API = Tawk_API || {}, Tawk_LoadStart = new Date(); -->
	
<!-- 	(function() { -->
<!-- 		var s1 = document.createElement("script"), s0 = document -->
<!-- 				.getElementsByTagName("script")[0]; -->
<!-- 		s1.async = true; -->
<!-- 		s1.src = 'https://embed.tawk.to/5de394e9d96992700fca2231/default'; -->
<!-- 		s1.charset = 'UTF-8'; -->
<!-- 		s1.setAttribute('crossorigin', '*'); -->
<!-- 		s0.parentNode.insertBefore(s1, s0); -->
<!-- 	})(); -->
	
<!-- </script> -->
<!--End of Tawk.to Script-->

<script type="text/javascript">
$(document).ready(function(){
	//메세지 리스트의 항목 하나를 눌렀을 떄
	$('#msgList').on("click", ".messagelist", function() {
		//메세지 리스트 드롭다운 메뉴가 사라짐(close)
		$('.dropdown-menu').css('visibility','hidden');
		//대화창이 hidden에서 visible로 변경됨
		$('#messagebox').css('visibility', 'visible');
		
// 		console.log( $(this) )
// 		console.log( $(this).find("input") )
// 		console.log( $(this).find("input").val() )
		$("#send").attr( "data-chat_no", $(this).find("input").val() )
		msgshow( $(this).find("input").val() )
	});
	
	//닫기 버튼을 눌렀을 때
	$('#closebtn').click(function(){
		//대화창이 visible에서 hidden으로 변경됨
		$('#messagebox').css('visibility', 'hidden');
		//메세지 리스트 드롭다운 메뉴가 hidden에서 visible로 변경됨 
		$('.dropdown-menu').css('visibility', 'visible');
	})
	
	//새 메시지 버튼을 눌렀을 때
	$('#chatplusbtn').click(function(){
		//메시지 목록창이 visible에서 hidden으로 변경됨
		$('#msgscroll').css('visibility', 'hidden');
	})
	
});

//메시지 사용자 검색시 중복체크 방지
function onecheckbox(a) {
	var obj = document.getElementsByName("msgcheckbox");
	
	for(var i=0; i<obj.length; i++){
		if(obj[i] != a) {
			obj[i].checked = false;
		}
	}
}

</script>

<!-- 메세지 -->
<script type="text/javascript">
//메시지 목록 띄우기
$(document).ready(function(){
	$("#btnMessageList").click(function(){
		var ws = new WebSocket("ws://localhost:8089/ws/msg");
		
		ws.onopen = function() {
	 		//메세지보내는 메소드 send
	 		var obj = { type: "list", chat_user: '${userno}', username: '${name}' };
	 		var msg = JSON.stringify(obj);
	 		console.log(msg)
	 		ws.send(msg);
	 	}
		
		ws.onmessage = function(data) {
// 			console.log("!")
			$("#msgList").html("");
			var list = JSON.parse(data.data)
			for(var i = 0; i<list.length; i++){
				var spantext = "&emsp;"+list[i].username+"님의 새로운 메시지 <br><small>"+list[i].chat_sendtime+"에 보냄</small>"+ "<hr class='a'>";
				
				$("<a>").attr({"role":"menuitem", "tabindex":"-1", "class":"dropdownA", "href":"#"})
					.append( $("<li>").attr({"class":"messagelist", "role":"presentation"})
					.append( $("<img>").attr({"src":"/resources/defaultuserphoto.png", "alt":"", "class":"img-circle", "id":"msgImage"}) )
					.append( $("<input>").attr({"type":"hidden", "value":list[i].chat_no}) )
					.append( $("<span>").attr({"class":"spanmsg"}).html(spantext) )
				).appendTo($("#msgList"))
			}
			ws.close();
		}
		
	})
});

//메세지 목록 중 하나 클릭 시
$(document).ready(function(){
	$('#msgList').on("click", ".messagelist", function() {
		var ws = new WebSocket("ws://localhost:8089/ws/msg");
		
		var chat_no = $(this).find("input").val()
		
		ws.onopen = function() {
	 		//메세지보내는 메소드 send
	 		var obj = { type: "msg", chat_user: '${userno}', chat_no: chat_no, username: '${name}' };
	 		var msg = JSON.stringify(obj);
// 	 		console.log(JSON.stringify(obj))
	 		ws.send(msg);
	 	}
		
		ws.onmessage = function(data) {
			ws.close();
		}
		
	})
})

//메시지 창에서 send버튼을 눌렀을 때
$(document).ready(function(){
// 	var ws = new WebSocket("ws://localhost:8089/ws/msg");
	//#send = 메시지 전송 버튼
	$("#send").click(function(){
// 		console.log("send clicked")
// 		var msg = {
// 				"type": "msg",
// 				"chat_msg": $("#msg").val()
// 		}
		
// 		$("#msg").val("");
// 		var json = JSON.stringify(msg)
// 		console.log("json ; " + json)
		console.log($(this).attr("data-chat_no"))
		msgsend($(this).attr("data-chat_no"))
	})
});
function msgshow(n) {
	$.ajax({
		type : "post",
		url : "/msg/chatting",
		data : {chat_no: n},
		dataType : "html",
		success : function(data){
			console.log(data)
			$("#msgresult").html(data)
			$("#msg").val("")
			$("#msg").focus()
// 			$("#msgresult").scrollTop($(window).height())
			$("#msgresult").scrollTop($("#msgresult")[0].scrollHeight);
		},
		error : function(e){
			console.log(e);
		}
	})
}
function msgsend(n) {
	console.log("msgsend---")
	console.log($("#msg").val())
	console.log(n)
	
	$.ajax({
		type : "post",
		url : "/msg/chatting",
		data : {chat_msg: $("#msg").val(), chat_no: n},
		dataType : "html",
		success : function(data){
			console.log(data)
			$("#msgresult").html(data)
			$("#msg").val("")
			$("#msg").focus()
// 			$("#msgresult").scrollTop($(window).height())
			$("#msgresult").scrollTop($("#msgresult")[0].scrollHeight);
		},
		error : function(e){
			console.log(e);
		}
	})
}


//메세지를 보낼 사용자 검색
$(document).ready(function(){
	$("#chatplusBtn").click(function(){
		var ws = new WebSocket("ws://localhost:8089/ws/msg");
		ws.onopen = function() {
			
			var obj = { type: "search", name : '${name}', email: '${email}' };
			var msg = JSON.stringify(obj);
			console.log(msg);
			ws.send(msg);
		}
		
		
		ws.onmessage = function(data){
			var search = JSON.parse(data.data);
			console.log(search)
			var tr = $("<tr>");
			tr.append( $("<th>"));
			tr.append( $("<th>").attr({"id":"tableth"}).html("이름"));
			tr.append( $("<th>").attr({"id":"tableth"}).html("이메일"));
			var table = $("<table></table>");
			table.attr({"class":"table table-striped table-hover text-center", "id":"msgtable"})
			.append(tr);
			
			for(var i=0; i<search.length; i++){
				
				var tr = $("<tr></tr>"); 
				var td = $("<td></td>");
				tr.append($("<td>").append($("<input>").attr({"type":"checkbox", "name":"msgcheckbox", "id":"msgcheckbox", "onclick":"onecheckbox(this)"})))
				tr.append( $("<td>").html(search[i].name))
				tr.append( $("<td>").html(search[i].email))
				
				table.append(tr);
			}
			$("#searchtable").append(table);
			ws.close();
		}
	
 	})
 	
 	$("#searchbtn").click(function(){
		$.ajax({
			type : "post",
			url : "/msg/search",
			data : { search: $("#searchform").val() },
			dataType : "html",
			success : function(data){
				
				$("#searchtable").html(data);
				
			},
			error : function(e) {
				console.log(e);
			}
			
		})
 	})
})

</script>


<style type="text/css">

#tableth {
	text-align: center;
}

#msgtable {
	text-align: center;
}

@font-face { 
	font-family: 'KHNPHD'; 
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/KHNPHD.woff') format('woff'); 
	font-weight: normal; 
	font-style: normal; 
}

.headerfont {
	font-family:'KHNPHD';
}

#mainservice {
/*  	font-family: 'Do Hyeon', sans-serif;  */
}

#mainservicediv1 {
	bottom: -7%;
}

#mainservicediv2{
	bottom: -2%;
}

#mainservicediv3{
	bottom: -2%;
}

#header {
	text-align: center;
	background-color: #ffffff;
}

#footer {
	text-align: center;
	height: 50px;
	margin-top: 35px;
	margin-bottom: 20px;
}

#wrapper {
	min-height: 500px;
	background-color: #ffffff;
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

.navbar-default {

    border-color: #ffffff;
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
	border-color: #2E64FE;
}

/* 각 게시판 리스트 높이 */
.caption-profile {
	height: 260px;
}
.caption-project {
	height: 260px;
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

#mainslide{
	width: 100%;
	height: 350px;
	background-color: #d8f1f9;
}

#mainboard {
	height: 1300px;
	padding-left: 60px;
}

#mainboard p {
	margin-bottom: 2px;
}

a#top {
    position: fixed;
    right: 5%;
    bottom: 50px;
    z-index: 999;
    font-size: 20px;
}

.badge {
    display: inline;
    min-width: 10px;
    padding: 4px 7px;
    font-size: 12px;
    font-weight: 700;
    line-height: 1;
    color: #fff;
    text-align: center;
    white-space: nowrap;
    vertical-align: baseline;
    background-color: #325adc;
    border-radius: 10px;
   	float: right;
}

#messagebadge {
	margin-top: 13px;
}

#badge {
	margin-top: 1px;
	margin-left:5px;
}

.btn-reply {
	height: 54px;
	width: 5%;
	padding: 0;
	margin-left: 0px;
	margin-right: 0px;
	float: left;
	border-top-left-radius: 0px;
	border-bottom-left-radius: 0px;
}

.reply-content {
	resize: none;
	width: 95%;
	display: inline;
	float: left;
	margin: 10px 0;
	border-top-right-radius: 0px;
	border-bottom-right-radius: 0px;
}

/* 메인 슬라이드 음영제거 */
.carousel-control.left {
	background-image: linear-gradient(to right,rgba(0,0,0,0) 0,rgba(0,0,0,0) 100%);
}

.carousel-control.right {
	background-image: linear-gradient(to right,rgba(0,0,0,0) 0,rgba(0,0,0,0) 100%);
}
/*                       */

#messagebox {
	border: 1px solid;
	border-color: #ccc;
	width: 390px;
	height: 460px;
	position: fixed;
	z-index: 1;
	background-color: #fff;
	right: 0;
	bottom: 0;	
	visibility: hidden;
}

#msgresult {
	overflow: auto;
	margin: 0;
	height: 345px;
	width: 348px;
	border: 1px solid;
	border-color: #ccc;
	margin-left: 20px;
}

#msg {
	width: 285px;
	height: 51px;
	margin: 0;
	margin-top: 10px;
}

#msgdate {
	color: #ccc;
}

#send {
	margin-right: -5px;
	margin-top: 0px;
}

#close {
	width:25px;
}

#drppdownA {
	padding-top:15px; 
	padding-bottom:15px;
}

#msgImage {
	width:50px; 
	height:50px;
}

#msgList { 
	list-style: none; 
	padding: 0; 
	margin: 0; 
} 
.messagelist { 
	margin: 7px; 
}
.dropdownA {
	padding-top: 15px;
	padding-bottom: 15px;
	text-decoration: none;
}
.messagelist img {
	width: 50px;
	height: 50px;
}
.messagelist span {
	color: black;
}

.spanmsg {
	text-decoration: none;
}

.spanmsg small {
	float: right;
}

.a {
	clear: both;
}

.chatplus { 
	text-align: center;
} 

#chatplusBtn {
	padding-right: 0;
	padding-left: 0;
	padding-bottom: 0px;
	padding-top: 0px;
	border: none;
	height: 15px;
	color: #2049dc;
	margin-top: 0;
	margin-left: 0;
	margin-right: 0;
}

#msgscroll {
	overflow: auto;
	visibility: visible;
}

#mainboardlist {
	width: 360px;
/* 	height: 210px; */
}

#searchform {
	width: 70%;
	margin-left: 57px;
}

#searchbtn {
	float: left;
}

#searchtable {
	width: 420px;
	height: 300px;
	margin-top: 50px;
	overflow: auto;
}

#serviceintro {
	border-color: #333;
    background-color: #00ff0000;
}

#serviceintro:hover {
	background-color: #fff;
}
</style>
</head>

<body class="headerfont">

<div id="header">
	<nav class="navbar navbar-default" id="header">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only"></span> <span class="icon-bar"></span> 
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				
				<a class="navbar-brand" href="/main">
					<img id="logo" alt="logo" src="/resources/logo2.png">
				</a>
			</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				
				<!-- 채팅창 -->	
					<div id="messagebox">
						<a class="close" id="closebtn" style="width: 25px;">x</a>
						<h4 style="padding-left: 25px;">채팅창</h4>
						
						<!-- 메세지 결과 창 -->	
						<div id="msgresult">
						
						
						</div>
						
						<!-- 메세지 작성 창 -->
						<input type="text" id="msg">
						
						<!-- 메세지 보내기 버튼 -->
						<input value="전송" type="button" class="btn btn-default" id="send">
						
						<hr>
					</div>
					
					
				<!-- 메뉴 -->
					<ul class="nav navbar-nav center" >
<!-- 					<li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li> -->
						<li><a href="/profileBoard/list">프로필 게시판</a></li>
						<li><a href="/projectBoard/list">프로젝트 게시판</a></li>
						<li><a href="/compBoard/list">완성된 프로젝트 게시판</a></li>
						<li><a href="/freeboard/list">자유게시판</a></li>
					</ul>

				<ul class="nav navbar-nav navbar-right">


				<c:choose>
					<c:when test="${not empty login }">	
 						<li role="presentation"> 					
 							<div class="dropdown">
								<button class="btn btn-default dropdown-toggle" type="button"
									id="btnAlertList" data-toggle="dropdown" aria-expanded="true">
									Alert <span class="badge" id="badge">7</span>
								</button>
								
								<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1" style="width: 400px;">
								
									<li class="close" style="width: 25px;">x</li>
								
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#" style="padding-top: 10px; padding-bottom: 10px;">새로운 알림</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#" style="padding-top: 10px; padding-bottom: 10px;">새로운 알림</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#" style="padding-top: 10px; padding-bottom: 10px;">새로운 알림</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#" style="padding-top: 10px; padding-bottom: 10px;">새로운 알림</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#" style="padding-top: 10px; padding-bottom: 10px;">새로운 알림</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#" style="padding-top: 10px; padding-bottom: 10px;">새로운 알림</a></li>
									<li role="presentation"><a role="menuitem" tabindex="-1"
										href="#" style="padding-top: 10px; padding-bottom: 10px;">새로운 알림</a></li>
								</ul>
							</div>
						</li>
						
						
						<li role="presentation"> 					
 							<div class="dropdown" id="dropdownMsg">
								<button class="btn btn-default dropdown-toggle" type="button"
									id="btnMessageList" data-toggle="dropdown" aria-expanded="true">
									Message <span class="badge" id="badge">5</span>
								</button>
								
<!-- 								메세지 목록 -->
<!-- 								<ul id="msgList" class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu2" style="width: 350px; height: 432px;"> -->
<!-- 									<li class="close" style="width: 25px;">x</li> -->
									
<!-- 									<li class="messagelist" role="presentation"> -->
<!-- 										<a role="menuitem" tabindex="-1" href="#" style="padding-top: 15px; padding-bottom: 15px;"> -->
<!-- 										<img src="/resources/gray.png" alt="" class="img-circle" style="width: 50px; height: 50px;"> -->
<%-- 										<input type="hidden" value="${chatList.chat_no }"> --%>
<%-- 										${chatList.chat_sender }님의 새로운 메세지 <small>${chatList.chat_sendtime }에 보냄</small> --%>
<!-- 										</a> -->
<!-- 									</li> -->
<!-- 								</ul> -->


								<div class="dropdown-menu" id="msgscroll" role="menu" aria-labelledby="dropdownMenu2" style="width: 350px; height: 432px;">
									<div class="chatplus">
										<button type="button" class="btn btn-default" data-toggle="modal" data-target=".bs-example-modal-md" id="chatplusBtn">새 메시지</button>
											<div class="modal fade bs-example-modal-md" tabindex="-1" role="dialog" aria-labelledby="myMiddleModalLabel" aria-hidden="true">
  												<div class="modal-dialog modal-md">
   													<div class="modal-content">
   													 	<div style="height: 500px;">
   													 		<br>
		    												<h4>메시지를 보낼 사용자 검색</h4>
		    												<hr>&emsp;&emsp;
		    												<input type="text" class="form-control col-lg-4" id="searchform">
		    												<button class="btn btn-default" id="searchbtn" type="button">검색</button>
		    												<br>
		    												
		    												<div class="container container-center" id="searchtable">
<!-- 			    												<table class="table table-striped table-hover text-center"> -->
			    												
<!-- 			    													<tr> -->
<!-- 			    														<th></th> -->
<!-- 			    														<th style="text-align: center;">이름</th> -->
<!-- 			    														<th style="text-align: center;">이메일</th> -->
<!-- 			    													</tr> -->
			    													
<!-- 			    													<tr style="text-align: center;" id="searchplusmsg"> -->
<!-- 			    														<td> -->
<!-- 																			<input type="checkbox" name="checkRow" id="checkRow"> -->
<!-- 																		</td> -->
<%-- 			    														<td>${name }</td> --%>
<%-- 			    														<td>${email }</td> --%>
<!-- 			    													</tr> -->
<!-- 																</table> -->
																
		    												</div>
															<button class="btn btn-default">메시지 보내기</button>
	    												</div> 
												    </div>
												</div>
											</div>
											
										<div class="close" style="width: 25px;">x</div>
									</div>
									<ul id="msgList" style="clear: both">
									
										<!-- 메세지 목록 -->
									
									</ul>
								</div>
							</div>
						</li>
					</c:when>	
				</c:choose>	
						
						
					<li>
					<c:choose>
						<c:when test="${not login }">
							<li><a href="/login">로그인</a></li>
							<li><a href="/insert">회원가입</a></li>
						</c:when>
						<c:otherwise>
							<ul style="list-style: none; padding-left: 5px; padding-right: 5px;">
								<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown">
														<c:choose>
														<c:when test ="${photo_storedname eq null }">
														<img id="headeruserimg" src="/resources/defaultuserphoto.png" class="img-circle" style="width: 50px; height: 50px;">
														</c:when>
														<c:otherwise>
														<img id="headeruserimg" src="/upload/${photo_storedname }" class="img-circle" style="width: 50px; height: 50px;">
														</c:otherwise>
														</c:choose>
											</a>
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
					
					<c:if test="${not empty login }">
						<li style="margin-top: 25px;">${name }님, 반갑습니다 :-)</li>
					</c:if>
					
				</ul>
			</div>
		</div>
	</nav>
</div>

	<div id="wrapper">