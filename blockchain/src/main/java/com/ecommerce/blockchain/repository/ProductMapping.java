package com.ecommerce.blockchain.repository;

import com.ecommerce.blockchain.domain.nft.NFT;
import com.ecommerce.blockchain.domain.user.User;

public interface ProductMapping {
    Long getId();
    boolean isStatus();
    double getPrice();
    User getUser();
    NFT getNft();
}
