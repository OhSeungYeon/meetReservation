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

//내용 수정하기 button 클릭시 
function modify() {

	var username = $("#username").val();
	var userNm = $("#userNm").val();
	var department = $('input:radio[name="department"]:checked').val();
	var phoneNum = $("#phoneNum").val();
	var email = $("#email").val();
	var position = $('input:radio[name="position"]:checked').val();

	var member = {
		username : username,
		userNm : userNm,
		department : department,
		phoneNum : phoneNum,
		email : email,
		position : position
	};

	$.ajax({
		type:"POST",
		dataType:"json",
		url:"http://localhost:8081/api/edu/updateMember",
		data:member,
		success : function(data) {
			var item = data.data;
			
			if(item == "success") {
				alert("개인정보가 수정되었습니다.");
				window.location.href="http://localhost:8081/api/edu/readView";
			} else {
				alert("수정에 실패했습니다.");
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
		<div class="container" style="float:right;width:100%;padding-right:20%;padding-bottom:5%;">
            <form id="userChange" class="form-horizontal" method="post">
            	
            	<c:forEach var="params" items="${params}" varStatus="status">
            	
            	<div class="form-group">
                    <label for="name" class="col-sm-3 control-label">
                    	<p>아이디</p>
                    </label>
                    <div class="col-sm-9">
                        <p><input type="text" id="username" name="username" value="${sessionScope.userId }" class="form-control" style="width:70%;" readonly autofocus></p>
                    </div>
                </div>
               
                <div class="form-group">
                    <label for="name" class="col-sm-3 control-label">
                    	<p>이름</p>
                    </label>
                    <div class="col-sm-9">
                        <p><input type="text" id="userNm" name="userNm" value="<c:out value="${params.userNm}" />" class="form-control" style="width:70%;" autofocus></p>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="control-label col-sm-3"><p>부서</p></label>
                    <div class="col-sm-6">
                        <div class="row">
                            <div class="col-sm-4">
                                <label class="radio-inline"> 
                                <c:forEach var="department" items="${department}" varStatus="status">
                                    <input type="radio" id="department" name="department" value="${department.departmentNm}">
                                     	<p><c:out value="${department.departmentNm}" /></p>
                                </c:forEach>
                                </label>
                                
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="phoneNum" class="col-sm-3 control-label"><p>전화번호</p></label>
                    <div class="col-sm-9">
                        <input type="text" id="phoneNum" name="phoneNum" value="<c:out value="${params.phoneNum}" />" style="width:70%;" class="form-control">
                    	<span class="help-block"><p>-를 포함해서 입력해주세요.</p></span>
                    </div>
                </div> 	
                
                <div class="form-group">
                    <label for="email" class="col-sm-3 control-label"><p>이메일</p></label>
                    <div class="col-sm-9">
                        <p><input type="email" id="email" name="email" value="<c:out value="${params.email}" />" class="form-control" style="width:70%;" name= "email"></p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3"><p>직급</p></label>
                    <div class="col-sm-6">
                        <div class="row">
                            <div class="col-sm-4">
                                <label class="radio-inline"> 
                                <c:forEach var="position" items="${position}" varStatus="status">
                                    <input type="radio" id="position" name="position" value="${position.positionNm}">
                                     	<p><c:out value="${position.positionNm}" /></p>
                                </c:forEach>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                
            </c:forEach>
            
            <div align="center">
            	<p><input type="button" id="modifyButton" value="내용 수정하기" onclick="modify()" class="btn btn-primary btn-block" style="width:60%;"></p>
 			</div>           
           </form> 
        </div>
</body>
</html>