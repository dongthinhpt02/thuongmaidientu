package dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import JPAConfig.JPAConfig;
import entity.Account;
import entity.Product;
import entity.Supplier;

public class ProductDaoImpl implements IProductDao{

	@Override
	public List<Product> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Product> query = enma.createNamedQuery("Product.findAll", Product.class);

		return query.getResultList();
	}

	@Override
	public void insert(Product product) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.persist(product);

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
	public void update(Product product) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.merge(product);

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
	        Product product = entityManager.find(Product.class, id);
	        if (product != null) {
	            product.setStatus(0); // Set status to 0 for soft delete
	            entityManager.merge(product);
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
	public List<Product> findAllActive() {
		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Product> query = enma.createNamedQuery("Product.findAllActive", Product.class);

		return query.getResultList();
	}

	@Override
	public List<Product> findAllUnactive() {
		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Product> query = enma.createNamedQuery("Product.findAllUnactive", Product.class);

		return query.getResultList();
	}

	@Override
	public Product findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();

		Product product = enma.find(Product.class,id);

		return product;
	}

	@Override
	public void restore(int id) throws Exception {
		EntityManager entityManager = JPAConfig.getEntityManager();
	    EntityTransaction transaction = entityManager.getTransaction();

	    try {
	        transaction.begin();
	        Product product = entityManager.find(Product.class, id);
	        if (product != null) {
	            product.setStatus(1); // Set status to 0 for soft delete
	            entityManager.merge(product);
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
	public List<Product> findProductBySupplierId(int supplierId) {
		EntityManager entityManager = JPAConfig.getEntityManager();

	    String jpql = "SELECT p FROM Product p WHERE p.supplier.id = :supplierId";

	    List<Product> products;

	    try {
	        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
	        query.setParameter("supplierId", supplierId);
	        products = query.getResultList();
	    } catch (NoResultException | NonUniqueResultException e) {
	        products = Collections.emptyList();
	    } finally {
	        entityManager.close();
	    }

	    return products;

	}

	@Override
	public List<Product> findProductByCategoryId(int categoryId) {
		EntityManager entityManager = JPAConfig.getEntityManager();

	    String jpql = "SELECT p FROM Product p WHERE p.category.id = :categoryId";

	    List<Product> products;

	    try {
	        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
	        query.setParameter("categoryId", categoryId);
	        products = query.getResultList();
	    } catch (NoResultException | NonUniqueResultException e) {
	        products = Collections.emptyList();
	    } finally {
	        entityManager.close();
	    }

	    return products;
	}
	
	@Override
	public List<Product> findProductByCategoryAndSupplierId(int categoryId, int supplierId) {
		EntityManager entityManager = JPAConfig.getEntityManager();
		
		String jpql = "SELECT p FROM Product p WHERE p.category.id = :categoryId AND p.supplier.id = :supplierId";

		if (categoryId == 0) jpql = "SELECT p FROM Product p WHERE p.supplier.id = :supplierId";
		if (supplierId == 0) jpql = "SELECT p FROM Product p WHERE p.category.id = :categoryId";
		if (categoryId == 0 && supplierId == 0) jpql = "SELECT p FROM Product p";
		

	    List<Product> products;

	    try {
	        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
	        if (categoryId != 0) query.setParameter("categoryId", categoryId);
	        if (supplierId != 0) query.setParameter("supplierId", supplierId);
	        products = query.getResultList();
	    } catch (NoResultException | NonUniqueResultException e) {
	        products = Collections.emptyList();
	    } finally {
	        entityManager.close();
	    }

	    return products;
	}

	@Override
	public Product getProductDetail(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getRevenueByAllProducts() {
		EntityManager entityManager = JPAConfig.getEntityManager();
		List<Object[]> revenueByProducts = null;

        try {
            String queryString = "SELECT p.id, p.name, SUM(bd.unitPriceBought * bd.quantity) " +
                                 "FROM BillDetail bd " +
                                 "JOIN bd.product p " +
                                 "GROUP BY p.id, p.name";

            Query query = entityManager.createQuery(queryString);
            revenueByProducts = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return revenueByProducts;
    }
}
