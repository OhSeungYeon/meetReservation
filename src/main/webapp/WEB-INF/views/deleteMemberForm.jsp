<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!-- font -->
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
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>

<script type="text/javascript">

function validation(){

	var existPass = $("#existPass").val();
	
	var chkPass = {
		existPass : existPass
	};
	
	if(!existPass){
		alert("��й�ȣ�� �Է��ϼ���.");
		$("#existPass").focus();
		return false;
	} else {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "http://localhost:8081/api/edu/pwChange",
			data : chkPass,
			success: function(data){
				var item = data.data;

				if(item=="fail") alert("�н����尡 Ʋ�Ƚ��ϴ�.");
				else {
					alert("ȸ�� Ż��Ǿ����ϴ�.");
					deleteMember();
				}		
			}, error:function(request,status,error){
			    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			   }
		});
	}
	
}

function deleteMember() {

	var sessionId = $("#sessionId").val();

	var sessionId = {
		sessionId : sessionId
	};
	
	$.ajax({
		type : "POST",
		dataType : "json",
		url : "http://localhost:8081/api/edu/deleteMember",
		data : sessionId,
		success: function(data){
			var item = data.data;

			if(item=="success")
				window.location.href="http://localhost:8081/api/login/login";	
		}, error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		   }
	});
}

</script>

<body>
<div class="container">
<form id="deleteMemberForm" method="post">

  <p style="padding-left:10%;">
  	<table class="table">
  	
		<tr>
			<td><p>�α���ID</p></td>
			<td><p><input type="text" id="sessionId" name="sessionId" value="${sessionScope.userId }" readonly></p></td>
		</tr>
		<tr>
			<td><p>��й�ȣ �Է�</p></td>
			<td><p><input type="password" id="existPass" name="existPass" autofocus></p></td>
		</tr>
		
	</table>
  </p>
  
  <p><input type="button" class="btn btn-light" value="ȸ�� Ż��" onclick="validation()"></p>
             
</form>
</div>

</body>
</html>