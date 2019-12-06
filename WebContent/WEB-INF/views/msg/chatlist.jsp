<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${chattingList }" var="chat">
<c:if test="${chat.username ne name}">
<span style="float: left;"><br>${chat.username } :  ${chat.chat_msg }<br><small>${chat.chat_sendtime }</small></span><br><br><br>
</c:if>
<c:if test="${chat.username eq name}">
<span style="float: right;	"><br>나 :  ${chat.chat_msg }<br><small>${chat.chat_sendtime }</small></span><br><br><br>
</c:if>
</c:forEach>