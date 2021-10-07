package com.ecommerce.blockchain.domain.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginPostReq {

    String email;
    String password;

}
