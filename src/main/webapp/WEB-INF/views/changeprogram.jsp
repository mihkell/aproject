<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="/"> Back </a>
<c:if test="${added}">Operation successfully</c:if>
<c:if test="${added == false}">Operation failed</c:if>
	<form action="/changeprogram" method="post">
		<p>If we already have the programm in the data base your chosen
			type will be replaced with the old one</p>
		<tr>
			<td>Program name</td>
			<td><input type="text" name="programname" /></td>
		</tr>
		<br> Type choose <select name="type">
			<c:forEach items="${types}" var="type">
				<option value="${type}">${type}</option>
			</c:forEach>
		</select> <br> <br>
		<input type="submit" name="submit" value="add" />
		<input type="submit" name="submit" value="remove" />
	</form>


</body>
</html>