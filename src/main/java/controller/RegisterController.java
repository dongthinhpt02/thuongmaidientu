package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Account;
import entity.Role;
import service.AccountServiceImpl;
import service.IAccountService;

@MultipartConfig
@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet{
	IAccountService accountService = new AccountServiceImpl();

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/login/register.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
		Register(req,resp);
		resp.sendRedirect("login");
	}
	
	protected void Register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");
		String image = req.getParameter("image");
		String phonenumber = req.getParameter("phonenumber");
		Role role = Role.CLIENT;
		int status = 1;
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Account account = new Account(username, password, fullname, phonenumber, email, role, image, status);
		
		account.setEmail(email);
		account.setFullName(fullname);
		account.setImage(image);
		account.setPhoneNumber(phonenumber);
		account.setRole(role);
		account.setStatus(status);
		account.setUsername(username);
		account.setPassword(password);
		
		accountService.insert(account);
		
		System.out.println(email);
		System.out.println(fullname);
	}
}
