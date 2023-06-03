

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class UpdateCategoryServlet
 */
@WebServlet("/UpdateCategoryServlet")
public class UpdateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCategoryServlet() {
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
			RequestDispatcher rd = null;
			QuizServices qs = new QuizServices();
			int id = Integer.parseInt(request.getParameter("id"));
			Category category = qs.getCategory(id);
			if(category == null) {
				out.println("<p>Invalid Category!</p>");
			}
			else {
				String name = request.getParameter("name");
				if(!name.replaceAll("\\s", "").equals("")) {
					List<Category> categoryList = qs.editCategory(id,name);
					request.setAttribute("categoryList", categoryList);
					rd = request.getRequestDispatcher("CategoryServlet");
					rd.forward(request, response);				
				}
				else {
					request.setAttribute("category", category);
					rd = request.getRequestDispatcher("displayCategory.jsp");
					rd.forward(request, response);
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
