package com.company.inventory.service;

import com.company.inventory.model.Product;
import com.company.inventory.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author anair@wayfair.com
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public List<Product> getAllProducts(int limit) {
    return productRepository.findAll(PageRequest.of(0, limit)).getContent();
  }

  @Override
  public Product addProduct(Product product) {
    return productRepository.save(product);
  }

}
