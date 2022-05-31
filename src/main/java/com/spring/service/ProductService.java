package com.spring.service;

import java.util.List;

import com.spring.entity.Product;
import com.spring.entity.ResourceNotFoundException;

public interface ProductService {

	List<Product> getAllProducts();

	Product createProduct(Product product);

	Product updateProduct(long id, Product product) throws ResourceNotFoundException;

	void deleteProduct(long id) throws ResourceNotFoundException;

	Product getProductById(long id) throws ResourceNotFoundException;

}
