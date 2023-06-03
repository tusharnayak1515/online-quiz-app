<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiz App</title>
<style>
	* {
		margin: 0;
		padding: 0;
		box-sizing: border-box;
	}
	
	input {
		outline: none;
		font-size: 18px;
	}
	
	html {
		position: relative;
		min-height: 100vh;
		width: 100%;
	}
	
	input[type="submit"] {
		width: 100%; 
		padding: 0.75rem 1rem;
		color: white;
		border: 1px solid green;
		border-radius: 0.3rem;
		cursor: pointer; 
		background-color: green;
	}
	
	input[type="submit"]:hover {
		background-color: white;
		color: green;
		transition: all 0.3s ease-in-out;
	}
</style>
</head>
<body style="min-height: 100vh; display: flex; flex-direction: column; align-items: center;">
	<%@include file="navbar.jsp" %>
	
	<div style="width: 100%; padding: 2rem; display: flex; flex-direction: column; align-items: center;">
		<h1>Login</h1>
		<form action="UserLoginServlet" method="post" style="width: 250px; margin-top: 2rem; display: flex; flex-direction: column; gap: 1rem;">
			
			<input type="email" name="email" placeholder="Email" 
			style="width: 100%; padding: 0.5rem 1rem;" required />
			
			<input type="password" name="password" placeholder="Password" 
			style="width: 100%; padding: 0.5rem 1rem;" required />
			
			<input type="submit" value="Login" />
		</form>
	</div>
</body>
</html>