package service;

import java.util.List;

import dao.AccountDaoImpl;
import dao.IAccountDao;
import entity.Account;

public class AccountServiceImpl implements IAccountService{
	IAccountDao accountDao = new AccountDaoImpl();

	@Override
	public void insert(Account account) {
		accountDao.insert(account);
	}

	@Override
	public void update(Account account) {
		accountDao.update(account);
		
	}

	@Override
	public void delete(int id) throws Exception {
		accountDao.delete(id);
	}

	@Override
	public List<Account> findAll() {
		return accountDao.findAll();
	}

	@Override
	public Account getByEmailAndPassword(String email, String password) {
		return accountDao.getByEmailAndPassword(email, password);
	}

	@Override
	public Account getByUsername(String username) {
		return accountDao.getByUsername(username);
	}

	@Override
	public List<Account> findAllActive() {
		return accountDao.findAllActive();
	}

	@Override
	public List<Account> findAllUnactive() {
		return accountDao.findAllUnactive();
	}

	@Override
	public List<Account> findAllUsers() {
		return accountDao.findAllUsers();
	}

	@Override
	public Account findById(int id) {
		return accountDao.findById(id);
	}

	@Override
	public void restore(int id) throws Exception {
		accountDao.restore(id);
	}
	
}
