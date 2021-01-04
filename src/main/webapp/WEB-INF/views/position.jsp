<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

  <link rel="shortcut icon" href="..\..\..\resources3\images\gsitm2.ico" type="image/x-icon">
  <link rel="icon" href="..\..\..\resources3\images\gsitm2.ico" type="image/x-icon">
  
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach var="position" items="${position}" varStatus="status">
	<c:out value="${position.positionNm}" />
</c:forEach>

</body>
</html>