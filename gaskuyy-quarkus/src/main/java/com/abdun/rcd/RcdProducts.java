package com.abdun.rcd;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author trainee
 */
@Entity
@Table(name = "products")
public class RcdProducts implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	public Integer id;
	@Column(name = "title")
	@NotBlank
	private String title;
	@Column(name = "image_url")
	private String imageUrl;
	@Column(name = "qty")
	private Integer qty;
	@Column(name = "discount")
	@Max(20000)
	private Integer discount;
	@Column(name = "price")
	private Integer price;
	@Column(name = "category")
	private String category;
	@Column(name = "user_id")
	private Integer userId;
	@OneToMany(mappedBy = "product")
	private Collection<RcdCart> cartCollection;

	public RcdProducts() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Collection<RcdCart> getCartCollection() {
		return cartCollection;
	}

	public void setCartCollection(Collection<RcdCart> cartCollection) {
		this.cartCollection = cartCollection;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}