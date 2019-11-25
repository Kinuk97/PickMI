<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/layouts/header.jsp"></jsp:include>


<c:choose>
	<c:when test="${postno == 2 }">
	
	</c:when>
	<c:when test="${postno == 3 }">
		<jsp:include page="/WEB-INF/views/board/freeboard/list.jsp"></jsp:include>
	</c:when>
	<c:when test="${postno == 4 }">
	</c:when>
	<c:otherwise>
		<!-- postno가 1이거나 2,3,4가 아닐 때 -->
	</c:otherwise>
</c:choose>


<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>