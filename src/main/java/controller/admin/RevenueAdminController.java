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
import service.AccountServiceImpl;
import service.IAccountService;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin/revenue"})
public class RevenueAdminController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	IAccountService accountService = new AccountServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    Cookie cookie = GetCookie.getCookieByName(req, "username");
		Account account = accountService.getByUsername((cookie != null) ? cookie.getValue() : null);
		req.setAttribute("account", account);
		req.getRequestDispatcher("/views/admin/revenue/revenue.jsp").forward(req, resp);
	}

}