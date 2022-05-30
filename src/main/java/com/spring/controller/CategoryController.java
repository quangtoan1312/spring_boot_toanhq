package com.spring.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.Category;
import com.spring.repository.CategoryRepository;

@RestController
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/category/all")
	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}

	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long id) {
		Category category = categoryRepository.findById(id).orElse(null);
		return ResponseEntity.ok().body(category);
	}

	@PostMapping("/category/add")
	public Category postCategory(@Valid @RequestBody Category category) {
		return categoryRepository.save(category);
	}

	@DeleteMapping("/category/{id}")
	public Map<String, Boolean> deleteCategory(@PathVariable(value = "id") Long id) {
		Category category = categoryRepository.findById(id).orElse(null);

		categoryRepository.delete(category);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@PutMapping("/category/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Category categoryDetails) {
		Category category = categoryRepository.findById(id).orElse(null);

		category.setName(categoryDetails.getName());
		category.setTitle(categoryDetails.getTitle());
		category.setUpdatedAt(LocalDate.now());
		final Category updatedCategory = categoryRepository.save(category);
		return ResponseEntity.ok(updatedCategory);
	}

}