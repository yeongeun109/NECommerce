package com.ecommerce.blockchain.service;

import com.ecommerce.blockchain.domain.wallet.WalletResponseDto;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface WalletService {

    WalletResponseDto getWallet(String address) throws IOException;

    WalletResponseDto chargeEther(String address) throws IOException, ExecutionException, InterruptedException;

}
