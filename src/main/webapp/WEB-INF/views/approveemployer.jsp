<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>Employers Awaiting Approval</h3><br />
<div>
				<table id="emptable" class="table table-striped table-bordered table-hover table-condensed">
					<thead>
						<tr>
							<th>Employer ID</th>
							<th>Employer Name</th>
							<th>Email Address</th>
							<th>Phone Number</th>
							<th>Approve / Decline</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.employers}" var="employer">
						<tr>
							<td><c:out value="${employer.employerId}"></c:out></td>
							<td><c:out value="${employer.companyName}"></c:out></td>
							<td><c:out value="${employer.user.emailId}"></c:out></td>
							<td><c:out value="${employer.phone}"></c:out></td>
							<td><button onclick="approve(${employer.employerId})" type="button" class="btn btn-success"><span class="glyphicon glyphicon-remove"> Approve</span></button> 
							<button onclick="reject()" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-remove"> Reject </span> </button></td>
						</tr>
						</c:forEach>
						
					</tbody>
				</table>
</div>