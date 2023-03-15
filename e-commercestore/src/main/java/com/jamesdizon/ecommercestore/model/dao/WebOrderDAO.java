package com.jamesdizon.ecommercestore.model.dao;

import com.jamesdizon.ecommercestore.model.LocalUser;
import com.jamesdizon.ecommercestore.model.WebOrder;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface WebOrderDAO extends ListCrudRepository<WebOrder, Long> {

    List<WebOrder> findByUser(LocalUser user);
}
