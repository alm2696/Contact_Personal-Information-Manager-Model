<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
<!--
        Error Page Title: This page is rendered when an error occurs within the application.
        It captures the exception details and displays an error message and the stack trace.
    -->
</head>
<body>

	<%!
	// Declare an exception object at the scriptlet declaration level to make it available for JSP runtime.
	Exception exception;%>

	<%
	// Retrieve the exception object from the request attributes
	// This is typically set when an error occurs within a servlet or other parts of the application
	exception = (Exception) request.getAttribute("exception");
	%>

	<h1>Error Page</h1>

	<!-- Display the error message from the exception object -->
	<h2>
		Error Message:
		<%=exception.getMessage()%></h2>

	<!-- 
    Display the stack trace of the exception for debugging purposes.
    The stack trace shows where the error occurred in the code.
	-->
	<pre>
<%
// Print the stack trace of the exception to the JSP output using PrintWriter
exception.printStackTrace(new PrintWriter(out));
%>
</pre>

</body>
</html>
