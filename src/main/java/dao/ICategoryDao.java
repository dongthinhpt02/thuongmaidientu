package dao;

import java.util.List;

import entity.Category;

public interface ICategoryDao {
	List<Category> findAll();
	public void insert(Category category);
	public void update(Category category);
	public void delete(int id) throws Exception;
	public void restore(int id) throws Exception;
	public List<Category> findAllActive();
	public List<Category> findAllUnactive();
	public Category findById(int id);
	public Category findByName(String name);
	public int findCategoryIdByName(String name);
	public List<Object[]> getRevenueByAllCategories();
}
