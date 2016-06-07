<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<nav class="navbar navbar-inverse">
<div class="container-fluid">
	<div class="navbar-header">
		<c:choose>
			<c:when test="${sessionScope.hibuser.roles.role == 'JobSeeker'}">
				<a class="navbar-brand" href="/jobportal">Job Portal</a>
			</c:when>
			<c:when test="${sessionScope.hibuser.roles.role == 'Employer'}">
				<a class="navbar-brand"
					href="/jobportal/employer/employerdashboard.htm">Job Portal</a>
			</c:when>
			<c:when test="${sessionScope.hibuser.roles.role == 'Admin'}">
				<a class="navbar-brand" href="/jobportal/y400z420/godfather.htm">Job
					Portal</a>
			</c:when>
			<c:otherwise>
				<a class="navbar-brand" href="/jobportal">Job Portal</a>
			</c:otherwise>

		</c:choose>

	</div>

	<c:choose>
		<c:when test="${sessionScope.hibuser == null}">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"><span
						class="glyphicon glyphicon-user"> SignUp</span> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/jobportal/signup.htm"> Job Seeker Sign Up</a></li>
						<li><a href="/jobportal/employersignup.htm">Employer Sign Up</a></li>
					</ul></li>
				
				<li><a href="/jobportal/login.htm"><span
						class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</ul>

		</c:when>
		<c:otherwise>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-user"></span>
						Welcome ${sessionScope.hibuser.lastName}!</a></li>
				<c:choose>
					<c:when test="${sessionScope.hibuser.roles.role == 'JobSeeker'}">
						<li><a href="/jobportal/jobseeker/jobseekerdashboard.htm">
								Dashboard </a></li>
					</c:when>
					<c:when test="${sessionScope.hibuser.roles.role == 'Employer'}">
						<li><a href="/jobportal/employer/employerdashboard.htm">
								Dashboard </a></li>
					</c:when>
					<c:when test="${sessionScope.hibuser.roles.role == 'Admin'}">
						<li><a href="/jobportal/y400z420/godfather.htm">Dashboard
						</a></li>
					</c:when>
					<c:otherwise>
						<a class="navbar-brand" href="/jobportal">Job Portal</a>
					</c:otherwise>

				</c:choose>

				<li><a href="/jobportal//appLogout"><span
						class="glyphicon glyphicon-log-out"></span> Sign Out</a></li>
			</ul>
		</c:otherwise>
	</c:choose>
</div>
</nav>