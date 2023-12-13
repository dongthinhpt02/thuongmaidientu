package service;

import java.util.List;

import dao.CategoryDaoImpl;
import dao.ICategoryDao;
import entity.Category;

public class CategoryServiceImpl implements ICategoryService{
	ICategoryDao cateDao = new CategoryDaoImpl();
	@Override
	public List<Category> findAll() {
		return cateDao.findAll();
	}
	@Override
	public void insert(Category category) {
		cateDao.insert(category);
	}
	@Override
	public void update(Category category) {
		cateDao.update(category);
	}
	@Override
	public void delete(int id) throws Exception {
		cateDao.delete(id);
	}
	@Override
	public List<Category> findAllActive() {
		return cateDao.findAllActive();
	}
	@Override
	public List<Category> findAllUnactive() {
		return cateDao.findAllUnactive();
	}
	@Override
	public Category findById(int id) {
		return cateDao.findById(id);
		}
	@Override
	public void restore(int id) throws Exception {
		cateDao.restore(id);
	}
	@Override
	public Category findByName(String name) {
		return cateDao.findByName(name);
	}
	@Override
	public int findCategoryIdByName(String name) {
		return cateDao.findCategoryIdByName(name);
	}
	@Override
	public List<Object[]> getRevenueByAllCategories() {
		return cateDao.getRevenueByAllCategories();
	}
	
}
