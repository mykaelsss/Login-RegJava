<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body class="p-4">
	<nav>
		<h1 style="color:#9900FF">Book Club</h1>
		<p>A place for friends to share thoughts on books.</p>
	</nav>
	<div class="d-flex gap-5">
		<form:form action="/register" method="post" modelAttribute="newUser">
			<h3>Register</h3>
			<div>
				<form:errors path="userName"></form:errors>
				<form:errors path="email"></form:errors>
				<form:errors path="password"></form:errors>
				<form:errors path="confirm"></form:errors>
<%-- 				<form:errors path="birthday"></form:errors> --%>
			</div>
			<div>
				<form:label path="userName">Username:</form:label>
				<form:input path="userName"/>
			</div>
			<div>
				<form:label path="email">Email:</form:label>
				<form:input path="email"/>
			</div>
			<div>
				<form:label path="password">Password:</form:label>
				<form:input path="password"/>
			</div>
			<div>
				<form:label path="confirm">Confirm:</form:label>
				<form:input path="confirm"/>
			</div>
			<%-- <div>
				<form:label path="birthday">Birthday:</form:label>
				<form:input type="date" path="birthday" />
			</div>
			<div>
				<p>Languages you know</p>
				C#<form:checkbox path="knownLanguages" value="C#"/>
				Java<form:checkbox path="knownLanguages" value="Java"/>
				Python<form:checkbox path="knownLanguages" value="Python"/>
				JavaScript<form:checkbox path="knownLanguages" value="JavaScript"/>
				Ruby<form:checkbox path="knownLanguages" value="Ruby"/>
			</div> --%>
			<input type="submit" value="Submit"/>			
		</form:form>
		<form:form action="/login" method="post" modelAttribute="newLogin">
			<h3>Login</h3>
			<div>
				<form:errors path="email" value="${error}"></form:errors>
				<form:errors path="password" value="${error}"></form:errors>
			</div>
			<div>
				<form:label path="email">Email:</form:label>
				<form:input path="email"/>
			</div>
			<div>
				<form:label path="password">Password:</form:label>
				<form:input path="password"/>
			</div>
			<input type="submit" value="Submit"/>	
		</form:form>
	</div>
</body>
</html>