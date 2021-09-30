package com.ecommerce.blockchain;

import com.ecommerce.blockchain.service.ERC20Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlockchainApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BlockchainApplication.class, args);
        ERC20Service erc20 = new ERC20Service();
        erc20.deployERC20();
//        erc20.loadERC20();
//        erc20.transferERC20Test();
    }

}
