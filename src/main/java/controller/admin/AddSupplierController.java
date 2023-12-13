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
import entity.Supplier;
import service.AccountServiceImpl;
import service.IAccountService;
import service.ISupplierService;
import service.SupplierServiceImpl;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin/suppliers/add"})
public class AddSupplierController extends HttpServlet{

	/**
	 * 
	 */
	ISupplierService supplierService = new SupplierServiceImpl();
	IAccountService accountService = new AccountServiceImpl();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    Cookie cookie = GetCookie.getCookieByName(req, "username");
		Account account = accountService.getByUsername((cookie != null) ? cookie.getValue() : null);
		req.setAttribute("account", account);
		req.getRequestDispatcher("/views/admin/supplier/addsupplier.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
		insertSupplier(req, resp);
		resp.sendRedirect("/Nhom11/admin/suppliers");
	}

	protected void insertSupplier(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String image = req.getParameter("image");
		int status = 1;
		
		Supplier supplier = new Supplier(name, image, status);
		supplier.setName(name);
		supplier.setImage(image);
		supplier.setStatus(status);
		
		
		supplierService.insert(supplier);
	}
}
