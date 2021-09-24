package com.ecommerce.blockchain.service;

import com.ecommerce.blockchain.domain.users.Users;
import com.ecommerce.blockchain.domain.users.UsersRequestDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    public Optional<Users> getUser(int userId);
    Users registerUser(UsersRequestDto usersRequestDto);
    Users getUserByEmail(String email);
    int deleteUser(String email);

}
