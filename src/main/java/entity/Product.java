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
@Table(name="Product")
@NamedQuery(name = "Product.findAll", query = "SELECT s FROM Product s")
@NamedQuery(name = "Product.findAllActive", query = "SELECT a FROM Product a WHERE a.status = 1")
@NamedQuery(name = "Product.findAllUnactive", query = "SELECT a FROM Product a WHERE a.status = 0")
public class Product implements Serializable {
	private static final long serialVersionUID = -750960800826620215L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	@Column(columnDefinition = "nvarchar(50)")
	private String name;
	
	@Column(columnDefinition = "money")
	private double unitPrice;
	
	@Column(columnDefinition = "integer")
	private int quantityLeft;
	
	@Column(columnDefinition = "text")
	private String description;
	
	@Column(columnDefinition = "nvarchar(100)")
	private String image;
	
	@Column(columnDefinition = "integer")
	private int status;
	
	@ManyToOne
	@JoinColumn(name="supplierId")
	private Supplier supplier;
	
	@ManyToOne
	@JoinColumn(name="categoryId")
	private Category category;
	
	public int getIntUnitPrice() {
		return (int) unitPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantityLeft() {
		return quantityLeft;
	}

	public void setQuantityLeft(int quantityLeft) {
		this.quantityLeft = quantityLeft;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Product() {
		super();
	}

	public Product(int id, String name, double unitPrice, int quantityLeft, String description, String image,
			int status, Supplier supplier, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.quantityLeft = quantityLeft;
		this.description = description;
		this.image = image;
		this.status = status;
		this.supplier = supplier;
		this.category = category;
	}
	
}
