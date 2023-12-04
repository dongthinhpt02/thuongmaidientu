package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import JPAConfig.JPAConfig;
import entity.Supplier;

public class SuppilerDaoImpl implements ISupplierDao {

	@Override
	public List<Supplier> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Supplier> query = enma.createNamedQuery("Supplier.findAll", Supplier.class);

		return query.getResultList();
	}

}
