<%@page import="utils.AlertFunc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="utils.AlertFunc" %>

<%
	if(session.getAttribute("UserId")==null){
		AlertFunc.alertLocation("로그인이 필요합니다.", "../07LoginOutPrac/LoginForm.jsp", out);
		return; //이후 하부 동작은 더이상 할 필요가 없기때문에..
	}
%>