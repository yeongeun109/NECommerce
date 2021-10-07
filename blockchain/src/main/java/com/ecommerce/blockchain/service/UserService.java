package com.ecommerce.blockchain.service;

import com.ecommerce.blockchain.domain.global.exception.NoUserException;
import com.ecommerce.blockchain.domain.user.User;
import com.ecommerce.blockchain.domain.user.UserRequestDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    public Optional<User> getUser(Long userId);
    User registerUser(UserRequestDto userRequestDto);
    public User getUserByEmail(String email);
    int deleteUser(String email);

    String getUserName(Long userId) throws NoUserException;
}
