<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>

<!---------- CSS ------------>
<link href="${ctx.contextPath}/static/css/addMedicalProfile.css"
	rel="stylesheet">
	
	<style type="text/css">
	
	td{
	border: 1px solid;
	border-spacing: 0px !important;
	border-collapse: collapse;
	}
	</style>
</head>

<body>
	<div style="margin: 0 auto;">
		<table style="width: 1020px;margin: 0 auto;">
			<thead>
				<tr style="font-weight: bold;">
					<td style="border: 1px solid;">Parameter</td>
					<td>User Value</td>
					<td>Threshold Value</td>
					<td>Three Location</td>
				</tr>
			</thead>
			<tr>
					<td>Blood Pressure</td>
					<td>${medicalProfile.bloodPressure}</td>
					<td>${threshold.bloodPressure}</td>
					<td>${medicalProfile.bloodPressureTreeLocation}</td>
			</tr>
			<tr>
					<td>Missed Medication</td>
					<td>${medicalProfile.missedMedication}</td>
					<td>${threshold.missedMedication}</td>
					<td>${medicalProfile.missedMedicationTreeLocation}</td>
			</tr>
			<tr>
					<td>Energy Expenditure</td>
					<td>${medicalProfile.energyExpenditure}</td>
					<td>${threshold.energyExpenditure}</td>
					<td>${medicalProfile.energyExpenditureTreeLocation}</td>
			</tr>
			<tr>
					<td>Salt Intake</td>
					<td>${medicalProfile.saltIntake}</td>
					<td>${threshold.saltIntake}</td>
					<td>${medicalProfile.saltIntakeTreeLocation}</td>
			</tr>
		</table>

	</div>
</body>
</html>

