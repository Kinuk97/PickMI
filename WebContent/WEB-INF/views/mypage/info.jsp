<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>개인정보 수정</h1>
<hr>

<button>프로필사진변경</button> <button>프로필사진삭제</button>
<a href="my/pwmodify"><button>비밀번호 변경</button></a> <button>회원탈퇴</button> <button>확인(수정완료)</button>


<div class="container">

	<form action = "/mypage/info" method="post">
		이메일 : <input name = "email" value="${userinfo.email }">
		이름 : <input name = "username" value="${userinfo.name }">	
	</form>


</div>

</body>
</html>