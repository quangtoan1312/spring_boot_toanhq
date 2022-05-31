package com.spring.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.entity.Product;
import com.spring.entity.ResourceNotFoundException;
import com.spring.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(long id, Product productRequest) throws ResourceNotFoundException {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not exist"));

		product.setCategoryId(productRequest.getCategoryId());
		product.setName(productRequest.getName());
		product.setTitle(productRequest.getTitle());
		product.setDescription(productRequest.getDescription());
		product.setImgUrl(productRequest.getImgUrl());
		product.setPrice(productRequest.getPrice());
		product.setQuantity(productRequest.getQuantity());
		product.setStatus(productRequest.getStatus());
		product.setUnit(productRequest.getUnit());
		product.setUpdatedAt(LocalDate.now());
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(long id) throws ResourceNotFoundException {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not exist"));

		productRepository.delete(product);
	}

	@Override
	public Product getProductById(long id) throws ResourceNotFoundException {
		Optional<Product> result = productRepository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new ResourceNotFoundException("Product not exist");
		}

	}
}
