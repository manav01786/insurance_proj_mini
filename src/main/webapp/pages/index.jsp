<%@page import="org.apache.taglibs.standard.tag.el.core.ForEachTag"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Report Application</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

<div class="container mt-2">
	<h1 class="text-left mb-4">Report Application</h1>

	<!-- Form Section -->
	<div class="card p-4 shadow-sm mb-5">
		<form:form action="handleSearch" modelAttribute="searchObj" method="post">
			<div class="row mb-3">
				<div class="col-md-6">
					<label for="planName" class="form-label">Plan Name:</label>
					<form:select path="planName" class="form-select">
						<form:option value="">-select-</form:option>
						<form:options items="${planNames}" />
					</form:select>
				</div>
				<div class="col-md-6">
					<label for="planStatus" class="form-label">Plan Status:</label>
					<form:select path="planStatus" class="form-select">
						<form:option value="">-select-</form:option>
						<form:options items="${status}" />
					</form:select>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-md-6">
					<label for="gender" class="form-label">Gender:</label>
					<form:select path="gender" class="form-select">
						<form:option value="">-select-</form:option>
						<form:option value="Male">Male</form:option>
						<form:option value="Female">Female</form:option>
					</form:select>
				</div>
				<div class="col-md-6">
					<label for="planStartDate" class="form-label">Start Date:</label>
					<form:input path="planStartDate" type="date" class="form-control" />
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-md-6">
					<label for="planEndDate" class="form-label">End Date:</label>
					<form:input path="planEndDate" type="date" class="form-control" />
				</div>
			</div>

			<div class="d-flex justify-content-between">
				<a href="/" class="btn btn-secondary">Reset</a>
				<input type="submit" value="Search" class="btn btn-primary">
			</div>
		</form:form>
	</div>

	<!-- Data Table Section -->
	<div class="card p-4 shadow-sm">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>Holder Name</th>
					<th>Gender</th>
					<th>Plan Name</th>
					<th>Plan Status</th>
					<th>Plan Start Date</th>
					<th>Plan End Date</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="plan" items="${handleSearchList}" varStatus="index">
					<tr>
						<td>${index.count}</td>
						<td>${plan.citizenName}</td>
						<td>${plan.gender}</td>
						<td>${plan.planName}</td>
						<td>${plan.planStatus}</td>
						<td>${plan.planStartDate}</td>
						<td>${plan.planEndDate}</td>
					</tr>
				</c:forEach>

				<tr>
					<c:if test="${empty handleSearchList}">
						<td colspan="7" class="text-center">No records found</td>
					</c:if>
				</tr>
			</tbody>
		</table>
	</div>

	<!-- Export Links -->
	<div class="mt-3 text-center">
		<strong>Export:</strong>
		<a href="pdf" class="btn btn-outline-dark btn-sm mx-2">PDF</a>
		<a href="excel" class="btn btn-outline-dark btn-sm mx-2">Excel</a>
	</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
