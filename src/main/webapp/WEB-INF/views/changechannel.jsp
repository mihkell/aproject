<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<a href="/"> Back </a>
	<h1>Add Your FAVORITE CHANNELS!</h1>

	<form action="/addchannel" method="post">
		<select name="channel">
			<c:forEach items="${channelnames}" var="channel">
				<option value="${channel}">${channel}</option>
			</c:forEach>
		</select> <input type="submit" name="submit" value="${removechannel}" />
	</form>
	<c:if test="${added}">Operation successfully</c:if>
	<c:if test="${added == false}">Operation failed</c:if>

	<p>Below you can add New Channel</p>
	<form action="/addchannel" method="post">
		<table>
			<tr>
				<td>Channel Name</td>
				<td><input type="text" name="channelname" /></td>
			</tr>
			<tr>
				<td>Channel Description</td>
				<td><input type="text" name="channeldesc" /></td>
			</tr>

		</table>

		<input type="submit" name="submit" value="${submit}" />
	</form>

</body>
</html>
