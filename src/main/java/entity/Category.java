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
@Table(name="Category")
@NamedQuery(name = "Category.findAll", query = "SELECT s FROM Category s")
@NamedQuery(name = "Category.findAllActive", query = "SELECT a FROM Category a WHERE a.status = 1")
@NamedQuery(name = "Category.findAllUnactive", query = "SELECT a FROM Category a WHERE a.status = 0")
public class Category implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2075150598194623726L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	@Column(columnDefinition = "nvarchar(20)")
	private String name;

	@Column(columnDefinition = "nvarchar(100)")
	private String image;
	
	@Column(columnDefinition = "integer")
	private int status;

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

	public Category() {
		super();
	}

	public Category(int id, String name, String image, int status) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.status = status;
	}

	public Category(String name, String image, int status) {
		super();
		this.name = name;
		this.image = image;
		this.status = status;
	}
	
	
}
