<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
	<div class="p-3">
		<h1 style="color:#9900FF">Welcome, <c:out value="${userName}"/></h1>
		<p>The languages you know:</p>
		<c:choose>
			<c:when test="${user.knownLanguages.size() > 0 }">
				<c:forEach var="lang" items="${user.knownLanguages}">
					<c:out value="${lang}"/>
				</c:forEach>		
			</c:when>
			<c:otherwise>
				<p>None :/</p>
			</c:otherwise>
		</c:choose>
		<br>
		<a href="/logout">Logout</a>
	</div>
</body>
</html>