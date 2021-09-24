package com.ecommerce.blockchain.service;

import com.ecommerce.blockchain.domain.user.UserDto;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    public static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    //@Value("${secret.key}")
    private String signature = "BlockChainWebProjectWithNonFungibleToken";

    private Long expireMin = 120L; // 토큰 유효기간 120분으로 설정


    //	로그인 성공시 사용자 정보를 기반으로 JWTToken을 생성하여 반환.
    public String create(UserDto user){
        JwtBuilder jwtBuilder = Jwts.builder();

        // Header 설정
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        // Payload 설정
        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * expireMin);

        // Signature 설정
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] signingKey = signature.getBytes();

        jwtBuilder
                .setHeader(headerMap)
                .setSubject("JWToken") // 토큰의 제목 설정
                .setExpiration(expireTime) // 유효기간 설정
                .claim("email", user.getEmail()) // 회원 정보 저장
                .signWith(signatureAlgorithm, signingKey);//암호화
        String jwt = jwtBuilder.compact(); // 직렬화

        logger.info("jwt : {}", jwt);
        return jwt;
    }

    // 토큰 유효성 검증
    public boolean checkValid(String jwt) {
        try {
            Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //	JWT Token을 분석해서 발급한 회원 아이디 반환
    public int getUserId(String jwt) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("claims : {}", claims);
        return (int) claims.getBody().get("user_id");
    }
}