package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AccountServiceImpl;
import service.IAccountService;

@MultipartConfig
@WebServlet(urlPatterns = { "/admin/accounts/restore" })
public class RestoreAccountController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IAccountService accountService = new AccountServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		restoreAccount(req,resp);
		resp.sendRedirect("/Nhom11/admin/accounts");
	}

	protected void restoreAccount(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		try {
			accountService.restore(id);
//            resp.sendRedirect("/admin/accounts"); // Redirect to account list page after deletion
		} catch (Exception e) {
			e.printStackTrace();

			req.setAttribute("error", "Eror: " + e.getMessage());
		}
	}
}
