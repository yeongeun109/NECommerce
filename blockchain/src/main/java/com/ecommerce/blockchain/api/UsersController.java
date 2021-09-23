package com.ecommerce.blockchain.api;

import com.ecommerce.blockchain.domain.users.UserLoginPostReq;
import com.ecommerce.blockchain.domain.users.UserLoginPostRes;
import com.ecommerce.blockchain.domain.users.Users;
import com.ecommerce.blockchain.domain.users.UsersRequestDto;
import com.ecommerce.blockchain.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @Autowired
    UserService userService;

    // 회원가입
    @ApiOperation(value = "register user")
    @PostMapping("/register")
    public Object registerUser(@RequestBody UsersRequestDto usersRequestDto) {
        Users user = userService.registerUser(usersRequestDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // 로그인
    // jwt token 사용시 관련 코드 추가 필요
    @ApiOperation(value = "login")
    @PostMapping("/login")
    public ResponseEntity<UserLoginPostRes> login(@RequestBody UserLoginPostReq loginInfo) {
        String email = loginInfo.getEmail();
        String password = loginInfo.getPassword();

        try {
            Users user = userService.getUserByEmail(email);
            if (user.getPassword().equals(password)) {
                return ResponseEntity.status(200).body(new UserLoginPostRes(200, "로그인 성공!"));
            } else {
                return ResponseEntity.status(401).body(new UserLoginPostRes(401, "잘못된 비밀번호입니다."));
            }
        } catch (NullPointerException e) {
            return ResponseEntity.status(404).body(new UserLoginPostRes(404, "존재하지 않는 계정입니다."));
        }
    }
}
