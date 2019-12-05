<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<style type="text/css">
	.feature_title{
		text-align: center;
	}
	.intro_chapter{
		text-align: center;
	}
	.all{
		width: 1000px;
	}
</style>

<script type="text/javascript">
    $(document).ready(function () {
        // popover demo
        $("a[data-toggle=popover]")
        .popover()
//         .click(function(e) {
//             e.preventDefault()
//         })
	
	// 프로필
	$("#profile").click(function(){
		$(location).attr("href", "/profileBoard/list");
	});
    
    // 프로젝트
	$("#project").click(function(){
		$(location).attr("href", "/projectBoard/list");
	});
	
    });
</script>
<div class="all container container-center">
<div class="intro_chapter">
	<div class="sub_title">
		<h4>Mate & Idea</h4>
	</div>
	<div class="sub_title2">
		<h1>"아이디, 사람을 찾습니다"</h1>
	</div>
	<div class="sub_title3">
		<p>PickMI는 프로젝트협업을 위한 사람과 사람을 이어주는 플랫폼입니다.</p> 
		<p>특별한 아이디어를 구현시켜줄 사람을 찾고 있다면,</p> 
		<p>특별한 아이디어가 없어도 함께 만들 사람을 찾고 있다면, PickMI에서 지금당장 시작해보세요.</p>
	</div>
	<br>
	
	<div>
		<button id="profile" class="btn btn-info">프로필 등록</button>
		<button id="project" class="btn btn-info">프로젝트 등록</button>
	</div>	
</div>
<br><br><br><br><br><br><br><br>

<div style="text-align: center;">
	<h3>프로젝트 협업을 도와줄 다양한 컨텐츠를 만나보세요.</h3>
	<br>
	<img src="/resources/bar.PNG">
</div>
<br><br><br><br><br><br>

<div class="sub_feature_t1">
	<div class="sub_feature" style="text-align: center;">
		<div class="sub_image">
			<a class="btn" data-toggle="popover" data-placement="top" title="지원" data-content="협업에 필요한 정보를 찾아보세요. 지원 정보를 한눈에 보실 수 있습니다.">
			<img id="logo" alt="logo" src="/resources/handshake.png"></a>
		</div>
		<div class="sub feature_contents">
			<div class="feature_title">지원</div>
		</div>
	</div>
	<div class="sub_feature" style="float: left; margin-left: 200px;">
		<div class="sub_image">
			<a class="btn" data-toggle="popover" data-placement="top" title="소통" data-content="서로 소통하세요. 아이디어와 프로젝트 공유를 통한 네트워킹은 향후 좋은 협업으로 이어질 수 있습니다.">
			<img id="logo" alt="logo" src="/resources/team.png"></a>
		</div>
		<div class="sub feature_contents">
			<div class="feature_title">소통</div>
		</div>
	</div>
	<div class="sub_feature " style="float: right; margin-right: 200px;">
		<div class="sub_image">
			<a class="btn" data-toggle="popover" data-placement="top" title="매거진" data-content="협업에 유용한 정보를 얻어가세요. 창업자 스타트없 디자이너, 개발자를 위한 유용한 정보들이 기다리고 있습니다.">
			<img id="logo" alt="logo" src="/resources/laptop.png"></a>
		</div>
		<div class="sub feature_contents">
			<div class="feature_title">매거진</div>
		</div>
	</div>
</div>
</div>


<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />
    