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
}
