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

// $('#testBtn').click(function(e){
// 	$('#testModal').modal();
// });
</script>

<script>
// 사용자 사진 업로드
function ajaxFileUpload() {
	
    // 업로드 버튼이 클릭되면 파일 찾기 창을 띄운다.
    jQuery("#ajaxFile").click();
}

function ajaxFileChange() {
    // 파일이 선택되면 업로드를 진행한다.
    ajaxFileTransmit();
}

function ajaxFileTransmit() {
    var form = jQuery("ajaxFrom")[0];
    var formData = new FormData(form);
    formData.append("message", "파일 확인 창 숨기기");
    formData.append("file", jQuery("#ajaxFile")[0].files[0]);

    jQuery.ajax({
          url : "/mypage"
        , type : "POST"
        , processData : false
        , contentType : false
        , data : formData
        , dataType : "text"
        , success:function(data) {
        	$("#profileImg").attr("src", "/upload/" + data);
        	$("#headeruserimg").attr("src", "/upload/" + data);
        }
    });
}
</script>

<script type="text/javascript">

//사용자 사진 업로드
function ajaxFileDelete() {
	
    // 버튼클릭
//     jQuery("#ajaxDelete").click();
    
    ajaxFileTansmit();

}

function ajaxFileDelete() {

    $.ajax({
          url : "/mypage/photodelete"
        , type : "POST"
        , dataType : "text"
        , success:function(data) {
        	$("#profileImg").attr("src", "/resources/defaultuserphoto.png");
        	$("#headeruserimg").attr("src", "/resources/defaultuserphoto.png");
        }
    	, error : function (e) {
    		console.log(e);
    	}
    });
}

</script>


    
    



<style type="text/css">

.myPageContainer {
	width: 95%;
}

.container1 {
	margin-left: auto;
	margin-right: auto;
	margin-top: 25px;
	width: 292px;
	height: 60px;
	border: 1px solid #ddd;
	text-align: center;
	font-size: 45px;
	 border-radius: 80px;
}

.box {
	width: 80%;
	min-height: 600px;
	backgorund:#49494A;
	text-align:center;
	border: 1px solid #ddd;
	border-radius: 80px;
}

.inner_con1{
	display: inner-block;
    float: left;
    width: 45%;
    height: 531px;
    border: 1px solid #ddd;
    box-sizing: border-box;
    margin: 50px;
    padding: 16px;
    border-radius: 40px;
}

.inner_con2{
	display: inner-block;
    float: right;
    background: #FFFFFF;
    width: 38%;
    height: 200px;
    padding: 16px;
    margin: 50px;
    border: 1px solid #ddd;
    margin-bottom: 30px;
    border-radius: 40px;
}

.inner_con3{
	display: inner-block;
    float: right;
    background: #FFFFFF;
    width: 38%;
    height: 300px;
    padding: 16px;
    margin: 50px;
    border: 1px solid #ddd;
    border-radius: 40px;
    margin-top: 0;
}

.img-responsive {
    display: block;
    max-width: 40%;
    height: auto;
    margin: auto;
}

#profileImg{
	width:144px;
	height:139px;
}

#wrapper{
	padding-top: 0px;
    padding-bottom: 0px
}

</style>

</head>
<body>
<div class="container myPageContainer">
	<div class="container text-center">
		<h1>😉마이페이지😉</h1>
	</div>
	<div class="container box">

		<div class="inner_con1" > 
			<ul class="list-group">
			  <li class="list-group-item list-group-item-info"><p style="font-size: 25px">나의정보</p></li>
			</ul>
			<hr>
			
			<c:choose>
			<c:when test ="${userinfo.photo_storedname eq null }">
			<p><img id="profileImg" src="/resources/defaultuserphoto.png" class="img-responsive img-circle" alt="Responsive image"></p>
			</c:when>
			<c:otherwise>
			<p><img id="profileImg" src="/upload/${userinfo.photo_storedname }" class="img-responsive img-circle" alt="Responsive image"></p>
			</c:otherwise>
			</c:choose>
			

   			 <!-- display:none으로 화면상에서 파일 확인 창을 숨겨둔다 -->
    		<input type="file" id="ajaxFile" onChange="ajaxFileChange();" style="display:none;" accept=".jpeg, .jpg, .png"/>
    		<input class="btn btn-info" type="button" onClick="ajaxFileUpload();" value="프로필사진 변경"/>
			
<!-- 			<input type="text" id="ajaxFile" onChange="ajaxFileChange();" style="display:none";/> -->
    		<input class="btn btn-info" type="button" onClick="ajaxFileDelete();" value="프로필사진 삭제"/>

<!-- 			<form action="/mypage/photodelete" method="post"> -->
<!-- 				<button type="submit" class="btn btn-info">프로필사진 삭제</button> -->
<!-- 			</form> -->
			
<!-- 			<form action="/mypage" method="post" enctype="multipart/form-data"> -->
<!-- 			<input type='file' id='file' name='file' /> -->
<!-- 			<button id='btn-upload' class="btn btn-info " onfocus="this.blur();">프로필사진변경</button> -->
<!-- 			<input class="btn btn-info" type="file" name="uploadFile"><button type="button" class="btn btn-info ">프로필사진 변경</button> -->
<!-- 		 	<button type= "submit">전송</button> -->
<!-- 			</form> -->
						
			<!-- 모달을 열기 위한 버튼 -->
			<br><br>
			<a href="/mypage/pwmodify"><button type="button" class="btn btn-info" data-toggle="modal" data-target="#testModal">
			비밀번호 수정
			</button></a>
			<!-- 모달 영역 -->
			<div class="modal fade" id="testModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
			<form action = "/mypage/selfdelete" method="post" name = "userdeletebtn">
			<button type="submit" class="btn btn-info">회원탈퇴</button>
			</form>
			<br>
			<p style="font-size: 17px; text-align:left; margin-left:5%;">이름 : ${userinfo.name }</p>
			<p style="font-size: 17px; text-align:left; margin-left:5%;">이메일 : ${userinfo.email }</p>
			
		</div>
		
		<div class="inner_con2">
			 <ul class="list-group">
			  <li class="list-group-item list-group-item-info"><p style="font-size: 25px; text-align: left;">활동이력</p></li>
			</ul>
<!-- 			<p style="text-align:left; font-size:25px">활동이력</p> -->
			<hr>
			<a href="/mypage/boardwrite">내가쓴게시글가져오기</a> 
			내가쓴댓글가져오기
			찜하기한글가져오기
		</div>

		<div class="inner_con3">
			<ul class="list-group">
			  <li class="list-group-item list-group-item-info"><p style="font-size: 25px; text-align: left;">프로젝트 관리</p></li>
			</ul>
			<hr>
		</div>
		
		<div style="clear: both;"></div>
	</div>
</div> <!-- container -->
		
	
<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />