package com.company.inventory.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.company.inventory.InventoryManagerApplication;
import com.company.inventory.model.Product;
import com.company.inventory.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {InventoryManagerApplication.class})
@TestPropertySource("file:src/test/resources/application-integrationtest.properties")
@AutoConfigureMockMvc
public class StockControllerIntegrationTest {

  @Autowired
  public MockMvc mockMvc;
  @Autowired
  public ProductRepository repository;

  private static boolean isSetupDone = false;

  @Before
  public void setUp() throws JsonProcessingException {
    if(isSetupDone) {
      return;
    }
    isSetupDone = true;
    List<Product> products = new ArrayList<>();
    String payload1 = "    {\n" +
        "        \"name\": \"PDT1\",\n" +
        "        \"description\": \"Test product description1\",\n" +
        "        \"quantity\": 10.0,\n" +
        "        \"price\": 20.21,\n" +
        "        \"discount\": 15.27,\n" +
        "        \"createdBy\": \"Arjun Nair\",\n" +
        "        \"createdAt\": \"2021-07-17\",\n" +
        "        \"updatedAt\": \"2021-07-17\"\n" +
        "    }";
    Product p1 = new ObjectMapper().readValue(payload1, Product.class);
    String payload2 = "{\n" +
        "        \"name\": \"PDT2\",\n" +
        "        \"description\": \"Test product description1\",\n" +
        "        \"quantity\": 1000.0,\n" +
        "        \"price\": 10.3,\n" +
        "        \"discount\": 10.0,\n" +
        "        \"createdBy\": \"Arjun Nair\",\n" +
        "        \"createdAt\": \"2021-07-17\",\n" +
        "        \"updatedAt\": \"2021-07-17\"\n" +
        "    }";
    Product p2 = new ObjectMapper().readValue(payload2, Product.class);
    String payload3 = "{\n" +
        "        \"name\": \"PDT3\",\n" +
        "        \"description\": \"beautiful fork For kids\",\n" +
        "        \"quantity\": 1000.0,\n" +
        "        \"price\": 10.3,\n" +
        "        \"discount\": 10.0,\n" +
        "        \"createdBy\": null,\n" +
        "        \"createdAt\": \"2021-07-17\",\n" +
        "        \"updatedAt\": \"2021-07-17\"\n" +
        "    }";
    Product p3 = new ObjectMapper().readValue(payload3, Product.class);
    products.add(p1);
    products.add(p2);
    products.add(p3);
    repository.saveAll(products);
  }

  @Test
  public void getAllProducts() throws Exception {
    mockMvc.perform(get("/get-stock").contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$", Matchers.hasSize(3)))
           .andExpect(jsonPath("$[0].id", Matchers.is(1)))
           .andExpect(jsonPath("$[0].name", Matchers.is("PDT1")))
           .andExpect(jsonPath("$[1].id", Matchers.is(2)))
           .andExpect(jsonPath("$[1].name", Matchers.is("PDT2")));

  }

  @Test
  public void addProduct() throws Exception {
    String payload = "{\n" +
        "    \"name\": \"PDT4\",\n" +
        "    \"description\": \"PDT3 description\",\n" +
        "    \"price\": 60.00,\n" +
        "    \"discount\": 2,\n" +
        "    \"quantity\": 1000,\n" +
        "    \"createdBy\": \"Test Insert\"\n" +
        "}";
    mockMvc.perform(post("/add-product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.id", Matchers.not(Matchers.nullValue())))
           .andExpect(jsonPath("$.createdAt", Matchers.not(Matchers.nullValue())))
           .andExpect(jsonPath("$.updatedAt", Matchers.not(Matchers.nullValue())))
           .andExpect(jsonPath("$.id", Matchers.greaterThan(3)))
           .andExpect(jsonPath("$.name", Matchers.is("PDT4")));

    mockMvc.perform(get("/get-stock").contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$", Matchers.hasSize(4)));

  }
}
