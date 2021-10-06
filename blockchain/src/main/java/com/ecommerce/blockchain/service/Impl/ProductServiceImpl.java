package com.ecommerce.blockchain.service.Impl;

import com.ecommerce.blockchain.domain.global.exception.NoUserException;
import com.ecommerce.blockchain.domain.nft.exception.NoNFTException;
import com.ecommerce.blockchain.domain.product.Product;
import com.ecommerce.blockchain.domain.product.ProductRequestDto;
import com.ecommerce.blockchain.domain.product.ProductResponseDto;
import com.ecommerce.blockchain.domain.product.exception.NoProductException;
import com.ecommerce.blockchain.repository.NFTRepository;
import com.ecommerce.blockchain.repository.ProductMapping;
import com.ecommerce.blockchain.repository.ProductRepository;
import com.ecommerce.blockchain.repository.UserRepository;
import com.ecommerce.blockchain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final NFTRepository nftRepository;
    private final UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public void register(ProductRequestDto productDto) throws NoNFTException, NoUserException {
        Product newItem = productDto.toEntity();
        newItem.setUser(userRepository.findById(productDto.getUserId()).orElseThrow(() -> new NoUserException("해당하는 사용자가 없습니다.")));
        newItem.setNft(nftRepository.findById(productDto.getNftId()).orElseThrow(() -> new NoNFTException("해당하는 사용자가 없습니다.")));
        productRepository.save(newItem);
        logger.debug("Product {}의 가격 : {}", newItem.getId(), newItem.getPrice());
    }

    @Override
    public List<ProductMapping> getAllList() throws NoProductException {
        logger.debug("product 전체 불러오기");
        return productRepository.findAllBy();
    }

    @Override
    public List<ProductMapping> getTrueSaleList() throws NoProductException {
        logger.debug("product 판매가 안 된 목록 불러오기");
        return productRepository.findProductsByStatusIsTrue();
    }

    @Override
    public List<ProductMapping> getFalseSaleList() throws NoProductException {
        logger.debug("product 판매가 된 목록 불러오기");
        return productRepository.findProductsByStatusIsFalse();
    }

    @Override
    public List<ProductMapping> getList(Long userId) throws NoProductException {
        logger.debug("유저 {} 의 판매 등록한 product list 불러오기", userId);
        return productRepository.findByUserId(userId);
    }

    @Override
    public ProductResponseDto getDetail(Long productId) throws NoNFTException, NoProductException, NoUserException {
        logger.debug("product {} 의 상세 정보 불러오기", productId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoProductException("해당하는 product가 없습니다."));
        logger.debug("불러온 product entity : {}", product);
        ProductResponseDto newResponse = new ProductResponseDto(product);
        logger.debug("product Repository에서 성공적으로 불러옴 {}", product);
        return newResponse;
    }

    @Override
    public void isPurchased(Long productId) throws NoProductException {
        logger.debug("product {} 의 상태 false로 변경하기", productId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoProductException("해당하는 product가 없습니다."));
        product.setStatus(false);
        productRepository.save(product);
    }
}
