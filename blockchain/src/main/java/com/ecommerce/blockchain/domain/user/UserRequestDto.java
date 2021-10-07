package com.ecommerce.blockchain.domain.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    String name;
    String email;
//    Timestamp created_at;
    String password;
}
