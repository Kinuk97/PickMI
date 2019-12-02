<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<script type="text/javascript">

var ws = null;

window.onload = function() {
	ws = new WebSocket("ws://localhost:8089/ws/msg");
	
	// 소켓열리면 
	ws.onopen = function() {
		//메세지보내는 메소드 send
		ws.send("hi");
	}
	
	
	setInterval(function() {
		ws.send("check");
	}, 1000)
	
	// 메세지를 받으면 
	// 매개변수가 받은 데이터 e
	ws.onmessage = function(e) {
// 		console.log("recieved")
		console.log(e.data)

		if( e.data != null ) {
			var jsonObj = JSON.parse(e.data);
			console.log(jsonObj)
	
			window.cnt.innerHTML = "<h1>"+jsonObj.cnt+"</h1>";
		}
	}
}

</script>

</head>
<body>
<div id="cnt"></div>
</body>
</html>