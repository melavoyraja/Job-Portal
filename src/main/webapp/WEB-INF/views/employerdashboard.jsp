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
	$(document).ready(
			function() {
				$("#myjobs").click(
						function() {
							$.post("employerdashboard.htm?id=employerjobs",
									function(result) {
										$("#addContent").empty();
										$("#alerts").empty();
										$("#addContent").append(result);

									});
						});

				$("#myprofile").click(
						function() {
							$.post("employerdashboard.htm?id=employerprofile",
									function(result) {
										$("#addContent").empty();
										$("#addContent").append(result);

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
			<h3>Welcome to Employer Dashboard!</h3>
		</div>
		<div id="alerts">
			<c:if test="${requestScope.addedjob == 'addedjob'}">
				<font color="green">Job added successfully!</font>
			</c:if>
			<br />
			<c:if test="${requestScope.unauth == 'true'}">
				<font color="red">Unauthorized Access!</font>
				<br />
			</c:if>
			<c:if test="${requestScope.invalid == 'true'}">
				<font color="red">Invalid job ID provided!</font>
				<br />
			</c:if>
			<c:if test="${requestScope.invalidapp == 'true'}">
				<font color="red">Invalid application ID provided!</font>
				<br />
			</c:if>
				<c:if test="${requestScope.invalidfile == 'true'}">
				<font color="red">File Not Found!</font>
				<br />
			</c:if>
		</div>
		<div class="btn-group">
			<button id="myjobs" type="button" class="btn btn-info">My
				Jobs</button>
		</div>
		<br /> <br /> <br />
		<div>

			<c:if test="${check == false}">
				<font color="red"><p>
						You don't have access to add jobs as Administrator has not
						approved your Employer SignUp request or has removed your access.
						You can send <a href="admin@joportal.com">email us</a> at for
						further information.
					</p></font>
			</c:if>
		</div>


		<div id="addContent">
			<c:choose>
				<c:when test="${requestScope.jobid != null}">
					<jsp:include page="/WEB-INF/views/viewapplicants.jsp"></jsp:include>
				</c:when>
				<c:when test="${requestScope.applicationID != null}">
					<jsp:include page="/WEB-INF/views/viewapplication.jsp"></jsp:include>
				</c:when>
			</c:choose>
		</div>


	</div>
</body>
</html>