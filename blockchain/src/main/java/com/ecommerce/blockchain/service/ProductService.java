package com.ecommerce.blockchain.service;

import com.ecommerce.blockchain.domain.global.exception.NoUserException;
import com.ecommerce.blockchain.domain.nft.exception.NoNFTException;
import com.ecommerce.blockchain.domain.product.ProductPurchaseRequestDto;
import com.ecommerce.blockchain.domain.product.ProductRequestDto;
import com.ecommerce.blockchain.domain.product.ProductResponseDto;
import com.ecommerce.blockchain.domain.product.exception.NoProductException;
import com.ecommerce.blockchain.repository.ProductMapping;

import java.util.List;

public interface ProductService {
    void register(ProductRequestDto productDto) throws NoNFTException, NoUserException;
    List<ProductMapping> getAllList() throws NoProductException;
    List<ProductMapping> getTrueSaleList() throws NoProductException;
    List<ProductMapping> getFalseSaleList() throws NoProductException;
    List<ProductMapping> getList(Long userId) throws NoProductException;
    ProductResponseDto getDetail(Long nftId) throws NoNFTException, NoProductException, NoUserException;
    void isPurchased(ProductPurchaseRequestDto pprDto) throws NoProductException, NoNFTException, NoUserException;
}
