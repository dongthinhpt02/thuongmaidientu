package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Account;
import entity.Role;
import service.AccountServiceImpl;
import service.IAccountService;

@MultipartConfig
@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

	IAccountService accountSer = new AccountServiceImpl();
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
		req.getRequestDispatcher("/views/login/login.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		
		Account account = accountSer.getByEmailAndPassword(email, password);
		int status = account.getStatus();
		
		if (account != null && status==1) {
			Cookie cookie = new Cookie("username", account.getUsername());
			cookie.setPath("/Nhom11");
			resp.addCookie(cookie);
			
			 if (account.getRole() == Role.ADMIN) { // Assuming UserRole is an enum
			        resp.sendRedirect("admin"); // Redirect to admin home page
			    } else {
			        // Handle other roles or scenarios
			        resp.sendRedirect("home");
			    }
		}
		else {
			req.setAttribute("email", "Đăng nhập thất bại");
			req.getRequestDispatcher("/views/login/login.jsp").forward(req, resp);
		}
	}
	
}
