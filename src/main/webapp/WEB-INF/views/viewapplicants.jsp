<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2>
			<font color="blueviolet">${job.jobTitle}</font>
			
			</h2>
			<p>${job.jobDescription}</p>
			<h2><font color="blueviolet">Applications</font></h2>
			<div>
				<table class="table table-striped table-bordered table-hover table-condensed">
					<thead>
						<tr>
							<th>Applicant</th>
							<th>Application Date</th>
							<th>Available from</th>
							<th>View Application Details</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${requestScope.applicants}" var="applicant">
						<tr>
							<td>${applicant.jobseeker.user.firstName},${applicant.jobseeker.user.lastName}</td>
							<td><c:out value="${applicant.appliedDate}"></c:out></td>
							<td><c:out value="${applicant.availableFrom}"></c:out></td>
							<td><a href="viewapplicationdetails.htm?applicationID=<c:out value="${applicant.id}"></c:out>&jobId=<c:out value="${applicant.jobId}"></c:out>" >View</a></td>
						</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>