package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.connection.DBConnection;
import com.to.Category;
import com.to.Question;
import com.to.Quiz;
import com.to.Score;
import com.to.User;

public class QuizServices {
	private Connection conObj = null;
	
	public QuizServices() {
		this.conObj = DBConnection.connectToDB();
	}
	
	public List<Quiz> fetchQuizes() {
		List<Quiz> quizList = new ArrayList<Quiz>();
		PreparedStatement ps = null;
		try {
			ps = conObj.prepareStatement("select * from quizes");
			ResultSet quizRes = ps.executeQuery();
			while(quizRes.next()) {
				Quiz quiz = new Quiz();
				quiz.setQuiz_id(quizRes.getInt("quiz_id"));
				quiz.setTitle(quizRes.getString("title"));
				ps = conObj.prepareStatement("select * from categories where category_id=?");
				ps.setInt(1, quizRes.getInt("category"));
				
				ResultSet isCategory = ps.executeQuery();
				if(isCategory.next()) {
					Category category = new Category();
					category.setCategory_id(isCategory.getInt("category_id"));
					category.setName(isCategory.getString("name"));
					quiz.setCategory(category);			
					quizList.add(quiz);
				}
				else {
					System.out.println("Invalid category");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quizList;
	}
	
	public List<Category> fetchCategories() {
		List<Category> categoryList = new ArrayList<Category>();
		PreparedStatement ps = null;
		try {
			ps = conObj.prepareStatement("select * from categories");
			ResultSet categoryRes = ps.executeQuery();
			while(categoryRes.next()) {
				Category category = new Category();
				category.setCategory_id(categoryRes.getInt("category_id"));
				category.setName(categoryRes.getString("name"));
				categoryList.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	
	public boolean addCategory(String name) {
		boolean result = false;
		PreparedStatement ps = null;
		try {
			ps = conObj.prepareStatement("insert into categories(name) values(?)");
			ps.setString(1, name);
			
			int res = ps.executeUpdate();
			if(res >= 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Category> deleteCategory(int id) {
		List<Category> categoryList = fetchCategories();
		PreparedStatement ps = null;
		try {
			ps = conObj.prepareStatement("delete from categories where category_id=?");
			ps.setInt(1, id);
			
			int res = ps.executeUpdate();
			if(res >= 1) {
				categoryList = fetchCategories();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	
	public Category getCategory(int id) {
		Category category = new Category();
		PreparedStatement ps = null;
		try {
			ps = conObj.prepareStatement("select * from categories where category_id=?");
			ps.setInt(1, id);
			
			ResultSet isCategory = ps.executeQuery();
			if(isCategory.next()) {
				category.setCategory_id(isCategory.getInt("category_id"));
				category.setName(isCategory.getString("name"));
			}
			else {
				category = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}
	
	public List<Category> editCategory(int id, String name) {
		List<Category> categoryList = fetchCategories();
		PreparedStatement ps = null;
		try {
			ps = conObj.prepareStatement("update categories set name=? where category_id=?");
			ps.setString(1, name);
			ps.setInt(2, id);
			
			int res = ps.executeUpdate();
			if(res >= 1) {
				categoryList = fetchCategories();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	
	public Quiz addQuiz(String title, int category) {
		Quiz quiz = new Quiz();
		PreparedStatement ps = null;
		try {
			ps = conObj.prepareStatement("insert into quizes(title,category) values(?,?)");
			ps.setString(1, title);
			ps.setInt(2, category);
			
			int res = ps.executeUpdate();
			
			if(res >= 1) {
				ps = conObj.prepareStatement("select * from quizes order by quiz_id desc limit 1");
				ResultSet isQuiz = ps.executeQuery();
				if(isQuiz.next()) {
					quiz.setQuiz_id(isQuiz.getInt("quiz_id"));
					quiz.setTitle(isQuiz.getString("title"));
					Category cat = new Category();
					ps= conObj.prepareStatement("select * from categories where category_id=?");
					ps.setInt(1, category);
					ResultSet isCategory = ps.executeQuery();
					if(isCategory.next()) {
						cat.setCategory_id(isCategory.getInt("category_id"));
						cat.setName(isCategory.getString("name"));
						quiz.setCategory(cat);											
					}
				}
			}
			else {
				quiz = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quiz;
	}
	
	public List<Question> fetchQuestionsByCategory(int category) {
		List<Question> questions = new ArrayList<Question>();
		PreparedStatement ps = null;
		try {
			ps = conObj.prepareStatement("select * from questions where category=?");
			ps.setInt(1, category);
			
			ResultSet isQuestions = ps.executeQuery();
			while(isQuestions.next()) {
				Question question = new Question();
				question.setQuestion_id(isQuestions.getInt("question_id"));
				question.setTitle(isQuestions.getString("title"));
				question.setOption1(isQuestions.getString("option1"));
				question.setOption2(isQuestions.getString("option2"));
				question.setOption3(isQuestions.getString("option3"));
				question.setOption4(isQuestions.getString("option4"));
				question.setAnswer(isQuestions.getString("answer"));
				
				ps = conObj.prepareStatement("select * from categories where category_id=?");
				ps.setInt(1, category);
				
				ResultSet isCategory = ps.executeQuery();
				if(isCategory.next()) {
					Category cat = new Category();
					cat.setCategory_id(isCategory.getInt("category_id"));
					cat.setName(isCategory.getString("name"));
					question.setCategory(cat);
					questions.add(question);					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questions;
	}
	
	public List<Question> fetchAllQuestions() {
		List<Question> questions = new ArrayList<Question>();
		PreparedStatement ps = null;
		try {
			ps = conObj.prepareStatement("select * from questions");
			
			ResultSet isQuestions = ps.executeQuery();
			while(isQuestions.next()) {
				Question question = new Question();
				question.setQuestion_id(isQuestions.getInt("question_id"));
				question.setTitle(isQuestions.getString("title"));
				question.setOption1(isQuestions.getString("option1"));
				question.setOption2(isQuestions.getString("option2"));
				question.setOption3(isQuestions.getString("option3"));
				question.setOption4(isQuestions.getString("option4"));
				question.setAnswer(isQuestions.getString("answer"));
				
				ps = conObj.prepareStatement("select * from categories where category_id=?");
				ps.setInt(1, isQuestions.getInt("category"));
				
				ResultSet isCategory = ps.executeQuery();
				if(isCategory.next()) {
					Category cat = new Category();
					cat.setCategory_id(isCategory.getInt("category_id"));
					cat.setName(isCategory.getString("name"));
					question.setCategory(cat);
					questions.add(question);					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questions;
	}
	
	public List<Question> addQuestion(Question question) {
		PreparedStatement ps = null;
		try {
			ps = conObj.prepareStatement("insert into questions(title,option1,option2,option3,option4,answer,category) values(?,?,?,?,?,?,?)");
			ps.setString(1, question.getTitle());
			ps.setString(2, question.getOption1());
			ps.setString(3, question.getOption2());
			ps.setString(4, question.getOption3());
			ps.setString(5, question.getOption4());
			ps.setString(6, question.getAnswer());
			ps.setInt(7, question.getCategory().getCategory_id());
			
			int res = ps.executeUpdate();
			
			if(res >= 1) {
				System.out.println("Question added......");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Question> questions = fetchAllQuestions();
		return questions;
	}
	
	public Quiz fetchQuizById(int id) {
		Quiz quiz = new Quiz();
		PreparedStatement ps = null;
		try {
			ps = conObj.prepareStatement("select * from quizes where quiz_id=?");
			ps.setInt(1, id);
			
			ResultSet isQuiz = ps.executeQuery();
			if(isQuiz.next()) {
				quiz.setQuiz_id(isQuiz.getInt("quiz_id"));
				quiz.setTitle(isQuiz.getString("title"));
				Category category = getCategory(isQuiz.getInt("category"));
				quiz.setCategory(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("question service");
		return quiz;
	}
	
	public Question getQuestionByCategory(int id, int offset) {
		Question question = new Question();
		PreparedStatement ps = null;
		try {
			ps = conObj.prepareStatement("select * from questions where category=? limit 1 offset ?");
			ps.setInt(1, id);
			ps.setInt(2, offset);
			
			ResultSet isQuestion = ps.executeQuery();
			if(isQuestion.next()) {
				question.setQuestion_id(isQuestion.getInt("question_id"));
				question.setTitle(isQuestion.getString("title"));
				question.setOption1(isQuestion.getString("option1"));
				question.setOption2(isQuestion.getString("option2"));
				question.setOption3(isQuestion.getString("option3"));
				question.setOption4(isQuestion.getString("option4"));
				question.setAnswer(isQuestion.getString("answer"));
				
				ps = conObj.prepareStatement("select * from categories where category_id=?");
				ps.setInt(1, isQuestion.getInt("category"));
				
				ResultSet isCategory = ps.executeQuery();
				if(isCategory.next()) {
					Category cat = new Category();
					cat.setCategory_id(isCategory.getInt("category_id"));
					cat.setName(isCategory.getString("name"));
					question.setCategory(cat);					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return question;
	}
	
	public boolean submitAnswer(int quizId, int qid, String answer, String email) {
		int userId = 0;
		boolean result = false;
		PreparedStatement ps = null;
		try {
			ps = conObj.prepareStatement("select * from users where email=?");
			ps.setString(1, email);
			
			ResultSet isUser = ps.executeQuery();
			if(isUser.next()) {
				userId = isUser.getInt("user_id");
			}
			
			Question question = getQuestion(qid);
			if(question != null) {
				System.out.println("question in service: "+question);
//				System.out.println("isCorrect service: "+answer.equals(question.getAnswer()));
				if(answer.equals(question.getAnswer())) {
					ps = conObj.prepareStatement("select * from scores where user=? and quiz=?");
					ps.setInt(1, userId);
					ps.setInt(2, quizId);
					
					ResultSet isScore = ps.executeQuery();
					if(isScore.next()) {
						ps = conObj.prepareStatement("update scores set score=? where score_id=?");
						ps.setInt(1, isScore.getInt("score")+1);
						ps.setInt(2, isScore.getInt("score_id"));
						
						int res = ps.executeUpdate();
						if(res >= 1) {
							result = true;
							System.out.println("Correct answer! Score updated......");
						}
					}
					else {
						System.out.println("yes");
						PreparedStatement ps1 = conObj.prepareStatement("insert into scores(score,quiz,user) values(?,?,?)");
						ps1.setInt(1, 1);
						ps1.setInt(2, quizId);
						ps1.setInt(3, userId);
						
						int res = ps1.executeUpdate();
						
						if(res >= 1) {
							result = true;
							System.out.println("Correct answer! Score updated......");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Score> getScores(int qid) {
		List<Score> scores = new ArrayList<Score>();
		PreparedStatement ps = null;
		try {
			ps = conObj.prepareStatement("select * from scores where quiz=? order by score desc");
			ps.setInt(1, qid);
			
			ResultSet isScores = ps.executeQuery();
			while(isScores.next()) {
				Score score = new Score();
				score.setScore_id(isScores.getInt("score_id"));
				score.setScore(isScores.getInt("score"));
				Quiz quiz = fetchQuizById(isScores.getInt("quiz"));
				score.setQuiz(quiz);
				ps = conObj.prepareStatement("select * from users where user_id=?");
				ps.setInt(1, isScores.getInt("user"));
				
				User user = new User();
				ResultSet isUser = ps.executeQuery();
				if(isUser.next()) {
					user.setEmail(isUser.getString("email"));
				}
				score.setUser(user);
				scores.add(score);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scores;
	}
	
	public Question getQuestion(int qid) {
		Question question = new Question();
		PreparedStatement ps = null;
		try {
			ps = conObj.prepareStatement("select * from questions where question_id=?");
			ps.setInt(1, qid);
			
			ResultSet isQuestion = ps.executeQuery();
			if(isQuestion.next()) {
				question.setQuestion_id(isQuestion.getInt("question_id"));
				question.setTitle(isQuestion.getString("title"));
				question.setOption1(isQuestion.getString("option1"));
				question.setOption2(isQuestion.getString("option2"));
				question.setOption3(isQuestion.getString("option3"));
				question.setOption4(isQuestion.getString("option4"));
				question.setAnswer(isQuestion.getString("answer"));
			}
			else {
				question = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return question;
	}
}
