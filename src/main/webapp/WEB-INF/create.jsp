<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Book</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
	<main class="p-4">
		<div class="d-flex justify-content-between align-items-end">
			<h1>Add a Book to Your Shelf!</h1>
			<a href="/books">Dashboard</a>
		</div>
		<form:form action="/books" method="post" modelAttribute="book" class="form w-25">
			<div>
				<form:errors path="title"/>
				<form:errors path="author"/>
				<form:errors path="thoughts"/>
			</div>
 			<div>		
				<form:label path="title">Title: </form:label>
				<form:input path="title" class="form-control"/>
			</div> 
			<div>		
				<form:label path="author">Author: </form:label>
				<form:input path="author" class="form-control"/>
			</div> 
			<div>		
				<form:label path="thoughts">Your Thoughts: </form:label>
				<form:textarea path="thoughts" class="form-control"/>
			</div> 
			<form:input type="hidden" path="user" value="${userId}"/>
			<input type="submit" value="Submit" class="btn btn-primary mt-2" />
		</form:form>
	</main>
</body>
</html>