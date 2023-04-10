<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL XML</title>
</head>
<body>
	<c:import url="./include/FruitList.xml" var="fruitlist" charEncoding="UTF-8" />
	${fruitlist }
	<x:parse xml="${fruitlist }" var ="flist" /><br>
	<x:out select="$flist/fruitlist/fruit[1]/name"/><br>
	<x:out select="$flist/fruitlist/fruit[1]/from"/><br>
	<x:out select="$flist/fruitlist/fruit[1]/price"/><br>
	

</body>
</html>