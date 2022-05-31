package com.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.CategoryDto;
import com.spring.entity.Category;
import com.spring.entity.ResourceNotFoundException;
import com.spring.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/Category")
	public List<CategoryDto> getAllCategorys() {
		return categoryService.getAllCategorys().stream().map(category -> modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/Category/{id}")
	public ResponseEntity<CategoryDto> getPostById(@PathVariable(name = "id") Long id)
			throws ResourceNotFoundException {
		Category category = categoryService.getCategoryById(id);

		CategoryDto categoryResponse = modelMapper.map(category, CategoryDto.class);

		return ResponseEntity.ok().body(categoryResponse);
	}

	@PostMapping("/Category/add")
	public ResponseEntity<CategoryDto> postCategory(@Valid @RequestBody CategoryDto categoryDto) {
		Category categoryRequest = modelMapper.map(categoryDto, Category.class);
		Category category = categoryService.createCategory(categoryRequest);
		CategoryDto categoryResponse = modelMapper.map(category, CategoryDto.class);

		return new ResponseEntity<CategoryDto>(categoryResponse, HttpStatus.CREATED);
	}

	@PutMapping("/Category/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto)
			throws ResourceNotFoundException {
		// convert DTO to Entity
		Category categoryRequest = modelMapper.map(categoryDto, Category.class);

		Category category = categoryService.updateCategory(id, categoryRequest);

		// entity to DTO
		CategoryDto categoryResponse = modelMapper.map(category, CategoryDto.class);

		return ResponseEntity.ok().body(categoryResponse);
	}

	@DeleteMapping("/Category/{id}")
	public Map<String, Boolean> deleCategory(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		categoryService.deleteCategory(id);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}