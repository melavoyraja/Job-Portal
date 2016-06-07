<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<a href="addjob.htm" class="btn btn-info" role="button">Add Job</a>

<h2>Jobs</h2>
<h5>These jobs are available for applicants to search and apply for:</h5>
<div>
				<table class="table table-striped table-bordered table-hover table-condensed">
					<thead>
						<tr>
							<th>Job ID</th>
							<th>Job Title</th>
							<th>Deadline To Apply</th>
							<th>Job Type</th>							
							<th>Job Details &  Applicants</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.jobs}" var="job">
						<tr>
							<td><c:out value="${job.jobId}"></c:out></td>
							<td><c:out value="${job.jobTitle}"></c:out></td>
							<td><c:out value="${job.deadlinToApply}"></c:out></td>
							<td><c:out value="${job.jobtype.name}"></c:out></td>
							<td><a href="viewapplicants.htm?jobid=<c:out value="${job.jobId}"></c:out>" >View Applicants</a></td>
						</tr>
						</c:forEach>
						
					</tbody>
				</table>
</div>