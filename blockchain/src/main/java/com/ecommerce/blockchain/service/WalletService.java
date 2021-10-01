package com.ecommerce.blockchain.service;

import com.ecommerce.blockchain.domain.user.User;
import com.ecommerce.blockchain.domain.wallet.WalletRegistReq;
import com.ecommerce.blockchain.domain.wallet.WalletResponseDto;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface WalletService {

    void registWallet(WalletRegistReq request);

    WalletResponseDto getWalletByAddress(String address) throws IOException;

    WalletResponseDto getWalletById(User user) throws IOException;

//    WalletResponseDto chargeEther(String address) throws IOException, ExecutionException, InterruptedException;
boolean chargeNEToken(int chargeNEToken, String address) throws IOException, ExecutionException, InterruptedException;

}
