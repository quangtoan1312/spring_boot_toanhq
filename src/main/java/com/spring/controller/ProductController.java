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

import com.spring.dto.ProductDto;
import com.spring.entity.Product;
import com.spring.entity.ResourceNotFoundException;
import com.spring.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductService productService;

	@GetMapping("/product")
	public List<ProductDto> getAllProducts() {
		return productService.getAllProducts().stream().map(product -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<ProductDto> getPostById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
		Product product = productService.getProductById(id);

		ProductDto productResponse = modelMapper.map(product, ProductDto.class);

		return ResponseEntity.ok().body(productResponse);
	}

	@PostMapping("/product/add")
	public ResponseEntity<ProductDto> postProduct(@Valid @RequestBody ProductDto productDto) {
		Product productRequest = modelMapper.map(productDto, Product.class);
		Product product = productService.createProduct(productRequest);
		ProductDto productResponse = modelMapper.map(product, ProductDto.class);

		return new ResponseEntity<ProductDto>(productResponse, HttpStatus.CREATED);
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) throws ResourceNotFoundException {
		// convert DTO to Entity
		Product productRequest = modelMapper.map(productDto, Product.class);

		Product product = productService.updateProduct(id, productRequest);

		// entity to DTO
		ProductDto productResponse = modelMapper.map(product, ProductDto.class);

		return ResponseEntity.ok().body(productResponse);
	}

	@DeleteMapping("/product/{id}")
	public Map<String, Boolean> deleProduct(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		productService.deleteProduct(id);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}