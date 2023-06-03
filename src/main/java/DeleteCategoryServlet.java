

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.QuizServices;
import com.to.Category;

/**
 * Servlet implementation class DeleteCategoryServlet
 */
@WebServlet("/DeleteCategoryServlet")
public class DeleteCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCategoryServlet() {
        super();
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
			List<Category> categoryList = new ArrayList<Category>();
			int id = Integer.parseInt(request.getParameter("id"));
			
			Category category = qs.getCategory(id);
			if(category == null) {
				categoryList = qs.fetchCategories();
				request.setAttribute("categoryList", categoryList);
				rd = request.getRequestDispatcher("category.jsp");
				rd.forward(request, response);
			}
			else {
				categoryList = qs.deleteCategory(id);
				request.setAttribute("categoryList", categoryList);
				rd = request.getRequestDispatcher("category.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
