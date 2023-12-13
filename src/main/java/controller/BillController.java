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
import entity.Bill;
import entity.Product;
import service.AccountServiceImpl;
import service.BillServiceImpl;
import service.IAccountService;
import service.IBillDetailService;
import service.IBillService;

@MultipartConfig
@WebServlet(urlPatterns = {"/bills"})
public class BillController extends HttpServlet {

	IAccountService accountService = new AccountServiceImpl();
	IBillService billService = new BillServiceImpl();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		Cookie cookie = GetCookie.getCookieByName(req, "username");
		String accountIdString = cookie.getValue();
		Account account = accountService.getByUsername(accountIdString);
		String name = account.getFullName();
		getAllBillByAccountId(req, resp);
		req.getRequestDispatcher("/views/bill/bill.jsp").forward(req, resp);

	}

	protected void getAllBillByAccountId(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Cookie cookie = GetCookie.getCookieByName(req, "username");
		String accountIdString = cookie.getValue();
		Account account = accountService.getByUsername(accountIdString);
		int accountId = account.getId();
		
		try {

			List<Bill> list = billService.getAllBillByAccountId(accountId);

			
			req.setAttribute("account", account);
			

			req.setAttribute("bills", list);

		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", "Eror: " + e.getMessage());

		}
	}
}
