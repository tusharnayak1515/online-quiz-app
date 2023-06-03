

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.QuizServices;
import com.to.Quiz;
import com.to.Score;

/**
 * Servlet implementation class LeaderBoard
 */
@WebServlet("/LeaderBoard")
public class LeaderBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaderBoard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			QuizServices qs = new QuizServices();
			RequestDispatcher rd =null;
			int qid = Integer.parseInt(request.getParameter("id"));
			Quiz quiz = qs.fetchQuizById(qid);
			List<Score> scores = qs.getScores(qid);
			request.setAttribute("scores", scores);
			request.setAttribute("quiz", quiz);
			rd = request.getRequestDispatcher("leaderboard.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
