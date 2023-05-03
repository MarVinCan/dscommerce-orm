package com.marcus.dscommerce.entities;

import javax.persistence.*;

@Entity
@Table(name = "tb_role")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    public Role(){

    }

    public Role(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getauthority() {
        return authority;
    }

    public void setauthority(String authority) {
        this.authority = authority;
    }

    
}
