package service;

import java.util.List;

import dao.ISupplierDao;
import dao.SuppilerDaoImpl;
import entity.Supplier;

public class SupplierServiceImpl implements ISupplierService {
	ISupplierDao suppilerDao = new SuppilerDaoImpl();	
	@Override
	public List<Supplier> findAll() {
		return suppilerDao.findAll();	
		}
	@Override
	public void insert(Supplier supplier) {
		suppilerDao.insert(supplier);
		
	}
	@Override
	public void update(Supplier supplier) {
		suppilerDao.update(supplier);
	}
	@Override
	public void delete(int id) throws Exception {
		suppilerDao.delete(id);
	}
	@Override
	public List<Supplier> findAllActive() {
		return suppilerDao.findAllActive();
	}
	@Override
	public List<Supplier> findAllUnactive() {
		return suppilerDao.findAllUnactive();
	}
	@Override
	public Supplier findById(int id) {
		return suppilerDao.findById(id);
	}
	@Override
	public void restore(int id) throws Exception {
		suppilerDao.restore(id);
	}
	@Override
	public Supplier findByName(String name) {
		return suppilerDao.findByName(name);
	}
	@Override
	public int findSupplierIdByName(String name) {
		return suppilerDao.findSupplierIdByName(name);
	}
	@Override
	public double getRevenueBySupplier(Supplier supplier) {
		return suppilerDao.getRevenueBySupplier(supplier);
	}
	@Override
	public List<Object[]> getRevenueByAllSuppliers() {
		return suppilerDao.getRevenueByAllSuppliers();
	}
}
