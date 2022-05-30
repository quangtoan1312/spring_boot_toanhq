package com.spring.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product implements Serializable {
	private static final long serialVersionUID = -297553281792804396L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "name")
	private String name;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "img_url")
	private String imgUrl;

	@Column(name = "price")
	private int price;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "status")
	private String status;

	@Column(name = "unit")
	private String unit;

	@Column(name = "updated_at")
	private LocalDate updatedAt;

	@Column(name = "created_at")
	private LocalDate createdAt;

	protected Product() {
	}

	public Product(Long categoryId, String name, String title, String description, String imgUrl, int price,
			int quantity, String status, String unit, LocalDate updatedAt, LocalDate createdAt) {
		this.categoryId = categoryId;
		this.name = name;
		this.title = title;
		this.description = description;
		this.imgUrl = imgUrl;
		this.price = price;
		this.quantity = quantity;
		this.status = status;
		this.unit = unit;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return String.format(
				"Product[ID = %d, categoryID = %d, name = '%s', title='%s', description = %s, img_url = %s, price = %d, quantity = % d, status = %s, unit = %s, updateAt = '%t', createAt = '%t']",
				id, categoryId, name, title, description, imgUrl, price, quantity, status, unit, updatedAt, createdAt);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

}