package com.company.inventory.controller;

import com.company.inventory.model.Product;
import com.company.inventory.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anair@wayfair.com
 */
@RestController
@RequiredArgsConstructor
public class StockController {

  public final ProductService productService;

  @GetMapping("/get-stock")
  public List<Product> getStockDetails(@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
    return productService.getAllProducts(limit);
  }

  @PostMapping("/add-product")
  public Product createRandomProduct(@RequestBody Product p) {
    return productService.addProduct(p);
  }

}
