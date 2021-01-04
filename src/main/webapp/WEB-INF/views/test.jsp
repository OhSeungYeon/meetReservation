<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>회의실</title>
</head>
<body>
 <hr>
	<article>
	<div class="container">
	<div class="table-responsive">
		<form id="boardForm" name="boardForm" method="post">
        <table class="table table-striped table-sm">
            <thead>
                <tr>
                    <th>
                    <a href='/api/edu/res'>회의실ID</a>
                    </th>
                    <th>사용가능인원수</th>
                    <th>회의실명</th>
                    <th>회의실설명</th>
                    <th>등록한시간</th>
                    <th>등록자</th>
                    <th>수정한시간</th>
                    <th>수정자</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="list" items="${params}" varStatus="status">
                    <tr>
                        <td>
                        	
                        	<c:choose>
                        		<c:when test="${list.meetingId eq 'M001' }">
                        		<a href='/api/edu/res/M001'><c:out value="${list.meetingId}" /></a>
                        		</c:when>
                        		<c:when test="${list.meetingId eq 'M002' }">
                        		<a href='/api/edu/res/M002'><c:out value="${list.meetingId}" /></a>
                        		</c:when>
                        		<c:when test="${list.meetingId eq 'M003' }">
                        		<a href='/api/edu/res/M003'><c:out value="${list.meetingId}" /></a>
                        		</c:when>
                        		<c:otherwise>
                        		<a href='/api/edu/res/M004'><c:out value="${list.meetingId}" /></a>
                        		</c:otherwise>
                        	</c:choose>
                        
                        </td>
                        
                        <td><c:out value="${list.avaPerson}" /></td>
						<td><c:out value="${list.meetingNm}" /></td>
						<td><c:out value="${list.meetingEtc}" /></td>
						<td><fmt:formatDate value="${list.regDt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td><c:out value="${list.regId}" /></td>
						<td><fmt:formatDate value="${list.modDt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td><c:out value="${list.modId}" /></td>
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