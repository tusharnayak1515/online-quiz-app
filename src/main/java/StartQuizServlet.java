

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.QuizServices;
import com.to.Question;
import com.to.Quiz;

/**
 * Servlet implementation class StartQuizServlet
 */
@WebServlet("/StartQuizServlet")
public class StartQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartQuizServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			QuizServices qs = new QuizServices();
			RequestDispatcher rd = null;
			int id = Integer.parseInt(request.getParameter("id"));
			int offset = Integer.parseInt(request.getParameter("offset"));
			Quiz quiz = qs.fetchQuizById(id);
			System.out.println("quiz: "+quiz);
			if(quiz != null) {
				List<Question> questions = qs.fetchQuestionsByCategory(quiz.getCategory().getCategory_id());
				Question question = qs.getQuestionByCategory(quiz.getCategory().getCategory_id(), offset);
				request.setAttribute("questions", questions);
				request.setAttribute("question", question);
				request.setAttribute("quiz", quiz);
				rd = request.getRequestDispatcher("playQuiz.jsp");
				rd.forward(request, response);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
