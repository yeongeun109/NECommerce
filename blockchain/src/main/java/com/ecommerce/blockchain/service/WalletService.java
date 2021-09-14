package com.ecommerce.blockchain.service;

import com.ecommerce.blockchain.domain.wallet.WalletResponseDto;

import java.io.IOException;

public interface WalletService {

    WalletResponseDto getWallet(String address) throws IOException;

}
