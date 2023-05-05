package com.marcus.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcus.dscommerce.dto.OrderDTO;
import com.marcus.dscommerce.entities.Order;
import com.marcus.dscommerce.repositories.OrderRepositor;
import com.marcus.dscommerce.services.exepitions.ResourceNotFoundExeption;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepositor repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order order = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundExeption("Recurso não encontrado"));
        return new OrderDTO(order);
    }

}
