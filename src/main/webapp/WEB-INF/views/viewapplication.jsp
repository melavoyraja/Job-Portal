<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2>
	<font color="blueviolet">View Application details</font>
</h2>
<br />
<div class="row">
	<div class="col-md-6">
		<b>Action</b>
	</div>
	<div class="col-md-6">
		<c:choose>


			<c:when
				test="${requestScope.appliedJob.jobApplicationStatusCode.statusId == 2}">
				<button type="button" disabled="true" class="btn btn-success">
					Declined Applicant </span>
				</button>
			</c:when>

			<c:when
				test="${requestScope.appliedJob.jobApplicationStatusCode.statusId == 3}">
				<button type="button" disabled="true" class="btn btn-success">
					Accepted Applicant </span>
				</button>
			</c:when>
			<c:otherwise>
				<a
					href="changeappliedjobstatus.htm?action=Accept&applicationID=${appliedJob.id}&jobId=${appliedJob.jobId}"
					class="btn btn-success" role="button">Accept Applicant</a>
				<a
					href="changeappliedjobstatus.htm?action=Decline&applicationID=${appliedJob.id}&jobId=${appliedJob.jobId}"
					class="btn btn-danger" role="button">Decline Applicant</a>

			</c:otherwise>
		</c:choose>
	</div>

</div>
<hr />
<div class="row">
	<div class="col-md-6">
		<b>Application Date</b>
	</div>
	<div class="col-md-6">${appliedJob.appliedDate}</div>
</div>
<hr />
<div class="row">
	<div class="col-md-6">
		<b>Applicant Name</b>
	</div>
	<div class="col-md-6">${appliedJob.jobseeker.user.firstName},
		${appliedJob.jobseeker.user.lastName}</div>
</div>
<hr />
<div class="row">
	<div class="col-md-6">
		<b>Resume</b>
	</div>
	<div class="col-md-6">
		<a href="viewapplicantresume.pdf?refid=${appliedJob.id}&jobId=${appliedJob.jobId}">Resume</a>
	</div>
</div>
<hr />

<div class="row">
	<div class="col-md-6">
		<b>Email</b>
	</div>
	<div class="col-md-6">
		<a href="mailto:${appliedJob.jobseeker.user.emailId}">${appliedJob.jobseeker.user.emailId}</a>
	</div>
</div>
<hr />
<div class="row">
	<div class="col-md-6">
		<b>Available from</b>
	</div>
	<div class="col-md-6">${appliedJob.availableFrom}</div>
</div>
<hr />
<div class="row">
	<div class="col-md-6">
		<b>Are you legally authorized to work in the United States?</b>
	</div>
	<div class="col-md-6">${appliedJob.legallyAuthorized}</div>
</div>
<hr />
<div class="row">
	<div class="col-md-6">
		<b>Will you now or in the future require sponsorship for
			employment visa status (e.g. H-1B visa status)?</b>
	</div>
	<div class="col-md-6">${appliedJob.sponsorship}</div>
</div>
<hr />
<div class="row">
	<div class="col-md-6">
		<b>Indicate your Protected Veteran Status:</b>
	</div>
	<div class="col-md-6">
		<c:choose>
			<c:when test="${appliedJob.veteran == 'Yes'}">
	I identify as one or more of the classifications of protected veterans listed above
	</c:when>
			<c:when test="${appliedJob.veteran == 'No'}">
	I am not a protected veteran
	</c:when>
			<c:when test="${appliedJob.veteran == 'Decline'}">
	I decline to self-identify for protected veteran status
	</c:when>

		</c:choose>


	</div>
</div>
<hr />
<a href="employerdashboard.htm"> Back to Dashboard</a>
<br />
<br />