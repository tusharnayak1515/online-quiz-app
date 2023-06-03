

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
 * Servlet implementation class AddCategoryServlet
 */
@WebServlet("/AddCategoryServlet")
public class AddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategoryServlet() {
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
			String name = request.getParameter("name");
			List<Category> categoryList = new ArrayList<Category>();
			if(!name.replaceAll("\\s", "").equals("")) {
				boolean res = qs.addCategory(name);
				
				if(!res) {
					categoryList = qs.fetchCategories();
					request.setAttribute("categoryList", categoryList);
					rd = request.getRequestDispatcher("category.jsp");
					rd.include(request, response);
					out.println("<b style='height: 6vh; width: 120px; padding: 1.5rem; background-color: white; border: 1px solid red; font-size: 18px; color: red; margin-bottom: 1rem;'>Add category failed!</b>");;
				}
				else {
					categoryList = qs.fetchCategories();
					request.setAttribute("categoryList", categoryList);
					rd = request.getRequestDispatcher("category.jsp");
					rd.forward(request, response);
				}
			}
			else {
				categoryList = qs.fetchCategories();
				request.setAttribute("categoryList", categoryList);
				rd = request.getRequestDispatcher("category.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
