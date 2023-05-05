package com.marcus.dscommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcus.dscommerce.dto.OrderDTO;
import com.marcus.dscommerce.dto.OrderItemDTO;
import com.marcus.dscommerce.entities.Order;
import com.marcus.dscommerce.entities.OrderItem;
import com.marcus.dscommerce.entities.OrderStatus;
import com.marcus.dscommerce.entities.Product;
import com.marcus.dscommerce.repositories.OrderItemRepository;
import com.marcus.dscommerce.repositories.OrderRepository;
import com.marcus.dscommerce.repositories.ProductRepository;
import com.marcus.dscommerce.services.exepitions.ResourceNotFoundExeption;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order order = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundExeption("Recurso não encontrado"));
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(userService.authenticated());

        for(OrderItemDTO itemDto : dto.getItems()){
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
            order.getItems().add(item); 
        }

        repository.save(order);
        orderItemRepository.saveAll(order.getItems());
        return new OrderDTO(order);
    }

}
