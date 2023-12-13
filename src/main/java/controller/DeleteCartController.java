package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
@WebServlet(urlPatterns = {"/cart/delete"})
public class DeleteCartController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		resp.sendRedirect("cart");
	}
}
