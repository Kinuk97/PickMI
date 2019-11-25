<%@page import="dto.CompBoard" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>


<div class="container">
	<h2 class="text-center">프로필 게시판</h2>
	<div class="text-right">
		<button onclick="location.href='/compBoard/write';" class="btn btn-info">&emsp;&emsp;글쓰기&emsp;&emsp;</button>
	</div>
	<br>
	
	<!-- 상위 3개 -->
<c:forEach items="${compList }" var="compList">
		<div class="col-sm-6 col-md-4 col-lg-4">
			<div class="thumbnail">
				<div class="caption">
					<h3>상위 3개 넣을 것 - 찜한 수</h3>
					<p>...</p>
					<p>
						<a href="/compBoard/view?comp_no=${compList.comp_no }" class="btn btn-default" role="button">Button</a>
					</p>
				</div>
			</div>
		</div>
</c:forEach>
	<br>
	<br>
	<br>
	
<!-- 두번째 줄 -->
<c:forEach items="${compList }" var="compList">
		<div class="col-sm-6 col-md-4 col-lg-3">
			<div class="thumbnail">
				<div class="caption">
					<input type="checkbox" name="checkRow" id="checkRow" value="${compList.comp_no }">
					<h4>프로젝트 제목 : ${compList.comp_title }</h4>
					<p>팀 이름 : ${compList.comp_name }</p>
					<p>
						<a href="/compBoard/view?comp_no=${compList.comp_no }" class="btn btn-default" role="button">상세보기</a>
					</p>
				</div>
			</div>
		</div>
</c:forEach>
	


</div>
<jsp:include page="/WEB-INF/views/layouts/paging.jsp"/>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />