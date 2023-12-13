package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Account;
import service.AccountServiceImpl;
import service.IAccountService;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin/accounts/delete"})
public class DeleteAccountController extends HttpServlet {

	IAccountService accountService = new AccountServiceImpl();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		deleteAccount(req,resp);
		resp.sendRedirect("/Nhom11/admin/accounts");
	}
	
	protected void deleteAccount(HttpServletRequest  req, HttpServletResponse resp) throws ServletException, IOException{
		int id = Integer.parseInt(req.getParameter("id"));
		
		try {
            accountService.delete(id);
//            resp.sendRedirect("/admin/accounts"); // Redirect to account list page after deletion
        } catch (Exception e) {
        	e.printStackTrace();

			req.setAttribute("error", "Eror: " + e.getMessage());
        }
	}
}
