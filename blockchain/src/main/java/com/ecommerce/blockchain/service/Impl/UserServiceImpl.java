package com.ecommerce.blockchain.service.Impl;

import com.ecommerce.blockchain.domain.user.User;
import com.ecommerce.blockchain.repository.UserRepository;
import com.ecommerce.blockchain.domain.user.UserRequestDto;
import com.ecommerce.blockchain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> getUser(int userId) {
        return userRepository.findById(userId);
    }

    public User registerUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        user.setCreated_at(timestamp);
        user.setPassword(userRequestDto.getPassword());

        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public int deleteUser(String email) {
        return userRepository.deleteByEmail(email);
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
