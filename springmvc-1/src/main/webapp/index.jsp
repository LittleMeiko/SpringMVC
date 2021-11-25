<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<Form action="springmvc/testRestPut/1" method="post">
		<input type="hidden" name="_method" value="PUT"/>
		<input type="submit" value="Test Rest PUT"/>
	</Form>
	
	<br></br>
	
	<Form action="springmvc/testRestDelete/1" method="post">
		<input type="hidden" name="_method" value="DELETE"/>
		<input type="submit" value="Test Rest DELETE"/>
	</Form>
	
	<br></br>
	
	<Form action="springmvc/testRestPost" method="post">
		<input type="submit" value="Test Rest POST"/>
	</Form>
			
	<br></br>
	
	<a href="springmvc/testRestGet/1">Test Rest Get</a>
	
	<br></br>
	
	<a href="springmvc/testPathVariable/1">Test PathVariable</a>	
	
	<br></br>
	
	<a href="springmvc/testAntPath/abc/def/mvc12">Test AntPath</a>	
	
	<br></br>

	<a href="springmvc/testParamsAndHeaders?username=atguigu&age=10">Test ParamsAndHeaders</a>	
	
	<br></br>
	
	<Form action="springmvc/testMethod" method="post">
		<input type="submit" value="submit"/>
	</Form>
		
	<br></br>
	
	<a href="springmvc/testMethod">Test Method</a>
	
	<br></br>
	
	<a href="springmvc/testRequestMapping">TestRequestMapping</a>
	
	<br></br>
	
	<a href="helloworld">Hello World</a>
</body>
</html>