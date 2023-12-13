package dao;

import java.util.List;

import entity.Account;

public interface IAccountDao {
	public void insert(Account account);
	public void update(Account account);
	public void delete(int id) throws Exception;
	public void restore(int id) throws Exception;
	List<Account> findAll();
	public Account getByEmailAndPassword(String email, String password);
	public Account getByUsername(String username);
	public Account findById(int id);
	public List<Account> findAllActive();
	public List<Account> findAllUnactive();
	public List<Account> findAllUsers();
}
