<%@page import="com.to.Quiz"%>
<%@page import="com.to.Score"%>
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
	%>
	<%@include file="user/userNavbar.jsp" %>
	
	<%
		List<Score> scores = (List<Score>)request.getAttribute("scores");
		Quiz quiz = (Quiz)request.getAttribute("quiz");
	%>
	
	<div style="min-height: 100vh; width: 100%; display: flex; flex-direction: column; align-items: center; padding: 2rem;">
		<div style="width: 75%; padding: 1rem; display: flex; flex-direction: column; gap: 1.5rem;">
			<h1>Quiz Name:<%= quiz.getTitle() %></h1>
			<h1>Category:<%= quiz.getCategory().getName() %></h1>
			
			<% if(scores.isEmpty()) { %>
				<p style="font-size: 20px;">No score to display!</p>
			<% } else { %>
				<table style="width: 100%; border-collapse: collapse;">
					<thead>
						<tr>
							<th style="padding: 0.7rem; border: 1px solid lightgrey;">Sl No</th>
							<th style="padding: 0.7rem; border: 1px solid lightgrey;">User</th>
							<th style="padding: 0.7rem; border: 1px solid lightgrey;">Score</th>
						</tr>
					</thead>
					
					<tbody>
						<% for(Score score:scores) { %>
							<tr>
								<td style="text-align: center; padding: 0.7rem; border: 1px solid lightgrey;"><%= scores.indexOf(score)+1 %></td>
								<td style="text-align: center; padding: 0.7rem; border: 1px solid lightgrey;"><%= score.getUser().getEmail() %></td>
								<td style="text-align: center; padding: 0.7rem; border: 1px solid lightgrey;"><%= score.getScore() %></td>
							</tr>
						<% } %>
					</tbody>
				</table>
			<% } %>
		</div>
	</div>
	
</body>
</html>