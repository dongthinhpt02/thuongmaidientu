package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import concern.GetCookie;
import entity.Account;
import entity.Category;
import entity.Supplier;
import service.AccountServiceImpl;
import service.CategoryServiceImpl;
import service.IAccountService;
import service.ICategoryService;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin/categories/update"})
public class UpdateCategoryController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	ICategoryService cateService = new CategoryServiceImpl();
	IAccountService accountService = new AccountServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    Cookie cookie = GetCookie.getCookieByName(req, "username");
		Account account = accountService.getByUsername((cookie != null) ? cookie.getValue() : null);
		req.setAttribute("account", account);
		int id = Integer.parseInt(req.getParameter("id"));
		Category category = cateService.findById(id);
		req.setAttribute("category", category);
		
		req.getRequestDispatcher("/views/admin/category/updatecategory.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		updateCategory(req, resp);
		resp.sendRedirect("/Nhom11/admin/categories");
	}
	
	protected void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String image = req.getParameter("image");
		int status = 1;
		
		Category category = new Category(id, name, image, status);
		
		
		cateService.update(category);
	}
	
}
