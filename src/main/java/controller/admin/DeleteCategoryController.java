package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CategoryServiceImpl;
import service.ICategoryService;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin/categories/delete"})
public class DeleteCategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ICategoryService cateService = new CategoryServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		deleteCategory(req,resp);
		resp.sendRedirect("/Nhom11/admin/categories");
	}
	
	protected void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		try {
            cateService.delete(id);
//            resp.sendRedirect("/admin/accounts"); // Redirect to account list page after deletion
        } catch (Exception e) {
        	e.printStackTrace();

			req.setAttribute("error", "Eror: " + e.getMessage());
        }
	}
}


