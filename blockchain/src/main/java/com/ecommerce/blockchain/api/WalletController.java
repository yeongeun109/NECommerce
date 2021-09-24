package com.ecommerce.blockchain.api;

import com.ecommerce.blockchain.domain.user.User;
import com.ecommerce.blockchain.domain.wallet.WalletRegistReq;
import com.ecommerce.blockchain.domain.wallet.WalletResponseDto;
import com.ecommerce.blockchain.service.JwtService;
import com.ecommerce.blockchain.service.UserService;
import com.ecommerce.blockchain.service.WalletService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.Wallet;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class WalletController {

    @Autowired
    WalletService walletService;

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    // 지갑 등록
    @ApiOperation(value = "생성된 지갑을 DB에 등록")
    @RequestMapping(value = "/wallets", method = RequestMethod.POST)
    public Object register(@RequestBody WalletRegistReq request, @RequestHeader String token) {

        int userId = request.getOwnerId();
        Optional<User> usersOpt = userService.getUser(userId);
        if (!usersOpt.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 비회원

        try {
            if (userId == jwtService.getUserId(token)) { // 요청자가 토큰 발급한 유저와 같다면
                walletService.registWallet(request);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 권한 없음
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 유효하지 않은 토큰
        }


    }

    // 이더리움 지갑(계정) 주소로 지갑 정보 조회
    @ApiOperation(value = "주소로 지갑 정보 조회")
    @RequestMapping(value = "/wallets/{address}", method = RequestMethod.GET)
    public Object getWalletInfoByAddress(@PathVariable String address) throws Exception{

        WalletResponseDto result = walletService.getWallet(address);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // 이더리움 지갑 주소에 이더 충전 요청(수수료 및 토큰 구매용)
    @ApiOperation(value = "주소로 이더 충전")
    @RequestMapping(value ="/wallets/{address}", method = RequestMethod.PUT)
    public Object requestEth(@PathVariable String address) throws Exception{ // 테스트 가능하도록 일정 개수의 코인을 충전해준다.

        WalletResponseDto result = walletService.chargeEther(address);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
