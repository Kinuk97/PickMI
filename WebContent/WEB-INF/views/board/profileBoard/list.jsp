<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="/WEB-INF/views/layouts/header.jsp"/>


<h1>hello , it's profile board list here!😉</h1>
<hr>

<c:forEach items="${ list }" var="pro">
	<div class="col-sm-6 col-md-4 col-lg-3">
		<div class="thumbnail">
			<div class="caption">
				<h3>${ pro.prof_no }</h3>
				<h4>${ pro.userno }</h4>
				<p>${ pro.prof_interest }</p>
				<p>${ pro.prof_loc }</p>
				<p>${ pro.prof_job }</p>
				<p>${ pro.prof_career }</p>
				<p>
					<a href="#" class="btn btn-primary" role="button">${ pro.prof_like }❤</a> 
<!-- 					<a href="#" class="btn btn-default" role="button">Button</a> -->
				</p>
			</div>
		</div>
	</div>
</c:forEach>
<div style="clear: both;"></div>

	




<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />
