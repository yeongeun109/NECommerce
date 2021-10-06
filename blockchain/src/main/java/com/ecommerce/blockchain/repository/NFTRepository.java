package com.ecommerce.blockchain.repository;


import com.ecommerce.blockchain.domain.nft.NFT;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JPA CRUD는 기본 메소드 사용

public interface NFTRepository extends JpaRepository<NFT, Long> {

    List<NFTMapping> findBySellerId(Long userId);
}