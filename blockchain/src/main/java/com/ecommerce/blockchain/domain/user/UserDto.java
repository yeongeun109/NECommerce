package com.ecommerce.blockchain.domain.user;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private Timestamp created_at;
    private String password;

    public UserDto(){

    }

    public UserDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.created_at = entity.getCreated_at();
        this.password = entity.getPassword();
    }

}
