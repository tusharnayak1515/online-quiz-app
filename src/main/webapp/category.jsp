<%@page import="com.to.Category"%>
<%@page import="java.util.List"%>
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
		width: 100px;
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
	
	label {
		font-size: 18px;
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
	
	<div 
	style="min-height: 100vh; width: 100%; 
	display: flex; flex-direction: column; align-items: center; 
	padding: 2rem;">
		<div style="width: 75%; padding: 1rem; display: flex; flex-direction: column; gap: 1.5rem;">
			<h1>Create New Category</h1>
			<form action="AddCategoryServlet" method="post" style="width: 100%; display: flex; flex-direction: column; align-items: start; gap: 1rem;">
				<div style="display: flex; flex-direction: column; align-items: start; gap: 0.3rem;">
					<label for="name">Category Name</label>
					<input type="text" name="name" id="name" placeholder="Enter category name" 
						style="padding: 0.5rem 1rem;" 
						required
					/>
				</div>
				
				<input type="submit" value="Submit" />
			</form>		
		</div>
		
		<div style="width: 75%; padding: 1rem; display: flex; flex-direction: column; gap: 1.5rem;">
			<h1>Categories</h1>
			<% 
				List<Category> categoryList = (List<Category>)request.getAttribute("categoryList");
				if(categoryList == null) {
					out.println("<p style='font-size: 20px;'>No category to display!</p>");
				}
				else {
					if(categoryList.isEmpty()) {
						out.println("<p style='font-size: 20px;'>No category to display!</p>");
					}
					else {%>
						<table style="border-collapse: collapse;">
							<thead>
								<tr>
									<th style="width: 20%; padding: 0.7rem; border: 1px solid lightgrey;">Sl No.</th>
									<th style="width: 50%; padding: 0.7rem; border: 1px solid lightgrey;">Category</th>
									<th style="width: 30%; padding: 0.7rem; border: 1px solid lightgrey;">Action</th>
								</tr>
							</thead>
				
							<tbody>
								<% for(Category category : categoryList) { %>
								<% int sno = categoryList.indexOf(category)+1; %>
								<tr>
									<td 
									style="width: 20%; padding: 0.7rem; 
									text-align: center; border: 1px solid lightgrey;">
										<%= sno %>
									</td>
									
									<td 
									style="width: 50%; padding: 0.7rem; 
									text-align: center; border: 1px solid lightgrey;">
										<%= category.getName() %>
									</td>
									
									<td style="width: 30%; padding: 0.7rem; 
									text-align: center; border: 1px solid lightgrey;">
										<a href="DisplayCategoryServlet?id=<%= category.getCategory_id() %>" style="margin-right: 0.5rem;">Edit</a>
										<a href="DeleteCategoryServlet?id=<%= category.getCategory_id() %>">Delete</a>
									</td>
								</tr>
								<%} %>
							</tbody>
						</table>
					<%}}%>
		</div>
	</div>
</body>
</html>