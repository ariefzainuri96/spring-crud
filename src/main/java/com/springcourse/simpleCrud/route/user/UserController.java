package com.springcourse.simpleCrud.route.user;

import com.springcourse.simpleCrud.model.BaseResponse;
import com.springcourse.simpleCrud.model.schema.UserProfile;
import com.springcourse.simpleCrud.route.user.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public BaseResponse<UserProfile> addUser(@RequestBody UserProfile user) {
        return userService.addUser(user);
    }

    @GetMapping("/user")
    public BaseResponse<List<UserProfile>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public BaseResponse<UserProfile> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PatchMapping("/user/{id}")
    public BaseResponse<UserProfile> updateUser(@PathVariable int id, @RequestBody Map<String, Object> user) {
        try {
            return userService.updateUser(id, user);
        } catch (Exception e) {
            return new BaseResponse<>(404, e.getLocalizedMessage(), null);
        }
    }

    @PostMapping("/auth/login")
    public BaseResponse<String> login(@RequestBody LoginRequest request) {
        try {
            return userService.login(request);
        } catch (Exception e) {
            return new BaseResponse<>(401, e.getMessage(), null);
        }
    }
}
