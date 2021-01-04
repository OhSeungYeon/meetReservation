<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

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
</head>
<body>
<article>

	<div class="container">
	<div class="table-responsive">

		<form id="boardForm" name="boardForm" method="post">
        <table class="table table-striped table-sm">
        	<div id="thead">
            <thead>
                <tr>
                    <th>회의실ID</th>
                    <th>사용가능인원수</th>
                    <th>회의실명</th>
                    <th>회의실설명</th>
                    <th>등록한시간</th>
                    <th>등록자</th>
                    <th>수정한시간</th>
                    <th>수정자</th>
                </tr>
            </thead>
            </div>
            
            
            <tbody>
          		<c:forEach var="list" items="${params}" varStatus="status">
                    <tr>
                        <td><c:out value="${list.meetingId}" /></td>
                        
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