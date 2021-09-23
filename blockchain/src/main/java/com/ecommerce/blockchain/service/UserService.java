package com.ecommerce.blockchain.service;

import com.ecommerce.blockchain.domain.users.Users;
import com.ecommerce.blockchain.domain.users.UsersRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Users registerUser(UsersRequestDto usersRequestDto);
    Users getUserByEmail(String email);
    int deleteUser(String email);

}
