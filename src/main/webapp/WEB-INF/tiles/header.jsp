<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400&display=swap" rel="stylesheet">

<style>
	A:link {text-decoration:none; color:#04B45F;}
	A:visited {text-decoration:none; color:#04B45F;}
	A:active {text-decoration:none; color:#04B45F;}
	A:hover {text-decoration:none; color:#04B45F;}
	#button {
		font-family: 'Noto Sans KR', sans-serif;
	}
</style>
</head>
<body> 

<div class="header">

	<nav class="navbar navbar-expand-sm">
	
	  <div class="container-fluid">
		
		  <ul class="nav-link navbar-left">
		  	<a href="<c:url value='/api/edu/readView' />">
		  	<img src="..\..\..\resources3\images\gsitm.jpg" id="logo_custom" width="30%" alt="logo" style="padding-top:3%;">
		  	</a>
		  </ul>
			
		    <ul class="nav navbar-nav navbar-left" style="padding-left:0%;padding-right:10%;">
		      <li class="active"><a class="nav-link mr-3" id="button" href="<c:url value='/api/edu/readView' />" style="font-size:1.4em;">회의실</a></li>
		      <li class="active"><a class="nav-link mr-3" id="button" href="<c:url value='/api/edu/res' />" style="font-size:1.4em">회의실예약</a></li>
		      <li class="active"><a class="nav-link mr-3" id="button" href="<c:url value='/api/edu/user' />" style="font-size:1.4em">사용자</a></li>
		      <li class="active"><a class="nav-link mr-3" id="button" href="<c:url value='/api/edu/manager' />" style="font-size:1.4em">관리자등급</a></li>    
		    </ul>
		    
		    <ul class="nav navbar-nav navbar-right">
		      <li><p id="button" style="font-size:1.1em;padding-top:15%;">${sessionScope.userId}님</p></li>
		      <li><a class="nav-link" id="button" href="<c:url value='/api/login/signup' />" style="font-size:1.3em"><span class="glyphicon glyphicon-user mr-3"></span>회원가입</a></li>
		      <li><a class="nav-link" id="button" href="<c:url value='/api/login/logout_process' />" style="font-size:1.3em"><span class="glyphicon glyphicon-log-in mr-3"></span>로그아웃</a></li>
		    </ul>
	    
	  </div>
	    
	</nav>

</div>

</body>
</html>