<%@page import="com.to.Quiz"%>
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
	<%@include file="adminNavbar.jsp" %>
	
	<div style="min-height: 100vh; width: 100%; display: flex; flex-direction: column; align-items: center; padding: 2rem;">
		<div style="width: 75%; padding: 1rem; display: flex; flex-direction: column; gap: 1.5rem;">
			<h1>Quiz List</h1>
			
			<% 
				List<Quiz> quizList = (List<Quiz>)request.getAttribute("quizes");
				if(quizList == null) {
					out.println("<p style='font-size: 20px;'>No Quiz to display!</p>");
				}
				else {
					if(quizList.isEmpty()) {
						out.println("<p style='font-size: 20px;'>No quiz to display!</p>");
					}
					else { %>
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
							<% for(Quiz quiz : quizList) { %>
							<tr>
								<td 
								style="width: 20%; padding: 0.7rem; 
								text-align: center; border: 1px solid lightgrey;">
									<%= quiz.getQuiz_id() %>
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
									<a href="#" style="margin-right: 0.5rem;">Edit</a>
									<a href="#">Delete</a>
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