package com.ecommerce.blockchain.domain.global.service;

import com.ecommerce.blockchain.domain.global.SuccessResponseDto;
import com.ecommerce.blockchain.domain.global.exception.ErrorDto;
import com.ecommerce.blockchain.domain.global.exception.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseGenerateService {

    public ExceptionResponseDto generateExceptionResponse(HttpStatus httpStatus, String message) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();

        ErrorDto errorDto = ErrorDto.builder().code(httpStatus).message(message).build();
        exceptionResponseDto.setError(errorDto);

        return exceptionResponseDto;
    }

    public SuccessResponseDto generateSuccessResponse(Object data) {
        SuccessResponseDto successResponseDto = SuccessResponseDto.builder().success(data).build();
        return successResponseDto;
    }

}
