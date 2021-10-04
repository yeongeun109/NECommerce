package com.ecommerce.blockchain.service;

import com.ecommerce.blockchain.domain.contract.ERC721;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;

import static org.web3j.tx.Transfer.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;

@Service
public class ERC721Service {

//    @Autowired
//    private Web3j web3j;
    String testnetUrl = "https://ropsten.infura.io/v3";
    String testnetToken = "9aa3d95b3bc440fa88ea12eaa4456161";

    @Value("${erc20.privateKey}")
    private String privateKey;

    Web3j web3j = Web3j.build(new HttpService(testnetUrl + "/" + testnetToken));

    Logger logger = LoggerFactory.getLogger(ERC721Service.class);

    public String test() throws IOException {
        Web3ClientVersion web3ClientVersion = null;
        web3ClientVersion = web3j.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        System.out.println(clientVersion);
        return clientVersion;
    }

    public void deployERC721() throws Exception {
        Credentials credentials = Credentials.create(privateKey);
        BigInteger decimal = new BigInteger("18");
        BigInteger gasPrice = new BigInteger("2500000");
        BigInteger gasLimit = new BigInteger("2500000");
        RemoteCall<ERC721> contract = ERC721.deploy(web3j, credentials, gasPrice, gasLimit);
        String result = contract.send().getContractAddress();  // constructor params
        logger.debug("배포한 컨트랙트 : {}", result);
    }

    public void loadERC721() throws Exception {
        Credentials credentials = Credentials.create(privateKey);
        ERC721 contract = ERC721.load("0x528E38bc6d03BFaabaE9585048c484b440b09fa8", web3j, credentials, GAS_PRICE, GAS_LIMIT);
        logger.debug("로드한 컨트랙트 : {}", contract);
//        BigInteger result = contract.totalSupply().send();
//        logger.debug("총 토큰 개수 : {}", result.toString());
    }

    public void transferERC721Test() throws Exception {
        Credentials credentials = Credentials.create(privateKey);
        BigInteger gasPrice = new BigInteger("50000");
        BigInteger gasLimit = new BigInteger("50000");
        ERC721 contract = ERC721.load("0x23a496b29f189aee7690abdc28f0321545dcdd5e", web3j, credentials, gasPrice, gasLimit);
        logger.debug("로드한 컨트랙트 : {}", contract);
        BigInteger amount = new BigInteger("10000");
//        TransactionReceipt transactionReceipt = contract.transfer("0x7cbe440132bdeA85e826DE9DfA6eb7b93fbB1074", amount).send();
//        logger.debug("트랜잭션 결과 : {}", transactionReceipt);
    }

    public void mintImageERC721Test() throws Exception {
        Credentials credentials = Credentials.create(privateKey);
        BigInteger gasPrice = new BigInteger("500000");
        BigInteger gasLimit = new BigInteger("500000");
        ERC721 contract = ERC721.load("0x23a496b29f189aee7690abdc28f0321545dcdd5e", web3j, credentials, gasPrice, gasLimit);
        logger.debug("로드한 컨트랙트 : {}", contract);
        BigInteger amount = new BigInteger("10000");
        TransactionReceipt transactionReceipt = contract.mintImage("강쥐", "0x737C7Ef052FeA39fde48E83e1E94d18B2FdfafE7","https://user-images.githubusercontent.com/54837242/135557307-8a67f97e-6a9a-4905-bf36-02e9ab91350a.gif","강아지입니다!",amount).send();
        logger.debug("트랜잭션 결과 : {}", transactionReceipt);
    }

}
