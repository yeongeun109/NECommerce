package com.ecommerce.blockchain.application;

import com.ecommerce.blockchain.domain.Record;

import java.util.List;

public interface IPurchaseRecordContractService {
    List<Record> getHistory(final String escrowContractAddress);
}
