package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import JPAConfig.JPAConfig;
import entity.Account;
import entity.Category;
import entity.Supplier;

public class CategoryDaoImpl implements ICategoryDao{

	@Override
	public List<Category> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);

		return query.getResultList();
	}

	@Override
	public void insert(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.persist(category);

			trans.commit();

		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}
	}

	@Override
	public void update(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.merge(category);

			trans.commit();

		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}
	}

	@Override
	public void delete(int id) throws Exception {
		EntityManager entityManager = JPAConfig.getEntityManager();
	    EntityTransaction transaction = entityManager.getTransaction();

	    try {
	        transaction.begin();
	        Category category = entityManager.find(Category.class, id);
	        if (category != null) {
	            category.setStatus(0); // Set status to 0 for soft delete
	            entityManager.merge(category);
	        }

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        throw e;
	    } finally {
	        entityManager.close();
	    }
	}

	@Override
	public List<Category> findAllActive() {
		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Category> query = enma.createNamedQuery("Category.findAllActive", Category.class);

		return query.getResultList();
	}

	@Override
	public List<Category> findAllUnactive() {
		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Category> query = enma.createNamedQuery("Category.findAllUnactive", Category.class);

		return query.getResultList();
	}

	@Override
	public Category findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();

		Category category = enma.find(Category.class,id);

		return category;
	}

	@Override
	public void restore(int id) throws Exception {
		EntityManager entityManager = JPAConfig.getEntityManager();
	    EntityTransaction transaction = entityManager.getTransaction();

	    try {
	        transaction.begin();
	        Category category = entityManager.find(Category.class, id);
	        if (category != null) {
	            category.setStatus(1); // Set status to 0 for soft delete
	            entityManager.merge(category);
	        }

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        throw e;
	    } finally {
	        entityManager.close();
	    }
	}

	@Override
	public Category findByName(String name) {
		EntityManager enma = JPAConfig.getEntityManager();

		Category category = enma.find(Category.class,name);

		return category;
	}

	@Override
	public int findCategoryIdByName(String name) {
		EntityManager entityManager = JPAConfig.getEntityManager();

	    String jpql = "SELECT c.id FROM Category c WHERE c.name = :name";

	    int categoryId = 0;

	    try {
	        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
	        query.setParameter("name", name);
	        categoryId = query.getSingleResult();
	    } catch (NoResultException | NonUniqueResultException e) {
	        // Handle exceptions or return a default value
	        categoryId = 0; // or throw an exception or handle the case as needed
	    } finally {
	        entityManager.close();
	    }
	    return categoryId;
	}

	@Override
	public List<Object[]> getRevenueByAllCategories() {
		EntityManager entityManager = JPAConfig.getEntityManager();
		List<Object[]> revenueByCategories = null;

        try {
            String queryString = "SELECT c.id, c.name, SUM(bd.unitPriceBought * bd.quantity) " +
                                 "FROM BillDetail bd " +
                                 "JOIN bd.product p " +
                                 "JOIN p.category c " +
                                 "GROUP BY c.id, c.name";

            Query query = entityManager.createQuery(queryString);
            revenueByCategories = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return revenueByCategories;
    }
}

