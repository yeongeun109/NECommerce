package com.ecommerce.blockchain.domain.user;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDto {

    private int id;
    private String name;
    private String email;
    private Timestamp created_at;
    private String password;

}
