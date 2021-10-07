package com.ecommerce.blockchain.api;

import com.ecommerce.blockchain.domain.global.exception.ExceptionResponseDto;
import com.ecommerce.blockchain.domain.global.exception.NoUserException;
import com.ecommerce.blockchain.domain.global.service.ResponseGenerateService;
import com.ecommerce.blockchain.domain.nft.exception.NoNFTException;
import com.ecommerce.blockchain.domain.product.exception.NoProductException;
import com.ecommerce.blockchain.domain.product.exception.preProductException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RequiredArgsConstructor
@ControllerAdvice
public class ExceptionController {

    private final ResponseGenerateService responseGenerateService;
    private final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ResponseEntity<ExceptionResponseDto> handleEmptyResultDataAccess(EmptyResultDataAccessException e) {
        logger.error("[EmptyResultDataAccessException] ", e);

        String message = "잘못된 데이터 접근입니다.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponseDto exceptionResponseDto = responseGenerateService.generateExceptionResponse(status, message);

        return new ResponseEntity<ExceptionResponseDto>(exceptionResponseDto, status);
    }

    @ExceptionHandler(NoUserException.class)
    protected ResponseEntity<ExceptionResponseDto> handleNoUser(NoUserException e) {
        logger.error("[No User Exception] ", e);

        String message = "없는 사용자입니다.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponseDto exceptionResponseDto = responseGenerateService.generateExceptionResponse(status, message);

        return new ResponseEntity<ExceptionResponseDto>(exceptionResponseDto, status);
    }

    @ExceptionHandler(NoNFTException.class)
    public ResponseEntity<ExceptionResponseDto> noNFTHandler(NoNFTException e) {
        logger.error("[No Route Exception] ", e);
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String message = "없는 NFT입니다.";

        ExceptionResponseDto exceptionResponseDto = responseGenerateService.generateExceptionResponse(httpStatus, message);
        return new ResponseEntity<ExceptionResponseDto>(exceptionResponseDto, httpStatus);
    }

    @ExceptionHandler(NoProductException.class)
    public ResponseEntity<ExceptionResponseDto> noNFTHandler(NoProductException e) {
        logger.error("[No Route Exception] ", e);
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String message = "판매 상품이 없습니다.";

        ExceptionResponseDto exceptionResponseDto = responseGenerateService.generateExceptionResponse(httpStatus, message);
        return new ResponseEntity<ExceptionResponseDto>(exceptionResponseDto, httpStatus);
    }

    @ExceptionHandler(preProductException.class)
    public ResponseEntity<ExceptionResponseDto> noNFTHandler(preProductException e) {
        logger.error("[No Route Exception] ", e);
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String message = "판매 상품이 이미 등록되어 있습니다.";

        ExceptionResponseDto exceptionResponseDto = responseGenerateService.generateExceptionResponse(httpStatus, message);
        return new ResponseEntity<ExceptionResponseDto>(exceptionResponseDto, httpStatus);
    }
}
