package com.ecommerce.blockchain.service;

import com.ecommerce.blockchain.domain.global.exception.NoUserException;
import com.ecommerce.blockchain.domain.nft.NFTRequestDto;
import com.ecommerce.blockchain.repository.NFTMapping;

import java.util.List;

public interface NFTService {
    void register(NFTRequestDto nftDto) throws NoUserException;
    List<NFTMapping> getList(Long userId) throws NoUserException;

}
