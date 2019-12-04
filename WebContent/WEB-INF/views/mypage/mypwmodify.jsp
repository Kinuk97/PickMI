<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<script type="text/javascript">


</script>

<title>Insert title here</title>
</head>
<body>

<form action="/mypage/pwmodify" method="post">
현재 비밀번호 : <input type="password" name="curpw"/>
수정 비밀번호 : <input type="password" name="modpw"/>
수정 비밀번호 확인 : <input type="password" name="modpw"/>
<button>전송</button>
</form>

</body>
</html>