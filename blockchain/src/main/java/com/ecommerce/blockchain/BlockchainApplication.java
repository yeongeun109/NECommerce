package com.ecommerce.blockchain;

import com.ecommerce.blockchain.service.ERC721Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BlockchainApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BlockchainApplication.class, args);
        ERC721Service erc721 = new ERC721Service();
//        erc721.deployERC721();
        erc721.mintImageERC721Test();

//        ERC20Service erc20 = new ERC20Service();
//        erc20.deployERC20();
//        erc20.loadERC20();
//        erc20.transferERC20Test();
    }

}
