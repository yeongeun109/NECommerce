package com.ecommerce.blockchain.api;

import com.ecommerce.blockchain.domain.global.SuccessResponseDto;
import com.ecommerce.blockchain.domain.global.service.ResponseGenerateService;
import com.ecommerce.blockchain.domain.nft.NFTRequestDto;
import com.ecommerce.blockchain.domain.nft.NFTResponseDto;
import com.ecommerce.blockchain.service.Impl.NFTServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class NFTController {

    private final NFTServiceImpl nftService;
    private final ResponseGenerateService responseGenerateService;

    Logger logger = LoggerFactory.getLogger(NFTController.class);

    @ApiOperation(value = "NFT 등록")
    @PostMapping("/nft/register")
    public ResponseEntity<SuccessResponseDto> registerNFT(@RequestBody NFTRequestDto nftDto) throws Exception {
        logger.debug("nft 생성 후 DB에 등록, 유저아이디 : {}", nftDto.getSeller_id());
        nftService.register(nftDto);
        HttpStatus status = HttpStatus.OK;
        SuccessResponseDto successResponseDto = responseGenerateService.generateSuccessResponse("Success");
        return new ResponseEntity<>(successResponseDto,status);
    }

    /*
    *   seller를 변경한다면 seller가 소유하고있는 nft 전체 목록
     */
    @GetMapping("/nft/list/{userId}")
    public ResponseEntity<SuccessResponseDto> loadAllNFT(@PathVariable Long userId) throws Exception {
        logger.debug("등록한 nft 목록 요청 : 유저 pk {}",userId);
        SuccessResponseDto successResponseDto = responseGenerateService.generateSuccessResponse(nftService.getList(userId));
        return new ResponseEntity<>(successResponseDto,HttpStatus.OK);
    }

    @GetMapping("/nft/detail/{nftId}")
    public ResponseEntity<SuccessResponseDto> loadNFT(@PathVariable Long nftId) throws Exception {
        logger.debug("nft 상세 정보 요청 : nft PK {}", nftId);
        NFTResponseDto tmp = nftService.getDetail(nftId);
        SuccessResponseDto successResponseDto = responseGenerateService.generateSuccessResponse(tmp);
        return new ResponseEntity<>(successResponseDto,HttpStatus.OK);
    }
}
