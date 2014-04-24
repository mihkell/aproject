<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>
		<a href="/"> Back </a>
	</p>

	<c:if test="${found}"> your enquire returned null </c:if>

	<form action="" method="POST">
		type <select name="type">
			<c:forEach items="${types}" var="type">
				<option value="${type}">${type}</option>
			</c:forEach>
		</select><br>
		Program <select name="program">
			<c:forEach items="${programnamess}" var="var">
				<option value="${var.name}">${var.name}</option>
			</c:forEach>
		</select>
		
		<br> <br> Start time<br> day <select name="day">
			<c:forEach items="${days}" var="var">
				<option value="${var}">${var}</option>
			</c:forEach>
		</select> month <select name="month">
			<c:forEach items="${months}" var="var">
				<option value="${var}">${var}</option>
			</c:forEach>
		</select> hours <select name="hours">
			<c:forEach items="${hours}" var="var">
				<option value="${var}">${var}</option>
			</c:forEach>
		</select><br> <br> End time<br>
		day <select name="endday">
			<c:forEach items="${days}" var="var">
				<option value="${var}">${var}</option>
			</c:forEach>
		</select>
		month <select name="endmonth">
			<c:forEach items="${months}" var="var">
				<option value="${var}">${var}</option>
			</c:forEach>
		</select> hours <select name="endhours">
			<c:forEach items="${hours}" var="var">
				<option value="${var}">${var}</option>
			</c:forEach>
		</select> <br><br> <input type="submit" name="submit" value="${findbytype}" />
		<input type="submit" name="submit" value="${findbyprogram}" />
	</form>
	<br>
	<c:if test="${schedules ne null}">

		<c:forEach items="${schedules}" var="schedule">
			<table>
				<tr>${schedule.program.programType} ${schedule.program.name} airs on ${schedule.channel.name} @ 
				<fmt:formatDate dateStyle="LONG" value="${schedule.date}" pattern="yyyy-MMMM-EE HH:mm" />
				
				
				</tr>
			</table>
		</c:forEach>


	</c:if>


</body>
</html>