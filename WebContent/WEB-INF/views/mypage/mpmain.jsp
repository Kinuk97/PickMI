<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="./jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="./bootstrapt/css/bootstrap.min.css" />
<link rel="stylesheet" href="./bootstrapt/css/bootstrap.css" />
<script src="./bootstrapt/js/bootstrap.min.js"></script>

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
	position: absoulte;
	width: 500px;
	height: 55px;
	border: 1px solid gray;
	text-align: center;
	font-size: 45px;
}

.container1{
	position: absoulte;
	width: 100px;
	height: 100px;
	top:30%;
	left:10%;
	margin: -50px 0 0 -50px;
}
</style>

</head>
<body>
<div class="container">마이페이지</div>
	<div class="container1" style="width:1500px; height:800px; background:#49494A; text-align:center; position:absolute; ">
		<div class="inner_con1" style="display:inner-block; float:left; background:#FFFFFF; width:650px; height:300px;"> 
			
				이메일 : ${userinfo.email } <br>
				이름 : ${userinfo.name }	<br>
			
		 	<button>프로필사진변경</button>
		 	<button>프로필사진삭제</button> <br>
			
			<!-- 모달을 열기 위한 버튼 -->
			<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
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
			<button>회원탈퇴</button>
		</div>
		
		<div class="inner_con2" style="display:inner-block; float:right; background:#FFFFFF; width:650px; height:300px;">
			활동이력 <br><br><br><br>
			
			<a href="/mypage/boardwrite">내가쓴게시글가져오기</a> 
			내가쓴댓글가져오기
			찜하기한글가져오기
		</div>
		
		<div class="inner_con3" style="display:inner-block; float:left; background:#FFFFFF; width:650px; height:300px;">
		</div>
		<div class="inner_con4" style="display:inner-block; float:right; background:#FFFFFF; width:650px; height:300px;">
			프로젝트관리
		</div>
		
	</div>
		
	
<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />