<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java Beans</title>
</head>
<body>
	<h2>값 전달</h2>
	<form action="UseBeanRec.jsp" method="post">
		<label>이름</label>
		<input tyep="text" name="name"/><br>
		<label>나이</label>
		<input tyep="text" name="age"/><br>
		<input type="submit" value="전송"/>
	</form>
	
</body>
</html>