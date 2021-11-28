<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h4>Success Page</h4>
	time: ${requestScope.time}
	
	<br><br>
	names: ${requestScope.names}
	
	<br><br>
	requestUser: ${requestScope.user}
	
	<br><br>
	sesssionUser: ${sessionScope.user}
	
	<br><br>
	requestAddress: ${requestScope.address}
	
	<br><br>
	sesssionAddress: ${sessionScope.address}
</body>
</html>