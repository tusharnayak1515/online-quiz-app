<%@page import="org.hibernate.internal.build.AllowSysOut"%>
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
	
	b {
		font-size: 22px;
	}
	
	#play {
		text-decoration: none;
		width: 100px;
		padding: 0.5rem;
		font-size: 18px;
		color: white;
		text-align: center;
		background-color: green;
		cursor: pointer;
		border: 1px solid green;
	}
	
	#play:hover {
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
	<%@include file="user/userNavbar.jsp" %>
	
	<%
		Quiz quiz = (Quiz)request.getAttribute("quiz");
		List<Question> questions = (List<Question>)request.getAttribute("questions");
	%>
	
	<div style="min-height: 100vh; width: 100%; display: flex; flex-direction: column; align-items: center; gap: 1rem; padding: 2rem;">
		<h1><%= quiz.getTitle() %></h1>
		<b>Total Questions: <%= questions.size() %></b>
		<b>Total Marks: <%= questions.size()*1 %></b>
		<a id="play" href="StartQuizServlet?id=<%= quiz.getQuiz_id() %>&offset=<%= 0 %>">Start</a>
	</div>
</body>
</html>