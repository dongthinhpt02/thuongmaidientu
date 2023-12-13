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
import entity.Supplier;

public class SuppilerDaoImpl implements ISupplierDao {

	@Override
	public List<Supplier> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Supplier> query = enma.createNamedQuery("Supplier.findAll", Supplier.class);

		return query.getResultList();
	}

	@Override
	public void insert(Supplier supplier) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.persist(supplier);

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
	public void update(Supplier supplier) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.merge(supplier);

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
	        Supplier supplier = entityManager.find(Supplier.class, id);
	        if (supplier != null) {
	            supplier.setStatus(0); // Set status to 0 for soft delete
	            entityManager.merge(supplier);
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
	public List<Supplier> findAllActive() {
		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Supplier> query = enma.createNamedQuery("Supplier.findAllActive", Supplier.class);

		return query.getResultList();
	}

	@Override
	public List<Supplier> findAllUnactive() {
		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Supplier> query = enma.createNamedQuery("Supplier.findAllUnactive", Supplier.class);

		return query.getResultList();
	}

	@Override
	public Supplier findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();

		Supplier supplier = enma.find(Supplier.class,id);

		return supplier;
	}

	@Override
	public void restore(int id) throws Exception {
		EntityManager entityManager = JPAConfig.getEntityManager();
	    EntityTransaction transaction = entityManager.getTransaction();

	    try {
	        transaction.begin();
	        Supplier supplier = entityManager.find(Supplier.class, id);
	        if (supplier != null) {
	            supplier.setStatus(1); // Set status to 0 for soft delete
	            entityManager.merge(supplier);
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
	public Supplier findByName(String name) {
		EntityManager enma = JPAConfig.getEntityManager();

		Supplier supplier = enma.find(Supplier.class,name);

		return supplier;
	}

	@Override
	public int findSupplierIdByName(String name) {
		EntityManager entityManager = JPAConfig.getEntityManager();

	    String jpql = "SELECT s.id FROM Supplier s WHERE s.name = :name";

	    int supplierId = 0;

	    try {
	        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
	        query.setParameter("name", name);
	        supplierId = query.getSingleResult();
	    } catch (NoResultException | NonUniqueResultException e) {
	        // Handle exceptions or return a default value
	        supplierId = 0; // or throw an exception or handle the case as needed
	    } finally {
	        entityManager.close();
	    }

	    return supplierId;
	}

	@Override
	public double getRevenueBySupplier(Supplier supplier) {
		EntityManager entityManager = JPAConfig.getEntityManager();
		double totalRevenue = 0.0;
		try {
            String queryString = "SELECT SUM(bd.unitPriceBought * bd.quantity) FROM BillDetail bd " +
                                 "JOIN bd.product p " +
                                 "WHERE p.supplier = :supplier";

            Query query = entityManager.createQuery(queryString);
            query.setParameter("supplier", supplier);

            // Execute query and get the total revenue
            totalRevenue = (Double) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalRevenue;
	}

	@Override
	public List<Object[]> getRevenueByAllSuppliers() {
		EntityManager entityManager = JPAConfig.getEntityManager();
		List<Object[]> revenueBySuppliers = null;

        try {
            String queryString = "SELECT s.id, s.name, SUM(bd.unitPriceBought * bd.quantity) " +
                                 "FROM BillDetail bd " +
                                 "JOIN bd.product p " +
                                 "JOIN p.supplier s " +
                                 "GROUP BY s.id, s.name";

            Query query = entityManager.createQuery(queryString);
            revenueBySuppliers = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return revenueBySuppliers;
    }
}
