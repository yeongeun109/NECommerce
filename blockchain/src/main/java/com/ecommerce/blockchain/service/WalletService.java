package com.ecommerce.blockchain.service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface WalletService {

//    void registWallet(WalletRegistReq request);
//
//    WalletResponseDto getWalletByAddress(String address) throws IOException;
//
//    WalletResponseDto getWalletById(User user) throws IOException;

//    WalletResponseDto chargeEther(String address) throws IOException, ExecutionException, InterruptedException;
boolean chargeNEToken(Long chargeNEToken, String address) throws IOException, ExecutionException, InterruptedException;

}
