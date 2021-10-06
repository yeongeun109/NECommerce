package com.ecommerce.blockchain.api;

import com.ecommerce.blockchain.domain.global.SuccessResponseDto;
import com.ecommerce.blockchain.domain.global.service.ResponseGenerateService;
import com.ecommerce.blockchain.domain.nft.NFT;
import com.ecommerce.blockchain.domain.nft.NFTRequestDto;
import com.ecommerce.blockchain.domain.nft.NFTResponseDto;
import com.ecommerce.blockchain.domain.user.User;
import com.ecommerce.blockchain.service.Impl.NFTServiceImpl;
import com.ecommerce.blockchain.service.JwtService;
import com.ecommerce.blockchain.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class NFTController {

    private final NFTServiceImpl nftService;
    private final ResponseGenerateService responseGenerateService;
    private final UserService userService;
    private final JwtService jwtService;


    Logger logger = LoggerFactory.getLogger(NFTController.class);

    @ApiOperation(value = "NFT 등록")
    @PostMapping("/nft/register")
    public Object registerNFT(@RequestBody NFTRequestDto nftDto) throws Exception {
        Long sellerId = nftDto.getOwner_id();
        logger.debug("nft 생성 후 DB에 등록, 유저아이디 : {}", sellerId);
        Optional<User> userOpt = userService.getUser(sellerId);

        if (!userOpt.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 비회원

        try {
            if (sellerId == jwtService.getUserId(nftDto.getToken())) { // 요청자가 토큰 발급한 유저와 같다면

                nftService.register(nftDto);
                HttpStatus status = HttpStatus.OK;
                SuccessResponseDto successResponseDto = responseGenerateService.generateSuccessResponse("Success");
                return new ResponseEntity<>(successResponseDto,status);

            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 권한 없음
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 유효하지 않은 토큰
        }
    }

    /*
    *   seller를 변경한다면 seller가 소유하고있는 nft 전체 목록
     */
    @PostMapping("/nft/list/{userId}")
    public Object loadAllNFT(@PathVariable Long userId, @RequestBody String token) throws Exception {
        Optional<User> userOpt = userService.getUser(userId);
        if (!userOpt.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 비회원
        logger.debug("등록한 nft 목록 요청 : 유저 pk {}",userId);

        try {
            if (userId == jwtService.getUserId(token)) { // 요청자가 토큰 발급한 유저와 같다면
                SuccessResponseDto successResponseDto = responseGenerateService.generateSuccessResponse(nftService.getList(userId));
                return new ResponseEntity<>(successResponseDto,HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 권한 없음
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 유효하지 않은 토큰
        }
    }

    @PostMapping("/nft/detail/{nftId}")
    public Object loadNFT(@PathVariable Long nftId, @RequestBody String token) throws Exception {
        System.out.println(token);
        Optional<NFT> NFTOpt = nftService.getNFT(nftId);
        if (!NFTOpt.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 없는 NFT
        logger.debug("nft 상세 정보 요청 : nft PK {}", nftId);

        try {
            jwtService.getUserId(token);
            NFTResponseDto tmp = nftService.getDetail(nftId);
            SuccessResponseDto successResponseDto = responseGenerateService.generateSuccessResponse(tmp);
            return new ResponseEntity<>(successResponseDto,HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 유효하지 않은 토큰
        }

    }
}
