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
	
	#startQuiz {
		text-decoration: none;
		padding: 0.5rem;
		color: white;
		border: 1px solid green;
		background-color: green;
	}
	
	#startQuiz:hover {
		color: green;
		background-color: white;
		transition: all 0.2s ease-in-out;
	}
</style>
</head>
<body>
	<% 
		User user = (User)session.getAttribute("user");
		if(user == null) {
			response.sendRedirect("index.jsp");
		}
	%>
	<%@include file="userNavbar.jsp" %>
	
	<div style="min-height: 100vh; width: 100%; display: flex; flex-direction: column; align-items: center; padding: 2rem;">
		<div style="width: 75%; padding: 1rem; display: flex; flex-direction: column; gap: 1.5rem;">
			<h1>Ongoing Quizes</h1>
			
			<% 
				List<Quiz> quizList = (List<Quiz>)request.getAttribute("quizes");
				if(quizList == null) {
					out.println("<p style='font-size: 20px;'>No Quiz to display!</p>");
				}
				else {
					if(quizList.isEmpty()) {
						out.println("<p style='font-size: 20px;'>No Quiz to display!</p>");
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
							<% int index = quizList.indexOf(quiz)+1; %>
							<tr>
								<td 
								style="width: 20%; padding: 0.7rem; 
								text-align: center; border: 1px solid lightgrey;">
									<%= index %>
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
									<a id="startQuiz" href="AttemptQuizServlet?id=<%= quiz.getQuiz_id() %>">
										Attempt
									</a>
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