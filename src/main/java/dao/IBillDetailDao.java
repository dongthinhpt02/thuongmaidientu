package dao;

import java.util.List;

import entity.BillDetail;

public interface IBillDetailDao {
	public void insert(BillDetail billdetail);
	public List<BillDetail> getBillDetailById(int billId);
}
