<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/"> Back </a><br>

	<h1>${channel}</h1>
	<p>
		<a href="/addprogramtochannel?channel=${channel}">Add programs
			to channel</a>
	</p>
	<p>
		<a href="/removeprogramfromchannel?channel=${channel}">Remove programs
			from channel</a>
	</p>
	<div>
		<c:forEach items="${days}" var="day">
			<tr>
				<td><a href="/channel?day=${day}&channel=${channel}">${day}</a></td>
			</tr>
		</c:forEach>
	</div>
	<c:if test="${schedules ne null}">
		<c:forEach items="${schedules}" var="schedule">
		
			<table>
				<tr>${schedule.program.name}: <fmt:formatDate dateStyle="LONG" value="${schedule.date}" pattern="yyyy MM EE HH:mm" />
				Programs length is ${schedule.length/60000} minutes
				</tr>
			</table>
		</c:forEach>
	</c:if>
<%--  --%>
</body>
</html>