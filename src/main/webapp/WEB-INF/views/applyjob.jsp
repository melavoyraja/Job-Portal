<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>Apply Job</title>
<jsp:include page="/WEB-INF/views/bootstrap.jsp"></jsp:include>

</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
		<form:form method="POST" commandName="appliedJobs" enctype="multipart/form-data">
			<div class="panel panel-default">

				<div class="panel-body">
					<div>
						<b><font size="5">${job.jobTitle}</font></b><br />
						${job.location.city},${job.location.stateName}
						<hr />
					</div>
					<div class="row">
						<div class="col-md-6">
							<p>STEP 1:</p>
							<b><font size="5">Resume</font></b>(Required)<br />
							<p>Please attach your resume</p>
						</div>

						<div class="col-md-6">
							<form:input path="resumeFile" type="file" name="resume" />
							<font color="red"><form:errors path="resumeFile" /> </font>
						</div>

					</div>
					<hr />
					

				<div class="row">

					<div class="col-md-6">
						<p>STEP 2:</p>
						<b><font size="5">Work Eligibility</font></b>(Required)<br />
						<p>Please answer the questions below.</p>
					</div>

					<div class="col-md-6">
						<div>
							<b>Are you legally authorized to work in the United States?</b> <br />
							
							<form:radiobutton path="legallyAuthorized"  name="authWork" value="Yes"/> Yes 
							<form:radiobutton path="legallyAuthorized"  name="authWork" value="No"/> No
							<font color="red"><form:errors path="legallyAuthorized"></form:errors> </font>	
						</div>
						<br />
						<div>
							<b>Will you now or in the future require sponsorship for
								employment visa status (e.g. H-1B visa status)?</b> <br /> 
								<form:radiobutton path="sponsorship"  name="sponsor" value="Yes" /> Yes 
								<form:radiobutton path="sponsorship"  name="sponsor" value="No"/> No
								<font color="red"><form:errors path="sponsorship"></form:errors> </font>
						</div>
						<br />
						<div>
							<b>Please indicate whether you are either a citizen or lawful
								permanent resident of any of the following countries: Cuba,
								Iran, North Korea, Syria or Sudan.</b> <br /> 
								<form:radiobutton  name="citizen" path="citizen" value="Yes"/> Yes 
								<form:radiobutton  name="citizen" path="citizen" value="No"/> No
								<font color="red"><form:errors path="citizen"></form:errors> </font>
						</div>

					</div>

				</div>
				<hr />
				<div class="row">
					<div class="col-md-6">
						<p>STEP 3:</p>
						<b><font size="5">Voluntary Self-Identification of Protected Veteran Status</font></b>(Required)<br />						
						<div>
						<p>If you believe you belong to any of the categories of protected veterans listed above, please indicate by checking the appropriate box below. Any information submitted will not be used in a manner inconsistent with the Act. Additionally, refusal to provide the requested information will not subject you to any adverse treatment.</p>
						</div>
					</div>

					<div class="col-md-6">
						<label>Indicate your Protected Veteran Status:</label><br />
						<form:radiobutton path="veteran"  name="veteran" value="Yes" /> I identify as one or more of the classifications of protected veterans listed above<br/>
						<form:radiobutton path="veteran"  name="veteran" value="No" /> I am not a protected veteran<br/>
						<form:radiobutton path="veteran"  name="veteran" value="Decline" checked="checked"/> I decline to self-identify for protected veteran status<br/>
						<font color="red"><form:errors path="veteran"></form:errors> </font>
					</div>

				</div>
				<hr />
				
				<div class="row">
					<div class="col-md-6">
						<p>STEP 4:</p>
						<b><font size="5">Indicate when you can start working:</font></b>(Required)<br />
						
					</div>

					<div class="col-md-6">
						<form:input path="availableFrom" type="date" class="form-control" placeholder="Date" />
						<font color="red"><form:errors path="availableFrom"></form:errors> </font>
						<form:input path="jobId" type="hidden" class="form-control" value="${job.jobId}" />
					</div>

				</div>
				<hr />

					<div>
						<span class="pull-right"><button type="submit"
								class="btn btn-primary">Submit Application</button> </span>
					</div>
				</div>
			</div>
		</form:form>

	</div>
</body>
</html>