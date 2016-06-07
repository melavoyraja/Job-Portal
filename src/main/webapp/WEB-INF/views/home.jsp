<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Welcome to Job Portal</title>
<jsp:include page="/WEB-INF/views/bootstrap.jsp"></jsp:include>


<style>
#part1 {
	background-color: #66cc66;
}

#part2 {
	background-color: #ffffff;
	line-height: 0;
}
</style>
</head>
<body>
	<div id="check" class="container">

		<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

		<div id="part1">
			<br />
			<div align="center">
				<h1>Happy Job Searching!</h1>
				<h4>Find your employer here. Salaries from $75,000 to $250,000</h4>
			</div>
			<form action="search.htm" method="get">
				<div class="col-md-3"></div>
				
				<div class="col-md-6">
					<input name="jobTitle" type="text" class=" form-control" placeholder="Job Title" />
					<input name="pageNo" type="hidden" class=" form-control" value="0"/>
				</div>
				<div class="col-md-2">
					<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Search</button>
				</div>
				
				<div class="col-md-2"></div>
			</form>
			<br /> <br /> <br />
			<div id="part2" align="center">
				<br />
				<h2>How Job Portal Works</h2>
				<h5>1. Singup and Upload Your Resume</h5>
				<h5>2. Search & Apply for the Jobs matching your skills.</h5>
				<h5>3. Employeer shortlists resumes and Calls in for Interviews</h5>

				<br />

				<hr />
			</div>



		</div>

		<div class="footer">
			<span class="pull-left">E-Mail: jobs@neu.edu, Phone:
				1-617-888-2495</span> <span class="pull-right">Mail: NEU Job Portal,
				3840 Greentree Ave SW, Boston, MA 02120</span>

		</div>
	</div>
</body>
</html>
