package com.ecommerce.blockchain.service;

import com.ecommerce.blockchain.domain.global.exception.NoUserException;
import com.ecommerce.blockchain.domain.nft.NFT;
import com.ecommerce.blockchain.domain.nft.NFTRequestDto;
import com.ecommerce.blockchain.domain.nft.NFTResponseDto;
import com.ecommerce.blockchain.domain.nft.exception.NoNFTException;
import com.ecommerce.blockchain.repository.NFTMapping;

import java.util.List;
import java.util.Optional;

public interface NFTService {
    Optional<NFT> getNFT(Long id);
    void register(NFTRequestDto nftDto) throws NoUserException;
    List<NFTMapping> getList(Long userId) throws NoUserException;
    NFTResponseDto getDetail(Long nftId) throws NoNFTException;
}
