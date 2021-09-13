package com.ecommerce.blockchain.domain.repository;

import com.ecommerce.blockchain.domain.EthInfo;
import org.springframework.transaction.annotation.Transactional;

public interface IEthInfoRepository {
    EthInfo get(String ethUrl);

    @Transactional
    void put(String ethUrl, String blockNumber);
}
