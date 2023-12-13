package dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import JPAConfig.JPAConfig;
import entity.Account;
import entity.Bill;
import entity.Category;

public class BillDaoImpl implements IBillDao{

	@Override
	public void insert(Bill bill) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.persist(bill);

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
	public List<Bill> getAllBillByAccountId(int accountId) {
		EntityManager enma = JPAConfig.getEntityManager();

        String jpql = "SELECT b FROM Bill b WHERE b.account.id = :accountId";

        List<Bill> bills;

        try {
            TypedQuery<Bill> query = enma.createQuery(jpql, Bill.class);
            query.setParameter("accountId", accountId);
            bills = query.getResultList();
        } catch (NoResultException | NonUniqueResultException e) {
            bills = Collections.emptyList();
        } finally {
        	enma.close();
        }

        return bills;
	}

	@Override
	public List<Bill> getAllBill() {
		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Bill> query = enma.createNamedQuery("Bill.findAll", Bill.class);

		return query.getResultList();
	}

}
