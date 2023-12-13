package service;

import java.util.List;

import entity.Bill;

public interface IBillService {
	public void insert(Bill bill);
	public List<Bill> getAllBillByAccountId(int accountId);
	public List<Bill> getAllBill();
}
