package com.marcus.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.marcus.dscommerce.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);



}