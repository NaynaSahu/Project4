<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<style type="text/css">
body {
	background-image: url('p13.jpg');
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: 100% 100%;
}
</style>
</head>
<body>
<%@ include file="Header.jsp" %>
<center>
<form action="LoginCtl" method="post">
<h1><b><i><font color="brown">Login To Rays</font></i></b></h1>
<table>
<tr>
<td><input type="Email" name="Email" placeholder="Email Address"></td>
</tr>
<tr>
<td><input type="Password" name="Password" placeholder="Enter Password"> </td>
</tr>
</table>
</form>
</center>
<%@ include file="Footer.jsp" %>
</body>
</html>