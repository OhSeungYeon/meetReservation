<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<style>   
	p {
		font-family: 'Noto Sans KR', sans-serif;
		font-size: 1.15em;
	}
</style>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>패스워드 변경</title>
</head>

<script type="text/javascript">

function validation(){

	var existPass = $("#existPass").val();
	var newPass = $("#newPass").val();
	var newPass1 = $("#newPass1").val();

	var pwChange = {
			existPass : existPass,
			newPass : newPass,
			newPass1 : newPass1,
	};
	
	if(!existPass){
		alert("현재 비밀번호를 입력하세요.");
		$("#existPass").focus();
		return false;
	}else if(!newPass || !newPass1){
		alert("새로운 비밀번호를 입력하세요.");
		$("#newPass").focus();
		return false;
	}else if(newPass != newPass1){
		alert("새 비밀번호 두개가 서로 일치하지 않습니다.");
		$("#newPass").focus();
		return false;
	}else {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "http://localhost:8081/api/edu/pwChange",
			data : pwChange,
			success: function(data){
				var item = data.data;

				if(item=="fail") alert("기존 패스워드가 틀렸습니다.");
				else {
					alert("비밀번호를 변경하였습니다.");
					updatePw();
				}		
			}, error:function(request,status,error){
			    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			   }
		});
	}
	
}

function updatePw() {

	var newPass = $("#newPass").val();
	var sessionId = $("#sessionId").val();

	var pwChange = {
		sessionId : sessionId,
		newPass : newPass
	};
	
	$.ajax({
		type : "POST",
		dataType : "json",
		url : "http://localhost:8081/api/edu/updatePw",
		data : pwChange,
		success: function(data){
			var item = data.data;

			if(item=="success")
				window.location.href="http://localhost:8081/api/edu/readView";	
		}, error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		   }
	});
}

</script>

<body>

<div class="container">
<form id="pwChangeForm" method="post">

  <p style="padding-left:10%;">
  	<table class="table">
		<tr>
			<td><p>로그인ID</p></td>
			<td><p><input type="text" id="sessionId" name="sessionId" value="${sessionScope.userId }" readonly></p></td>
		</tr>
		<tr>
			<td><p>현재 비밀번호*</p></td>
			<td><p><input type="password" id="existPass" name="existPass" autofocus></p></td>
		</tr>
		<tr>
			<td><p>새 비밀번호*</p></td>
			<td><p><input type="password" id="newPass" name="newPass"></p></td>
		</tr>
		<tr>
			<td><p>새 비밀번호(확인)*</p></td>
			<td><p><input type="password" id="newPass1" name="newPass1"></p></td>
		</tr>
		
	</table>
  </p>
  
  <p><input type="button" class="btn btn-light" value="비밀번호 변경" onclick="validation()"></p>
             
</form>
</div>

</body>
</html>