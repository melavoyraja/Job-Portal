<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
<jsp:include page="/WEB-INF/views/bootstrap.jsp"></jsp:include>
<style>
#pagination {
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
		<br />
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<h4>About ${requestScope.numberOfResults} results found</h4>
			<c:forEach items="${requestScope.jobList}" var="job">
				<div class="panel panel-default">
					<div class="panel-heading">
						
							<font size="4">
							<b><a href="jobseeker/aboutjob.htm?jobid=${job.jobId}">${job.jobTitle}</a></b>
							</font>
						
					</div>
					<div class="panel-body">

						${job.employer.companyName}
						<br/>
						${job.location.city},${job.location.stateName}
						<h6>Date Posted: ${job.datePosted}
						<h6>Late Date to apply: ${job.deadlinToApply}</h6>
					</div>
				</div>
			</c:forEach>

		</div>
		<div class="col-md-2"></div>
		<div id="pagination">

			<ul class="pagination">
				<c:choose>
					<c:when test="${param.pageNo > 5}">
						<li><a
							href="search.htm?jobTitle=${param.jobTitle}&pageNo=${param.pageNo-1}">Previous
						</a>&nbsp; <c:set var="count" value="${param.pageNo-5}" scope="page" />
							<c:forEach begin="${param.pageNo-5}" end="${param.pageNo+4}">
								<li><a
									href="search.htm?jobTitle=${param.jobTitle}&pageNo=${count}"><c:out
											value="${count+1}" /></a> <c:set var="count"
										value="${count + 1}" scope="page" />
							</c:forEach>
						<li><a
							href="search.htm?jobTitle=${param.jobTitle}&pageNo=${param.pageNo+1}">
								Next</a>&nbsp;
					</c:when>
					<c:otherwise>
						<c:set var="count" value="0" scope="page" />
						<c:if test="${param.pageNo > 0 }">
							<li><a
								href="search.htm?jobTitle=${param.jobTitle}&pageNo=${param.pageNo-1}">Previous</a>&nbsp;


							
						</c:if>
						<c:forEach begin="0" end="9">
							<li><a
								href="search.htm?jobTitle=${param.jobTitle}&pageNo=${count}"><c:out
										value="${count+1}" /></a> <c:set var="count" value="${count + 1}"
									scope="page" />
						</c:forEach>
						<c:if test="${param.pageNo == null }">
							<li><a
								href="search.htm?jobTitle=${param.jobTitle}&pageNo=${param.pageNo+2}">
									Next</a>&nbsp;
						</c:if>
						<c:if test="${param.pageNo >= 0 }">
							<li><a
								href="search.htm?jobTitle=${param.jobTitle}&pageNo=${param.pageNo+1}">
									Next</a>&nbsp;
						</c:if>
					</c:otherwise>
				</c:choose>
			</ul>

		</div>

	</div>
</body>
</html>