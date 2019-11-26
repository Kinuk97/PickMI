<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/layouts/header.jsp" />


<section class="content container-fluid">

	<div class="box box-primary">
		<div class="box-header with-border">
			<h2 class="box-title">프로젝트</h2>
		</div>
	
		<table class="table table-bordered">
			<tr>
				<td class="info">프로젝트 번호</td>
				<td colspan="3">입력해야함</td>
			</tr>

			<tr>
				<td class="info">프로젝트 제목</td>
				<td colspan="3">입력해야함</td>
			</tr>

			<tr>
				<td class="info">프로젝트 이름</td>
				<td colspan="3">입력해야함</td>
				<td class="info">작성시간</td>
				<td>입력해야함</td>
				
			</tr>

			<tr>
				<td class="info">경력</td>
				<td>입력해야함</td>
				<td class="info">참여인원</td>
				<td id="recommend">입력해야함</td>
				<td class="info">찜개수</td>
				<td id="recommend">입력해야함</td>
				
			</tr>

			<tr>
				<td class="info">시작날짜</td>
				<td id="recommend">입력해야함</td>
				<td class="info">마감날짜</td>
				<td id="recommend">입력해야함</td>
				<td class="info">모집기간</td>
				<td id="recommend">입력해야함</td>
			</tr>


		</table>

		<div class="form-group">
			<label>내용</label> <input type="text" class="form-control"
				readonly="readonly" style="height: 300px;" />
		</div>
	</div>


	<div class="box-footer">
		<c:if test="${email eq board.email }">
		<button type="submit" class="btn btn-warning">수정</button>
		<button type="submit" class="btn btn-danger">삭제</button>
		</c:if>
		<button type="submit" class="btn btn-primary">목록</button>
	</div>



</section>


<jsp:include page="/WEB-INF/views/layouts/footer.jsp" />