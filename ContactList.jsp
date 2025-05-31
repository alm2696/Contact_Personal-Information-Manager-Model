<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	import="java.util.List,mod09_02.Contact"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact List</title>
</head>
<body>

	<h2>Contact List</h2>

	<%
	// Retrieve the list of contacts from the request object
	List<Contact> contacts = (List<Contact>) request.getAttribute("contacts");

	// Check if the contact list is empty or null and display a message accordingly
	if (contacts == null || contacts.isEmpty()) {
	%>
	<!-- Display a message if no contacts were found -->
	<p>No contacts found.</p>
	<%
	} else {
	%>
	<!-- HTML table structure for displaying the contact list -->
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Company</th>
				<th>Email</th>
			</tr>
		</thead>
		<tbody>
			<%
			// Loop through each contact and render their details in table rows
			for (Contact contact : contacts) {
			%>
			<tr>
				<!-- Combine first and last name for display in the Name column -->
				<td><%=contact.getFirstName() + " " + contact.getLastName()%></td>
				<!-- Display the company name -->
				<td><%=contact.getCompany()%></td>
				<!-- Display the email address -->
				<td><%=contact.getEmail()%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<%
	}
	%>

</body>
</html>
