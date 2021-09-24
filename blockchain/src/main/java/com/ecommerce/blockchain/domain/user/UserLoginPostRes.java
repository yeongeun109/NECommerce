package com.ecommerce.blockchain.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginPostRes {

    String message;
    int statusCode;
    String jwtToken;

    public UserLoginPostRes(int statusCode, String message, String jwtToken){
        this.message = message;
        this.statusCode = statusCode;
        this.jwtToken = jwtToken;
    }
}
