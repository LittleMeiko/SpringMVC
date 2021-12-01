<%@page import="org.springframework.web.context.request.RequestScope"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 将一个字符串直接转换为一个Employee对象：使用自定义的类型转换器 -->
	<form action="testConversionServiceConverter" method="POST">
		Employee:<input type="text" name="employee"/>
		<br><br>
		<input type="submit" value="Submit" />
	</form>
	
	<!-- 
		可以通过modelAttribute属性指定绑定的模型属性，若没有指定该属性，则
		默认从request域对象中读取command的表单bean，如果该属性值也不存在
		则会报错
	 -->
	<form:form action="${pageContext.request.contextPath}/emp" method="POST" modelAttribute="employee" > 
		<c:if test="${employee.id == null}">
			LastName:<form:input path="lastName" />	
		</c:if>
		
		<c:if test="${employee.id != null}">
			<form:hidden path="id" />
			<input type="hidden" name="_method" value="PUT" />	
		</c:if>
		
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
		Birth:<form:input path="birth"/>
		<br>
		Salary:<form:input path="salary"/>
		<br>
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>