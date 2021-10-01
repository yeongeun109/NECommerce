package com.ecommerce.blockchain;

import com.ecommerce.blockchain.service.ERC20Service;
import com.ecommerce.blockchain.service.ERC721Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlockchainApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BlockchainApplication.class, args);
        ERC721Service erc721 = new ERC721Service();
//        erc721.deployERC721();
        erc721.mintImageERC721Test();

//        erc20.loadERC20();
//        erc20.transferERC20Test();
    }

}
