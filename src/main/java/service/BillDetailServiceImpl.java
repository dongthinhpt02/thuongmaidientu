package service;

import dao.BillDetailDaoImpl;
import dao.IBillDetailDao;
import entity.BillDetail;

public class BillDetailServiceImpl implements IBillDetailService{

	IBillDetailDao billDetailService = new BillDetailDaoImpl();
	@Override
	public void insert(BillDetail billDetail) {
		billDetailService.insert(billDetail);
	}

}
