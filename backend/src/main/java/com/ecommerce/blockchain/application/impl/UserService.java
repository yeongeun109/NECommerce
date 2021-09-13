package com.ecommerce.blockchain.application.impl;

import com.ecommerce.blockchain.application.IUserService;
import com.ecommerce.blockchain.domain.User;
import com.ecommerce.blockchain.domain.exception.ApplicationException;
import com.ecommerce.blockchain.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;

//    @Autowired
//    public UserService(IUserRepository userRepository) {
//        this.userRepository = userRepository;
//    }


    @Override
    public List<User> list() {
        return this.userRepository.list();
    }

    @Override
    public User get(long id) {
        return this.userRepository.get(id);
    }

    @Override
    public User get(String email) { return this.userRepository.get(email); }

    @Override
    public User add(User user) {
        long id = this.userRepository.create(user);
        return this.userRepository.get(id);
    }

    @Override
    public User update(User user) {

        User found = this.userRepository.get(user.getEmail());
        if(found == null)
            throw new ApplicationException("회원 정보를 찾을 수 없습니다.");

        if(user.getId() == 0)
            user.setId(found.getId());
        if(user.getName() == null)
            user.setName(found.getName());
        if(user.getPassword() == null)
            user.setPassword(found.getPassword());

        int affected = this.userRepository.update(user);
        if(affected == 0)
            throw new ApplicationException("작품정보수정 처리가 반영되지 않았습니다.");

        return this.userRepository.get(user.getId());
    }

    @Override
    public void delete(long id) {
        this.userRepository.delete(id);
    }
}
