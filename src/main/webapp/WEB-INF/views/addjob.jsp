<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>Add Job</title>
<jsp:include page="/WEB-INF/views/bootstrap.jsp"></jsp:include>

</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

		<div>
			<h2>Add Job</h2>
			<form:form commandName="job">
				<label for="jobTitle">Job Title:</label>
				<br />
				<form:input path="jobTitle" type="text" class="form-control"
					id="jobTitle" placeholder="Job Title" />
				<font color="red"><form:errors path="jobTitle"></form:errors></font>
				<br />

				<label for="jobType">Job Type:</label>
				<br />
				<form:select path="jobtype.name" class="form-control" id="jobType">
					<c:if test="${requestScope.jobTypeList != null}">
					<c:forEach items="${requestScope.jobTypeList}" var="jobType">
						<option><c:out value="${jobType.name}"></c:out></option>
					</c:forEach>
					</c:if>
					<c:if test="${requestScope.jobTypeList == null}">
					<option>Full Time</option>
					<option>Part Time</option>
					</c:if>
				</form:select>
				<font color="red"><form:errors path="jobtype.name"></form:errors></font>
				<br />


				<label for="jobDescription">Job Description:</label>
				<br />
				<form:textarea path="jobDescription" rows="5" class="form-control"
					id="jobDescription" placeholder="Job Description"></form:textarea>
				<font color="red"><form:errors path="jobDescription"></form:errors></font>
				<br />

				<label for="desiredSkills">Desired Skills:</label>
				<br />
				<form:textarea path="desiredSkills" rows="5" class="form-control"
					id="desiredSkills" placeholder="Desired Skill"></form:textarea>
				<font color="red"><form:errors path="desiredSkills"></form:errors></font>
				<br />


				<label for="city">City:</label>
				<br />
				<form:input path="location.city" type="text" class="form-control"
					id="city" placeholder="City" />
				<font color="red"><form:errors path="location.city"></form:errors></font>
				<br />

				<label for="state">State:</label>
				<br />
				<form:input path="location.stateName" type="text"
					class="form-control" id="state" placeholder="State" />
				<font color="red"><form:errors path="location.stateName"></form:errors></font>
				<br />



				<button type="submit" class="btn btn-primary">Add Job</button>
				<br />
				<br />
			</form:form>

			<a href="employerdashboard.htm">Back to Jobs</a> <br /> <br /> <br />
		</div>
	</div>
</body>
</html>