package controller;

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
import entity.Category;
import entity.Role;
import service.AccountServiceImpl;
import service.IAccountService;

@MultipartConfig
@WebServlet(urlPatterns = {"/updateuser"})
public class UpdateUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IAccountService accountService = new AccountServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Cookie cookie = GetCookie.getCookieByName(req, "username");
		String accountIdString = cookie.getValue();
		Account account = accountService.getByUsername(accountIdString);
		int accountId = account.getId();
		Account account1 = accountService.findById(accountId);
		req.setAttribute("account", account1);
		
		System.out.print(accountId);

		req.getRequestDispatcher("/views/user/infouser.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		updateAccount(req, resp);
		resp.sendRedirect("/Nhom11/updateuser");
	}

	protected void updateAccount(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String email = req.getParameter("email");
		String fullName = req.getParameter("fullName");
		String username = req.getParameter("username");
		String image = req.getParameter("image");
		String phoneNumber = req.getParameter("phoneNumber");
		String password = req.getParameter("password");
		int status = 1;
		Role role = Role.CLIENT;

		Account account = new Account(id, username, password, fullName, phoneNumber, email, role, image, status);

		accountService.update(account);
	}
}
