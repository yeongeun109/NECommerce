package com.ecommerce.blockchain.api;

import com.ecommerce.blockchain.application.IUserService;
import com.ecommerce.blockchain.domain.User;
import com.ecommerce.blockchain.domain.exception.DomainException;
import com.ecommerce.blockchain.domain.exception.EmptyListException;
import com.ecommerce.blockchain.domain.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        Assert.notNull(userService, "userService 개체가 반드시 필요!");
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> list() {
        List<User> userList = userService.list();

        if (userList == null || userList.isEmpty() )
            throw new EmptyListException("NO DATA");

        return userList;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User get(@PathVariable int id) {

        User user = userService.get(id);
        if (user == null) {
            logger.error("NOT FOUND ID: ", id);
            throw new NotFoundException(id + " 회원 정보를 찾을 수 없습니다.");
        }

        return user;
    }

    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public User login(@RequestBody User user) {
        User userFetched = userService.get(user.getEmail());
        if (!userFetched.getPassword().equals(user.getPassword()))
            throw new DomainException("비밀번호가 일치하지 않습니다.");
        userFetched.setPassword("");
        return userFetched;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User create(@RequestBody User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }

}
