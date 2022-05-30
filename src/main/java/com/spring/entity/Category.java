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
@Table(name="category")
@Data
public class Category implements Serializable {
	private static final long serialVersionUID = -297553281792804396L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "title")
	private String title;

	@Column(name = "updated_at")
	private LocalDate updatedAt;

	@Column(name = "created_at")
	private LocalDate createdAt;

	public Category(String name, String title, LocalDate updatedAt, LocalDate createdAt) {
		this.name = name;
		this.title = title;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return String.format("Category[ID = %d, name = '%s', title='%s', updateAt = '%t', createAt = '%t']", id, name,
				title, updatedAt, createdAt);
	}

	public Long getId() {
		return id;
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

	public void setId(Long id) {
		this.id = id;
	}
	

}