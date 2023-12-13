package service;

import java.util.List;

import dao.IProductDao;
import dao.ProductDaoImpl;
import entity.Product;

public class ProductServiceImpl implements IProductService {
	IProductDao productDao = new ProductDaoImpl();
	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}
	@Override
	public void insert(Product product) {
		productDao.insert(product);
	}
	@Override
	public void update(Product product) {
		productDao.update(product);
	}
	@Override
	public void delete(int id) throws Exception {
		productDao.delete(id);
	}
	@Override
	public List<Product> findAllActive() {
		return productDao.findAllActive();
	}
	@Override
	public List<Product> findAllUnactive() {
		return productDao.findAllUnactive();
	}
	@Override
	public Product findById(int id) {
		return productDao.findById(id);
	}
	@Override
	public void restore(int id) throws Exception {
		productDao.restore(id);
	}
	@Override
	public List<Product> findProductBySupplierId(int supplierId) {
		return productDao.findProductBySupplierId(supplierId);
	}
	@Override
	public List<Product> findProductByCategoryId(int categoryId) {
		return productDao.findProductByCategoryId(categoryId);
	}
	@Override
	public List<Product> findProductByCategoryAndSupplierId(int categoryId, int supplierId) {
		return productDao.findProductByCategoryAndSupplierId(categoryId, supplierId);
	}
	@Override
	public List<Object[]> getRevenueByAllProducts() {
		return productDao.getRevenueByAllProducts();
	}

}
