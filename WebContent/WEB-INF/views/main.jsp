<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div id="mainslide" class="item active">
<!--       <img src="/resources/gray.png" alt="..."> -->
	      <div class="carousel-caption">
	      	<h2>PickMI(Mate&Idea)를 이용하실 땐,</h2>
			<h3>회원가입을 한 후 이용해보세요 :-)</h3>
	        <h3>더 많은 서비스를 이용할 수 있습니다.</h3>
	        <br>
	        <br>
	        <br>
	      </div>
    </div>
    <div id="mainslide" class="item">
<!--       <img src="/resources/gray.png" alt="..."> -->
      <div class="carousel-caption">
        <h3>게시판에 글을 업로드해보세요.</h3>
        <h3>팀원을 찾거나 아이디어를 공유할 수 있습니다.</h3>
        <br>
        <br>
        <br>
        <br>
      </div>
    </div>
    <div id="mainslide" class="item">
<!--       <img src="/resources/gray.png" alt="..."> -->
      <div class="carousel-caption">
		<h3>프로젝트와 일정관리를 해보세요.</h3>
        <h3>편리하게 팀원들과 일정을 공유할 수 있습니다.</h3>
        <br>
        <br>
        <br>
        <br>
      </div>
    </div>
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>


<!-- <div class="jumbotron" style="background-image: url(/resources/mainphoto.png)"> -->

<!-- 	<h3>좋은 아이디어를 공유하고, </h3> -->
<!-- 	<h3>좋은 팀원을 찾아보세요.</h3> -->
<!-- 	<h3>아이디어 공유, 소통 플랫폼</h3> -->
	
<!-- 	<p> -->
<!-- 		<a class="btn btn-primary" href="/serviceIntroduce" role="button">서비스 소개</a> -->
<!-- 	</p> -->
<!-- </div> -->
<br><br><br>
<div class="container" id="mainboard">

	<!-- 첫번째 줄 -->
	<h4>프로필 게시판</h4>
	<div class="row">
		<c:forEach items="${profileBoard }" var="pro">
			<div class="col-sm-6 col-md-4 col-lg-3">
				<div class="thumbnail"
					onclick="location.href='/profileBoard/view?prof_no=${pro.prof_no }'">
					<div class="caption caption-profile">
						<h4>${ pro.prof_no }</h4>
						<p>${ pro.username }</p>
						<p>${ pro.prof_interest }</p>
						<p>${ pro.prof_loc }</p>
						<p>${ pro.prof_job }</p>
						<p>${ pro.prof_state }</p>
						<p>${ pro.prof_career }</p>
						<p class="text-right">${ pro.prof_like }❤</p>
						<p>${ pro.prof_time }에작성</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<!-- 두번째 줄 -->
	<h4>프로젝트 게시판</h4>
	<div class="row">
		<!-- 			<div class="thumbnail"> -->
		<!-- 				<div class="caption"> -->
		<c:forEach var="board" items="${projectBoard }">
			<div class="col-sm-6 col-md-4 col-lg-4">
				<div class="thumbnail"
					onclick="location.href='/projectBoard/view?proj_no=${board.proj_no }'">
					<div class="caption caption-project">

						<h2>${board.proj_title }</h2>

						<p>지역 : ${board.proj_loc }</p>
						<p>경력 : ${board.proj_career }</p>
						<p>직업 : ${board.proj_job }</p>
						<p>진행상황 : ${board.proj_progress }</p>

						<div class="text-right">신청수 : ${board.proj_apply }</div>
						<div class="text-right">찜개수 : ${board.proj_like }</div>
						<div class="text-right">${board.proj_time }</div>
					</div>
				</div>
				<!-- 				</div> -->
				<!-- 			</div> -->
			</div>
		</c:forEach>
	</div>
	
	<h4>완성된 프로젝트 게시판</h4>
	<div class="row">
		<c:forEach items="${compBoard }" var="compList">
			<div class="col-sm-6 col-md-4 col-lg-4">
				<div class="thumbnail"
					onclick="location.href='/compBoard/view?comp_no=${compList.comp_no }'"
					id="compboardlist" style="cursor: pointer; hover: #ccc;">
					<div class="caption caption-comp">
						<h4>${compList.comp_no}.${compList.comp_title }</h4>
						<p>팀 이름 : ${compList.comp_name }</p>
						<br>
						<br>
						<p class="text-right" style="margin: 0 0 0px;">작성자 :
							${compList.userno }</p>
						<p class="text-right" style="margin: 0 0 0px;">조회수 :
							${compList.comp_view }</p>
						<p class="text-right" style="margin: 0 0 0px;">작성날짜 :
							${compList.comp_date }</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

	<!-- 네번째 줄 -->
	<h4>자유게시판</h4>
	<div class="row">
		<c:forEach items="${freeBoard }" var="free">
			<div class="col-sm-6 col-md-4 col-lg-4">
				<div class="thumbnail"
					onclick="location.href='/compBoard/view?free_no=${free.free_no }'"
					id="compboardlist" style="cursor: pointer; hover: #ccc;">
					<div class="caption caption-comp">
						<h4>${free.free_title }</h4>
						<p>${free.free_content }</p>
						<br>
						<br>
						<p class="text-right" style="margin: 0 0 0px;">
							작성자 : ${free.userno }</p>
						<p class="text-right" style="margin: 0 0 0px;">
							조회수 : ${free.views }</p>
						<p class="text-right" style="margin: 0 0 0px;">
							${free.free_time }</p>
					</div>
				</div>
			</div>
		</c:forEach>
		
	</div>
	
</div>


<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />
