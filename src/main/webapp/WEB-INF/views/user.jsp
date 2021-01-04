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
                    <th>사용자ID</th>
                    <th>관리자등급</th>
                    <th>이름</th>
                    <th>부서</th>
                    <th>전화번호</th>
                    <th>이메일</th>
                    <th>직급</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${params}" varStatus="status">
                    <tr>
                        <td><c:out value="${user.username}" /></td>
                        <td><c:out value="${user.managerLe}" /></td>
						<td><c:out value="${user.userNm}" /></td>
						<td><c:out value="${user.department}" /></td>
						<td><c:out value="${user.phoneNum}" /></td>
						<td><c:out value="${user.email}" /></td>
						<td><c:out value="${user.position}" /></td>
                    </tr>
            </c:forEach>
            
            </tbody>
        </table>
        
        <div class="paging" style="display: block; text-align: center;">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<c:if test="${paging.startPage != 1 }">
						<li class="page-item">
							<a class="page-link" href="/api/edu/user?nowPage=${paging.startPage - 1 }" tabindex="-1"> 
								Previous 
							</a>
						</li>
					</c:if>
					<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
						<c:choose>
							<c:when test="${p == paging.nowPage }">
								<li class="page-item">
									<a class="page-link" href="/api/edu/user?nowPage=${p}"> 
										<b>${p}</b>
									</a>
								</li>
							</c:when>
							<c:when test="${p != paging.nowPage }">
								<li class="page-item">
									<a class="page-link" href="/api/edu/user?nowPage=${p}"> 
										${p}
									</a>
								</li>
							</c:when>
						</c:choose>
					</c:forEach>
					<c:if test="${paging.endPage != paging.lastPage}">
						<li class="page-item">
							<a class="page-link" href="/api/edu/user?nowPage=${paging.endPage+1 }"> Next </a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>
        
    	</form>
    </div>
    </div>
</article>
		
</body>
</html>