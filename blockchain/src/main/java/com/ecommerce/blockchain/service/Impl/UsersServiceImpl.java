package com.ecommerce.blockchain.service.Impl;

import com.ecommerce.blockchain.domain.users.Users;
import com.ecommerce.blockchain.domain.users.UsersRepository;
import com.ecommerce.blockchain.domain.users.UsersRequestDto;
import com.ecommerce.blockchain.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users registerUser(UsersRequestDto usersRequestDto) {
        Users user = new Users();
        user.setName(usersRequestDto.getName());
        user.setEmail(usersRequestDto.getEmail());
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        user.setCreated_at(timestamp);
        user.setPassword(usersRequestDto.getPassword());

        return usersRepository.save(user);
    }
}
