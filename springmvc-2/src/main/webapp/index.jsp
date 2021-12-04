<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.9.1.min.js" />
<script type="text/javascript">
	$(function() {
		$("#testJson").click*(function(){
			var url = this.href;
			var args = {};
			$.post(url,args,function(data){
				for(var i = 0;i < data.length;i++) {
					var id = data[i].id;
					var lastName = data[i].lastName;
					
					alert(id + ":" + lastName);
				}
			})
			return false;
		})
	})
</script>
</head>
<body>
	<!-- 文件上传 -->
	<form action="testFileUpload" method="POST" enctype="multipart/form-data">
		File:<input type="file" name="file"/>
		Desc:<input type="text" name="desc"/>
		<input type="submit" value="Submit"/>
	</form>

	<a href="/springmvc-2/emps">List Employees</a>
	
	<br><br>
	
	<a href="/springmvc-2/testJson" id="testJson">Test Json</a>
	<br><br>
	
	<!-- 文件上传 -->
	<form action="testHttpMessageConverter" method="POST" enctype="multipart/form-data">
		File:<input type="file" name="file"/>
		Desc:<input type="text" name="desc"/>
		<input type="submit" value="Submit"/>
	</form>
	
	<br><br>
	
	<!-- 下载文件 -->
	<a href="testResponseEntity">Test ResponseEntity</a>
	
	<br><br>
	
	<a href="i18n">I18N PAGE</a>
	
	<br><br>
	
	<a href="testExceptionHandlerExceptionReslover?i=10">Test ExceptionHandlerExceptionReslover</a>
	<br><br>
	
	<a href="testResponseStatusExceptionHandler?i=10">Test ResponseStatusExceptionHandler</a>
	<br><br>
	
	<a href="testDefaultHandlerExceptionResolver">Test DefaultHandlerExceptionResolver</a>
	<br><br>
	
	<a href="testSimpleMappingExceptionResolver?i=2">TestSimpleMappingExceptionResolver</a>
	<br><br>
</body>
</html>