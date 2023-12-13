package service;

import java.util.List;

import dao.BillDaoImpl;
import dao.IBillDao;
import entity.Bill;

public class BillServiceImpl implements IBillService{
	
	IBillDao billService = new BillDaoImpl();
	@Override
	public void insert(Bill bill) {
		billService.insert(bill);
	}
	@Override
	public List<Bill> getAllBillByAccountId(int accountId) {
		return billService.getAllBillByAccountId(accountId);
	}
	@Override
	public List<Bill> getAllBill() {
		return billService.getAllBill();
	}
	
}
