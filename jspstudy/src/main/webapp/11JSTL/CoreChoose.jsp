<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL core choose/when/otherwise</title>
</head>
<body>
	<c:set var="num" value="99"/>
	
	<h4>choose</h4>
	<c:choose>
		<c:when test="${num mod 2 eq 0 }">
			${num }는 짝수입니다.
		</c:when>
		<c:otherwise>
			${num }는 홀수입니다.	
		</c:otherwise>
	</c:choose>
	
	<h4>영어, 수학, 과학 점수를 입력받아서 평균을 구하고 학점 출력</h4>
	<!-- 90점 이상 수, 80점 이상 우, 70점 이상 미, 60점이상 양, 나머지 가 -->
	<form>
		영어: <input type="number" name="eng" min="0" max="100" value="${param.eng }"/><br>
		수학: <input type="number" name="math" min="0" max="100" value="${param.math }"/><br>
		과학: <input type="number" name="sci" min="0" max="100" value="${param.sci }"/><br>
		<input type="submit" value="학점 구하기"/>
	</form>
	<!-- 평균점수 : ?? / 학점 : ??  -->

		<!-- 변수 선언 -->
	<c:set var="eng" value="${param.eng}"/>
	<c:set var="math" value="${param.math }"/>
	<c:set var="sci" value="${param.sci }"/>
	
	<h3>과목점수 출력</h3>
	영어:${eng }<br>수학:${math }<br>과학:${sci }<br>
	
	<h3>평균점수 출력</h3>
	
	<c:set var="avg" value="${(eng+math+sci)/3}"/>
	평균점수:${avg }<br><br>
	
	<c:if test="${avg ge 90  }" var="result">
		평균점수 : ${avg } / 학점 : 수<br>
	</c:if>
	
	<c:if test="${avg lt 90 and avg ge 80 }" var="result">
		평균점수 : ${avg } / 학점 : 우<br>
	</c:if>
	
	<c:if test="${avg lt 80 and avg ge 70 }" var="result">
		평균점수 : ${avg } / 학점 : 미<br>
	</c:if>
	
	<c:if test="${avg lt 70 and avg ge 60}" var="result">
		평균점수 : ${avg } / 학점 : 양<br>
	</c:if>
	
	<c:if test="${avg lt 60}" var="result">
		평균점수 : ${avg } / 학점 : 가<br>
	</c:if>
	
	평균점수 : ${avg } / 학점 : 
	<c:choose>
		<c:when test="${avg>=90 }">수</c:when>
		<c:when test="${avg>=80 }">우</c:when>
		<c:when test="${avg>=70 }">미</c:when>
		<c:when test="${avg>=60 }">양</c:when>
		<c:otherwise>가</c:otherwise>
	</c:choose>
	
	
</body>
</html>