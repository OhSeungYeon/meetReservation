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
  
<html>
<head>

<script src="https://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script> 
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<style>
body {
	background-color:#BCCDD2;
	align:center;
}
</style>

<script type="text/javascript">

</script>
<title>로그인창</title>
</head>
<body>

    <div id="main">
        <h3 class="text-center text-white pt-5">회의실예약관리시스템</h3>
       	<sec:authorize access="isAnonymous()">
		    <h5><a href='<c:url value="/api/login/login"/>'>LOGIN</a> 로그인 해주세요.</h5>
		</sec:authorize>
    </div>

	 
</body>
</html>