<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!-- EL表达式是1.1之后出现所以taglib URI应改为 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="scripts/jquery-1.9.1.min.js" />
<script type="text/javascript">
	$(function(){
		alert("delete...");
		$(".delete").click(function(){
			var href = $(this).attr("href");
			$("form").attr("action", href).submit();
			return false;
		});
	})
</script>

</head>
<body>
	<form action="" method="POST">
		<input type="hidden" name="_method" value="DELETE"/>
	</form>

	<c:if test="${empty requestScope.employees}">
		没有任何员工信息
	</c:if>
	
	<c:if test="${!empty requestScope.employees}">
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>ID</th>
				<th>LastName</th>
				<th>Email</th>
				<th>Gender</th>
				<th>Department</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${requestScope.employees}" var="emp">
				<tr>
					<td>${emp.id}</td>
					<td>${emp.lastName}</td>
					<td>${emp.email}</td>
					<td>${emp.gender == 0 ? 'Female' : "Male"}</td>
					<td>${emp.department.departmentName}</td>
					<td><a href="emp/${emp.id}">Edit</a></td>
					<td><a class=".delete" href="emp/${emp.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	<br><br>
	<a href="emp">Add New Employee</a>
</body>
</html>