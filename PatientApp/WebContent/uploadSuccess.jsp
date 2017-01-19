<%@page import=" java.util.ArrayList"%>
<%@include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
           <jsp:useBean id="Reader" class="java.util.ArrayList" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
UserBean userBean = (UserBean)session.getAttribute("user");
if (userBean.getFirstName() == null) {
   request.setAttribute("Error", "Session has ended.  Please login.");
   RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
   rd.forward(request, response);
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Successfully Uploaded</title>






</head>
<body>
	<br>
	<br>
	<br>
	<br>


	<div class="container">
		<div id="wrapper">
			<div id="page-wrapper">
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">

							<!-- /.panel-heading -->
							<div class="panel-body">
								<table width="100%"
									class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<caption style="text-align: center; font-size: 30px">
										<strong>Uploaded Details</strong>
									</caption>
						
									<thead>
										<tr style="background-color: #99CCFF">
											<th>Account No.</th>
											<th>First name</th>
											<th>Last name</th>
											<th>Facility</th>
											<th>Admit Date</th>
											<th>Discharge Date</th>
										</tr>
									</thead>
									<tbody>
											<c:forEach items="${Reader}" var="item">
										<tr>
											<td><c:out value="${item.accNo}"/></td>
												<td><c:out value="${item.firstName}"/></td>
												<td> <c:out value="${item.lastName}"/></td>
												<td><c:out value="${item.facility}"/></td>
												<td><c:out value="${item.admitDate}"/></td>
												<td class="center"><c:out value="${item.dischargeDate}"/></td>
											
											</tr>
											</c:forEach>
									</tbody>
								</table>
								<script type="text/javascript">
							
							    $(document).ready(function() {
							        $('#dataTables-example').DataTable({
							            responsive: true
							        });
							    });
							    </script>


							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>