package service;

import java.util.List;

import dao.BillDetailDaoImpl;
import dao.IBillDetailDao;
import entity.BillDetail;

public class BillDetailServiceImpl implements IBillDetailService{

	IBillDetailDao billDetailService = new BillDetailDaoImpl();
	@Override
	public void insert(BillDetail billDetail) {
		billDetailService.insert(billDetail);
	}
	@Override
	public List<BillDetail> getBillDetailById(int billId) {
		return billDetailService.getBillDetailById(billId);
	}

}
