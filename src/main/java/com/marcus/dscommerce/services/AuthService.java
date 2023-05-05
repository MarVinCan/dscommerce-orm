package com.marcus.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcus.dscommerce.entities.User;
import com.marcus.dscommerce.services.exepitions.ForbiddenException;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public void validateSelfOfAdmin(long userId){
        User me = userService.authenticated();
        if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)){
            throw new ForbiddenException("Access denied");

        }
    }
    
}
