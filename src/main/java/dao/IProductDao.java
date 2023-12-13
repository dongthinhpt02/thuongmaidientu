package dao;

import java.util.List;

import entity.Product;

public interface IProductDao {
	List<Product> findAll();
	public void insert(Product product);
	public void update(Product product);
	public void delete(int id) throws Exception;
	public void restore(int id) throws Exception;
	public List<Product> findAllActive();
	public List<Product> findAllUnactive();
	public Product findById(int id);
	public List<Product> findProductBySupplierId(int supplierId);
	public List<Product> findProductByCategoryId(int categoryId);
	public Product getProductDetail(int id);
	public List<Product> findProductByCategoryAndSupplierId(int categoryId, int supplierId);
	public List<Object[]> getRevenueByAllProducts();
}
