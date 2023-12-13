package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
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
import entity.BillDetail;
import entity.Product;
import service.AccountServiceImpl;
import service.BillDetailServiceImpl;
import service.IAccountService;
import service.IBillDetailService;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin/bills/detail"})
public class BillDetailAdminController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	IBillDetailService billDetailService = new BillDetailServiceImpl();
	IAccountService accountService = new AccountServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    Cookie cookie = GetCookie.getCookieByName(req, "username");
		Account account = accountService.getByUsername((cookie != null) ? cookie.getValue() : null);
		getBillDetailById(req, resp);
		req.getRequestDispatcher("/views/admin/bill/billdetail.jsp").forward(req, resp);
	}
	
	protected void getBillDetailById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int billId = Integer.parseInt(req.getParameter("billId"));
		try {

			List<BillDetail> list = billDetailService.getBillDetailById(billId);

			int totalCost = 0;
			int index = 0;
			int amount = 0;
			for (BillDetail billDetail : list) {
					index = (int) billDetail.getUnitPriceBought();
					amount = billDetail.getQuantity();
					totalCost += index * amount;
			}

			req.setAttribute("totalCost", totalCost);

			
			req.setAttribute("list", list);

		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", "Eror: " + e.getMessage());

		}
	}
}
