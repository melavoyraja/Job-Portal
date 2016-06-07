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
	$(document).ready(function() {
		$("#myapplications").click(function() {
			$.post("jobseekerdashboard.htm?id=appliedjobs", function(result) {
				$("#addContent").empty();
				$("#addContent").append(result);

			});
		});

	});
</script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
		<br />
		<div>
			<h3>Welcome to Job Seeker Dashboard!</h3>
		</div>

		<br />

		<c:if test="${requestScope.check == true}">
			<font color="green">Job withdrawn successfully!</font>
			<br />
		</c:if>

		<c:if test="${requestScope.check == false}">
			<font color="red">Job not found in your applied job list!</font>
			<br />
		</c:if>

		<c:if test="${requestScope.invalid == true}">
			<font color="red">Looking for Invalid Job!</font>
			<br />
		</c:if>
		<div class="btn-group">
			<button id="myapplications" type="button" class="btn btn-info">My
				Applications</button>
			<a href="/jobportal" class="btn btn-info" role="button">Search
				Jobs</a>
		</div>
		<br /> <br />
		<div id="addContent">

			<h2>
				<font color="blueviolet">5 Latest Job Postings</font>
			</h2>
			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th>#</th>
							<th>Job Title</th>
							<th>Deadline To Apply</th>
							<th>Job Type</th>
							<th>City</th>
							<th>State</th>
							<th>More Details</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.jobs}" var="job">
							<tr>
								<td><c:out value="${job.jobId}"></c:out></td>
								<td><c:out value="${job.jobTitle}"></c:out></td>
								<td><c:out value="${job.deadlinToApply}"></c:out></td>
								<td><c:out value="${job.jobtype.name}"></c:out></td>
								<td><c:out value="${job.location.city}"></c:out></td>
								<td><c:out value="${job.location.stateName}"></c:out></td>
								<td><a
									href="/jobportal/jobseeker/aboutjob.htm?jobid=<c:out value="${job.jobId}"></c:out>">View
										& Apply</a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>

		</div>


	</div>
</body>
</html>