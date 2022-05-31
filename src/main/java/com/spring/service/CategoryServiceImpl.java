package com.spring.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.entity.Category;
import com.spring.entity.ResourceNotFoundException;
import com.spring.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Category> getAllCategorys() {
		return categoryRepository.findAll();
	}

	@Override
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(long id, Category categoryRequest) throws ResourceNotFoundException {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not exist"));

		category.setName(categoryRequest.getName());
		category.setTitle(categoryRequest.getTitle());
		category.setUpdatedAt(LocalDate.now());
		return categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(long id) throws ResourceNotFoundException {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("category Not exist"));

		categoryRepository.delete(category);
	}

	@Override
	public Category getCategoryById(long id) throws ResourceNotFoundException {
		Optional<Category> result = categoryRepository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new ResourceNotFoundException("category not exist");
		}

	}

}
