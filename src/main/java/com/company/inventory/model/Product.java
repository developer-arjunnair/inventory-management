package com.company.inventory.model;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author anair@wayfair.com
 */
@Entity
@Table(name = "products", schema = "inventory")
@Getter
@Setter
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String description;
  private Double quantity;
  private Double price;
  private Double discount;
  private String createdBy;
  private Date createdAt = Date.valueOf(LocalDate.now());
  private Date updatedAt = Date.valueOf(LocalDate.now());
}
