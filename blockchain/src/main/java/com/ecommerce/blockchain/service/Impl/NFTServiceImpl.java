package com.ecommerce.blockchain.service.Impl;

import com.ecommerce.blockchain.domain.global.exception.NoUserException;
import com.ecommerce.blockchain.domain.nft.NFT;
import com.ecommerce.blockchain.domain.nft.NFTRequestDto;
import com.ecommerce.blockchain.domain.nft.NFTResponseDto;
import com.ecommerce.blockchain.domain.nft.exception.NoNFTException;
import com.ecommerce.blockchain.domain.user.User;
import com.ecommerce.blockchain.repository.NFTMapping;
import com.ecommerce.blockchain.repository.NFTRepository;
import com.ecommerce.blockchain.repository.UserRepository;
import com.ecommerce.blockchain.service.NFTService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NFTServiceImpl implements NFTService {

    private final NFTRepository nftRepository;
    private final UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(NFTServiceImpl.class);

    @Override
    public Optional<NFT> getNFT(Long id) {
        return nftRepository.findById(id);
    }

    @Override
    @Transactional
    public void register(NFTRequestDto nftDto) throws NoUserException {
        logger.debug("유저 {}가 만든 nft 등록", nftDto.getSeller_id());
        NFT newItem = nftDto.toEntity();
        newItem.setUser(userRepository.findById(nftDto.getSeller_id()).orElseThrow(() -> new NoUserException("해당하는 사용자가 없습니다.")));
        nftRepository.save(newItem);
        logger.debug("NFT Title : {}, 등록 완료",nftDto.getTitle());
    }

    @Override
    public List<NFTMapping> getList(Long userId) throws NoUserException {
        return nftRepository.findBySellerId(userId);
    }

    @Override
    public NFTResponseDto getDetail(Long nftId) throws NoNFTException {
        logger.debug("getDetail");
        Optional<NFT> nft = Optional.ofNullable(nftRepository.findById(nftId).orElseThrow(() -> new NoNFTException("해당하는 루트가 없습니다.")));
        logger.debug("nft Repository를 이용해 nft불러옴 {}",nft.get());
        return new NFTResponseDto(nft.get());
    }
}
