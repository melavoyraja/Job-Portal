<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2>
				<font color="blueviolet">Applied Jobs</font>
			</h2>
			<div>
				<table class="table table-striped table-bordered table-hover table-condensed">
					<thead>
						<tr>
							<th>#</th>
							<th>Job Title</th>
							<th>Applied Date</th>
							<th>Job Type</th>
							<th>City</th>
							<th>State</th>
							<th>Status</th>
							<th>Withdraw</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${requestScope.appliedjobs}" var="appliedjob">
						<tr>
							<td><c:out value="${appliedjob.job.jobId}"></c:out></td>
							<td><c:out value="${appliedjob.job.jobTitle}"></c:out></td>
							<td><c:out value="${appliedjob.appliedDate}"></c:out></td>
							<td><c:out value="${appliedjob.job.jobtype.name}"></c:out></td>
							<td><c:out value="${appliedjob.job.location.city}"></c:out></td>
							<td><c:out value="${appliedjob.job.location.stateName}"></c:out></td>
							<td><c:out value="${appliedjob.jobApplicationStatusCode.status}"></c:out></td>
							<td><a href="withdrawjobapplication.htm?id=${appliedjob.id}">Withdraw Application</a></td>
							
						</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>