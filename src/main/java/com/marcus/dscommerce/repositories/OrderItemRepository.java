package com.marcus.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcus.dscommerce.entities.OrderItem;
import com.marcus.dscommerce.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}