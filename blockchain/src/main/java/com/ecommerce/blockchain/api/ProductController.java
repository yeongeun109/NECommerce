package com.ecommerce.blockchain.api;

import com.ecommerce.blockchain.domain.global.SuccessResponseDto;
import com.ecommerce.blockchain.domain.global.service.ResponseGenerateService;
import com.ecommerce.blockchain.domain.product.ProductPurchaseRequestDto;
import com.ecommerce.blockchain.domain.product.ProductRequestDto;
import com.ecommerce.blockchain.service.Impl.ProductServiceImpl;
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
public class ProductController {

    private final ProductServiceImpl productService;
    private final ResponseGenerateService responseGenerateService;

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @ApiOperation(value = "Product 판매 등록")
    @PostMapping("/product/register")
    public ResponseEntity<SuccessResponseDto> registerProduct(@RequestBody ProductRequestDto productDto) throws Exception {
        logger.debug("기존의 nft {}를 판매 등록", productDto.getNftId());
        productService.register(productDto);
        HttpStatus status = HttpStatus.OK;
        SuccessResponseDto successResponseDto = responseGenerateService.generateSuccessResponse("Success");
        return new ResponseEntity<>(successResponseDto,status);
    }

    @GetMapping("/product/list/all")
    public ResponseEntity<SuccessResponseDto> loadAllProduct() throws Exception {
        SuccessResponseDto successResponseDto = responseGenerateService.generateSuccessResponse(productService.getAllList());
        return new ResponseEntity<>(successResponseDto, HttpStatus.OK);
    }

    @GetMapping("/product/list/true")
    public ResponseEntity<SuccessResponseDto> loadTrueProduct() throws Exception {
        SuccessResponseDto successResponseDto = responseGenerateService.generateSuccessResponse(productService.getTrueSaleList());
        return new ResponseEntity<>(successResponseDto, HttpStatus.OK);
    }

    @GetMapping("/product/list/false")
    public ResponseEntity<SuccessResponseDto> loadFalseProduct() throws Exception {
        SuccessResponseDto successResponseDto = responseGenerateService.generateSuccessResponse(productService.getFalseSaleList());
        return new ResponseEntity<>(successResponseDto, HttpStatus.OK);
    }

    @GetMapping("/product/list/{userId}")
    public ResponseEntity<SuccessResponseDto> loadUserProduct(@PathVariable Long userId) throws Exception {
        logger.debug("유저 pk {} 의 판매 등록 목록",userId);
        SuccessResponseDto successResponseDto = responseGenerateService.generateSuccessResponse(productService.getList(userId));
        return new ResponseEntity<>(successResponseDto, HttpStatus.OK);
    }

//    @GetMapping("/product/detail/{productId}")
//    public ResponseEntity<SuccessResponseDto> loadProduct(@PathVariable Long productId) throws Exception {
//        logger.debug("product 상세 정보 요청 : product PK {}", productId);
//        ProductResponseDto product = productService.getDetail(productId);
//        SuccessResponseDto successResponseDto = responseGenerateService.generateSuccessResponse(product);
//        return new ResponseEntity<>(successResponseDto,HttpStatus.OK);
//    }

    @PutMapping("/product/purchase/{productId}")
    public ResponseEntity<SuccessResponseDto> purchaseProduct(@RequestBody ProductPurchaseRequestDto PPRDto) throws Exception {
        productService.isPurchased(PPRDto);
        SuccessResponseDto successResponseDto = responseGenerateService.generateSuccessResponse("Success");
        return new ResponseEntity<>(successResponseDto,HttpStatus.OK);
    }

}
