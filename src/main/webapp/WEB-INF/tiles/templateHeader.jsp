<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<link rel="shortcut icon" href="..\..\..\resources3\images\gsitm2.ico" type="image/x-icon">
<link rel="icon" href="..\..\..\resources3\images\gsitm2.ico" type="image/x-icon">

    <meta charset="UTF-8">
    <title>변경</title>
    <style>
        #header{        
            width:100%;
            height:30%;
            font-weight:bold;
        }
        #main{
            width:100%;
      		float:center;
            align:center;
            padding-top:5%;
        }
        
    </style>
    

</head>
<body>
    <div style="width:100%; height:100%;">
	    <div id="header"><tiles:insertAttribute name="header" /></div>
	    <hr>
	    <div id="main"><tiles:insertAttribute name="body" /></div>   
    </div>  
</body>
</html>