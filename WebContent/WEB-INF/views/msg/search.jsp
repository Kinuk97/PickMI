<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-striped table-hover text-center" id="msgtable">
<tr>
<th></th>
<th id="tableth">이름</th>
<th id="tableth">이메일</th>
</tr>
<c:forEach items="${searchList }" var="user">
<tr>
	<td><input type="checkbox" name="msgcheckbox" id="msgcheckbox" onclick="onecheckbox(this);" /></td>
	<td>${user.name }</td>
	<td>${user.email }</td>
</tr>
</c:forEach>
</table>