

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

/**
 * Servlet implementation class AddQuestionServlet
 */
@WebServlet("/AddQuestionServlet")
public class AddQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestionServlet() {
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
			String title = request.getParameter("title");
			String option1 = request.getParameter("option1");
			String option2 = request.getParameter("option2");
			String option3 = request.getParameter("option3");
			String option4 = request.getParameter("option4");
			String answer = request.getParameter("answer");
			int categoryId = Integer.parseInt(request.getParameter("category"));
			
			Category category = qs.getCategory(categoryId);
			
			if(
					title.replaceAll("\\s", "").length() != 0 && 
					option1.replaceAll("\\s", "").length() != 0 && 
					option2.replaceAll("\\s", "").length() != 0 && 
					option3.replaceAll("\\s", "").length() != 0 && 
					option4.replaceAll("\\s", "").length() != 0 && 
					(answer.replaceAll("\\s", "").length() != 0 && 
					(answer.equals(option1) || answer.equals(option2) || 
					 answer.equals(option3) || answer.equals(option4))) && 
					category != null
			) {
				Question question = new Question();
				question.setTitle(title);
				question.setOption1(option1);
				question.setOption2(option2);
				question.setOption3(option3);
				question.setOption4(option4);
				question.setAnswer(answer);
				question.setCategory(category);
				
				List<Question> questions = qs.addQuestion(question);
				request.setAttribute("questions", questions);
			}
			rd = request.getRequestDispatcher("QuestionServlet");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
