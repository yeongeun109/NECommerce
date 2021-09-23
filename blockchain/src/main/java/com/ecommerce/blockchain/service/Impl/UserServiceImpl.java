package com.ecommerce.blockchain.service.Impl;

import com.ecommerce.blockchain.domain.users.Users;
import com.ecommerce.blockchain.repository.UserRepository;
import com.ecommerce.blockchain.domain.users.UsersRequestDto;
import com.ecommerce.blockchain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Users registerUser(UsersRequestDto usersRequestDto) {
        Users user = new Users();
        user.setName(usersRequestDto.getName());
        user.setEmail(usersRequestDto.getEmail());
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        user.setCreated_at(timestamp);
        user.setPassword(usersRequestDto.getPassword());

        return userRepository.save(user);
    }

    @Override
    public Users getUserByEmail(String email) {
        Users user = userRepository.findByEmail(email);
        return user;
    }
//
//    @Override
//    public int updateUser(Users user, String name) {
//        if(userRepository.findByName(name) == null){
//            user.setName(name);
//            userRepository.save(user);
//            return 1;
//        }else{
//            return 0;
//        }
//    }

}
