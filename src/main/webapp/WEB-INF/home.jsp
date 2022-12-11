<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
	<main class="p-4">
		<div class="d-flex justify-content-between align-items-end">
			<h1 style="color:#9900FF">Welcome, <c:out value="${userName}"/></h1>
	<%-- 		<p>The languages you know:</p>
			<c:choose>
				<c:when test="${user.knownLanguages.size() > 0 }">
					<c:forEach var="lang" items="${user.knownLanguages}">
						<c:out value="${lang}"/>
					</c:forEach>		
				</c:when>
				<c:otherwise>
					<p>None :/</p>
				</c:otherwise>
			</c:choose> --%>
			<a href="/logout">Logout</a>
		</div>
		<div class="d-flex justify-content-between align-items-end">
			<h4>Books from everyone's shelves:</h4>
			<a href="/books/new">+ Add to my shelf!</a>
		</div>
		<div class="d-flex justify-content-end align-items-end pt-3">
			<a href="/books/bookmarket">View Book Market</a>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Title</th>
					<th>Author</th>
					<th>Posted By</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${books}">
					<tr>
						<td>
							<c:out value="${book.id}"/>
						</td>
						<td>
							<a href="/books/${book.id}">							
								<c:out value="${book.title}"/>
							</a>
						</td>
						<td>
							<c:out value="${book.author}"/>
						</td>
						<td>
							<c:out value="${book.user.userName}"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</main>
</body>
</html>