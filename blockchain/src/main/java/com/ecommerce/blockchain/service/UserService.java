package com.ecommerce.blockchain.service;

import com.ecommerce.blockchain.domain.user.User;
import com.ecommerce.blockchain.domain.user.UserRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User registerUser(UserRequestDto userRequestDto);
    User getUserByEmail(String email);
    int deleteUser(String email);

}
