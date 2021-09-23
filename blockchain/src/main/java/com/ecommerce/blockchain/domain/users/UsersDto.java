package com.ecommerce.blockchain.domain.users;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UsersDto {

    private int id;
    private String name;
    private String email;
    private Timestamp created_at;
    private String password;

}
