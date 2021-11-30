<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<a href="springmvc/tesetRedirect">Test Redirect</a>
	<br></br>

	<a href="springmvc/testView">Test View</a>
	<br></br>
	
	<a href="springmvc/testResourceViewReslover">Test ResourceViewReslover</a>
	<br></br>
	
	<form action="springmvc/testModelAttribute" method="post">
		<input type="hidden" name="id" value="1" />
		<br>
		username: <input type="text" name="username" value="Tom"/>
		<br>
		email: <input type="text" name="email" value="tom@atguigu.com" />
		<br>
		age: <input type="text" name="age" value="12" />
		<br>
		<input type="submit" value="Submit" />
	</form>
	<br></br>

	<a href="springmvc/testSessionAttributes">Test SessionAttributes</a>
	<br></br>

	<a href="springmvc/testMap">Test Map</a>
	<br></br>
	
	<a href="springmvc/testModelAndView">Test ModelAndView</a>
	<br></br>
	
	<a href="springmvc/testServletAPI">Test ServletAPI</a>
	<br></br>
	
	<form action="springmvc/testPojo" method="post">
		username:<input type="text" name="username"/>
		<br>
		password:<input type="password" name="password" />
		<br>
		email:<input type="text" name="email">
		<br>
		age:<input type="text" name="age" />
		<br>
		province:<input type="text" name="address.province" />
		<br>
		city:<input type="text" name="address.city" />
		<br>
		<input type="submit" name="submit" value="Submit"/>
	</form>
	<br></br>
	
	<a href="springmvc/testCookieValue">Test CookieValue</a>
	<br></br>
	
	<a href="springmvc/testRequestHeander">Test RequestHeander</a>
	<br></br>
	
	<a href="springmvc/testRequestParam?username=atguigu">Test testRequestParam</a>
	<br></br>
	
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