package service;

import dao.BillDaoImpl;
import dao.IBillDao;
import entity.Bill;

public class BillServiceImpl implements IBillService{
	
	IBillDao billService = new BillDaoImpl();
	@Override
	public void insert(Bill bill) {
		billService.insert(bill);
	}
	
}
