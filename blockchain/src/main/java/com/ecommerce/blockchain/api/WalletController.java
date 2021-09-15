package com.ecommerce.blockchain.api;

import com.ecommerce.blockchain.domain.wallet.WalletResponseDto;
import com.ecommerce.blockchain.service.WalletService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.Wallet;

@RestController
@RequestMapping("/api/v1")
public class WalletController {

    @Autowired
    WalletService walletService;

    // 이더리움 지갑(계정) 주소로 지갑 정보 조회
    @ApiOperation(value = "get Wallet info")
    @RequestMapping(value = "/wallets/{address}", method = RequestMethod.GET)
    public Object getWalletInfo(@PathVariable String address) throws Exception{

        WalletResponseDto result = walletService.getWallet(address);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 이더리움 지갑 주소에 이더 충전 요청(수수료 및 토큰 구매용)
    @ApiOperation(value = "charge ether")
    @RequestMapping(value ="/wallets/{address}", method = RequestMethod.PUT)
    public Object requestEth(@PathVariable String address) throws Exception{ // 테스트 가능하도록 일정 개수의 코인을 충전해준다.

        WalletResponseDto result = walletService.chargeEther(address);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
