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
import entity.Category;
import entity.Supplier;
import service.AccountServiceImpl;
import service.IAccountService;
import service.ISupplierService;
import service.SupplierServiceImpl;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin/suppliers"})
public class SuppilerAdminController extends HttpServlet{

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
		findAll(req,resp);
		findAllActive(req, resp);
		req.getRequestDispatcher("/views/admin/supplier/listsupplier.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	protected void findAll(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		try {

			List<Supplier> list = supplierService.findAll();

			// thông báo

			request.setAttribute("Suppliers", list);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}
	protected void findAllActive(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		try {

			List<Supplier> list1 = supplierService.findAllActive();
			
			for (Supplier supplier : list1) {
			    System.out.println("Supplier ID: " + supplier.getId());
			    System.out.println("Supplier Name: " + supplier.getName());
			    // Print other supplier details as needed
			    System.out.println("-----------------------------");
			}

			// thông báo

			request.setAttribute("Suppliers1", list1);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}
}
