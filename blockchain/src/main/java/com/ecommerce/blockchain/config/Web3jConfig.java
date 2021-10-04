package com.ecommerce.blockchain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3jConfig {

    private String testnetUrl = "https://ropsten.infura.io/v3";
    private String testnetToken = "9aa3d95b3bc440fa88ea12eaa4456161";

    @Bean
    public Web3j web3j() {
        Web3j web3j = Web3j.build(new HttpService(testnetUrl + "/" + testnetToken));
        return web3j;
    }

}
