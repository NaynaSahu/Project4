<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="in.co.sunrays.proj4.model.CollegeModel"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.servlet.CollegeListCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.bean.CollegeBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title>College View</title></head>

<script src="<%=ORSView.APP_CONTEXT %>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT %>/js/Checkbox11.js"></script>

<body>
	 <%@include file="Header.jsp"%>
    <center>
        <h1>College List</h1>

        <form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="POST">
        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.CollegeBean" scope="request"></jsp:useBean>
	
<h2> <font color = "red"><%=ServletUtility.getErrorMessage(request) %></font></h2>
  <h2> <font color = "green"><%=ServletUtility.getSuccessMessage(request) %></font></h2>
                
<%
		List names =(List) request.getAttribute("namee");
	%>
	<%
	 int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<CollegeBean> it = list.iterator();
	if(list.size()!=0){
	%>
	

            <table width="100%">
                <tr>
                    <td align="center"><label> Name :</label> 
                        &emsp;
                         <%=HTMLUtility.getList("name", String.valueOf(bean.getId()), names) %>&emsp;
                         
                        <label>City :</label> <input type="text" name="city" placeholder="Enter a City"
                        value="<%=ServletUtility.getParameter("city", request)%>">
                        
                        <input type="submit" name="operation"
                        value="<%=CollegeListCtl.OP_SEARCH%>">
                        &emsp; <input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_RESET%>"></td>
                </tr>
            </table>
            <br>
            <table border="1" width="100%" cellspacing = "0" cellpadding="10">
                <tr>
                     <th><input type="checkbox" id="select_all" name="select"> Select All </th>
                    <th>S.No.</th>
                    <th>Name.</th>
                    <th>Address.</th>
                    <th>State.</th>
                    <th>City.</th>
                    <th>Phone No.</th>
                    <th>Edit</th>
                </tr>
                
                <%
                   
                    while (it.hasNext()) {

                        CollegeBean ben = it.next();
                %>
                <tr align="center">
                <td><input type="checkbox" class = "checkbox" name="ids"
						value="<%=ben.getId()%>" > </td>
                    <td><%=index++%></td>
                    <td><%=ben.getName()%></td>
                    <td><%=ben.getAddress()%></td>
                    <td><%=ben.getState()%></td>
                    <td><%=ben.getCity()%></td>
                    <td><%=ben.getPhoneNo()%></td>
                    <td><a href="CollegeCtl?id=<%=ben.getId()%>">Edit</a></td>
                </tr>
                <%
                    }
                %>
            </table>
            <table width="100%">
				<tr>
				<% if (pageNo==1){ %>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=CollegeListCtl.OP_PREVIOUS%>"></td><%}else{ %>
						<td><input type="submit" name="operation" 
						value="<%=CollegeListCtl.OP_PREVIOUS%>"></td>
						<%} %>
					
						<td><input type="submit" name="operation" value="<%=CollegeListCtl.OP_DELETE%>"></td>
			<td><input type="submit" name="operation" value="<%=CollegeListCtl.OP_NEW%>"></td>	
		
						
						
						<% CollegeModel model = new CollegeModel(); 
						if(list.size()<pageSize || model.nextPK()-1 == bean.getId()){ 
						%><td align="right"><input type="submit" name="operation" disabled="disabled"
						value="<%=CollegeListCtl.OP_NEXT%>"></td><%}else{ %>
					<td align="right"><input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_NEXT%>"></td>
						<%} %>
				</tr>
			</table>
			
			<%
                    }
                    if(list.size() == 0){
			%>
			<td align="center"> <input type = "submit" name="operation" value="<%=CollegeListCtl.OP_BACK%>"></td>
			<%} %>
			
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
                type="hidden" name="pageSize" value="<%=pageSize%>">
        </form>
        <br><br><br><br><br>
    </center>
	<%@ include file="Footer.jsp"%>
</body>
</html>