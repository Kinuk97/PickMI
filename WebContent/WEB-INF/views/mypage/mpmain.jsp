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

// $('#testBtn1').click(function(e){
// 	$('#deleteModal').modal();
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

// --- 비밀번호수정 모달 ---
$(document).ready(function() {
	
	//경고 모달 호출 메서드
   function warningModal(content) {
      $(".modal-contents").text(content);
      $("#defaultModal").modal('show');
      console.log(11111111111);
   }

	$("#change").on("click", function(){
		// 비밀번호 입력
        if($("#pw").val() ==''){
        	warningModal('현재 비밀번호를 입력하세요');
            $("#pw").focus();
            return false;
        }
		
		// 현재 비밀번호 불일치
        if($("#pw").val() !== "${user.pw}"){
        	warningModal('현재 비밀번호가 일치하지 않습니다.');
            $("#pw").focus();
            console.log('${user.pw}');
            return false;
        }
		
        // 비밀번호 확인
        if($("#pw1").val() ==''){
        	warningModal('변경하실 비밀번호를 입력하세요');
            $("#pw1").focus();
            return false;
        }
		
        if($("#pw2").val() ==''){
        	warningModal('변경하실 비밀번호를 다시 한번 더 입력하세요');
            $("#pw2").focus();
            return false;
        }
        
       	 // 동일 비밀번호
		if(($("#pw").val()) == ($("#pw1").val())){
	    	warningModal('현재비밀번호와 다르게 입력하세요');
	    	return false;
		}
       	
		if(($("#pw1").val()) !== ($("#pw2").val())){
	    	warningModal('변경하실 비밀번호가 일치하지 않습니다');
	    	return false;
		}
		       	 
       	 $("#pwdchangeForm").submit();
	});
	
});



</script>




<script>
//탈퇴 확인 비밀번호 모달창

$(document).ready(function() {
	
	//경고 모달 호출 메서드
   function warningModal1(content) {
      $(".modal-contents1").text(content);
      $("#deleteModal").modal('show');

   }

	$("#change1").on("click", function(){
		
		// 비밀번호 입력
        if($("#pw3").val() ==''){
    		alert("비밀번호를 입력하세요")
        	//warningModal('현재 비밀번호를 입력하세요');
	
            $("#pw3").focus();
            return false;
        }
		
		// 현재 비밀번호 불일치
        if($("#pw3").val() !== "${user.pw}"){
			console.log(2);
			alert("비밀번호가 일치하지 않습니다.");
//         	warningModal1('현재 비밀번호가 일치하지 않습니다.');
            $("#pw3").focus();
            console.log('${user.pw}');
            return false;
        }
       	 $("#deleteForm").submit();
	});
	
});



</script>
    
    



<style type="text/css">
.modal-backdrop{
	z-index: -1;
}

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
						
			<!-- 비밀번호 수정 모달을 열기 위한 버튼 -->
			<br><br>
			<button type="button" class="btn btn-info" data-toggle="modal" data-target="#testModal" >
			비밀번호 수정
			</button>
			<!-- 모달 영역 -->
			<form action="/mypage/pwmodify" method="post" id="deleteForm">
			<div class="modal fade" id="testModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
			<div class="modal-content">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
			<h4 class="modal-title" id="myModalLabel">비밀번호 수정</h4>
			</div>
						
				<div class="modal-body">
				<input type="hidden" value="${USER.email }" id="email" name="email"/>
				<input type="hidden" value="${USER.userno }" id="userno" name="userno"/>
				
				<p>현재 비밀번호<input type="password" name="pw" id="pw"
				placeholder="현재 비밀번호를 입력하세요" required="required"></p> 
				<p>변경 비밀번호<input type="password" name="pw1" id="pw1"
				placeholder="수정 비밀번호를 입력하세요" required="required"></p> 
				<p>변경 비밀번호 확인<input type="password" name="password2"  name="pw2" id="pw2"
				placeholder="수정 비밀번호를 다시 입력하세요" required="required"></p>
				</div>
			<div class="modal-footer">
			<button type="submit" class="btn btn-primary" id="change">변경하기</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
			</div>
			</div>
			</div>
			</div>
			</form>
			
				<!--모달창 -->
	<div class="modal fade" id="defaultModal">
		<div class="modal-dialog">
			<div class="modal-content ">
				<div class="modal-header panel-heading">
					<h4 class="modal-title">알림</h4>
				</div>
				<div class="modal-body">
					<p class="modal-contents"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- 모달창 -->

	
	
			<!-- 회원탈퇴 확인을 위한 모달창 -->
			<!-- 모달을 열기 위한 버튼 -->
			<button type="button" class="btn btn-info" data-toggle="modal" data-target="#deleteModal" >
			회원 탈퇴
			</button>
			<!-- 모달 영역 -->
			<form action="/mypage/selfdelete" method="post" id="selfdelete">
			<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
			<div class="modal-content">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
			<h4 class="modal-title" id="myModalLabel">회원 탈퇴를 위해 비밀번호를 입력해 주세요</h4>
			</div>
						
				<div class="modal-body">
				<input type="hidden" value="${USER.email }" id="email2" name="email"/>
				<input type="hidden" value="${USER.userno }" id="userno2" name="userno"/>
				
				<p>현재 비밀번호<input type="password" name="pw" id="pw3"
				placeholder="현재 비밀번호를 입력하세요" required="required"></p> 
				</div>
			<div class="modal-footer">
			<button type="submit" class="btn btn-primary" id="change1">확인</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
			</div>
			</div>
			</div>
			</div>
			</form>
			
				<!--모달창 -->
	<div class="modal fade" id="deleteModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header panel-heading">
					<h4 class="modal-title">알림</h4>
				</div>
				<div class="modal-body">
					<p class="modal-contents1"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- 모달창 -->
			
					
<!-- 			<form action = "/mypage/selfdelete" method="post" name = "userdeletebtn"> -->
<!-- 			<button type="submit" class="btn btn-info">회원탈퇴</button> -->
<!-- 			</form> -->
		
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
			<a href="/mycomment/list">내가쓴댓글가져오기</a> 
			찜하기한글가져오기
		</div>

		<div class="inner_con3">
			<ul class="list-group">
			  <li class="list-group-item list-group-item-info"><p style="font-size: 25px; text-align: left;">프로젝트 관리</p></li>
			</ul>
			<hr>
			<a href="/mate/list" class="btn btn-info" role="button">팀원 관리하러 가기</a>
		</div>
		
		<div style="clear: both;"></div>
	</div>
</div> <!-- container -->
		
	
<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />