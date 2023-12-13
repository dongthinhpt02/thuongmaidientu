package service;

import java.util.List;

import entity.Supplier;

public interface ISupplierService {
	List<Supplier> findAll();
	public void insert(Supplier supplier);
	public void update(Supplier supplier);
	public void delete(int id) throws Exception;
	public void restore(int id) throws Exception;
	public List<Supplier> findAllActive();
	public List<Supplier> findAllUnactive();
	public Supplier findById(int id);
	public Supplier findByName(String name);
	public int findSupplierIdByName(String name);
	public double getRevenueBySupplier(Supplier supplier);
	public List<Object[]> getRevenueByAllSuppliers();
}
