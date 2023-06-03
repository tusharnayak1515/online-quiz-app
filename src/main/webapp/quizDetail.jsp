<%@page import="com.to.Category"%>
<%@page import="com.to.Question"%>
<%@page import="java.util.List"%>
<%@page import="com.to.Quiz"%>
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
	<%@include file="admin/adminNavbar.jsp" %>
	
	<% 
		Quiz quiz = (Quiz)request.getAttribute("quiz");
		Category category = quiz.getCategory();
		List<Question> questions = (List<Question>)request.getAttribute("questions");
	%>
	
	<div style="width: 100%; padding:2rem; display: flex; flex-direction: column; align-items: center; gap: 2rem;">
		<div style="width: 75%; padding: 1rem; display: flex; flex-direction: column; gap: 1rem;">
			<h1>Quiz Details</h1>
			<table style="width: 100%; border-collapse: collapse;">
				<thead>
					<tr>
						<th style="padding: 0.7rem; border: 1px solid lightgrey;">Quiz Title</th>
						<th style="padding: 0.7rem; border: 1px solid lightgrey;">Category</th>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<td style="text-align: center; padding: 0.7rem; border: 1px solid lightgrey;"><%= quiz.getTitle() %></td>
						<td style="text-align: center; padding: 0.7rem; border: 1px solid lightgrey;"><%= category.getName() %></td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div style="width: 75%; padding: 1rem; display: flex; flex-direction: column; gap: 1rem;">
			<h1>Questions List</h1>
			<% if(questions.isEmpty()) { %>
				<p style="font-size: 18px;">No questions to display!</p>
			<%} else {%>
				<table style="width: 100%; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="width: 10%; padding: 0.7rem; border: 1px solid lightgrey;">Sl No.</th>
							<th style="width: 30%; padding: 0.7rem; border: 1px solid lightgrey;">Question</th>
							<th style="width: 10%; padding: 0.7rem; border: 1px solid lightgrey;">Option-1</th>
							<th style="width: 10%; padding: 0.7rem; border: 1px solid lightgrey;">Option-2</th>
							<th style="width: 10%; padding: 0.7rem; border: 1px solid lightgrey;">Option-3</th>
							<th style="width: 10%; padding: 0.7rem; border: 1px solid lightgrey;">Option-4</th>
							<th style="width: 10%; padding: 0.7rem; border: 1px solid lightgrey;">Answer</th>
							<th style="width: 10%; padding: 0.7rem; border: 1px solid lightgrey;">Action</th>
						</tr>
					</thead>
					
					<tbody>
						<% for(Question question : questions) { %>
							<tr>
								<td style="width: 10%; text-align: center; padding: 0.7rem; border: 1px solid lightgrey;"><%= questions.indexOf(question)+1 %></td>
								<td style="width: 30%; text-align: center; padding: 0.7rem; border: 1px solid lightgrey;"><%= question.getTitle() %></td>
								<td style="width: 10%; text-align: center; padding: 0.7rem; border: 1px solid lightgrey;"><%= question.getOption1() %></td>
								<td style="width: 10%; text-align: center; padding: 0.7rem; border: 1px solid lightgrey;"><%= question.getOption2() %></td>
								<td style="width: 10%; text-align: center; padding: 0.7rem; border: 1px solid lightgrey;"><%= question.getOption3() %></td>
								<td style="width: 10%; text-align: center; padding: 0.7rem; border: 1px solid lightgrey;"><%= question.getOption4() %></td>
								<td style="width: 10%; text-align: center; padding: 0.7rem; border: 1px solid lightgrey;"><%= question.getAnswer() %></td>
								<td style="width: 10%; text-align: center; padding: 0.7rem; border: 1px solid lightgrey;">
									<a href="#">Edit</a>
									<a href="#">Delete</a>
								</td>
							</tr>
						<%}%>
					</tbody>
				</table>
			<% } %>
			
		</div>
	</div>
</body>
</html>