<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action = "/mypage/infomodify" method = "post">

email : <input type = "text" name = "email" value="${userinfo. email }">
비밀번호 : <input type = "password" name ="pw"> <br>
이름 : <input type = "text" name = "username" value="${userinfo. name }">

<input type="submit" value ="수정하기">

</form>

</body>
</html>