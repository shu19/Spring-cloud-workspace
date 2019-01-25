<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%-- ${dataset } --%>
	<table>
		<tr>
			<th>Transaction Id</th>
			<th>Account Number</th>
			<th>Amount</th>
			<th>Current Balance</th>
			<th>Transaction Date</th>
			<th>Transaction Details</th>
			<th>Transaction Type</th>
		</tr>

 		<jstl:forEach var="transaction" items="${dataset.transaction}">
 		
 		<tr>
			<td>${transaction.transactionId}</td>
			<td>${transaction.accountNumber}</td>
			<td>${transaction.transactionDetails}</td>
			<td>${transaction.amount}</td>
			<td>${transaction.transactionType}</td>
			<td>${transaction.transactionDate}</td>
			<td>${transaction.currentBalance}</td>
		</tr>
		</jstl:forEach>

	</table>

	<a href="${dataset.previousLink.href}">Previous</a>

	<a href="${dataset.nextLink.href}">Next</a>

</body>
</html>