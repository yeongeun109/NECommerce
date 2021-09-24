package com.ecommerce.blockchain.repository;

import com.ecommerce.blockchain.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByName(String name);

    @Transactional
    int deleteByEmail(String email);
}
