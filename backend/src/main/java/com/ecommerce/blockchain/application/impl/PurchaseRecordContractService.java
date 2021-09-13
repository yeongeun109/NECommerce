package com.ecommerce.blockchain.application.impl;

import com.ecommerce.blockchain.application.IPurchaseRecordContractService;
import com.ecommerce.blockchain.domain.Record;
import com.ecommerce.blockchain.domain.repository.IPurchaseRepository;
import com.ecommerce.blockchain.domain.wrapper.PurchaseRecordContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.util.List;

/**
 * PurchaseRecordContractService
 * 작성, 배포한 PurchaseRecord.sol 스마트 컨트랙트에서 정보를 조회한다.
 */
@Service
public class PurchaseRecordContractService implements IPurchaseRecordContractService {
    private static final Logger log = LoggerFactory.getLogger(PurchaseRecordContractService.class);

    @Value("${eth.purchase.record.contract}")
    private String PURCHASE_CONTRACT_ADDRESS;

    @Value("${eth.admin.address}")
    private String ADMIN_ADDRESS;

    @Value("${eth.admin.wallet.filename}")
    private String WALLET_RESOURCE;

    @Value("${eth.encrypted.password}")
    private String PASSWORD;

    private PurchaseRecordContract purchaseRecordContract;
    private ContractGasProvider contractGasProvider = new DefaultGasProvider();
    private Credentials credentials;

    @Autowired
    private Web3j web3j;

    private IPurchaseRepository purchaseRepository;

//    @Autowired
//    public PurchaseRecordContractService(IPurchaseRepository purchaseRepository) {
//        this.purchaseRepository = purchaseRepository;
//    }

    /**
     * TODO Sub PJT Ⅲ 과제 3
     * 구매 이력 조회
     */
    @Override
    public List<Record> getHistory(String contractAddress) {
        return null;
    }
}
