

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
 * Servlet implementation class AttemptQuizServlet
 */
@WebServlet("/AttemptQuizServlet")
public class AttemptQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttemptQuizServlet() {
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
			Quiz quiz = qs.fetchQuizById(id);
			request.setAttribute("quiz", quiz);
			int categoryId = quiz.getCategory().getCategory_id();
			List<Question> questions = qs.fetchQuestionsByCategory(categoryId);
			request.setAttribute("questions", questions);
			rd = request.getRequestDispatcher("attemptQuiz.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
