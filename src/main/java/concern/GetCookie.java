package concern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCookie {

	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

	public static String getAccountIdFromCookie(HttpServletRequest request, String cookieName) {
		Cookie cookie = getCookieByName(request, cookieName);
		if (cookie != null) {
			String accountId = cookie.getValue();
			return accountId;
		}
		return null; // Return null if the cookie isn't found
	}

	public void deleteCookieByName(HttpServletRequest request, HttpServletResponse response, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					cookie.setMaxAge(0); // Set the cookie's max age to 0 to delete it
					cookie.setPath("/"); // Set the cookie's path to match the original cookie's path
					response.addCookie(cookie); // Add the modified cookie to the response to delete it
					break; // Stop the loop after finding and deleting the specified cookie
				}
			}
		}
	}
}
