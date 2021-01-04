<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<!-- Bootstrap CSS -->
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script> 
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<!-- font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<style>   
	p {
		font-family: 'Noto Sans KR', sans-serif;
		font-size: 1.25em;
	}
</style>  
<html>
<head>
<meta charset="UTF-8">
<title>패스워드 변경</title>
</head>

<script type="text/javascript">

function validation(){

	var username = $("#username").val();

	var admin = {
			username : username
	};
	
	if(!username){
		alert("관리자로 등록하고 싶은 아이디를 입력해주세요.");
		$("#username").focus();
		return false;
	}else {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "http://localhost:8081/api/edu/updateAdmin",
			data : admin,
			success: function(data){
				var item = data.data;

				if(item=="fail") alert("관리자 등록에 실패했습니다.");
				else {
					alert("관리자 등록 완료되었습니다.");
					window.location.href="http://localhost:8081/api/edu/readView";
				}		
			}, error:function(request,status,error){
			    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			   }
		});
	}
	
}

</script>

<body>
	<div class="container" align="center">
	<form id="pwChangeForm" method="post">
	
	  	<p style="padding:5%;">관리자로 등록하고 싶은 아이디를 입력해주세요.</p>
	  	<p><input type="text" id="username" name="username" class="form-control" style="width:20%;" autofocus></p>
	  <br>
	  <p><input type="button" class="btn btn-light" value="관리자 등록" onclick="validation()"></p>
	             
	</form>
	</div>
</body>
</html>