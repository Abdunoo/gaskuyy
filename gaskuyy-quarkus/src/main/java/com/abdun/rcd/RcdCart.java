package com.abdun.rcd;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author trainee
 */
@Entity
@Table(name = "cart")
public class RcdCart implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	public int id;
	@Column(name = "qty")
	private int qty;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name="product_id")
	private int productId;

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
	private RcdProducts product;

	public RcdCart() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public RcdProducts getProduct() {
		return product;
	}

	public void setProduct(RcdProducts product) {
		this.product = product;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	

	

	
}