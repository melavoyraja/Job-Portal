<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>Sign Up</title>
<jsp:include page="/WEB-INF/views/bootstrap.jsp"></jsp:include>

</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
		<br />
		<div class="col-md-2"></div>
		<div class="col-md-8">
		<div class="pull-right"><a href="employersignup.htm">Employer SignUp</a><br /></div>
		<br />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Sign Up for Job Portal with Email</h4>
					
				</div>
				<div class="panel-body">
					<form:form commandName="jobSeeker">

						<label for="firstName">First Name:</label>
						<br />
						<form:input path="user.firstName" type="text" class="form-control"
							id="firstName" placeholder="First Name"/>
						<font color="red"><form:errors path="user.firstName" /></font>
						<br />

						<label for="lastName">Last Name:</label>
						<br />
						<form:input path="user.lastName" type="text" class="form-control"
							id="lastName" placeholder="Last Name" />
						<font color="red"><form:errors path="user.lastName" /></font>
						<br />

						<label for="email">Email Address:</label>
						<br />
						<form:input path="user.emailId" type="email" class="form-control"
							id="email" placeholder="Email Address" />
						<font color="red"><form:errors path="user.emailId" /></font>
						<br />

						<label for="password">Password:</label>
						<br />
						<form:input path="user.password" type="password"
							class="form-control" id="password" placeholder="Password" />
						<font color="red"><form:errors path="user.password" /></font>
						<br />

						<label for="yearsExperience">Years of Experience:</label>
						<br />
						<form:input path="yearsExperience" type="number" class="form-control"
							id="yearsExperience" placeholder="Years of Experience" />
						<font color="red"><form:errors path="yearsExperience" /></font>
						<br />


						<button type="submit" class="btn btn-primary">CREATE
							ACCOUNT</button>
					</form:form>
				</div>
			</div>
		</div>
		<div class="col-md-2"></div>
	</div>
</body>
</html>