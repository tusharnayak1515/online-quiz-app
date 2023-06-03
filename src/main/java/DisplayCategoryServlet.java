

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.QuizServices;
import com.to.Category;

/**
 * Servlet implementation class DisplayCategoryServlet
 */
@WebServlet("/DisplayCategoryServlet")
public class DisplayCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			QuizServices qs = new QuizServices();
			RequestDispatcher rd =null;
			int id = Integer.parseInt(request.getParameter("id"));
			Category category = qs.getCategory(id);
			if(category == null) {
				out.println("<p>Invalid Category!</p>");
			}
			else {
				request.setAttribute("category", category);
				rd = request.getRequestDispatcher("displayCategory.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
