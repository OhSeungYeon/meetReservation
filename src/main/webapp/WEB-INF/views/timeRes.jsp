<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<!-- bootstrap -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<style>   
	p {
		font-family: 'Noto Sans KR', sans-serif;
		font-size: 1.15em;
	}
</style>
<title>Insert title here</title>

<script type="text/javascript">

function validation(){

	var stHour = $("#stHour").val();
	var stMinute = $("#stMinute").val();
	var finHour = $("#finHour").val();
	var finMinute = $("#finMinute").val();
	
	var username = $("#username").val();
	var userNm = $("#userNm").val();
	var reason = $("#reason").val();

	var meetingId = $("select[name='meetingId']").val();


	var chkRes = {
		stHour : stHour,
		stMinute : stMinute,
		finHour : finHour,
		finMinute : finMinute,
		username : username,
		userNm : userNm,
		reason : reason,
		meetingId : meetingId
	};

	if(!meetingId) {
		alert("사용하실 회의실을 선택해주세요.");
		$("#meetingId").focus();
		return false;
	} else if(!username) {
		alert("사용하실 분의 아이디를 입력하세요.");
		$("#userId").focus();
		return false;
	} else if(!userNm) {
		alert("사용하실 분의 이름을 입력하세요.");
		$("#userNm").focus();
		return false;
	} else if(stHour==0 || stMinute==0 || finHour==0 || finMinute==0){
		alert("사용하실 시간을 정확하게 입력해주세요.");
		$("#stHour").focus();
		return false;

	} else {
		$.ajax({
			type : "POST",
			dataType : "json",
			//contentType : "application/json; charset=UTF-8",
			url : "http://localhost:8081/api/edu/insertRes",
			data : chkRes,			
			success: function(data){
				var item = data.data;

				if(item=="success") {
					alert("회의실이 예약되었습니다.");
					window.location.href="http://localhost:8081/api/edu/readView";
				} else {
					alert("회의실 예약에 실패했습니다.");
				}		
			}, error:function(request,status,error){
			    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			   }
		});
	}
	
}
</script>
</head>
<body>
	
<div class="container" style="padding-bottom:5%;">
<form id="timeResForm" method="post">

  	<table class="table">
  		
  		<tr>
  			<td><p>회의실</p></td>
  			<td>
  				<div class="form-group">
	  				<select name="meetingId" class="form-control" style="width:150px;height:40px;">
					    <option value=""><p>회의실선택</p></option>
					    <option value="M001"><p>M001</p></option>
					    <option value="M002"><p>M002</p></option>
					    <option value="M003"><p>M003</p></option>
					    <option value="M004"><p>M004</p></option>
					</select>
				</div>
			</td>
  		</tr>
  		<tr>
  			<td><p>사용자ID</p></td>
  			<td><input class="form-control" type="text" id="username" name="username" style="width:250px;height:40px;"></td>
  		</tr> 
  		<tr>
  			<td><p>사용자이름</p></td>
  			<td><input class="form-control" type="text" id="userNm" name="userNm" style="width:250px;height:40px;"></td>
  		</tr>
  		<tr>
  			<td><p>사용 목적</p></td>
  			<td>
  				<input class="form-control" type="text" id="reason" name="reason" cols=45 rows="5"></textarea>
  			</td>
  		</tr>
		<tr>
			<td><p>시작시간</p></td>
			<td>
				
			<div style="float:left; padding-right:1%;">
				<select id="stHour" name="stHour" style="width:70px;height:35px;" class="form-control">
	                <c:forEach var="hour" begin="0" end="23" >
	                    <option value="<c:if test="${hour < 10}">0</c:if>${hour}"><c:if test="${hour < 10}">0</c:if>${hour}</option>
	                </c:forEach>
	        	</select>
	        </div>
	        <div style="float:left; padding-right:1%;">
        		<p>시</p>
        	</div>
        	<div style="float:left; padding-right:1%;">
	        	<select id="stMinute" name="stMinute" style="width:70px;height:35px;" class="form-control">
	            <c:forEach var="min" begin="0" end="59" >
	                <option value="<c:if test="${min < 10}">0</c:if>${min}"><c:if test="${min < 10}">0</c:if>${min}</option>
	            </c:forEach>
	            </select>
	        </div>
	        <div style="float:left; padding-right:1%;">
            	<p>분</p>
            </div>
				
			</td>
		</tr>
		<tr>
			<td><p>종료시간</p></td>
			<td>
				
			<div style="float:left; padding-right:1%;">
				<select id="finHour" name="stHour" style="width:70px;height:35px;" class="form-control">
	                <c:forEach var="hour" begin="0" end="23" >
	                    <option value="<c:if test="${hour < 10}">0</c:if>${hour}"><c:if test="${hour < 10}">0</c:if>${hour}</option>
	                </c:forEach>
	        	</select>
	        </div>
	        <div style="float:left; padding-right:1%;">
        		<p>시</p>
        	</div>
        	<div style="float:left; padding-right:1%;">
	        	<select id="finMinute" name="stMinute" style="width:70px;height:35px;" class="form-control">
	            <c:forEach var="min" begin="0" end="59" >
	                <option value="<c:if test="${min < 10}">0</c:if>${min}"><c:if test="${min < 10}">0</c:if>${min}</option>
	            </c:forEach>
	            </select>
	        </div>
	        <div style="float:left; padding-right:1%;">
            	<p>분</p>
            </div>
				
			</td>
		</tr>
		
		
	</table>
  <br>
  <p><input type="button" style="font-size:1.0em;" class="btn btn-light" value="회의실 예약하기" onclick="validation()"></p>     
             
</form>
</div>
	
	
</body>
</html>