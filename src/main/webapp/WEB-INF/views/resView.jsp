<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<style>
	th {
		font-family: 'Noto Sans KR', sans-serif;
		font-weight: bold;
	}     
	td {
		font-family: 'Noto Sans KR', sans-serif;
	}   
</style>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

<article>
	<div class="container">
	<div class="table-responsive">
		<form id="boardForm" name="boardForm" method="post">
	        <table class="table table-striped table-sm">
	            <thead>
	                <tr>
	                    <th>회의실ID</th>
	                    <th>사용자ID</th>
	                    <th>예약자</th>
	                    <th>용건</th>
	                    <th>사용시작</th>
	                    <th>사용종료</th>
	                </tr>
	            </thead>
	            <tbody>
	            	<c:forEach var="res" items="${params}" varStatus="status">
	                    <tr>
	                        <td><c:out value="${res.meetingId}" /></td>
							<td><c:out value="${res.username}" /></td>
							<td><c:out value="${res.userNm}" /></td>
							<td><c:out value="${res.reason}" /></td>
							<td><fmt:formatDate value="${res.startTm}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td><fmt:formatDate value="${res.finishTm}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                    </tr>
	            	</c:forEach>
	            </tbody>
	        </table> 
    	</form>
    </div>
    </div>
</article>
		
</body>
</html>