package dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import JPAConfig.JPAConfig;
import entity.BillDetail;

public class BillDetailDaoImpl implements IBillDetailDao {

	@Override
	public void insert(BillDetail billdetail) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.persist(billdetail);

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
	public List<BillDetail> getBillDetailById(int billId) {
		EntityManager enma = JPAConfig.getEntityManager();

		String jpql = "SELECT bd FROM BillDetail bd WHERE bd.bill.id = :billId";

		List<BillDetail> billDetails;

		try {
			TypedQuery<BillDetail> query = enma.createQuery(jpql, BillDetail.class);
			query.setParameter("billId", billId);
			billDetails = query.getResultList();
		} catch (NoResultException | NonUniqueResultException e) {
			billDetails = Collections.emptyList();
		} finally {
			enma.close();
		}

		return billDetails;
	}

}
