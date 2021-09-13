package com.ecommerce.blockchain.application.impl;

import com.ecommerce.blockchain.application.ICashContractService;
import com.ecommerce.blockchain.domain.wrapper.CashContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

@Service
public class CashContractService implements ICashContractService {
    private static final Logger log = LoggerFactory.getLogger(CashContractService.class);

    @Value("${eth.erc20.contract}")
    private String ERC20_TOKEN_CONTRACT;

    @Value("${eth.admin.address}")
    private String ADMIN_ADDRESS;

    @Value("${eth.admin.wallet.filename}")
    private String WALLET_RESOURCE;

    @Value("${eth.encrypted.password}")
    private String PASSWORD;

    private CashContract cashContract;
    private ContractGasProvider contractGasProvider = new DefaultGasProvider();
    private Credentials credentials;

    @Autowired
    private Web3j web3j;

    /**
     * TODO Sub PJT Ⅱ 과제 3
     * 토큰 잔액 조회
     * @param eoa
     * @return
     */
    @Override
    public int getBalance(String eoa) {
        return -1;
    }
}
