package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IProductService;
import service.ProductServiceImpl;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin/products/restore"})
public class RestoreProductController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	IProductService productService = new ProductServiceImpl();
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		restoreProduct(req,resp);
		resp.sendRedirect("/Nhom11/admin/products");
	}

	protected void restoreProduct(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		try {
			productService.restore(id);
//            resp.sendRedirect("/admin/accounts"); // Redirect to account list page after deletion
		} catch (Exception e) {
			e.printStackTrace();

			req.setAttribute("error", "Eror: " + e.getMessage());
		}
	}
}
