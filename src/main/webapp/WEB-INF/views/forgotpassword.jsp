<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>Forgot Password</title>
<jsp:include page="/WEB-INF/views/bootstrap.jsp"></jsp:include>
<c:if test="${check  == true}">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script>
		$(document)
				.ready(
						alert("New Password information has been sent to you Email Address. Kindly please check and login with new password!"));
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
					<h4>Forgot Password</h4>
				</div>
				<div class="panel-body">
					<form:form commandName="user">
						<label for="email">Email Address:</label>
						<br />
						<form:input path="emailId" type="email" class="form-control"
							id="email" placeholder="Email Address" />
						<font color="red"><form:errors path="emailId"></form:errors></font>
						<br />

						<button type="submit" class="btn btn-primary">RESET
							PASSWORD</button>
					</form:form>
				</div>
			</div>
		</div>
		<div class="col-md-3"></div>
	</div>
</body>
</html>