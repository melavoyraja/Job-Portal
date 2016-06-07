<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>Sign In</title>
<jsp:include page="/WEB-INF/views/bootstrap.jsp"></jsp:include>
<c:if test="${requestScope.check == 'jobseeker'}">
	<script>
		$(document)
				.ready(
						alert("Your Account Created Successfully! You can login now and start aplying for jobs."));
	</script>
</c:if>
<c:if test="${requestScope.check == 'employer'}">
	<script>
		$(document)
				.ready(
						alert("Your Account has been created successfully. However Admin needs to Approve your access to post jobs. Please wait for confirmation email from us!")

				);
	</script>
</c:if>

</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
		<br /> <br /> <br />
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Sign In</h4>
				</div>
				<div class="panel-body">
					<c:url value="/login" var="loginUrl" />
					<form action="${loginUrl}" method="post">
						<label for="email">Email Address:</label> <br /> <input
							type="email" class="form-control" id="email" name="email"
							placeholder="Email Address" />
						<c:if test="${param.loginerror == true}">
							<font color="red">Invalid Username and Password!</font>
							<br />
						</c:if>
						<label for="password">Password:</label> <br /> <input
							type="password" class="form-control" id="password"
							name="password" placeholder="Password" /> <br /> <span><a
							href="forgotpassword.htm"> Forgot Password?</a></span> <span
							class="pull-right">Don't have Login Account? <a
							href="signup.htm">Sign Up</a>
						</span> <br /> <input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
							<input type='checkbox' name="remember-me-param"/>Remember Me? <br/>
						<button type="submit" class="btn btn-primary">SIGN IN</button>
					</form>
				</div>
			</div>
		</div>
		<div class="col-md-3"></div>
	</div>
</body>
</html>