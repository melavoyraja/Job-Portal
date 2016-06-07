<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>Dashboard</title>
<jsp:include page="/WEB-INF/views/bootstrap.jsp"></jsp:include>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
						$(".btn-success").click(function() {

											var button = this;
											var row = $(this).parent().parent();
											var rowValues = $(this).parent()
													.parent().children();
											var query = "godfather.htm?employerid="
													+ rowValues[0].firstChild.data + "&statusId=2";
											$.post(query,function(result) {
																if (result == 1) {
																	$(rowValues[4]).remove();
																	$(row).append('<td><button type="button" disabled="true" class="btn btn-success"> Approved</span></button></td>');
																}
															});

										});
						$(".btn-danger").click(function() {

							var button = this;
							var row = $(this).parent().parent();
							var rowValues = $(this).parent()
									.parent().children();
							var query = "godfather.htm?employerid="
									+ rowValues[0].firstChild.data + "&statusId=3";
							$.post(query,function(result) {
												if (result == 1) {
													$(rowValues[4]).remove();
													$(row).append('<td><button type="button" disabled="true" class="btn btn-danger">Declined</span></button></td>');
												}
											});

						});
					});
</script>
</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
		<br />
		<div>
			<h3>Welcome to Administrator Dashboard!</h3>
		</div>
		<br />

		<br /> <br /> <br />
		<div id="addContent">
			<table id="emptable"
				class="table table-striped table-bordered table-hover table-condensed">
				<thead>
					<tr>
						<th>Employer ID</th>
						<th>Employer Name</th>
						<th>Email Address</th>
						<th>Phone Number</th>
						<th>Approve / Decline</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.employers}" var="employer">
						<tr>
							<td><c:out value="${employer.employerId}"></c:out></td>
							<td><c:out value="${employer.companyName}"></c:out></td>
							<td><c:out value="${employer.user.emailId}"></c:out></td>
							<td><c:out value="${employer.phone}"></c:out></td>
							<c:choose>
								<c:when test="${employer.adminapprovalstatuscode.statusId == 1}">
									<td><button type="button" class="btn btn-success">
											<span class="glyphicon glyphicon-ok"> Approve</span>
										</button>
										<button type="button" class="btn btn-danger">
											<span class="glyphicon glyphicon-remove"> Decline </span>
										</button></td>
								</c:when>
								<c:when test="${employer.adminapprovalstatuscode.statusId == 2}">

									<td><button type="button" disabled
											class="btn btn-success">
											Approved
										</button></td>
								</c:when>
								<c:when test="${employer.adminapprovalstatuscode.statusId == 3}">

									<td><button type="button" disabled
											class="btn btn-danger">
											Decline
										</button></td>
								</c:when>
							</c:choose>

						</tr>
					</c:forEach>

				</tbody>
			</table>

		</div>
	</div>
</body>
</html>