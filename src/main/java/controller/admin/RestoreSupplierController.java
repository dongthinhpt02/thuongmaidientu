package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ISupplierService;
import service.SupplierServiceImpl;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin/suppliers/restore"})
public class RestoreSupplierController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	ISupplierService supplierService = new SupplierServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		restoreSupplier(req,resp);
		resp.sendRedirect("/Nhom11/admin/suppliers");
	}

	protected void restoreSupplier(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		try {
			supplierService.restore(id);
//            resp.sendRedirect("/admin/accounts"); // Redirect to account list page after deletion
		} catch (Exception e) {
			e.printStackTrace();

			req.setAttribute("error", "Eror: " + e.getMessage());
		}
	}

}
