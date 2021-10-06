package com.ecommerce.blockchain.domain.global.service;

import com.ecommerce.blockchain.domain.global.SuccessResponseDto;
import org.springframework.stereotype.Service;

@Service
public class ResponseGenerateService {

    public SuccessResponseDto generateSuccessResponse(Object data) {
        SuccessResponseDto successResponseDto = SuccessResponseDto.builder().success(data).build();
        return successResponseDto;
    }

}
