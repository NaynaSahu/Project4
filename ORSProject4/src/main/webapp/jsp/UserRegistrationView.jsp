<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="in.co.sunrays.proj4.servlet.UserRegistrationCtl"%>
<%@page import="in.co.sunrays.proj4.servlet.ORSView"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/><title>User Registration</title></head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'dd/mm/yy',
			changeMonth : true,
			changeYear : true,
			yearRange : "1980:2003",
		maxDate:'31/12/2003',
		// minDate:0
		//yearRange : "-40:-18"
		});
	});
</script>

<body >

	<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">

		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="./js/calendar.js"></script>
		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>

		<center>
			<h1>
				<b><i><font color="brown">User Registration</font></i></b>
			</h1>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
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
					<th align="left">First Name<span style="color: red">*</span></th>
					<td><input type="text" name="firstName" size="24"
						placeholder="Enter First Name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>">
						</td>
						<td style="position: fixed;"><font
						color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Last Name<span style="color: red">*</span></th>
					<td><input type="text" name="lastName"  size="24"
						placeholder="Enter Last Name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
						<td style="position: fixed;"><font
						color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>
				<tr>
					<th  align="left">LoginId<span style="color: red">*</span> </th>
					<td><input type="text" name="login" size="24"
						placeholder="Must be an Email ID"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"></td>
						<td style="position: fixed;"><font
						color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Password<span style="color: red">*</span></th>
					<td><input type="password" name="password"  size="24"
						placeholder="Enter Password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
						<td style="position: fixed;"><font
						color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Confirm Password<span style="color: red">*</span></th>
					<td><input type="password" name="confirmPassword" size="24"
				                     placeholder="Re-Enter your Password"
						value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>"></td>
						<td style="position: fixed;"><font
						color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td>
				</tr>
				
				
				<tr>
					<th align="left">Mobile No. <span style="color: red">*</span></th>

					<td><input type="text" name="mobileno" size="24" maxlength="10"
						placeholder="Enter Mobile No."
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("mobileno", request)%></font></td>
				</tr>
			
				
				<tr>
					<th align="left">Gender <span style="color: red">*</span></th>
					<td>
						<%
						HashMap map = new HashMap();
						map.put("M", "Male");
						map.put("F", "Female");

						String htmlList = HTMLUtility.getList("Gender", bean.getGender(), map);
						%> <%=htmlList%>

					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font></td>
					
				</tr>

				<tr>
					<th align="left">Date Of Birth  <span style="color: red">*</span></th>
					<td><input type="text" name="dob" readonly="readonly" id="datepicker" size="24" placeholder="Select a Date"
						value="<%=DataUtility.getDateString(bean.getDob())%>"> </td>
					<td style="position: fixed;"><font
						color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>	
				</tr>
				<tr>
					<th></th>
					<td colspan="2">
						<input type="submit" name="operation"
						value="<%=UserRegistrationCtl.OP_SIGN_UP%>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" name="operation"
						value="<%=UserRegistrationCtl.OP_RESET%>">
					</td>
				</tr>
			</table>
			<br><br><br><br><br><br><br><br>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>

</body>
</html>