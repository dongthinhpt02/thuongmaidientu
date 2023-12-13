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
import entity.Bill;
import service.AccountServiceImpl;
import service.BillServiceImpl;
import service.IAccountService;
import service.IBillService;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin/bills"})
public class BillAdminController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	IBillService billService = new BillServiceImpl();
	IAccountService accountService = new AccountServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    Cookie cookie = GetCookie.getCookieByName(req, "username");
		Account account = accountService.getByUsername((cookie != null) ? cookie.getValue() : null);
		getAllBill(req, resp);
		req.getRequestDispatcher("/views/admin/bill/bill.jsp").forward(req, resp);
	}
	
	protected void getAllBill(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		try {

			List<Bill> list = billService.getAllBill();
		
			req.setAttribute("bills", list);

		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", "Eror: " + e.getMessage());

		}
	}
}
