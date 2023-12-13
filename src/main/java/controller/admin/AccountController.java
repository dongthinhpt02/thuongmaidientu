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

@MultipartConfig
@WebServlet(urlPatterns = {"/admin/accounts"})
public class AccountController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IAccountService accountService = new AccountServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    Cookie cookie = GetCookie.getCookieByName(req, "username");
		Account account = accountService.getByUsername((cookie != null) ? cookie.getValue() : null);
		req.setAttribute("account", account);
		findAllUser(req,resp);
		req.getRequestDispatcher("/views/admin/account/listaccount.jsp").forward(req, resp);
	}
	
	protected void findAll(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		try {

			List<Account> list = accountService.findAll();

			// thông báo

			request.setAttribute("accounts", list);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}
	}
	protected void findAllUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			List<Account> list1 = accountService.findAllUsers();
			
			for (Account account : list1) {
			    System.out.println("Account ID: " + account.getId());
			    System.out.println("Account Name: " + account.getFullName());
			    // Print other details as needed
			    System.out.println("---------------------------------------");
			}

			// thông báo

			request.setAttribute("accounts1", list1);
			
			

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}
	}
}
