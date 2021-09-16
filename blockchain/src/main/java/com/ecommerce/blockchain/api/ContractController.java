package com.ecommerce.blockchain.api;

import com.ecommerce.blockchain.domain.contract.Cash;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import static org.web3j.tx.Transfer.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;

public class ContractController {

    public void deployERC20() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://j5a4071.p.ssafy.io:8545"));
//        Credentials credentials = WalletUtils.loadCredentials("password", "/path/to/walletfile");
        Credentials credentials = Credentials.create("0x05bad9b0d2e8b8a0da5a97694bf4fc279d5b0cbd");

        Cash contract = Cash.deploy(
                web3j, credentials,
                GAS_PRICE, GAS_LIMIT).send();  // constructor params
    }

}
