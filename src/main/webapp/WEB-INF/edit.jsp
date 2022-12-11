<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Book</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
	<main class="p-4">
		<div class="d-flex justify-content-between align-items-end">
			<h1>Add a Book to Your Shelf!</h1>
			<a href="/books">Dashboard</a>
		</div>
		<form:form action="/books/${book.id}/update" method="post" modelAttribute="book" class="form w-25">
			<input type="hidden" value="put" name="_method">
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
			<form:input type="hidden" path="borrower" value="${borrower_id}" />
			<input type="submit" value="Submit" class="btn btn-primary mt-2" />
		</form:form>
			 <form action="/books/${book.id}/delete" method="post" >
		        <input type="hidden" value="delete" name="_method">
		        <input type="submit" value="delete" class="btn btn-danger">
	    	</form>
	</main>
</body>
</html>