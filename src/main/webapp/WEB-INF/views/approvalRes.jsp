<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<title>회의실예약상태</title>

<script type="text/javascript">

//승인버튼 함수
function approvalButton(i) {

	var userNm = $('#userNm' + i).val();
	
	
	var id = {
		userNm : userNm
	};
	
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"http://localhost:8081/api/edu/updateRes",
		data:id,
		success : function(data) {
			var item = data.data;
			
			if(item == "success") {
				alert("승인되었습니다.");
				window.location.href="http://localhost:8081/api/edu/readView";
			} else {
				alert("승인실패하였습니다.");
			}
		}, error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		   }
	});
	
} 


</script>

</head>
<body>
	<article>
	<div class="container">
		<form id="boardForm" name="boardForm" method="post">
        <table class="table">
            <thead>
                <tr>
                	<th>번호</th>
                    <th>회의실ID</th>
                    <th>사용자ID</th>
                    <th>예약자</th>
                    <th>용건</th>
                    <th>사용시작</th>
                    <th>사용종료</th>
                    <th>승인하기</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach var="res" items="${params}" varStatus="status">
                    <tr>
                    	<td><input type="text" name="number" id="number" value="<c:out value="${status.count}" />" style="border:none;outline:none;width:50px;"></td>
                        <td><c:out value="${res.meetingId}" /></td>
						<td><c:out value="${res.username}" /></td>
						<td>
						<input type="text" name="userNm<c:out value="${status.count}" />" id="userNm<c:out value="${status.count}" />" value="<c:out value="${res.userNm}" />" style="border:none;outline:none;width:100px;"></td>
						<td><c:out value="${res.reason}" /></td>
						<td><fmt:formatDate value="${res.startTm}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td><fmt:formatDate value="${res.finishTm}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<!-- 승인버튼 -->
						<td><input type="button" onclick="approvalButton(<c:out value="${status.count}" />)" value="승인" class="btn btn-block-sm" ></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </form>
    </div>
    </article>	
</body>
</html>