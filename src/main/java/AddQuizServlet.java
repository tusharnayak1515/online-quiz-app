

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.QuizServices;
import com.to.Quiz;

/**
 * Servlet implementation class AddQuizServlet
 */
@WebServlet("/AddQuizServlet")
public class AddQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuizServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			RequestDispatcher rd = null;
			String title = request.getParameter("title");
			int category = Integer.parseInt(request.getParameter("category"));
			QuizServices qs = new QuizServices();
			Quiz quiz = qs.addQuiz(title,category);
			if(quiz == null) {
				rd = request.getRequestDispatcher("QuizServlet");
				rd.forward(request, response);
			}
			else {
				request.setAttribute("quiz", quiz);
				rd = request.getRequestDispatcher("QuizDetailServlet");
				rd.forward(request, response);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
