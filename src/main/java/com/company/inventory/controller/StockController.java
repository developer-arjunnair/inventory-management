package com.company.inventory.controller;

import com.company.inventory.model.Product;
import com.company.inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anair@wayfair.com
 */
@RestController
@RequiredArgsConstructor
public class StockController {

  public final ProductRepository productRepository;

  @GetMapping("/get-stock")
  public String getStockDetails() {
    return "you have reached";
  }

  @PostMapping("/add-product")
  public Product createRandomProduct(@RequestBody Product p) {
    productRepository.save(p);
    return p;
  }

}
