<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
<head>

<link href="/resources/css/style.css" rel="stylesheet">
<link rel="shortcut icon" href="..\..\..\resources3\images\gsitm2.ico" type="image/x-icon">
<link rel="icon" href="..\..\..\resources3\images\gsitm2.ico" type="image/x-icon">

    <meta charset="UTF-8">
    <title>회의실갤러리</title>
    <style>
        #header{            
            width:100%;
            height:30%;
            font-weight:bold;
        }
        #main{
        	width:100%;
            float:left;
            align:center;
            padding-left:5%;
            padding-bottom:3%;
            padding-top:3%;
        }
        #footer{
            width:100%;
            height: 50%;            
            text-align: center;
            clear:both;
        }
    </style>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
    <div style="width:100%; height:100%;">
	    <div id="header"><tiles:insertAttribute name="header" /></div>
	    <hr>
	    <div id="main"><tiles:insertAttribute name="body" /></div>   
	    <div id="footer"><tiles:insertAttribute name="footer" /></div>
    </div>  
</body>
</html>