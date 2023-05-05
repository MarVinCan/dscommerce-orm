package com.marcus.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcus.dscommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}