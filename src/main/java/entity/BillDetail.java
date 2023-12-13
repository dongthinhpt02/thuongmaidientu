package entity;

import java.io.Serializable;

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
@Table(name="BillDetail")
@NamedQuery(name = "BillDetail.findAll", query = "SELECT s FROM BillDetail s")
public class BillDetail implements Serializable{

	private static final long serialVersionUID = -2437394629226587151L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	@ManyToOne
	@JoinColumn(name="billId")
	private Bill bill;
	
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product;
	
	@Column(columnDefinition = "integer")
	private int quantity;
	
	@Column(columnDefinition = "money")
	private double unitPriceBought;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPriceBought() {
		return unitPriceBought;
	}

	public void setUnitPriceBought(double unitPriceBought) {
		this.unitPriceBought = unitPriceBought;
	}
	

	public BillDetail() {
		super();
	}

	public BillDetail(int id, Bill bill, Product product, int quantity, double unitPriceBought) {
		super();
		this.id = id;
		this.bill = bill;
		this.product = product;
		this.quantity = quantity;
		this.unitPriceBought = unitPriceBought;
	}

	public BillDetail(Bill bill, Product product, int quantity, double unitPriceBought) {
		super();
		this.bill = bill;
		this.product = product;
		this.quantity = quantity;
		this.unitPriceBought = unitPriceBought;
	}
	
	
	
}
