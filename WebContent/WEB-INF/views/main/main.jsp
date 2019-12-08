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
      <img src="/resources/desk.png" style="width : 100%; height: 400px; filter: opacity(0.5);">
	      <div class="carousel-caption" id="mainservicediv1">
	      	<h2 id="mainservice">"PickMI Service Guide"</h2>
	      	<h3 id="mainservice">PickMI(Mate&amp;Idea)를 이용하실 땐, 회원가입을 한 후 이용해보세요 :-)</h3>
			<h3 id="mainservice">간편한 회원가입을 통해서 더 많은 서비스를 이용하실 수 있습니다.</h3>
	        <button class="btn btn-default" onclick="location.href='/main/intro'" id="serviceintro">&emsp;&emsp;서비스 소개&emsp;&emsp;</button>
	        <br>
	        <br>
	        <br>
	        <br>
	      </div>
    </div>
    <div id="mainslide" class="item">
      <img src="/resources/note.png" style="width : 100%; height: 400px; filter: opacity(0.5);">
      <div class="carousel-caption" id="mainservicediv2">
      	<h2 id="mainservice">"PickMI Service Guide"</h2>
        <h3 id="mainservice">여러 게시판에 글을 작성하여 업로드해보세요.</h3>
        <h3 id="mainservice">프로젝트 또는 팀원을 발견하거나 아이디어를 공유할 수 있습니다.</h3>
        <br>
        <br>
        <br>
        <br>
      </div>
    </div>
    <div id="mainslide" class="item">
      <img src="/resources/work.png" style="width : 100%; height: 400px; filter: opacity(0.5);">
      <div class="carousel-caption" id="mainservicediv3">
      	<h2 id="mainservice">"PickMI Service Guide"</h2>
		<h3 id="mainservice">팀원들과 함께 프로젝트와 일정관리를 해보세요.</h3>
        <h3 id="mainservice">편리한 PickMI서비스를 통해 팀원들과 일정을 공유할 수 있습니다.</h3>
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

<br><br><br>
<div class="container" id="mainboard">

<p style="font-size: 27px;">최근 게시물&nbsp;♬</p>
	<!-- 첫번째 줄 -->
	<h3>프로필 게시판</h3>
	<div class="row">
		<c:forEach items="${profileBoard }" var="pro">
			<div class="col-sm-6 col-md-4 col-lg-4" id="mainboardlist">
				<div class="thumbnail"
					onclick="location.href='/profileBoard/view?prof_no=${pro.prof_no }'">
					<div class="caption">
						<h4>${ pro.username }</h4>
						<p>${ pro.prof_interest }</p>
						<p>${ pro.prof_loc }</p>
						<p>${ pro.prof_job }</p>
						<p>${ pro.prof_state }</p>
						<p>${ pro.prof_career }</p>
						<p class="text-right">${ pro.prof_like}❤</p>
						<p class="text-right">${ pro.prof_time }에작성</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<!-- 두번째 줄 -->
	<h3>프로젝트 게시판</h3>
	<div class="row">
		<!-- 			<div class="thumbnail"> -->
		<!-- 				<div class="caption"> -->
		<c:forEach var="board" items="${projectBoard }">
			<div class="col-sm-6 col-md-4 col-lg-4" id="mainboardlist">
				<div class="thumbnail"
					onclick="location.href='/projectBoard/view?proj_no=${board.proj_no }'">
					<div class="caption">

						<h4>${board.proj_title }</h4>

						<p>지역 : ${board.proj_loc }</p>
						<p>경력 : ${board.proj_career }</p>
						<p>직업 : ${board.proj_job }</p>
						<p>진행상황 : ${board.proj_progress }</p>

						<div class="text-right">${board.proj_like }💙</div>
						<div class="text-right">작성자 : ${board.username }</div>
						<div class="text-right">${board.proj_time }</div>
					</div>
				</div>
				<!-- 				</div> -->
				<!-- 			</div> -->
			</div>
		</c:forEach>
	</div>
	
	<h3>완성된 프로젝트 게시판</h3>
	<div class="row">
		<c:forEach items="${compBoard }" var="compList">
			<div class="col-sm-6 col-md-4 col-lg-4" id="mainboardlist">
				<div class="thumbnail">
					<div class="caption" onclick="location.href='/compBoard/view?comp_no=${compList.comp_no }'">
						<h4 class="overtext">프로젝트 명 : ${compList.comp_title }</h4>
						<div class="comp_name overtext">팀 이름 : ${compList.comp_name }</div>
						<br>
						<br>
						<div class="text-right">${compList.comp_like }개의 👍</div>
						<div class="text-right">조회수 : ${compList.comp_view }</div>
						<div class="text-right">작성자 : ${compList.username }</div>
						<div class="text-right">작성날짜 : ${compList.comp_date }</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

	<!-- 네번째 줄 -->
	<h3>자유게시판</h3>
	<div class="row">
		<c:forEach items="${freeBoard }" var="free">
			<div class="col-sm-6 col-md-4 col-lg-4" id="mainboardlist">
				<div class="thumbnail"
					onclick="location.href='/freeboard/view?free_no=${free.free_no }'"
					id="compboardlist" style="cursor: pointer; hover: #ccc;">
					<div class="caption">
						<h4>${free.free_title }</h4>
						<p>${free.free_content }</p>
						<br>
						<br>
						<p class="text-right" style="margin: 0 0 0px;">
							작성자 : ${free.username }</p>
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

<div style="clear: both;"></div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />
