<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
@font-face { 
	font-family: 'KHNPHD'; 
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/KHNPHD.woff') format('woff'); 
	font-weight: normal; 
	font-style: normal; 
}

#userupdatefont {
	font-family:'KHNPHD';
}
</style>
</head>

<body id="userupdatefont">

<form action = "/mypage/infomodify" method = "post">

email : <input type = "text" name = "email" value="${userinfo. email }">
비밀번호 : <input type = "password" name ="pw"> <br>
이름 : <input type = "text" name = "username" value="${userinfo. name }">

<input type="submit" value ="수정하기">

</form>

</body>
</html>