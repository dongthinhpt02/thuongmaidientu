package controller;

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
import entity.Product;
import entity.Supplier;
import service.AccountServiceImpl;
import service.IAccountService;
import service.IProductService;
import service.ProductServiceImpl;

@MultipartConfig
@WebServlet(urlPatterns = {"/products/detail"})
public class ProductDetailController extends HttpServlet{

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
		getProductDetail(req, resp);
		req.getRequestDispatcher("/views/product/productdetail.jsp").forward(req, resp);
	}
	
	protected void getProductDetail (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int id = Integer.parseInt(req.getParameter("id"));
		try {

			Product product = productService.findById(id);

			// th�ng b�o
			
			req.setAttribute("unitPrice", (int) product.getUnitPrice());

			req.setAttribute("product", product);

		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", "Eror: " + e.getMessage());

		}
	}
}
