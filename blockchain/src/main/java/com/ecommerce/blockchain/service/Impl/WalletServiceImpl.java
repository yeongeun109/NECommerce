package com.ecommerce.blockchain.service.Impl;

import com.ecommerce.blockchain.domain.wallet.WalletResponseDto;
import com.ecommerce.blockchain.service.WalletService;
import org.springframework.stereotype.Service;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service
public class WalletServiceImpl implements WalletService{


    @Override
    public WalletResponseDto getWallet(String address) throws IOException {

        Web3j web3j = Web3j.build(new HttpService("http://j5a4071.p.ssafy.io:8545"));
        //Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        //String version = web3ClientVersion.getWeb3ClientVersion();

        //address = "0x05bad9b0d2e8b8a0da5a97694bf4fc279d5b0cbd"; //coinbase 계정 주소
        BigInteger balance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send().getBalance();
        BigDecimal ethBalance = Convert.fromWei( balance.toString(), Convert.Unit.ETHER );

        WalletResponseDto walletDto = new WalletResponseDto();
        walletDto.setAddress(address);
        walletDto.setBalance(ethBalance);
        return walletDto;
    }
}
