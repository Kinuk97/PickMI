<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div style="text-align: center;">
	<ul id="pagingUl" class="pagination pagination-sm">
	
	<!-- 처음으로 가기 -->
	<c:if test="${paging.curPage ne 1 }">
	<li>
		<a href="/freeboard/view?boardno=${board.free_no }">&larr;맨앞</a>
	</li>
	</c:if>
	
	<c:if test="${paging.curPage eq 1 }">
		<li class="disabled"><a>&larr;맨앞</a></li>
	</c:if>
	


	<!-- 이전 페이징 리스트로 가기 -->
	<c:if test="${paging.startPage gt paging.pageCount }">
	<li>
		<a href="/freeboard/view?boardno=${board.free_no }&curPage=${paging.startPage - paging.pageCount }">&laquo;</a>
	</li>
	</c:if>
	
	<c:if test="${paging.startPage le paging.pageCount }">
		<li class="disabled"><a>&laquo;이전</a></li>
	</c:if>


	<!-- 이전 페이지로 가기 -->
	<c:if test="${paging.curPage ne 1 }">
	<li>
		<a href="/board/list?curPage=${paging.curPage-1 }">&lt;</a>
	</li>
	</c:if>
	
	<c:if test="${paging.curPage eq 1 }">
		<li class="disabled"><a>&lt;</a></li>
	</c:if>
	
	
	<!-- 페이징 리스트 -->
	<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="i">
	
		<c:choose>
			<c:when test="${paging.curPage eq i }">
				<li class="active"><a href ="#reply">${i }</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="#reply">${i }</a></li>
			</c:otherwise>
		</c:choose>
		
	</c:forEach>
	
	
	<!--  다음 페이징 리스트로 가기 -->
	<c:if test="${paging.endPage ne paging.totalPage }">
	<li>
		<a href="#reply" >&raquo;</a>
<%-- 		curPage=${paging.startPage + paging.pageCount } --%>
	</li>
	</c:if>
	
	<c:if test="${paging.endPage eq paging.totalPage }">
		<li class="disabled"><a>다음&raquo;</a></li>
	</c:if>


	<!-- 끝 페이지로 가기 -->
	<c:if test="${paging.curPage ne paging.totalPage }">
		<li><a href="/freeboard/view?free_no=${board.free_no }&curPage=${paging.totalPage }">맨뒤&rarr;</a></li>
	</c:if>
	
	<c:if test="${paging.curPage eq paging.totalPage }">
		<li class="disabled"><a>맨뒤&rarr;</a></li>
	</c:if>
	
	</ul>

</div>














