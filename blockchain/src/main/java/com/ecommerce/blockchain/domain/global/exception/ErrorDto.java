package com.ecommerce.blockchain.domain.global.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorDto {
    private HttpStatus code;
    private String message;

    @Builder
    public ErrorDto(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }
}
