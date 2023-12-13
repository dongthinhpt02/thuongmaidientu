package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.BillDetail;
import entity.Product;
import service.BillDetailServiceImpl;
import service.IBillDetailService;

@MultipartConfig()
@WebServlet(urlPatterns = { "/bills/detail" })
public class BillDetailController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IBillDetailService billDetailService = new BillDetailServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getBillDetailById(req, resp);
		req.getRequestDispatcher("/views/bill/billdetail.jsp").forward(req, resp);
	}

	protected void getBillDetailById(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int billId = Integer.parseInt(req.getParameter("billId"));
		try {

			List<BillDetail> list = billDetailService.getBillDetailById(billId);

			// thông báo
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
