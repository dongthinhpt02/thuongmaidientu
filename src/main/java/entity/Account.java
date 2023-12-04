package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Account")
@NamedQuery(name = "Account.findAll", query = "SELECT s FROM Account s")
public class Account implements Serializable {
	private static final long serialVersionUID = 5212117825651273110L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	@Column(columnDefinition = "nvarchar(20)")
	private String username;
	
	@Column(columnDefinition = "nvarchar(50)")
	private String fullName;
	
	@Column(columnDefinition = "nvarchar(15)")
	private String phoneNumber;
	
	@Column(columnDefinition = "nvarchar(50)")
	private String email;
	
	@Column(columnDefinition = "integer")
	private Role role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Account() {
		super();
	}

	public Account(int id, String username, String fullName, String phoneNumber, String email, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.role = role;
	}
	
	
}
