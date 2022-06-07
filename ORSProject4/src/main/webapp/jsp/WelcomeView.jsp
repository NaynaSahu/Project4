<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="in.co.sunrays.proj4.servlet.ORSView"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>welcome page</title>
<style type="text/css">
body {
	background-image: url('./img/p13.jpg');
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: 100% 100%;
}
</style>
</head>
<body>
<%@ include file="Header.jsp"%>
<form action="<%=ORSView.WELCOME_CTL%>" >
<center>
<h1><font color="brown"><i>Welcome To Rays !</i></font></h1>
</center>
</form>
<%@ include file="Footer.jsp" %>
</body>
</html>