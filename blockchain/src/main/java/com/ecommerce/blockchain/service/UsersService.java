package com.ecommerce.blockchain.service;

import com.ecommerce.blockchain.domain.users.Users;
import com.ecommerce.blockchain.domain.users.UsersRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface UsersService {

    Users registerUser(UsersRequestDto usersRequestDto);

}
