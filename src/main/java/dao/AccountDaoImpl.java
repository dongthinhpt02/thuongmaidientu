package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;

import JPAConfig.JPAConfig;
import entity.Account;
import entity.Category;
import entity.Role;
import entity.Supplier;

public class AccountDaoImpl implements IAccountDao {

	@Override
	public void insert(Account account) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.persist(account);

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
	public void update(Account account) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.merge(account);

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
	        Account account = entityManager.find(Account.class, id);
	        if (account != null) {
	            account.setStatus(0); // Set status to 0 for soft delete
	            entityManager.merge(account);
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
	public List<Account> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Account> query = enma.createNamedQuery("Account.findAll", Account.class);

		return query.getResultList();
	}

	@Override
	public Account getByEmailAndPassword(String email, String password) {
		EntityManager enma = JPAConfig.getEntityManager();

		try {
			TypedQuery<Account> query = enma.createQuery(
					"SELECT a FROM Account a WHERE a.email = :email AND a.password = :password", Account.class);
			query.setParameter("email", email);
			query.setParameter("password", password);

			List<Account> resultList = query.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				// Assuming email & password combination is unique, return the first found
				// account
				return resultList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Handle exceptions if needed
		} finally {
			enma.close();
		}
		return null; // Return null if no matching account is found
	}

	@Override
	public Account getByUsername(String username) {
		EntityManager enma = JPAConfig.getEntityManager();

		try {
			TypedQuery<Account> query = enma.createQuery("SELECT a FROM Account a WHERE a.username = :username",
					Account.class);
			query.setParameter("username", username);

			List<Account> resultList = query.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				// Assuming username is unique, return the first found account
				return resultList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Handle exceptions if needed
		} finally {
			enma.close();
		}
		return null; // Return null if no matching account is found
	}

	@Override
	public List<Account> findAllActive() {
		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Account> query = enma.createNamedQuery("Account.findAllActive", Account.class);

		return query.getResultList();
	}

	@Override
	public List<Account> findAllUnactive() {
		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Account> query = enma.createNamedQuery("Account.findAllUnactive", Account.class);

		return query.getResultList();
	}

	@Override
	public List<Account> findAllUsers() {
		EntityManager enma = JPAConfig.getEntityManager();
		
		TypedQuery<Account> query = enma.createNamedQuery("Account.findAllUsers", Account.class);
        query.setParameter("roleValue", Role.CLIENT); // Assuming USER is the role you consider as "user"
        return query.getResultList();
	}

	@Override
	public Account findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();

		Account account = enma.find(Account.class,id);

		return account;
	}

	@Override
	public void restore(int id) throws Exception {
		EntityManager entityManager = JPAConfig.getEntityManager();
	    EntityTransaction transaction = entityManager.getTransaction();

	    try {
	        transaction.begin();
	        Account account = entityManager.find(Account.class, id);
	        if (account != null) {
	            account.setStatus(1); // Set status to 0 for soft delete
	            entityManager.merge(account);
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

}
