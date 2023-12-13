package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import concern.GetCookie;
import entity.Account;
import service.AccountServiceImpl;
import service.IAccountService;
import service.IProductService;
import service.ProductServiceImpl;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin/revenue/products"})
public class RevenueProductController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	IProductService productService = new ProductServiceImpl();
	IAccountService accountService = new AccountServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    Cookie cookie = GetCookie.getCookieByName(req, "username");
		Account account = accountService.getByUsername((cookie != null) ? cookie.getValue() : null);
		req.setAttribute("account", account);
		getRevenueByAllProducts(req, resp);
		req.getRequestDispatcher("/views/admin/revenue/revenueproduct.jsp").forward(req, resp);
	}
	protected void getRevenueByAllProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			List<Object[]> list = productService.getRevenueByAllProducts();

			// thông báo

			req.setAttribute("products", list);
			
			

		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", "Eror: " + e.getMessage());

		}
	}
}
