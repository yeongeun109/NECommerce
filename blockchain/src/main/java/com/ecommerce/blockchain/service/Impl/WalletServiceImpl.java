package com.ecommerce.blockchain.service.Impl;

import com.ecommerce.blockchain.domain.wallet.WalletResponseDto;
import com.ecommerce.blockchain.service.WalletService;
import org.springframework.stereotype.Service;

import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCoinbase;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
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

    Web3j web3j = Web3j.build(new HttpService("http://j5a4071.p.ssafy.io:54072"));

    //계정 관리 API 사용 ex) 지갑의 unlock
    Admin admin = Admin.build(new HttpService("http://j5a4071.p.ssafy.io:54072"));

    BigInteger GAS_PRICE = BigInteger.valueOf(154000000000L);

    BigInteger GAS_LIMIT = BigInteger.valueOf(100000);

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

        // DB의 지갑 정보 업데이트해서 리턴


        return walletDto;
    }
    // 해당 계정에 10이더 충전
    @Override
    public WalletResponseDto chargeEther(String address) throws IOException, ExecutionException, InterruptedException {

        // 충전량 제한하여 충전
        // user 및 wallet DB 필요
        
        // 마이닝한 이더를 가지고 있는 코인베이스 계정을 조회
        EthCoinbase coinbase = web3j.ethCoinbase().sendAsync().get();
        String coinbaseAddr = coinbase.getAddress();
        System.out.println(coinbaseAddr);

        // 코인베이스 계정의 lock 해제
        PersonalUnlockAccount personalUnlockAccount = admin.personalUnlockAccount(coinbaseAddr, "eth").send();

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

        // 코인베이스 계정의 nonce 값 얻기
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                coinbaseAddr, DefaultBlockParameterName.LATEST).sendAsync().get();

        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        System.out.println(nonce);

        // 충전할 이더량 wei로 환산
        BigInteger value =
        Convert.toWei("10", Convert.Unit.ETHER).toBigIntegerExact();
        System.out.println(value);

        // wei를 이더로 환산
//        int result = Integer.parseInt( Convert.fromWei(value.toString() , Convert.Unit.ETHER).toString() );
//        System.out.println(result);

        // 트랜잭션 생성
//        RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(
//                nonce, GAS_PRICE, GAS_LIMIT, address, value);

        Transaction transaction =
        Transaction.createFunctionCallTransaction(coinbaseAddr, nonce, GAS_PRICE, GAS_LIMIT, address, value, null);
        EthSendTransaction ethSendTransaction = web3j.ethSendTransaction(transaction).sendAsync().get();

        System.out.println(ethSendTransaction.getTransactionHash());
        WalletResponseDto walletDto = new WalletResponseDto();


        return getWallet(address);
    }
}
