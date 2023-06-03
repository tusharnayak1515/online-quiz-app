<%@page import="com.to.Question"%>
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
	
	<%
		List<Category> categoryList = (List<Category>)request.getAttribute("categoryList");
		List<Question> questions = (List<Question>)request.getAttribute("questions");
	%>
	
	<div 
	style="min-height: 100vh; width: 100%; 
	display: flex; flex-direction: column; align-items: center; 
	padding: 2rem;">
		<div style="width: 75%; padding: 1rem; display: flex; flex-direction: column; gap: 1.5rem;">
			<h1>Create New Question</h1>
			<form action="AddQuestionServlet" method="post" style="width: 100%; display: flex; flex-direction: column; align-items: start; gap: 1rem;">
				<div style="display: flex; flex-direction: column; align-items: start; gap: 0.3rem;">
					<label for="title">Question</label>
					<input type="text" name="title" id="title" placeholder="Enter question" 
						style="padding: 0.5rem 1rem;" 
						required
					/>
				</div>
				
				<div style="display: flex; flex-direction: column; align-items: start; gap: 0.3rem;">
					<label for="option1">Option-1</label>
					<input type="text" name="option1" id="option1" placeholder="Option 1" 
						style="padding: 0.5rem 1rem;" 
						required
					/>
				</div>
				
				<div style="display: flex; flex-direction: column; align-items: start; gap: 0.3rem;">
					<label for="option2">Option-2</label>
					<input type="text" name="option2" id="option2" placeholder="Option 2" 
						style="padding: 0.5rem 1rem;" 
						required
					/>
				</div>
				
				<div style="display: flex; flex-direction: column; align-items: start; gap: 0.3rem;">
					<label for="option3">Option-3</label>
					<input type="text" name="option3" id="option3" placeholder="Option 3" 
						style="padding: 0.5rem 1rem;" 
						required
					/>
				</div>
				
				<div style="display: flex; flex-direction: column; align-items: start; gap: 0.3rem;">
					<label for="option4">Option-4</label>
					<input type="text" name="option4" id="option4" placeholder="Option 4" 
						style="padding: 0.5rem 1rem;" 
						required
					/>
				</div>
				
				<div style="display: flex; flex-direction: column; align-items: start; gap: 0.3rem;">
					<label for="answer">Answer</label>
					<input type="text" name="answer" id="answer" placeholder="Answer" 
						style="padding: 0.5rem 1rem;" 
						required
					/>
				</div>
				
				<div style="width: 100%; display: flex; flex-direction: column; align-items: start; gap: 0.3rem;">
					<label for="category">Category</label>
					<select name="category" id="category" style="padding: 0.5rem;">
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
			<h1>Questions</h1>
			<% 
				if(categoryList.isEmpty()) {
					out.println("<p style='font-size: 20px;'>No category to display!</p>");
				}
				else {%>
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
				<%}%>
		</div>
	</div>
</body>
</html>