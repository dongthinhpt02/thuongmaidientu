package controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Supplier;
import service.ISupplierService;
import service.SupplierServiceImpl;

@MultipartConfig
@WebServlet(urlPatterns = { "/supplier" })
public class SuppilerController extends HttpServlet {
	private static final long serialVersionUID = 1994974955450731075L;

	ISupplierService supplierService = new SupplierServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		Supplier supplier = null;
		/*
		 * if (url.contains("index")) {
		 * request.getRequestDispatcher("index.jsp").forward(request, response); }
		 */
		findAll(request, response);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void findAll(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		try {

			List<Supplier> list = supplierService.findAll();

			// thông báo

			request.setAttribute("Suppliers", list);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}
}
