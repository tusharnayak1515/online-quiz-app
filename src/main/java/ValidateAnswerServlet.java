

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.services.QuizServices;
import com.to.Question;
import com.to.Quiz;
import com.to.User;

/**
 * Servlet implementation class ValidateAnswerServlet
 */
@WebServlet("/ValidateAnswerServlet")
public class ValidateAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateAnswerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			User user = new User();
			if(session != null) {
				user = (User)session.getAttribute("user");
			}
			String answer = null;
			int qid = Integer.parseInt(request.getParameter("quiz_id"));
			int questionid = Integer.parseInt(request.getParameter("questionid"));
			QuizServices qs = new QuizServices();
			Quiz quiz = qs.fetchQuizById(qid);
			int index = -1;
			Question question = qs.getQuestion(questionid);
			List<Question> questions = qs.fetchQuestionsByCategory(quiz.getCategory().getCategory_id()); 
			for(Question q:questions) {
				if(q.getQuestion_id() == questionid) {
					index = questions.indexOf(q);
				}
			}
			RequestDispatcher rd = null;
	 		if(request.getParameter("option1") != null) {
	 			answer = request.getParameter("option1");
	 		}
	 		else if(request.getParameter("option2") != null) {
	 			answer = request.getParameter("option2");
	 		}
	 		else if(request.getParameter("option3") != null) {
	 			answer = request.getParameter("option3");
	 			System.out.println(answer);
	 		}
	 		else if(request.getParameter("option4") != null) {
	 			answer = request.getParameter("option4");			 			
	 		}
	 		else {
	 			rd = request.getRequestDispatcher("StartQuizServlet?id="+quiz.getQuiz_id()+"&offset="+questions.indexOf(question));
	 			rd.forward(request, response);
	 		}
	 		
	 		boolean isCorrect = qs.submitAnswer(quiz.getQuiz_id(),question.getQuestion_id(), answer, user.getEmail());
	 		
	 		int offset = index+1;
	 		if(offset < questions.size()) {
 				rd = request.getRequestDispatcher("StartQuizServlet?id="+quiz.getQuiz_id()+"&offset="+offset);
 				rd.forward(request,response);
 			}
 			else {
 				rd = request.getRequestDispatcher("LeaderBoard?id="+quiz.getQuiz_id());
 				rd.forward(request,response);
 			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
