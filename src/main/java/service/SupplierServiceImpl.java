package service;

import java.util.List;

import dao.ISupplierDao;
import dao.SuppilerDaoImpl;
import entity.Supplier;

public class SupplierServiceImpl implements ISupplierService {
	ISupplierDao suppilerDao = new SuppilerDaoImpl();	
	@Override
	public List<Supplier> findAll() {
		return suppilerDao.findAll();	}
}
