<%@page import="com.psl.model.Patient"%>
<%@include file="header.jsp"%>
<%@page
	import=" com.psl.model.UserBean,com.psl.model.Message"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
           <%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="patient" class="java.util.ArrayList" scope="request"/>
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
<title>Patient Data</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">





</head>
<body>
	<% Message message= (Message)request.getAttribute("name");
if(message!=null)
{
	
%>
	<div
		class="alert alert-<%=message.isSuccessful()?"success":"danger" %> role="
		alert"
		id="myalert">
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<strong><%=message.getMsg() %></strong>
	</div>
	<%} %>
	<script>
	$("#myalert").fadeTo(2000, 500).slideUp(500, function(){
	    $("#myalert").slideUp(500);
	});
	</script>
	<div class="container">
		<form class="form-horizontal" role="form" method="get">
			<br>


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
										<thead>
											<tr>
												<th>Name</th>
												<th>Facility</th>
												<th>Admit Date</th>
												<th>Discharge Date</th>
												<th>Delete</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${patient}" var="item">

											<tr>
												<td><c:out value="${item.firstName}"/> <c:out value="${item.lastName}"/></td>
												<td><c:out value="${item.facility}"/></td>
												<td><c:out value="${item.admitDate}"/></td>
												<td class="center"><c:out value="${item.dischargeDate}"/></td>
												<td class="center">
													<div class="checkbox">
													
															<c:choose>
														  <c:when test="${item.deleted}==0">
														    <c:set var="status" scope="session" value="Delete"/>
														   <c:set var="val" scope="session" value="1"/>
														  </c:when>
														  <c:otherwise>
														   <c:set var="status" scope="session" value="Add"/>
															<c:set var="val" scope="session" value="0"/>
														  </c:otherwise>
														</c:choose>
														<c:set var="aa" value="${item.accNo}${item.deleted}"/>
														<label><input type="checkbox" name="status[]"
															value="<c:out value="${item.accNo}"/> <c:out value="${item.deleted}"/>"></label>
													</div>
												</td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
									<script type="text/javascript">
                            $(document).ready(function(){
                            	$('#delete').hide();
                                $('input[type="checkbox"]').click(function(){
                                    if($("[name='status[]']:checked").length > 0){
                                    	
                                        $('#delete').show();
                                    }
                                    else if($("[name='status[]']:checked").length==0){
                                    	$('#delete').hide();
                                    	//alert($("[name='status[]']:checked").length);
                                    }
                                    
                                });
                            });
								</script>
									<input type="submit" id="delete" value="Delete"
										class="btn btn-success pull-right"
										onclick="javascript: form.action='deleted.do';" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</form>
	</div>
	<script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
    </script>
</body>
</html>