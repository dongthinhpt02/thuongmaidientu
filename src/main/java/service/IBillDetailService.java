package service;

import java.util.List;

import entity.BillDetail;

public interface IBillDetailService {
	public void insert(BillDetail billDetail);
	public List<BillDetail> getBillDetailById(int billId);
}
