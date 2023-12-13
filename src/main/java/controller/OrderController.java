package controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import concern.GetCookie;
import entity.Account;
import entity.Bill;
import entity.BillDetail;
import entity.Product;
import service.AccountServiceImpl;
import service.BillDetailServiceImpl;
import service.BillServiceImpl;
import service.IAccountService;
import service.IBillDetailService;
import service.IBillService;
import service.IProductService;
import service.ProductServiceImpl;

@MultipartConfig
@WebServlet(urlPatterns = { "/order" })
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	IProductService productService = new ProductServiceImpl();
	IAccountService accountService = new AccountServiceImpl();
	IBillDetailService billDetailService = new BillDetailServiceImpl();
	IBillService billService = new BillServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie cookie = GetCookie.getCookieByName(req, "username");
		String accountIdString = cookie.getValue();
		Account account = accountService.getByUsername(accountIdString);
		int accountId = account.getId();

		String address = req.getParameter("address");
		String phonenumber = req.getParameter("phonenumber");

		Bill bill = new Bill(address, phonenumber, account);

		billService.insert(bill);

		String serializedArray = GetCookie.getCookieByName(req, "cart") != null
				? GetCookie.getCookieByName(req, "cart").getValue()
				: "";
		serializedArray = URLDecoder.decode(serializedArray, "UTF-8");
		String[] cartSplit = serializedArray.split(",");

		List<Map.Entry<Product, Integer>> cartArray = new ArrayList<>();
		int totalCost = 0;
		for (String cart : cartSplit) {
			if (cart.isEmpty())
				continue;
			int productId = Integer.parseInt(cart.split(" ")[0]);
			Product product = productService.findById(productId);
			totalCost += (int) product.getUnitPrice();
			int amount = Integer.parseInt(cart.split(" ")[1]);

			cartArray.add(new AbstractMap.SimpleEntry<>(product, amount));
		}

		for (Map.Entry<Product, Integer> entry : cartArray) {
			Product product = entry.getKey();
			int amount = entry.getValue();
			double unitPrice = product.getUnitPrice(); // Assuming unitPrice is of type double

			// Create BillDetail object
			BillDetail billDetail = new BillDetail();
			billDetail.setProduct(product);
			billDetail.setQuantity(amount);
			billDetail.setUnitPriceBought(unitPrice);
			billDetail.setBill(bill);

			// Add BillDetail object to the list
			billDetailService.insert(billDetail);

			int newQuantityLeft = product.getQuantityLeft() - amount;

			if (newQuantityLeft < 0) {
				String errorMessage = "Sorry, the quantity ordered for product '" + product.getName()
						+ "' exceeds the available stock.";

				// Set the error message in a session attribute to display on the home page
				req.getSession().setAttribute("errorMessage", errorMessage);
				
				Cookie[] cookies = req.getCookies();
				if (cookies != null) {
					for (Cookie cookie1 : cookies) {
						if (cookie1.getName().equals("cart")) {
							// Expire the cookie by setting its max age to 0
							cookie1.setMaxAge(0);
							resp.addCookie(cookie1);
						}
					}
				}

				// Redirect back to the home page
				resp.sendRedirect("/Nhom11/home");
				return; // Stop further processing as an error occurred
			}

			// Update the quantityLeft of the Product
			product.setQuantityLeft(newQuantityLeft);

			// Update the Product in the database
			productService.update(product);
		}
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie1 : cookies) {
				if (cookie1.getName().equals("cart")) {
					// Expire the cookie by setting its max age to 0
					cookie1.setMaxAge(0);
					resp.addCookie(cookie1);
				}
			}
		}
		resp.sendRedirect("/Nhom11/home");

	}
}
