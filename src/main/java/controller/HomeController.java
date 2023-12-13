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
import javax.servlet.http.HttpSession;

import concern.GetCookie;
import entity.Account;
import entity.Category;
import entity.Product;
import entity.Supplier;
import service.AccountServiceImpl;
import service.CategoryServiceImpl;
import service.IAccountService;
import service.ICategoryService;
import service.IProductService;
import service.ISupplierService;
import service.ProductServiceImpl;
import service.SupplierServiceImpl;

@MultipartConfig
@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet{
	ICategoryService cateService = new CategoryServiceImpl();
	IProductService productService = new ProductServiceImpl();
	ISupplierService supplierService = new SupplierServiceImpl();
	IAccountService accountService = new AccountServiceImpl();
	private static final long serialVersionUID = 7093021346907994761L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
		Cookie cookie = GetCookie.getCookieByName(req, "username");
		Account account = accountService.getByUsername((cookie != null) ? cookie.getValue() : null);
		req.setAttribute("account", account);
		findAll(req, resp);
		req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
	}
	protected void findAll(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		try {
			String categoryParam = request.getParameter("categoryId");
			String supplierParam = request.getParameter("supplierId");
			
//			int categoryId = categoryParam.isEmpty() ? 0 : Integer.parseInt(categoryParam);
//			int supplierId = supplierParam.isEmpty() ? 0 : Integer.parseInt(supplierParam);
			
			int categoryId = 0;
			int supplierId = 0;
			
			if (categoryParam != null)
				if (!categoryParam.isEmpty()) categoryId = Integer.parseInt(categoryParam);
			if (supplierParam != null)
				if (!supplierParam.isEmpty()) supplierId = Integer.parseInt(supplierParam);
			List<Product> list = productService.findProductByCategoryAndSupplierId(categoryId, supplierId);
//			List<Product> list = productService.findAll();

			// thông báo

			request.setAttribute("products", list);
			
			List<Category> list1 = cateService.findAllActive();

			// thông báo

			request.setAttribute("categories", list1);
			
			List<Supplier> list2 = supplierService.findAll();

			// thông báo

			request.setAttribute("suppliers", list2);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
	}
//    private Cookie getCookieByName(HttpServletRequest request, String name) {
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals(name)) {
//                    return cookie;
//                }
//            }
//        }
//        return null;
//    }
}
