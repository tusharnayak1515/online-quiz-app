<%@page import="com.to.Quiz"%>
<%@page import="java.util.List"%>
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
	
	<% 
		List<Quiz> quizes = (List<Quiz>)request.getAttribute("quizes");
		List<Category> categoryList = (List<Category>)request.getAttribute("categoryList");
	%>
	
	<div 
	style="min-height: 100vh; width: 100%; 
	display: flex; flex-direction: column; align-items: center; 
	padding: 2rem;">
		<div style="width: 75%; padding: 1rem; display: flex; flex-direction: column; gap: 1.5rem;">
			<h1>Create New Quiz</h1>
			<form action="AddQuizServlet" method="post" style="width: 30%; display: flex; flex-direction: column; align-items: start; gap: 1rem;">
				<div style="width: 100%; display: flex; flex-direction: column; align-items: start; gap: 0.3rem;">
					<label for="title">Quiz Title</label>
					<input type="text" name="title" id="title" placeholder="Quiz title" 
						style="width: 100%; padding: 0.5rem 1rem;" 
						required
					/>
				</div>
				
				<div style="width: 100%; display: flex; flex-direction: column; align-items: start; gap: 0.3rem;">
					<label for="category">Category</label>
					<select name="category" id="category" style="width: 100%; padding: 0.5rem;">
						<% if(categoryList.size() == 0) { %>
							<option>No category to display</option>
						<%}%>
						
						<% for(Category category : categoryList) { %>
							<option value="<%= category.getCategory_id() %>"><%= category.getName() %></option>
						<%} %>
					</select>
				</div>
				
				<input type="submit" value="Submit" />
			</form>		
		</div>
		
		<div style="width: 75%; padding: 1rem; display: flex; flex-direction: column; gap: 1.5rem;">
			<h1>Quiz List</h1>
			<% 
				if(quizes.isEmpty()) {
					out.println("<p style='font-size: 20px;'>No quiz to display!</p>");
				}
				else {%>
					<table style="border-collapse: collapse;">
						<thead>
							<tr>
								<th style="width: 20%; padding: 0.7rem; border: 1px solid lightgrey;">Sl No.</th>
								<th style="width: 40%; padding: 0.7rem; border: 1px solid lightgrey;">Quiz Title</th>
								<th style="width: 20%; padding: 0.7rem; border: 1px solid lightgrey;">Category</th>
								<th style="width: 20%; padding: 0.7rem; border: 1px solid lightgrey;">Action</th>
							</tr>
						</thead>
			
						<tbody>
							<% for(Quiz quiz : quizes) { %>
							<% int sno = quizes.indexOf(quiz)+1; %>
							<tr>
								<td 
								style="width: 20%; padding: 0.7rem; 
								text-align: center; border: 1px solid lightgrey;">
									<%= sno %>
								</td>
								
								<td 
								style="width: 40%; padding: 0.7rem; 
								text-align: center; border: 1px solid lightgrey;">
									<%= quiz.getTitle() %>
								</td>
								
								<td 
								style="width: 20%; padding: 0.7rem; 
								text-align: center; border: 1px solid lightgrey;">
									<%= quiz.getCategory().getName() %>
								</td>
								
								<td style="width: 20%; padding: 0.7rem; 
								text-align: center; border: 1px solid lightgrey;">
									<a href="DisplayCategoryServlet?id=<%= quiz.getQuiz_id() %>" style="margin-right: 0.5rem;">Edit</a>
									<a href="DeleteCategoryServlet?id=<%= quiz.getQuiz_id() %>">Delete</a>
								</td>
							</tr>
							<%} %>
						</tbody>
					</table>
				<%}%>
		</div>
	</div>
</body>
</html>