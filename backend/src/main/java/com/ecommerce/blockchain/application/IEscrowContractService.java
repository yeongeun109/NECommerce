package com.ecommerce.blockchain.application;

import com.ecommerce.blockchain.domain.Purchase;
import org.springframework.transaction.annotation.Transactional;

public interface IEscrowContractService {
    @Transactional
    Purchase checkDeposit(int pid);
}
