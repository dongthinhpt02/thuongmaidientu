package dao;

import java.util.List;

import entity.Supplier;

public interface ISupplierDao {
	List<Supplier> findAll();
}
