package com.spring.service;

import java.util.List;

import com.spring.entity.Category;
import com.spring.entity.ResourceNotFoundException;

public interface CategoryService {

	List<Category> getAllCategorys();

	Category createCategory(Category category);

	Category updateCategory(long id, Category category) throws ResourceNotFoundException;

	void deleteCategory(long id) throws ResourceNotFoundException;

	Category getCategoryById(long id) throws ResourceNotFoundException;

}
