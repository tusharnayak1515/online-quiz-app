

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.QuizServices;
import com.to.Category;
import com.to.Question;
import com.to.Quiz;

/**
 * Servlet implementation class QuizDetailServlet
 */
@WebServlet("/QuizDetailServlet")
public class QuizDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			RequestDispatcher rd =null;
			QuizServices qs = new QuizServices();
			Quiz quiz = (Quiz)request.getAttribute("quiz");
			Category category = quiz.getCategory();
			List<Question> questions = qs.fetchQuestionsByCategory(category.getCategory_id());
			request.setAttribute("questions", questions);
			rd = request.getRequestDispatcher("quizDetail.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
