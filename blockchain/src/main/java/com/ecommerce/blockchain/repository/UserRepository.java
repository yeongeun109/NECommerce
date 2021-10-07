package com.ecommerce.blockchain.repository;

import com.ecommerce.blockchain.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByName(String name);

    LocalDateTime findLatelyTimeById(Long userId);

    @Transactional
    int deleteByEmail(String email);
}
