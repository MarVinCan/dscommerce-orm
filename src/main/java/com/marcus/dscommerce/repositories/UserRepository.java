package com.marcus.dscommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marcus.dscommerce.entities.Product;
import com.marcus.dscommerce.entities.User;

public interface UserRepository extends JpaRepository<Product, Long> {

    User findByEmail(String email);



}