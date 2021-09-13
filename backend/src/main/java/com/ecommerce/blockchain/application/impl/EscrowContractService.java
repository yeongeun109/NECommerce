package com.ecommerce.blockchain.application.impl;

import com.ecommerce.blockchain.application.IEscrowContractService;
import com.ecommerce.blockchain.application.IPurchaseService;
import com.ecommerce.blockchain.domain.Purchase;
import com.ecommerce.blockchain.domain.wrapper.EscrowContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

/**
 * EscrowContractService
 * Escrow.sol 스마트 컨트랙트의 checkDeposit() 함수를 호출한다.
 */
@Service
public class EscrowContractService implements IEscrowContractService {

    private static final Logger log = LoggerFactory.getLogger(EscrowContractService.class);

    private IPurchaseService purchaseService;

    @Value("${eth.admin.address}")
    private String ADMIN_ADDRESS;

    @Value("${eth.admin.wallet.filename}")
    private String WALLET_RESOURCE;

    @Value("${eth.encrypted.password}")
    private String PASSWORD;

    private EscrowContract escrowContract;
    private ContractGasProvider contractGasProvider = new DefaultGasProvider();
    private Credentials credentials;

    @Autowired
    private Web3j web3j;

    @Autowired
    public EscrowContractService(IPurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /**
     * TODO Sub PJT Ⅲ 과제 3
     * 입금 상태 조회 - checkDeposit
     */
    @Override
    public Purchase checkDeposit(int pid) {
        return null;
    }
}
