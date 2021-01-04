<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="shortcut icon" href="..\..\..\resources3\images\gsitm2.ico" type="image/x-icon">
<link rel="icon" href="..\..\..\resources3\images\gsitm2.ico" type="image/x-icon">
<!-- font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
  
<!DOCTYPE html>
<html>
<head>
<style>
body {
   text-align: center;
   font-family: 'Noto Sans KR', sans-serif;
   font-size: 2.3em;
   color: black;
   background: #BCCDD2;
}
.loginButton {
   position: relative;
   vertical-align: top;
   width: 50%;
   height: 54px;
   padding: 0;
   font-size: 22px;
   color: black;
   text-align: center;
   background: #CED8F6;
   border: 0;
   border-bottom: 2px solid #A9D0F5;
   border-radius: 5px;
   cursor: pointer;
   -webkit-box-shadow: inset 0 -2px #A9D0F5;
   box-shadow: inset 0 -2px #D76B60;
}

.loginButton:active {
   top: 1px;
   outline: none;
   -webkit-box-shadow: none;
   box-shadow: none;
}
#content {
	line-height:250px;
}

a:link { color: black; text-decoration: none;}
a:visited { color: black; text-decoration: none;}
a:hover { color: black; text-decoration: none;}

</style>
<meta charset="UTF-8">
<title>환영합니다</title>
</head>
<body>
	<div id="content">
		<p>회원가입 되었습니다!</p>
		<button type="button" class="loginButton"><a href='/api/login/login'>로그인 창으로</a></button>
	</div>
</body>
</html>