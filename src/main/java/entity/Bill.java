package entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Bill")
@NamedQuery(name = "Bill.findAll", query = "SELECT s FROM Bill s")

public class Bill implements Serializable{

	private static final long serialVersionUID = -1737859893948370664L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	@Column(columnDefinition = "nvarchar(100)")
	private String shippingAddress;
	
	@Column(columnDefinition = "nvarchar(15)")
	private String phoneNumber;
	
	@ManyToOne
	@JoinColumn(name="accountId")
	private Account account;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Bill() {
		super();
	}

	public Bill(int id, String shippingAddress, String phoneNumber, Account account) {
		super();
		this.id = id;
		this.shippingAddress = shippingAddress;
		this.phoneNumber = phoneNumber;
		this.account = account;
	}

	public Bill(String shippingAddress, String phoneNumber, Account account) {
		super();
		this.shippingAddress = shippingAddress;
		this.phoneNumber = phoneNumber;
		this.account = account;
	}
	
	
	
}


