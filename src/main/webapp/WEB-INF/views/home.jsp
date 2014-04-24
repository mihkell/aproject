<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Welcome to Scheduler!
</h1>

<div>
<a href="/changechannel">Add/remove Channel</a>
<a href="/changeprogram">Add Program</a>
<a href="/findprogram">Find Program</a>
</div>
<h2>Channels:</h2>
<c:forEach items="${channels}" var="channel">
    <table>      
        <tr style="white-space:nowrap;"><a href="/channel?channel=${channel}">${channel}</a></tr>
    </table>
</c:forEach>



</body>
</html>
