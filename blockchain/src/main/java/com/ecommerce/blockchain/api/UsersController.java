package com.ecommerce.blockchain.api;

import com.ecommerce.blockchain.domain.users.Users;
import com.ecommerce.blockchain.domain.users.UsersRequestDto;
import com.ecommerce.blockchain.service.UsersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @ApiOperation(value = "register user")
    @PostMapping()
    public Object registerUser(@RequestBody UsersRequestDto usersRequestDto){
        Users user = usersService.registerUser(usersRequestDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
