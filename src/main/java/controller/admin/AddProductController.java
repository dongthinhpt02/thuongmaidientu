package controller.admin;

import java.io.IOException;
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
import entity.Category;
import entity.Product;
import entity.Supplier;
import service.AccountServiceImpl;
import service.CategoryServiceImpl;
import service.IAccountService;
import service.ICategoryService;
import service.IProductService;
import service.ISupplierService;
import service.ProductServiceImpl;
import service.SupplierServiceImpl;

@MultipartConfig
@WebServlet(urlPatterns = { "/admin/products/add" })
public class AddProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IProductService productService = new ProductServiceImpl();
	ISupplierService supplierService = new SupplierServiceImpl();
	ICategoryService categoryService = new CategoryServiceImpl();
	IAccountService accountService = new AccountServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    Cookie cookie = GetCookie.getCookieByName(req, "username");
		Account account = accountService.getByUsername((cookie != null) ? cookie.getValue() : null);
		req.setAttribute("account", account);
		findAllActive(req, resp);
		findAllActive1(req, resp);
		req.getRequestDispatcher("/views/admin/product/addproduct.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		insertProduct(req, resp);
		resp.sendRedirect("/Nhom11/admin/products");
	}

	protected void findAllActive(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		try {

			List<Supplier> list = supplierService.findAllActive();

			request.setAttribute("suppliers", list);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}

	protected void findAllActive1(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		try {

			List<Category> list1 = categoryService.findAllActive();

			// thông báo

			request.setAttribute("categories", list1);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}

	protected void insertProduct(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String name = req.getParameter("name");
		double unitPrice = Double.parseDouble(req.getParameter("unitPrice"));
		int quantityLeft = Integer.parseInt(req.getParameter("quantityLeft"));
		String description = req.getParameter("description");
		String image = req.getParameter("image");
		int status = 1;
		int supplierId = Integer.parseInt(req.getParameter("supplierId"));
		int categoryId = Integer.parseInt(req.getParameter("categoryId"));

		Product product = new Product();
        product.setName(name);
        product.setUnitPrice(unitPrice);
        product.setQuantityLeft(quantityLeft);
        product.setDescription(description);
        product.setImage(image);
        product.setStatus(status);
        
        Supplier supplier = supplierService.findById(supplierId);
        Category category = categoryService.findById(categoryId);
        
        product.setSupplier(supplier);
        product.setCategory(category);
        
        productService.insert(product);
	}

}
