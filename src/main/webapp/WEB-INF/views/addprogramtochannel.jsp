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
	<a href="/channel?channel=${channel}"> Back </a>

	<c:if test="${added ne null}">
		<p style="color: red;">
			<c:if test="${added}">added successfully</c:if>
			<c:if test="${added == false}">Not added</c:if>
		</p>
	</c:if>
	<form action="" method="post">
	<table>
		<select name="programname">
			<c:forEach items="${programs}" var="program">
				<option value="${program.name}">${program.name}</option>
			</c:forEach>
		</select>
		<tr><td>
			day of month</td><td> <select name="day">
				<c:forEach items="${days}" var="day">
					<option value="${day}">${day}</option>
				</c:forEach>
			</select><br></td></tr>

		
		<tr><td>
			month</td><td> <select name="month">
				<c:forEach items="${intmonths}" var="month">
					<option value="${month}">${month}</option>
				
				</c:forEach>
			</select></td></tr>
		
		<tr><td>
			hour Of Day</td><td> <select name="hours">
				<c:forEach items="${hours}" var="hour">
					<option value="${hour}">${hour}</option>
					
				</c:forEach>
			</select></td></tr>
		
		<tr><td>
			minutes</td><td> <select name="minutes">
				<c:forEach items="${minutes}" var="minute">
					
					<option value="${minute}">${minute}</option>
					
				</c:forEach>
									
			</select></td></tr>

		
		<tr><td>
		Program length </td><td>
			<select name="length">
				<c:forEach items="${minutes}" var="length">
					<option value="${length}">${length}</option>
					
				</c:forEach>
			</select></td></tr>
	
		<tr><td><input type="submit" name="${submit}" value="${addchange}" /></td></tr>
		</table>
	</form>
</body>
</html>