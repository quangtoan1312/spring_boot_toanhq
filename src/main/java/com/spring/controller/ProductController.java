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

import com.spring.entity.Product;
import com.spring.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/product/all")
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long id) {
		Product product = productRepository.findById(id).orElse(null);
		return ResponseEntity.ok().body(product);
	}

	@PostMapping("/product/add")
	public Product postProduct(@Valid @RequestBody Product product) {
		return productRepository.save(product);
	}

	@DeleteMapping("/product/{id}")
	public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long id) {
		Product product = productRepository.findById(id).orElse(null);

		productRepository.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Product productDetails) {
		Product product = productRepository.findById(id).orElse(null);

		product.setCategoryId(productDetails.getCategoryId());
		product.setName(productDetails.getName());
		product.setTitle(productDetails.getTitle());
		product.setDescription(productDetails.getDescription());
		product.setImgUrl(productDetails.getImgUrl());
		product.setPrice(productDetails.getPrice());
		product.setQuantity(productDetails.getQuantity());
		product.setStatus(productDetails.getStatus());
		product.setUnit(productDetails.getUnit());
		product.setUpdatedAt(LocalDate.now());
		final Product updatedProduct = productRepository.save(product);
		return ResponseEntity.ok(updatedProduct);
	}

}