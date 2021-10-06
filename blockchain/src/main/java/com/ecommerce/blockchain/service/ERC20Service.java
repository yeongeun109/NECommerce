package com.ecommerce.blockchain.service;

import com.ecommerce.blockchain.domain.contract.NeERC20_sol_NeERC20;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import static org.web3j.tx.Transfer.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;

@Service
public class ERC20Service {

//    @Autowired
//    private Web3j web3j;
    String testnetUrl = "https://ropsten.infura.io/v3";
    String testnetToken = "9aa3d95b3bc440fa88ea12eaa4456161";

//    @Value("${erc20.privateKey}")
    private final String privateKey = "dabfca3161aeb1bfdbed8fc43f34437addf16e04d694530cb0f26092371badb5";
    private final String SmartContract = "0xDA4a547a5622fc3D592F0144fBCae2754679245e";

    private final Web3j web3j = Web3j.build(new HttpService(testnetUrl + "/" + testnetToken));

    Logger logger = LoggerFactory.getLogger(ERC20Service.class);

    public String test() throws IOException {
        Web3ClientVersion web3ClientVersion = null;
        web3ClientVersion = web3j.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        System.out.println(clientVersion);
        return clientVersion;
    }

    public void deployERC20() throws Exception {
        Credentials credentials = Credentials.create(privateKey);
        BigInteger decimal = new BigInteger("18");
        BigInteger gasPrice = new BigInteger("3500000");
        BigInteger gasLimit = new BigInteger("3500000");
        logger.debug("ERC20 배포");
        RemoteCall<NeERC20_sol_NeERC20> contract = NeERC20_sol_NeERC20.deploy(web3j, credentials, gasPrice, gasLimit, "NeToken", "NE",decimal);
        String result = contract.send().getContractAddress();  // constructor params
        logger.debug("배포한 컨트랙트 : {}", result);
    }

    public void loadERC20() throws Exception {
        Credentials credentials = Credentials.create(privateKey);
        NeERC20_sol_NeERC20 contract = NeERC20_sol_NeERC20.load(SmartContract, web3j, credentials, GAS_PRICE, GAS_LIMIT);
        logger.debug("로드한 컨트랙트 : {}", contract);
        BigInteger result = contract.totalSupply().send();
        logger.debug("총 토큰 개수 : {}", result.toString());
    }

    public void transferNEToken(String address, BigInteger amount) throws Exception {
        logger.debug("private key : {}", privateKey); // 지워야 함!!!!!
        Credentials credentials = Credentials.create(privateKey);
        BigInteger gasPrice = new BigInteger("50000");
        BigInteger gasLimit = new BigInteger("50000");
        NeERC20_sol_NeERC20 contract = NeERC20_sol_NeERC20.load(SmartContract, web3j, credentials, gasPrice, gasLimit);
        logger.debug("로드한 컨트랙트 : {}", contract);
        logger.debug("충전 요청한 주소 : {}", address);
        logger.debug("충전 NEToken 량 : {}", amount);
        TransactionReceipt transactionReceipt = contract.transfer(address, amount).send();
        logger.debug("트랜잭션 결과 : {}", transactionReceipt);
    }

    public void transferERC20Test() throws Exception {
        Credentials credentials = Credentials.create(privateKey);
//        BigInteger gasPrice = new BigInteger("5000000");
//        BigInteger gasLimit = new BigInteger("5000000");
//        NeERC20_sol_NeERC20 contract = NeERC20_sol_NeERC20.load(SmartContract, web3j, credentials, GAS_PRICE, GAS_LIMIT);
//        logger.debug("로드한 컨트랙트 : {}", contract);
//        BigInteger amount = new BigInteger("5000000000000000000");
////        BigInteger transactionReceipt = contract.balanceOf("0x7cbe440132bdeA85e826DE9DfA6eb7b93fbB1074").send();
//        TransactionReceipt transactionReceipt = contract.transfer("0x7cbe440132bdeA85e826DE9DfA6eb7b93fbB1074", amount).send();
//        logger.debug("트랜잭션 결과 : {}", transactionReceipt);

        TransactionReceipt transactionReceipt = Transfer.sendFundsEIP1559(
                web3j, credentials,
                "0x7cbe440132bdeA85e826DE9DfA6eb7b93fbB1074", //toAddress
                BigDecimal.ONE.valueOf(1), //value
                Convert.Unit.ETHER, //unit
                BigInteger.valueOf(8_000_000),
                DefaultGasProvider.GAS_LIMIT, //maxPriorityFeePerGas (max fee per gas transaction willing to give to miners)
                BigInteger.valueOf(3_100_000_000L) //maxFeePerGas (max fee transaction willing to pay)
        ).send();
    }

}
