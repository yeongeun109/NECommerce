package com.ecommerce.blockchain.api;

import com.ecommerce.blockchain.common.response.BaseResponseBody;
import com.ecommerce.blockchain.domain.user.*;
import com.ecommerce.blockchain.service.JwtService;
import com.ecommerce.blockchain.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    // 회원가입
    @ApiOperation(value = "register user")
    @PostMapping("/register")
    public Object registerUser(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.registerUser(userRequestDto);
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
            User user = userService.getUserByEmail(email);
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            if (user.getPassword().equals(password)) {
                String jwtToken = jwtService.create(userDto);
                return ResponseEntity.status(200).body(new UserLoginPostRes(200, "로그인 성공!", jwtToken));
            } else {
                return ResponseEntity.status(401).body(new UserLoginPostRes(401, "잘못된 비밀번호입니다.", null));
            }
        } catch (NullPointerException e) {
            return ResponseEntity.status(404).body(new UserLoginPostRes(404, "존재하지 않는 계정입니다.", null));
        }
    }

    // 회원탈퇴
    @ApiOperation(value = "delete user")
    @DeleteMapping()
    public ResponseEntity<? extends BaseResponseBody> deleteUser(String email) {
        if(userService.deleteUser(email) == 1) {
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "회원탈퇴에 성공하였습니다."));
        }
        else {
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "회원탈퇴중에 문제가 발생하였습니다."));
        }
    }
}
