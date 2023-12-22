package com.car.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.common.JwtUtil;
import com.car.entity.LoginRequest;
import com.car.entity.TokenObject;
import com.car.entity.User;
import com.car.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author djh
 * @since 2023-12-14
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    //分页查询
    @GetMapping("/page")
    public ResponseEntity<Page<User>> getUsersWithPagination(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize)
    {
        Page<User> page= userService.getUsersWithPagination(pageNum, pageSize);
        return ResponseEntity.ok(page);
    }

    // 创建用户
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    // 更新用户
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.removeById(id);
        return ResponseEntity.ok().build();
    }

    // 获取所有用户
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.list();
        return ResponseEntity.ok(users);
    }

    // 登录验证等
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getUsername()+' '+loginRequest.getPassword());
        User user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            String token = JwtUtil.generateToken(user.getName());
            return ResponseEntity.ok(new TokenObject(token));
        }
        return ResponseEntity.badRequest().body("Invalid username or password");
    }
}

