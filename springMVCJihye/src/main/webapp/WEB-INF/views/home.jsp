<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/subjectsBySemester">학기별 이수 학점 조회</a><br>
	<a href="${pageContext.request.contextPath}/enrolment">수강 신청</a><br>
	<a href="${pageContext.request.contextPath}/selectEnrolment?year=2018&semester=1">수강 신청 조회</a><br>
	
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<a href="javascript:document.getElementById('logout').submit()">Logout</a>
	</c:if>
	<form id="logout" action="<c:url value="/logout" />" method="post">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
		<c:if test="${success != null}">
		<br/>
		<c:out value="성공!"></c:out>
		<br/>
	</c:if>
</body>
</html>