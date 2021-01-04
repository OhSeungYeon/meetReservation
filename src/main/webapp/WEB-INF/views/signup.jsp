<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

 <link rel="shortcut icon" href="..\..\..\resources3\images\gsitm2.ico" type="image/x-icon">
 <link rel="icon" href="..\..\..\resources3\images\gsitm2.ico" type="image/x-icon">
  
<!------ Include the above in your HEAD tag ---------->
<script src="https://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script> 
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<!-- font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<style>   
	p {
		font-family: 'Noto Sans KR', sans-serif;
		font-size: 1.15em;
	}
</style>

<html>
<head>
<script type="text/javascript">
//비밀번호 확인 query
$(document).ready(function(){
	$("#alert-success").hide();
	$("#alert-danger").hide();
	$("input").keyup(function() {
			var pwd1=$("#password").val();
			var pwd2=$("#password2").val();
			if(pwd1 != "" || pwd2 != "") {
					if(pwd1 == pwd2) {
							$("#alert-success").show();
							$("#alert-danger").hide();
							$("#submit").removeAttr("disabled");
					} else {
						$("#alert-success").hide();
						$("#alert-danger").show();
						$("#submit").attr("disabled", "disabled");
					}
			}
	});
});

//아이디 중복 체크 query
function idChk() {
	
	var username = $("#username").val();

	var id = {
		username : username
	};
	
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"http://localhost:8081/api/login/idCheck",
		data:id,
		success : function(data) {
			var item = data.data;
			
			if(item == "success") {
				alert("사용가능한 아이디입니다.");
			} else {
				alert("이미 사용중인 아이디입니다.");
			}
		}, error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		   }
	});

}


</script>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

	<div class="container" style="padding-top:5%;padding-bottom:5%;">
            <form class="form-horizontal" action="/api/login/signupList" method="post">
                <p style="font-size:2.5em;font-weight:bold;">회원가입</p><br>
                <div class="form-group">
                    <label for="username" class="col-sm-3 control-label"><p>아이디</p></label>
                    <div class="col-sm-9">
                        <p><input type="text" id="username" name="username" placeholder="사용하실 아이디를 입력해주세요." class="form-control" autofocus></p>
                        <p><input type="button" id="check" value="중복체크" onclick="idChk()" class="btn btn-primary btn-block"></p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-3 control-label"><p>비밀번호</p></label>
                    <div class="col-sm-9">
                        <p><input type="password" id="password" name="password" placeholder="사용하실 비밀번호를 입력해주세요." class="form-control" required></p>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="password" class="col-sm-3 control-label"><p>비밀번호 확인</p></label>
                    <div class="col-sm-9">
                        <p><input type="password" id="password2" placeholder="비밀번호를 다시 입력해주세요." class="form-control" required></p>
                    </div>
                </div>
                
                <div style="padding-left:25%;">
                	<div class="alert alert-success" id="alert-success"><p>비밀번호가 일치합니다.</p></div>
                	<div class="alert alert-danger" id="alert-danger"><p>비밀번호가 일치하지 않습니다.</p></div>
              	</div>
              	
                <div class="form-group">
                    <label for="name" class="col-sm-3 control-label"><p>이름</p></label>
                    <div class="col-sm-9">
                        <p><input type="text" id="userNm" name="userNm" placeholder="이름을 입력해주세요." class="form-control" autofocus></p>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="control-label col-sm-3"><p>부서</p></label>
                    <div class="col-sm-6">
                        <div class="row">
                            <div class="col-sm-4">
                                <label class="radio-inline"> 
                                <c:forEach var="department" items="${department}" varStatus="status">
                                    <input type="radio" id="departmentRadio" name="department" value="${department.departmentNm}">
                                     	<p><c:out value="${department.departmentNm}" /></p>
                                </c:forEach>
                                </label>
                                
                            </div>
                        </div>
                    </div>
                </div> <!-- /.form-group -->
                
                <div class="form-group">
                    <label for="phoneNum" class="col-sm-3 control-label"><p>전화번호</p></label>
                    <div class="col-sm-9">
                        <p><input type="text" id="phoneNum" name="phoneNum" placeholder="전화번호를 입력해주세요." class="form-control"></p>
                    	<span class="help-block"><p>-를 포함해서 입력해주세요.</p></span>
                    </div>
                </div> 	
                
                <div class="form-group">
                    <label for="email" class="col-sm-3 control-label"><p>이메일</p></label>
                    <div class="col-sm-9">
                        <p><input type="email" id="email" name="email" placeholder="이메일을 입력해주세요." class="form-control"  name= "email"></p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3"><p>직급</p></label>
                    <div class="col-sm-6">
                        <div class="row">
                            <div class="col-sm-4">
                                <label class="radio-inline"> 
                                <c:forEach var="position" items="${position}" varStatus="status">
                                    <input type="radio" id="positionRadio" name="position" value="${position.positionNm}">
                                     	<p><c:out value="${position.positionNm}" /></p>
                                </c:forEach>
                                </label>
                            </div>
                        </div>
                    </div>
                </div> <!-- /.form-group -->
                
                <p><input type="submit" value="회원가입" class="btn btn-primary btn-block" style="width:80%;float:right;"></p>
            </form> <!-- /form -->
        </div> <!-- ./container -->
        <br>

</body>
</html>