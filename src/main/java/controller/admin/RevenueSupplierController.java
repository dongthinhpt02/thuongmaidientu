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
import service.ISupplierService;
import service.SupplierServiceImpl;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin/revenue/suppliers"})
public class RevenueSupplierController extends HttpServlet{

	ISupplierService supplierService = new SupplierServiceImpl();
	private static final long serialVersionUID = 1L;
	IAccountService accountService = new AccountServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    Cookie cookie = GetCookie.getCookieByName(req, "username");
		Account account = accountService.getByUsername((cookie != null) ? cookie.getValue() : null);
		req.setAttribute("account", account);
		getRevenueByAllSuppliers(req, resp);
		req.getRequestDispatcher("/views/admin/revenue/revenuesupplier.jsp").forward(req, resp);
	}

	protected void getRevenueByAllSuppliers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			List<Object[]> list = supplierService.getRevenueByAllSuppliers();

			// thông báo

			req.setAttribute("suppliers", list);
			
			

		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", "Eror: " + e.getMessage());

		}
	}
}
