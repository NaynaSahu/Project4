<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="in.co.sunrays.proj4.servlet.UserListCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.model.RoleModel"%>
<%@page import="in.co.sunrays.proj4.model.UserModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>
<head>
<meta charset="ISO-8859-1">
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title>UserList View</title>
</head>
<body>
  <%@include file="Header.jsp"%>

	<center>
		<h1>User List</h1>

		<form action="<%=ORSView.USER_LIST_CTL%>" method="POST">
			<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
				scope="request"></jsp:useBean>

			<h2>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h2>
	<h2>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h2>
			

			<%
				List loglist = (List) request.getAttribute("login");

				int pageNo = ServletUtility.getPageNo(request);

				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<UserBean> it = list.iterator();

				if (list.size() != 0) {
			%>
			<table width="100%">
				<tr>
					<td align="center"><label>First Name :</label> <input
						type="text" name="firstName" placeholder="Enter a Name"
						value="<%=ServletUtility.getParameter("firstName", request)%>">
							<%-- &emsp; <label>LoginId:</label> <input type="text" name="login"
						value="<%=ServletUtility.getParameter("login", request)%>"> --%>
						&emsp; <label>Login Id:</label> <%=HTMLUtility.getList("loglist", String.valueOf(bean.getId()), loglist)%>
						&emsp; <input type="submit" name="operation"
						value="<%=UserListCtl.OP_SEARCH%>"> &emsp; <input
						type="submit" name="operation" value="<%=UserListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>

			<table border="1" width="100%" cellpadding="10" cellspacing="0">
				<tr>
					<th><input type="checkbox" id="select_all" name="select">Select
						All</th>
						<th>S.No.</th> 
					<th>First Name</th>
					<th>Last Name</th>
					<th>Login Id</th>
					<th>Role</th>
					<th>Gender</th>
					<th>DOB (YYYY-MM-DD)</th>
					<th>Edit</th>
				</tr>


				<%
					while (it.hasNext()) {
							 bean = it.next();
							RoleModel rm = new RoleModel();
							RoleBean rb = new RoleBean();
							
						
				%>
				<tr align="center">
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=bean.getId()%>" <%if (bean.getRoleId() == 1) {%>
						disabled="disabled" <%}%>></td>
						<td> <%= index++ %> </td> 
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getLogin()%></td>
					<%
						if (bean.getRoleId() == 1) {
					%><td>Admin</td>
					<%
						} else if (bean.getRoleId() == 2) {
					%><td>Student</td>
					<%
						} else if (bean.getRoleId() == 3) {
					%><td>College</td>
					<%
						} else if (bean.getRoleId() == 4) {
					%><td>Kiosk</td>
					<%
						}else if (bean.getRoleId() == 5) {
					%><td>Faculty</td>
					<%
					}
					%>
					
					<td><%=bean.getGender()%></td>
					<td><%=bean.getDob()%></td>
					<%
						if (bean.getRoleId() == 1) {
					%>
					<td><a href="UserCtl?id=<%=bean.getId()%>"
						onclick="return false;">Edit</a></td>
					<%
						} else {
					%>
					<td><a href="UserCtl?id=<%=bean.getId()%>">Edit</a></td>
					<%
						}
					%>

				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=UserListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>
					<td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_NEW%>"></td>


					<%
						UserModel model = new UserModel();
							if (list.size() < pageSize || model.nextPK() - 1 == bean.getId() ) {
					%><td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=UserListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=UserListCtl.OP_NEXT%>"></td>
					<%
						}
					%>
				</tr>
			</table>
			<%
				}
				if (list.size() == 0) {
			%>
			<td align="center"><input type="submit" name="operation"
				value="<%=UserListCtl.OP_BACK%>"></td>
			<%
				}
			%>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
	</center>
	<br><br><br><br><br><br>
	<%@include file="Footer.jsp"%>

</body>
</html>