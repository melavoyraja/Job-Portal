<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>${job.jobTitle}</title>
<jsp:include page="/WEB-INF/views/bootstrap.jsp"></jsp:include>

</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

		<c:if test="${requestScope.jobapplied == 'jobapplied'}">
			<br />
			<font color="green">Job applied successfully!</font>
			<br />
		</c:if>

		<div class="panel panel-default">
			<div class="panel-heading">
				<b><font size="5">${job.jobTitle}</font></b> <span
					class="pull-right"> <c:choose>
						<c:when test="${requestScope.check == true }">
							<font color="red">You have already applied for this job.</font>
						</c:when>
						<c:otherwise>
							<a href="applyjob.htm?jobid=${job.jobId}" class="btn btn-info"
								role="button">Apply Now</a>
						</c:otherwise>
					</c:choose>
				</span>
			</div>
			<div class="panel-body">
				<font color="blue">Job Type:</font>
				<p>${job.jobtype.name}</p>
				<font color="blue">Job Description:</font>
				<p>${job.jobDescription}</p>
				<font color="blue">Desired Skill:</font>
				<p>${job.desiredSkills}
				<p>
			</div>
		</div>
	</div>
</body>
</html>