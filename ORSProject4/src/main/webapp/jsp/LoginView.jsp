<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="in.co.sunrays.proj4.servlet.LoginCtl"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.servlet.ORSView"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<body>

	<form action="<%=ORSView.LOGIN_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>
		<input type="hidden" name="URI"
			value="<%=session.getAttribute("uri")%>">

		<center>
			<div align="center">
				<h1>Login</h1>
				<H2>
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
					</font>
				</H2>
				<H2>
					<font color="Green"> <%=ServletUtility.getSuccessMessage(request)%></font>
				</H2>


				<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
					type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
				<input type="hidden" name="modifiedBy"
					value="<%=bean.getModifiedBy()%>"> <input type="hidden"
					name="createdDatetime"
					value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
				<input type="hidden" name="modifiedDatetime"
					value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

				<table>
					<tr>
						
							<th align="left">LoginId<span style="color: red">*</span></th>
							<td><input type="text" name="login" size="fixed" placeholder="Enter Login Id"
								value="<%=DataUtility.getStringData(bean.getLogin())%>">
						<%System.out.println("========>"+bean.getLogin());
                    %>
						</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
					</tr>
					<tr>
						
							<th>Password<span style="color: red">*</span></th>
							<td><input type="password" name="password" size="fixed" placeholder="Enter Password"
								value="<%=DataUtility.getStringData(bean.getPassword())%>">
						</td>
						<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
					</tr>
					<tr>
						<th></th>
						<td colspan="2"><input type="submit" name="operation"
							value="<%=LoginCtl.OP_SIGN_IN%>">&nbsp;&nbsp; &nbsp;&nbsp; <input
							type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_UP%>">
							&nbsp;</td>
					</tr>
					<tr>
						<th></th>
						<td><a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forget
									my password</b></a>&nbsp;</td>
					</tr>
				</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>