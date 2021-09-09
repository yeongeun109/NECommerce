package com.blockchain.backend.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
