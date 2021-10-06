package com.ecommerce.blockchain.api;

import com.ecommerce.blockchain.domain.user.User;
import com.ecommerce.blockchain.domain.wallet.WalletRegistReq;
import com.ecommerce.blockchain.service.JwtService;
import com.ecommerce.blockchain.service.UserService;
import com.ecommerce.blockchain.service.WalletService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@ApiResponses(value = { @ApiResponse(code = 401, message = "유효하지 않은 토큰"),
        @ApiResponse(code = 403, message = "요청 권한 없음"),
        @ApiResponse(code = 404, message = "비회원"),
 })

@RestController
@RequestMapping("/api/v1")
public class WalletController {

    @Autowired
    WalletService walletService;

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    Logger logger = LoggerFactory.getLogger(WalletController.class);

//    // 지갑 등록
//    @ApiOperation(value = "생성된 지갑을 DB에 등록")
//    @RequestMapping(value = "/wallets", method = RequestMethod.POST)
//    public Object register(@RequestBody WalletRegistReq request, @RequestHeader String token) {
//
//        int userId = request.getOwnerId();
//        Optional<User> userOpt = userService.getUser(userId);
//        if (!userOpt.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 비회원
//
//        try {
//            if (userId == jwtService.getUserId(token)) { // 요청자가 토큰 발급한 유저와 같다면
//                walletService.registWallet(request);
//                return new ResponseEntity<>(HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 권한 없음
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 유효하지 않은 토큰
//        }
//
//
//    }
//    // 유저 아이디로 지갑 정보 조회
//    @ApiOperation(value = "유저 아이디로 지갑 정보 조회")
//    @RequestMapping(value = "/wallets/of/{userId}", method = RequestMethod.GET)
//    public Object getWalletInfoByUserId(@PathVariable int userId, @RequestHeader String token) throws Exception{
//
//        Optional<User> userOpt = userService.getUser(userId);
//        if (!userOpt.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 비회원;
//
//        try {
//            if (userId == jwtService.getUserId(token)) { // 요청자가 토큰 발급한 유저와 같다면
//                WalletResponseDto result = walletService.getWalletById(userOpt.get());
//                return new ResponseEntity<>(result, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 권한 없음
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 유효하지 않은 토큰
//        }
//
//
//
//
//    }
//
//    // 이더리움 지갑(계정) 주소로 지갑 정보 조회
//    @ApiOperation(value = "주소로 지갑 정보 조회")
//    @RequestMapping(value = "/wallets/{address}", method = RequestMethod.GET)
//    public Object getWalletInfoByAddress(@PathVariable String address) throws Exception{
//
//        WalletResponseDto result = walletService.getWalletByAddress(address);
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @ApiOperation(value = "주소로 NEToken 충전")
    @RequestMapping(value ="/wallet/token", method = RequestMethod.PUT)
    public Object requestEth(@RequestBody WalletRegistReq request, @RequestHeader String token) throws Exception{ // 테스트 가능하도록 일정 개수의 코인을 충전해준다.

        Long userId = request.getOwnerId();
        Optional<User> userOpt = userService.getUser(userId);
        if (!userOpt.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 비회원

        try {
            if (userId == jwtService.getUserId(token)) { // 요청자가 토큰 발급한 유저와 같다면
                if (request.getAddress().equals("0x7cbe440132bdeA85e826DE9DfA6eb7b93fbB1074")) {
                    logger.debug("controller에서 입력받은 주소 : {}", request.getAddress());
                    boolean flag = walletService.chargeNEToken(userId, request.getAddress());
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 권한 없음
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 유효하지 않은 토큰
        }
    }

    // JWT requestBody로 받는 방법
    // 이더리움 지갑 주소에 이더 충전 요청(수수료 및 토큰 구매용)
//    @ApiOperation(value = "주소로 NEToken 충전")
//    @RequestMapping(value ="/wallet/token", method = RequestMethod.PUT)
//    public Object requestEth(@RequestBody WalletRegistReq request) throws Exception{ // 테스트 가능하도록 일정 개수의 코인을 충전해준다.
//
//        int userId = request.getOwnerId();
//        Optional<User> userOpt = userService.getUser(userId);
//        if (!userOpt.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 비회원
//
//        try {
//            if (userId == jwtService.getUserId(request.getToken())) { // 요청자가 토큰 발급한 유저와 같다면
//                //boolean flag = walletService.chargeNEToken(userId, request.getAddress());
//                return new ResponseEntity<>(HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 권한 없음
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 유효하지 않은 토큰
//        }
//    }
}

