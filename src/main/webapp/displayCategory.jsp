<%@page import="com.to.Category"%>
<%@page import="com.to.User"%>
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
	
	html {
		position: relative;
		min-height: 100vh;
		width: 100%;
	}
	
	input {
		outline: none;
		font-size: 18px;
	}
	
	input[type="submit"] {
		width: 100%;
		padding: 0.5rem;
		color: white;
		border: 1px solid green;
		border-radius: 0.3rem;
		background-color: green;
		cursor: pointer;
	}
	
	input[type="submit"]:hover {
		color: green;
		background-color: white;
		transistion: all 0.2s ease-in-out;
	}
</style>
</head>
<body>
	<% 
		User user = (User)session.getAttribute("user");
		if(user == null) {
			response.sendRedirect("index.jsp");
		}
		else if(!user.getEmail().equals("admin@gmail.com")) {
			response.sendRedirect("index.jsp");
		}
	%>
	<%@include file="admin/adminNavbar.jsp" %>
	
	<%Category category = (Category)request.getAttribute("category"); %>
	
	<div 
	style="min-height: 100vh; width: 100%; 
	display: flex; flex-direction: column; align-items: center; 
	padding: 2rem;">
		<h1>Edit Category</h1>
		
		<form action="UpdateCategoryServlet" method="post" 
			  style="width: 250px; margin-top: 1rem; display: flex; flex-direction: column; gap: 1rem;"
		>
			<input type="hidden" name="id" value="<%= category.getCategory_id() %>" />
			<input type="text" name="name" value="<%= category.getName() %>" placeholder="Category Name" 
				style="width: 100%; padding:0.5rem 1rem;" required 
			/>
			<input type="submit" value="Edit" />
		</form>
	</div>
</body>
</html>