<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!-- favicon -->
<link rel="shortcut icon" href="..\..\..\resources3\images\gsitm2.ico" type="image/x-icon">
<link rel="icon" href="..\..\..\resources3\images\gsitm2.ico" type="image/x-icon">

<!DOCTYPE html>
<!-- Bootstrap CSS -->
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
  
<!-- font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400&display=swap" rel="stylesheet">

<html>
<head>

<script src="https://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script> 
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<style>
body {
	background-color:#BCCDD2;
}
</style>

<script type="text/javascript">

function signup() {
	window.location.href="http://localhost:8081/api/login/signup";
}

</script>
<title>로그인창</title>
</head>
<body>
    <div id="login">
    
    <div align="center" style="padding-top:7%;">
    	<img src="..\..\..\resources3\images\gstim3.png" width="300px" height="100px" align="center">
    </div>
    
        <h3 class="text-center text-white pt-5" style="font-family: 'Noto Sans KR', sans-serif;">회의실예약관리시스템</h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        
                        <form id="login-form" class="form" method="post" action="/api/login/login_process">
                            <h3 class="text-center text-info" style="font-family: 'Noto Sans KR', sans-serif; font-weight:bold;">로그인</h3>
                            <div class="form-group">
                                <label for="username" class="text-info" style="font-family: 'Noto Sans KR', sans-serif;">아이디:</label><br>
                                <input type="text" name="username" id="username" class="form-control" style="font-family: 'Noto Sans KR', sans-serif;">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info" style="font-family: 'Noto Sans KR', sans-serif;">비밀번호:</label><br>
                                <input type="password" name="password" id="password" class="form-control" style="font-family: 'Noto Sans KR', sans-serif;">
                            </div>
                            <div class="form-group">
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="로그인" style="font-family: 'Noto Sans KR', sans-serif;">
                            </div>
                            <div id="signup" class="text-right">
                                <input type="button" name="submit" onclick="signup()" class="btn btn-info btn-md" value="회원가입" style="font-family: 'Noto Sans KR', sans-serif;">
                            </div>
                            <input name="${_csrf.parameterName }" type="hidden" value="${_csrf.token}">
                            <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
							    <font color="red">
							        아이디나 비밀번호가 일치하지 않습니다.
							        <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
							    </font>
							</c:if>
                        </form>
                    
                    </div>
                </div>
            </div>
        </div>
    </div> 
</body>
</html>