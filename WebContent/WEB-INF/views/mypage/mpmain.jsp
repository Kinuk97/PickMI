<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<script type="text/javascript">

$('#myModal').on('shown.bs.modal', function () {
	  $('#myInput').focus()
});


$('#testBtn').click(function(e){
	e.preventDefault();
	$('#testModal').modal("show");
});

</script>

<style type="text/css">

.container{
	width:100%;
	position: relative;
}

.container1{
	margin-left: auto;
	margin-right: auto;
	margin-top: 25px;
	width: 292px;
	height: 60px;
	border: 1px solid #ddd;
	text-align: center;
	font-size: 45px;
	 border-radius: 5%;
}

.container2{
	width: 1500px;
	height: 800px;
	backgorund:#49494A;
	text-align:center;
	position: absolute;
	top: 50%;
	left: 50%;
	margin: 150px 0 0 -750px;
	border: 1px solid #ddd;
	border-radius: 5%;
}

.inner_con1{
	display: inner-block;
    float: left;
    width: 600px;
    height: 700px;
    border: 1px solid #ddd;
    box-sizing: border-box;
    margin: 50px;
    padding: 16px;
    border-radius: 5%;
}

.inner_con2{
	display: inner-block;
    float: right;
    background: #FFFFFF;
    width: 650px;
    height: 300px;
    padding: 16px;
    margin: 50px;
    border: 1px solid #ddd;
    border-radius: 5%;
}

.inner_con3{
	display: inner-block;
    float: right;
    background: #FFFFFF;
    width: 650px;
    height: 300px;
    padding: 16px;
    margin: 50px;
    border: 1px solid #ddd;
    border-radius: 5%;


}

.img-responsive {
    display: block;
    max-width: 40%;
    height: auto;
    margin: auto;
}

</style>

</head>
<body>
<div class="container">
	<div class="container1">마이페이지</div>
	<div class="container2">

		<div class="inner_con1" > 
			<ul class="list-group">
			  <li class="list-group-item list-group-item-info"><p style="font-size: 25px">나의정보</p></li>
			</ul>
			<hr>
			<p><img src="/resources/mainphoto.png" class="img-responsive img-circle" alt="Responsive image"></p>
			<p><button type="button" class="btn btn-info ">프로필사진 변경</button>
		 	<button type="button" class="btn btn-info ">프로필사진 변경</button></p>
			
			<ul class="list-group">
			  <li class="list-group-item list-group-item-info"><p style="font-size: 25px">이름 : ${userinfo.name }</p></li>
			  <li class="list-group-item list-group-item-info"><p style="font-size: 25px">이메일 : ${userinfo.email }</p></li>
			</ul>

			<p style="font-size: 25px">이름 : ${userinfo.name }</p>	
			<p style="font-size: 25px">이메일 : ${userinfo.email }</p>
			
			
			<!-- 모달을 열기 위한 버튼 -->
			<button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">
			비밀번호 수정
			</button>
			<!-- 모달 영역 -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
			<div class="modal-content">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
			<h4 class="modal-title" id="myModalLabel">비밀번호 수정</h4>
			</div>
			<form action="/mypage/pwmodify" method="post">
				<div class="modal-body">
				현재비밀번호<input type="password" name="password1"> <br>
				수정할비밀번호<input type="password" name="password2"> <br>
				수정할비밀번호 확인<input type="password" name="password2">
				</div>
			</form>
			<div class="modal-footer">
			<button type="button" class="btn btn-primary">확인</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
			</div>
			</div>
			</div>
			</div>
			
			<br>
			<button type="button" class="btn btn-info">회원탈퇴</button>
		</div>
		
		<div class="inner_con2">
			<p style="text-align:left; font-size:25px">활동이력</p>
			<hr>
			<a href="/mypage/boardwrite">내가쓴게시글가져오기</a> 
			내가쓴댓글가져오기
			찜하기한글가져오기
		</div>

		<div class="inner_con3">
			<p style=" text-align: left; font-size:25px;">프로젝트관리 <hr>
		</div>
		
	</div>
</div> <!-- container -->
		
	
<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />