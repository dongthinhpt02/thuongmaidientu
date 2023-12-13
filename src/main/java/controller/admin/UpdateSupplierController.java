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
@WebServlet(urlPatterns = {"/admin/suppliers/update"})
public class UpdateSupplierController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ISupplierService supplierService = new SupplierServiceImpl();
	IAccountService accountService = new AccountServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    Cookie cookie = GetCookie.getCookieByName(req, "username");
		Account account = accountService.getByUsername((cookie != null) ? cookie.getValue() : null);
		req.setAttribute("account", account);
		int id = Integer.parseInt(req.getParameter("id"));
		Supplier supplier = supplierService.findById(id);
		req.setAttribute("supplier", supplier);
		
		req.getRequestDispatcher("/views/admin/supplier/updatesupplier.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		updateSupplier(req, resp);
		resp.sendRedirect("/Nhom11/admin/suppliers");
	}
	
	protected void updateSupplier(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String image = req.getParameter("image");
		int status = 1;
		
		Supplier supplier = new Supplier(id, name, image, status);
		
		
		supplierService.update(supplier);
	}
}
