package controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import entity.Product;
import service.IProductService;
import service.ProductServiceImpl;

@MultipartConfig
@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	IProductService productService = new ProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
		String serializedArray = GetCookie.getCookieByName(req, "cart") != null ? GetCookie.getCookieByName(req, "cart").getValue() : "";
		serializedArray = URLDecoder.decode(serializedArray, "UTF-8");
		String[] cartSplit = serializedArray.split(",");
		
		List<Map.Entry<Product, Integer>> cartArray = new ArrayList<>();
		int totalCost = 0;
		for (String cart : cartSplit) {
			if (cart.isEmpty()) continue;
			int productId = Integer.parseInt(cart.split(" ")[0]);
			Product product = productService.findById(productId);
			totalCost += (int)product.getUnitPrice();
			int amount = Integer.parseInt(cart.split(" ")[1]);

			cartArray.add(new AbstractMap.SimpleEntry<>(product, amount));
		}
		
		req.setAttribute("cartArray", cartArray);
		req.setAttribute("totalCost", totalCost);
		req.getRequestDispatcher("/views/cart/listcart.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String serializedArray = GetCookie.getCookieByName(req, "cart") != null ? GetCookie.getCookieByName(req, "cart").getValue() : "";
		serializedArray = URLDecoder.decode(serializedArray, "UTF-8");
		String[] cartSplit = serializedArray.split(",");
		
		List<Map.Entry<Integer, Integer>> cartArray = new ArrayList<>();
		boolean isAdd = false;
		for (String cart : cartSplit) {
			if (cart.isEmpty()) continue;
			int productId = Integer.parseInt(cart.split(" ")[0]);
			int amount = Integer.parseInt(cart.split(" ")[1]);
			
			if (productId == Integer.parseInt(req.getParameter("productId"))) {
				amount++;
				isAdd = true;
			}
			cartArray.add(new AbstractMap.SimpleEntry<>(productId, amount));
		}
		if (!isAdd) cartArray.add(new AbstractMap.SimpleEntry<>(Integer.parseInt(req.getParameter("productId")), 1));
		
		String cartArrayString = "";
		for (Map.Entry<Integer, Integer> cart : cartArray) {
		    String cartString = cart.getKey().toString() + " " + cart.getValue().toString() + ",";
		    cartArrayString += cartString;
		}
		cartArrayString = URLEncoder.encode(cartArrayString, "UTF-8");
		Cookie cookie = new Cookie("cart", cartArrayString);
		cookie.setPath("/Nhom11");
		resp.addCookie(cookie);
		resp.sendRedirect(req.getHeader("referer"));
	}
}
