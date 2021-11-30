<%@page import="org.springframework.web.context.request.RequestScope"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 
		可以通过modelAttribute属性指定绑定的模型属性，若没有指定该属性，则
		默认从request域对象中读取command的表单bean，如果该属性值也不存在
		则会报错
	 -->
	<form:form action="emp" method="POST" modelAttribute="employee" > 
		LastName:<form:input path="lastName" />
		<br>
		Email:<form:input path="email" />
		<br>
		<% 
			Map<String, String> genders = new HashMap();
			genders.put("1", "Male");
			genders.put("0", "Female");
			request.setAttribute("genders", genders);
		%>
		Gender:<form:radiobuttons path="gender" items="${genders}"/>
		<br>
		Department:<form:select path="department.id" items="${departments}" itemLabel="departmentName" itemValue="id" />
		<br>
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>