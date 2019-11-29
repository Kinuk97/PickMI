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
		<div class="col-sm-6 col-md-4 col-lg-4">
			<div class="thumbnail">
				<div class="caption">
					<h3>Thumbnail label</h3>
					<p>...</p>
					<p>
						<a href="#" class="btn btn-primary" role="button">Button</a> <a
							href="#" class="btn btn-default" role="button">Button</a>
					</p>
				</div>
			</div>
		</div>
		<div class="col-sm-6 col-md-4 col-lg-4">
			<div class="thumbnail">
				<div class="caption">
					<h3>Thumbnail label</h3>
					<p>...</p>
					<p>
						<a href="#" class="btn btn-primary" role="button">Button</a> <a
							href="#" class="btn btn-default" role="button">Button</a>
					</p>
				</div>
			</div>
		</div>
		<div class="col-sm-6 col-md-4 col-lg-4">
			<div class="thumbnail">
				<div class="caption">
					<h3>Thumbnail label</h3>
					<p>...</p>
					<p>
						<a href="#" class="btn btn-primary" role="button">Button</a> <a
							href="#" class="btn btn-default" role="button">Button</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	<!-- 두번째 줄 -->
	<h4>프로젝트 게시판</h4>
	<div class="row">
		<div class="col-sm-6 col-md-4 col-lg-4">
			<div class="thumbnail">
				<div class="caption">
					<h3>Thumbnail label</h3>
					<p>...</p>
					<p>
						<a href="#" class="btn btn-primary" role="button">Button</a> <a
							href="#" class="btn btn-default" role="button">Button</a>
					</p>
				</div>
			</div>
		</div>
		<div class="col-sm-6 col-md-4 col-lg-4">
			<div class="thumbnail">
				<div class="caption">
					<h3>Thumbnail label</h3>
					<p>...</p>
					<p>
						<a href="#" class="btn btn-primary" role="button">Button</a> <a
							href="#" class="btn btn-default" role="button">Button</a>
					</p>
				</div>
			</div>
		</div>
		<div class="col-sm-6 col-md-4 col-lg-4">
			<div class="thumbnail">
				<div class="caption">
					<h3>Thumbnail label</h3>
					<p>...</p>
					<p>
						<a href="#" class="btn btn-primary" role="button">Button</a> <a
							href="#" class="btn btn-default" role="button">Button</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	<!-- 세번째 줄 -->
	<h4>자유게시판</h4>
	<div class="row">
		<div class="col-sm-6 col-md-4 col-lg-4">
			<div class="thumbnail">
				<div class="caption">
					<h3>Thumbnail label</h3>
					<p>...</p>
					<p>
						<a href="#" class="btn btn-primary" role="button">Button</a> <a
							href="#" class="btn btn-default" role="button">Button</a>
					</p>
				</div>
			</div>
		</div>
		<div class="col-sm-6 col-md-4 col-lg-4">
			<div class="thumbnail">
				<div class="caption">
					<h3>Thumbnail label</h3>
					<p>...</p>
					<p>
						<a href="#" class="btn btn-primary" role="button">Button</a> <a
							href="#" class="btn btn-default" role="button">Button</a>
					</p>
				</div>
			</div> 
		</div>
		<div class="col-sm-6 col-md-4 col-lg-4">
			<div class="thumbnail">
				<div class="caption">
					<h3>Thumbnail label</h3>
					<p>...</p>
					<p>
						<a href="#" class="btn btn-primary" role="button">Button</a> <a
							href="#" class="btn btn-default" role="button">Button</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	
</div>


<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />
