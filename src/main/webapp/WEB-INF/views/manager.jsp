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
<title>회의실예약</title>
</head>
<body>
	<article>
	<div class="container">
	<div class="table-responsive">
		<form id="boardForm" name="boardForm" method="post">
        <table class="table table-striped table-sm">
            <thead>
                <tr>
                    <th>관리자등급</th>    
                    <th>관리자구분</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="manager" items="${params}" varStatus="status">
                    <tr>
                        <td><c:out value="${manager.managerLe}" /></td>
                        <td>
                        	<c:choose>
                        		<c:when test="${manager.managerLe eq 'A001' }">
                        			사용자
                        		</c:when>
                        		<c:otherwise>
                        			관리자
                        		</c:otherwise>
                        	</c:choose>
                        </td>
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