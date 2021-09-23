package com.ecommerce.blockchain.repository;

import com.ecommerce.blockchain.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);
    Users findByName(String name);

    @Transactional
    int deleteByEmail(String email);
}
