<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="com.services.QuizServices"%>
<%@page import="com.to.Quiz"%>
<%@page import="com.to.User"%>
<%@page import="java.util.List"%>
<%@page import="com.to.Question"%>
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
	
	label {
		font-size: 18px;
	}
	
	#submit {
		text-decoration: none;
		width: 100px;
		padding: 0.5rem;
		font-size: 18px;
		color: green;
		text-align: center;
		background-color: white;
		cursor: pointer;
		border: 1px solid green;
	}
	
	#submit:hover {
		color: white;
		background-color: green;
		transition: all 0.2s ease-in-out;
	}
	
	#next {
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
	
	#next:hover {
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
		Question question = (Question)request.getAttribute("question");
		Quiz quiz = (Quiz)request.getAttribute("quiz");
		List<Question> questions = (List<Question>)request.getAttribute("questions");
		int index = -1;
		for(Question q:questions) {
			if(q.getQuestion_id() == question.getQuestion_id()) {
				index = questions.indexOf(q)+1;
			}
		}
	%>
	
	<div style="min-height: 100vh; width: 100%; display: flex; flex-direction: column; align-items: center; padding: 2rem;">
		<div style="width: 75%; padding: 1rem; display: flex; flex-direction: column; gap: 1.5rem;">
			 <h1>Q<%= index %>. <%= question.getTitle() %></h1>
			 <b style="font-size: 20px;">Options</b>
			 <form action="ValidateAnswerServlet" method="post" style=" display: flex; flex-direction: column; gap: 1rem;">
				 <div style="width: 100%; display: flex; justify-content: start; align-items: center; gap: 0.3rem;">
				 	<input type="checkbox" name="option1" id="option1" value="<%= question.getOption1() %>" />
				 	<label for="option1"><%= question.getOption1() %></label>
				 </div>
				 
				 <input type="hidden" name="quiz_id" value="<%= quiz.getQuiz_id() %>" />
				 <input type="hidden" name="questionid" value="<%= question.getQuestion_id() %>" />
			 
				 <div style="width: 100%; display: flex; justify-content: start; align-items: center; gap: 0.3rem;">
				 	<input type="checkbox" name="option2" id="option2" value="<%= question.getOption2() %>" />
				 	<label for="option2"><%= question.getOption2() %></label>
				 </div>
			 
				 <div style="width: 100%; display: flex; justify-content: start; align-items: center; gap: 0.3rem;">
				 	<input type="checkbox" name="option3" id="option3" value="<%= question.getOption3() %>" />
				 	<label for="option3"><%= question.getOption3() %></label>
				 </div>
			 
				 <div style="width: 100%; display: flex; justify-content: start; align-items: center; gap: 0.3rem;">
				 	<input type="checkbox" name="option4" id="option4" value="<%= question.getOption4() %>" />
				 	<label for="option4"><%= question.getOption4() %></label>
				 </div>
			 
				 <div style="width: 100%; display: flex; justify-content: start; align-items: center; gap: 1rem;">
				 	<input type="submit" id="submit" name="submit" value="Submit" />
				 </div>
			 </form>
			 
		</div>
	</div>
</body>
</html>