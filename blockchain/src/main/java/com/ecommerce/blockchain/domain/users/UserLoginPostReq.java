package com.ecommerce.blockchain.domain.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginPostReq {

    String email;
    String password;

}
