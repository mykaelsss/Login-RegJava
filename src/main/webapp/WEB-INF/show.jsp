<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Book</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
	<main class="p-4">
		<div class="d-flex justify-content-between align-items-end">
			<h1>
				<c:out value="${book.title}"/>
			</h1>
			<a href="/books">Dashboard</a>
		</div>
		<div>
			<c:choose>
				<c:when  test="${book.user.id == userId }">
					<h4>
					You Read
					<c:out value="${book.title }"/>
					</h4>
					<p>Here are your thoughts:</p>
				</c:when>
				<c:otherwise>
					<h4>
						<c:out value="${book.user.userName}"/>
						read
						<c:out value="${book.title}"/>
						by
						<c:out value="${book.author}"/>
					</h4>
					<p>
						Here are 
						<c:out value="${book.user.userName}"/>'s
						thoughts:
					</p>
				</c:otherwise>
			</c:choose>
		</div>
		<hr>
		<div>
			<p>
				<c:out value="${book.thoughts }"/>
			</p>
		</div>
		<hr>
		<c:if test="${book.user.id == userId }">
			<div class="d-flex gap-3">	
				<a href="/books/${book.id}/edit" class="btn btn-success">edit</a>
				<form action="/books/${book.id}/delete" method="post">
					<input type="hidden" value="delete" name="_method">
					<input type="submit" value="delete" class="btn btn-danger">
				</form>
			</div>
		</c:if>
	</main>
</body>
</html>