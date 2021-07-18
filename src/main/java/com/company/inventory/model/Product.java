package com.company.inventory.model;

import java.sql.Date;
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
  private Date createdAt;
  private Date updatedAt;


}
