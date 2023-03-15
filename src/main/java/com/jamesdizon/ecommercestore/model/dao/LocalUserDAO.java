package com.jamesdizon.ecommercestore.model.dao;

import com.jamesdizon.ecommercestore.model.LocalUser;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface LocalUserDAO extends ListCrudRepository<LocalUser, Long> {
    Optional<LocalUser> findByEmailIgnoreCase(String email);
    Optional<LocalUser> findByUsernameIgnoreCase(String username);


}
