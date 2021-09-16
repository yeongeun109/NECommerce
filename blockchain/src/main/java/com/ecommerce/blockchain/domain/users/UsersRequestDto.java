package com.ecommerce.blockchain.domain.users;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UsersRequestDto {
    String name;
    String email;
//    Timestamp created_at;
    String password;
}
