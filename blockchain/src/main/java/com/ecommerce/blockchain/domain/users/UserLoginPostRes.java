package com.ecommerce.blockchain.domain.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginPostRes {

    String message;
    int statusCode;

    public UserLoginPostRes(int statusCode, String message){
        this.message = message;
        this.statusCode = statusCode;
    }
}
