package com.blockchain.backend.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "*")
@RequestMapping("/api/eth")
@RestController
public class EthereumController {

    @GetMapping("/")
    @ApiOperation(value = "eth test")
    public Object ethTest() throws Exception
    {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545/"));
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();

        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("version", web3ClientVersion.getWeb3ClientVersion());
        BigInteger balance = web3j.ethGetBalance("0xb70926e42409dfc50ba726497f6c3ed222f46e0f", DefaultBlockParameterName.LATEST).send().getBalance();

        resultMap.put("balance", Convert.fromWei(balance.toString(), Convert.Unit.ETHER));
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

}
