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
	<a href="/channel?channel=${channel}"> Back </a>

	<c:if test="${added ne null}"><p style="color:red;">
	
		<c:if test="${added}">Removed successfully</c:if>
		<c:if test="${added == false}">Not removed</c:if>
		
		</p>
	</c:if>
	
	<form action="" method="post">
		<select name="scheduleId">
			<c:forEach items="${schedules}" var="schedule">
				<option value="${schedule.id}">${schedule.program.name}:<fmt:formatDate dateStyle="LONG" value="${schedule.date}" pattern="yyyy MM EE HH:mm" /></option>
				</table>
			</c:forEach>
		</select><input type="submit" name="${submit}" value="${removeprogram}" />
		
	</form>



</body>
</html>