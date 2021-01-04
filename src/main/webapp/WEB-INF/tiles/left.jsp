<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<html>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<head>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css" rel="stylesheet"/>
<!-- font -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<style>   
	li {
		font-family: 'Noto Sans KR', sans-serif;
		font-size:1.0em;
	}
	h4 {
		font-family: 'Noto Sans KR', sans-serif;
		font-weight: bold;
		font-size:1.1em;
	}
</style>
</head>
<body>
<div class="left">
<div class="col-md-11">

<div class="panel panel-success">
    <div class="panel-heading">
      <h4 class="panel-title" align="center" style="padding:10px;font-weight:bold;">회의실</h4>
    </div>
    <!-- 사이드바 메뉴목록1 -->
    <ul class="list-group" style="padding:10px";>
      <li class="list-group-item"><a href="/api/edu/reservation">회의실 예약하기</a></li>
      <li class="list-group-item"><a href="/api/edu/gallery1">회의실 갤러리</a></li>
    </ul>
</div>

<div class="panel panel-success">
  <div class="panel-heading">
    <h4 class="panel-title" align="center" style="padding:10px;font-weight:bold;">마이페이지</h4>
  </div>
      <ul class="list-group" style="padding:10px";>
        <li class="list-group-item"><a href="/api/edu/modifyInformation">개인정보 수정</a></li>
        <li class="list-group-item"><a href="/api/edu/pwChangeForm">비밀번호 변경</a></li>
         <li class="list-group-item"><a href="/api/edu/deleteMemberForm">회원 탈퇴</a></li>
      </ul>
</div>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<div class="panel panel-success">
  <div class="panel-heading">
    <h4 class="panel-title" align="center" style="padding:10px;font-weight:bold;">관리자페이지</h4>
  </div>
      <ul class="list-group" style="padding:10px";>
        <li class="list-group-item"><a href="/api/edu/admin/admin">관리자 등록</a></li>
        <li class="list-group-item"><a href="/api/edu/admin/approvalRes">예약 승인하기</a></li>
      </ul>
</div>
</sec:authorize>

</div> 
</div>
</body>
</html>