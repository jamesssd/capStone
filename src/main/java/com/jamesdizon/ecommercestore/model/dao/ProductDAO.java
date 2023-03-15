package com.jamesdizon.ecommercestore.model.dao;

import com.jamesdizon.ecommercestore.model.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductDAO extends ListCrudRepository<Product, Long> {

}
