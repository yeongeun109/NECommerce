package com.ecommerce.blockchain.service.Impl;

import com.ecommerce.blockchain.domain.wallet.WalletResponseDto;
import com.ecommerce.blockchain.service.WalletService;
import org.springframework.stereotype.Service;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class WalletServiceImpl implements WalletService{

    Web3j web3j = Web3j.build(new HttpService("http://j5a4071.p.ssafy.io:5407"));

    Admin admin = Admin.build(new HttpService("http://j5a4071.p.ssafy.io:5407"));

    @Override
    public WalletResponseDto getWallet(String address) throws IOException {

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

    @Override
    public WalletResponseDto chargeEther(String address) throws IOException, ExecutionException, InterruptedException {

        // 지갑의 lock 해제
        PersonalUnlockAccount personalUnlockAccount = admin.personalUnlockAccount(address, "eth").send();

        // 트랜잭션 생성
//                        <from address>,
//                <nonce>,
//                BigInteger.valueOf(<gas price>),  // we use default gas limit
//        "0x...<smart contract code to execute>"
//        Transaction transaction = Transaction.createEtherTransaction(
//
//        );

//        org.web3j.protocol.core.methods.response.EthSendTransaction
//                transactionResponse = parity.ethSendTransaction(ethSendTransaction)
//                .send();
//
//        String transactionHash = transactionResponse.getTransactionHash();

        // poll for transaction response via org.web3j.protocol.Web3j.ethGetTransactionReceipt(<txHash>)


        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                address, DefaultBlockParameterName.LATEST).sendAsync().get();

        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        System.out.println(nonce);

        WalletResponseDto walletDto = new WalletResponseDto();
        return walletDto;
    }
}
