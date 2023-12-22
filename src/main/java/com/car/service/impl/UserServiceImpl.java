package com.car.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.entity.User;
import com.car.mapper.UserMapper;
import com.car.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author djh
 * @since 2023-12-14
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper; // 注入UserMapper

    @Override
    public Page<User> getUsersWithPagination(int pageNum, int pageSize){
        Page<User> page = new Page<>(pageNum, pageSize);
        userMapper.selectPage(page, null);
        return page;
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        int updatedRows = userMapper.updateUserById(id, userDetails.getName(), userDetails.getPassword(), userDetails.getEmail());

        if (updatedRows == 1) {
            return userMapper.selectById(id);
        } else {
            // Handle the case where the update failed
            return null;
        }
    }
//    @Override
//    public User updateUser(Long id, User userDetails) {
//        // Check if the user with the given ID exists
//        User existingUser = getById(id);
//        if (existingUser == null) {
//            // Handle the case where the user is not found
//            return null;
//        }
//
//        // Update the fields you want to change
//        existingUser.setName(userDetails.getName());
//        existingUser.setPassword(userDetails.getPassword());
//        existingUser.setEmail(userDetails.getEmail());
//
//        // Call the MyBatis-Plus updateById method
//        boolean updated = updateById(existingUser);
//        if (updated) {
//            // Return the updated user
//            return existingUser;
//        } else {
//            // Handle the case where the update failed
//            return null;
//        }
//    }
    public User authenticate(String username, String password) {
        User user = userMapper.findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public void deleteById(long id) {
        userMapper.deleteById(id);
    }
}

