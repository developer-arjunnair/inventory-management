package com.company.inventory.service;

import com.company.inventory.model.Product;
import java.util.List;

public interface ProductService {
  List<Product> getAllProducts(int limit);

  Product addProduct(Product product);
}
