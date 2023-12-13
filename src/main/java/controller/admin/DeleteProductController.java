package controller.admin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IProductService;
import service.ProductServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin/products/delete"})
public class DeleteProductController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	IProductService productService = new ProductServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		deleteProduct(req, resp);
		resp.sendRedirect("/Nhom11/admin/products");
	}
	
	protected void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		try {
            productService.delete(id);
            
        } catch (Exception e) {
        	e.printStackTrace();

			req.setAttribute("error", "Eror: " + e.getMessage());
        }
	}
}
